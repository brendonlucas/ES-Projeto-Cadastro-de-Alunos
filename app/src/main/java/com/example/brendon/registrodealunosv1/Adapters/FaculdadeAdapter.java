package com.example.brendon.registrodealunosv1.Adapters;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Toast;

import com.example.brendon.registrodealunosv1.Formularios.FormularioAddAluno;
import com.example.brendon.registrodealunosv1.Formularios.FormularioAddFaculdade;
import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.R;
import io.objectbox.Box;


public class FaculdadeAdapter extends RecyclerView.Adapter<FaculdadeAdapter.FaculdadeViewHolder> {

    public static String ID = "idFaculdade";

    private Context context;
    private Box<Faculdade> listFaculdades;

    public FaculdadeAdapter(Context context, Box<Faculdade> listFaculdades) {
        this.listFaculdades = listFaculdades;
        this.context = context;
    }

    @NonNull
    @Override
    public FaculdadeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_faculdade, viewGroup, false);
        return new FaculdadeViewHolder(view);
    }

    @SuppressLint({"SetTextI18n","RecyclerView"})
    @Override
    public void onBindViewHolder(@NonNull FaculdadeViewHolder holder, final int position) {
        final Faculdade faculdadeAtual = this.listFaculdades.getAll().get(position);

        holder.txtNome.setText("Nome: " + faculdadeAtual.getNome());
        holder.txtEmail.setText("Email: " + faculdadeAtual.getEmail());
        holder.txtContato.setText("Contato: " + faculdadeAtual.getContatoPrincipal());

        imageMenus(holder.itemView, faculdadeAtual, position);
        popMenu(holder.itemView, faculdadeAtual, position);
    }

    private void popMenu(View itemView, Faculdade faculdade, int position){
        itemView.setOnLongClickListener((view ) -> {
            PopupMenu popup = new PopupMenu(context, view);
            popup.getMenuInflater().inflate(R.menu.pop_menu_faculdade, popup.getMenu());

            popup.setOnMenuItemClickListener((item) -> {

                switch (item.getItemId()){
                    case R.id.op_editar_faculdade:

                        Intent intent = new Intent(context, FormularioAddFaculdade.class);
                        intent.putExtra(ID, faculdade.id);
                        context.startActivity(intent);
                        notifyItemChanged(position);
                        break;

                    case R.id.op_remover_faculdade:

                        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                        alerta.setTitle("Remover")
                                .setMessage("Deseja remover: " + faculdade.getNome()+" ?")
                                .setPositiveButton("Sim", (arg0, arg1) -> {
                                    listFaculdades.remove(faculdade);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, getItemCount());
                                    Snackbar.make(view,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
                                })
                                .setNegativeButton("Nao", (arg0, arg1) -> {
                                })
                                .create()
                                .show();
                        break;

                }

                return false;
            });

            popup.show();

            return true;
        });
    }

    private void imageMenus(View itemView, Faculdade faculdade, int position){
        ImageButton excluir = itemView.findViewById(R.id.excluir_faculdade);
        ImageButton editar = itemView.findViewById(R.id.editar_faculdade);

        editar.setOnClickListener(l ->{
            Intent intent = new Intent(context, FormularioAddFaculdade.class);
            intent.putExtra(ID, faculdade.id);
            context.startActivity(intent);
            notifyItemChanged(position);
        });

        excluir.setOnClickListener(l ->{
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Remover");
            builder.setMessage("Deseja remover : " + faculdade.getNome()+" ?");
            builder.setPositiveButton("Sim", (arg0, arg1) -> {
                listFaculdades.remove(faculdade);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                Snackbar.make(l,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
            });
            builder.setNegativeButton("Não", (arg0, arg1) -> {
            });
            alerta = builder.create();
            alerta.show();
        });

        editar.setOnLongClickListener(l ->{
            Toast.makeText(context, "Editar", Toast.LENGTH_LONG).show();
            return true;
        });

        excluir.setOnLongClickListener(l ->{
            Toast.makeText(context, "Excluir", Toast.LENGTH_LONG).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return this.listFaculdades.getAll().size();
    }

    public class FaculdadeViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtEmail, txtContato;

        public FaculdadeViewHolder(View view) {
            super(view);
            txtNome = view.findViewById(R.id.view_nome_faculdade);
            txtEmail = view.findViewById(R.id.view_email);
            txtContato = view.findViewById(R.id.view_contato);

        }
    }
}

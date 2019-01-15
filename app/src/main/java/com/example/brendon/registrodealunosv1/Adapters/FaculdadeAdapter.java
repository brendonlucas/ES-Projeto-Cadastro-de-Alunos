package com.example.brendon.registrodealunosv1.Adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;

import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.R;
import io.objectbox.Box;


public class FaculdadeAdapter extends RecyclerView.Adapter<FaculdadeAdapter.FaculdadeViewHolder> {

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
        holder.txtContato.setText("Contato " + faculdadeAtual.getContatoPrincipal());

        holder.editar_faculdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // implementar edição de faculdade

            }
        });

        holder.excluir_faculdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog alerta;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remover");
                builder.setMessage("Deseja remover : " + faculdadeAtual.getNome()+" ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        listFaculdades.remove(faculdadeAtual);
                        notifyItemRemoved(position);
                        Snackbar.make(view,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alerta = builder.create();
                alerta.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listFaculdades.getAll().size();
    }

    public class FaculdadeViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtEmail, txtContato;
        ImageButton excluir_faculdade, editar_faculdade;

        public FaculdadeViewHolder(View view) {
            super(view);
            txtNome = view.findViewById(R.id.view_nome_faculdade);
            txtEmail = view.findViewById(R.id.view_email);
            txtContato = view.findViewById(R.id.view_contato);
            excluir_faculdade = view.findViewById(R.id.excluir_faculdade);
            editar_faculdade = view.findViewById(R.id.editar_faculdade);
        }
}
}

package com.example.brendon.registrodealunosv1.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.brendon.registrodealunosv1.Models.Aluno;
import com.example.brendon.registrodealunosv1.Models.Aluno_Diplomado;
import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.R;
import com.example.brendon.registrodealunosv1.dal.App;

import io.objectbox.Box;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private Context context;
    private Box<Aluno> listAlunos;
    Box<Aluno_Diplomado> BoxAlunosDiplomados;


    public AlunoAdapter(Context context, Box<Aluno> listAlunos) {
        this.listAlunos = listAlunos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_aluno, viewGroup, false);
        BoxAlunosDiplomados = ((App)view.getContext().getApplicationContext()).getBoxStore().boxFor(Aluno_Diplomado.class);
        return new AlunoViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AlunoViewHolder holder, final int position) {
        final Aluno alunoAtual = this.listAlunos.getAll().get(position);

        holder.txtNome.setText("Nome: " + alunoAtual.getNome());
        holder.txtCurso.setText("Curso: " + alunoAtual.getCurso());
        holder.txtFaculdade.setText("Faculdade " + alunoAtual.getFaculdade());

        holder.editar_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // implementar edição de aluno
            }
        });

        holder.excluir_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog alerta;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remover");
                builder.setMessage("Deseja remover aluno(a): " + alunoAtual.getNome()+" ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        listAlunos.remove(alunoAtual);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                final PopupMenu menupop = new PopupMenu(AlunoAdapter.this.context, view,position);
                menupop.getMenu().add("Diplomar Aluno").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AlertDialog alerta;
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Diplomar Aluno");
                        builder.setMessage("Deseja diplomar aluno(a): " + alunoAtual.getNome()+" ?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                                String nome = alunoAtual.getNome();
                                String curso= alunoAtual.getCurso();
                                String faculdade = alunoAtual.getFaculdade();
                                boolean diplomado = true;
                                BoxAlunosDiplomados.put(new Aluno_Diplomado(nome,curso,faculdade,diplomado));
                                Snackbar.make(view,"Aluno diplomado com sucesso!", Snackbar.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        alerta = builder.create();
                        alerta.show();
                        return false;
                    }
                });
                menupop.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listAlunos.getAll().size();
    }

    public class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCurso, txtFaculdade;
        ImageButton excluir_aluno, editar_aluno;

        public AlunoViewHolder(View view) {
            super(view);
            txtNome = view.findViewById(R.id.view_nome_aluno);
            txtCurso = view.findViewById(R.id.view_curso);
            txtFaculdade = view.findViewById(R.id.view_faculdade);
            excluir_aluno = view.findViewById(R.id.excluir_aluno);
            editar_aluno = view.findViewById(R.id.editar_aluno);
        }
    }
}





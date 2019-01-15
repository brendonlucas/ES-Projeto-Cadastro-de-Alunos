package com.example.brendon.registrodealunosv1.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.brendon.registrodealunosv1.Models.Aluno;
import com.example.brendon.registrodealunosv1.Models.Aluno_Diplomado;
import com.example.brendon.registrodealunosv1.R;
import com.example.brendon.registrodealunosv1.dal.App;

import io.objectbox.Box;

public class AdapterAluno_Diplomado extends RecyclerView.Adapter<AdapterAluno_Diplomado.Aluno_DiplomadoViewHolder>{

    private Context context;
    private Box<Aluno_Diplomado> BoxAlunosDiplomados;


    public AdapterAluno_Diplomado(Context context, Box<Aluno_Diplomado> BoxAlunosDiplomados) {
        this.BoxAlunosDiplomados = BoxAlunosDiplomados;
        this.context = context;
    }

    @NonNull
    @Override
    public Aluno_DiplomadoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_aluno, viewGroup, false);
        //BoxAlunosDiplomados = ((App)view.getContext().getApplicationContext()).getBoxStore().boxFor(Aluno_Diplomado.class);
        return new Aluno_DiplomadoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Aluno_DiplomadoViewHolder holder, final int position) {
        final Aluno_Diplomado alunoAtual = this.BoxAlunosDiplomados.getAll().get(position);

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
                        BoxAlunosDiplomados.remove(alunoAtual);
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

    }

    @Override
    public int getItemCount() {
        return this.BoxAlunosDiplomados.getAll().size();
    }

    public class Aluno_DiplomadoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCurso, txtFaculdade;
        ImageButton excluir_aluno, editar_aluno;

        public Aluno_DiplomadoViewHolder(View view) {
            super(view);
            txtNome = view.findViewById(R.id.view_nome_aluno);
            txtCurso = view.findViewById(R.id.view_curso);
            txtFaculdade = view.findViewById(R.id.view_faculdade);
            excluir_aluno = view.findViewById(R.id.excluir_aluno);
            editar_aluno = view.findViewById(R.id.editar_aluno);
        }
    }
}

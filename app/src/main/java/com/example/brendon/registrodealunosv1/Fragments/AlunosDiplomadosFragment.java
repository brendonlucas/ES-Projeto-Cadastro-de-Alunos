package com.example.brendon.registrodealunosv1.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brendon.registrodealunosv1.Adapters.AdapterAluno_Diplomado;
import com.example.brendon.registrodealunosv1.Models.Aluno_Diplomado;
import com.example.brendon.registrodealunosv1.R;
import com.example.brendon.registrodealunosv1.dal.App;

import io.objectbox.Box;

public class AlunosDiplomadosFragment extends Fragment {

    View myView;
    Box<Aluno_Diplomado> boxAlunosDiplomados;
    RecyclerView recicleAlunosDiplomados;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.aluno_diplomado_layout, container, false);
        boxAlunosDiplomados = ((App) myView.getContext().getApplicationContext()).getBoxStore().boxFor(Aluno_Diplomado.class);

        recicleAlunosDiplomados = myView.findViewById(R.id.recicler_aluno_diplomado);
        AdapterAluno_Diplomado adapter = new AdapterAluno_Diplomado(myView.getContext(), boxAlunosDiplomados);
        recicleAlunosDiplomados.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        recicleAlunosDiplomados.setAdapter(adapter);


        return myView;
    }
}

package com.example.projetopetshop.Controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projetopetshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisarProduto extends Fragment {


    public PesquisarProduto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesquisar_produto, container, false);
    }

}

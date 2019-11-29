package com.example.projetopetshop.Controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.projetopetshop.Interfaces.User;
import com.example.projetopetshop.Model.Produto;
import com.example.projetopetshop.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProdutos extends Fragment {


    private ViewGroup mensagens;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_lista_produtos, container, false);
        mensagens = view.findViewById(R.id.container);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://oficinacordova.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        User api = retrofit.create(User.class);

        Call<List<Produto>> call = api.listarProduto();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                List<Produto> produtos = response.body();

                for (Produto p : produtos) {
                    addItem(p.getNomeProduto(), String.valueOf(p.getPrecProduto()), p.getIdProduto());
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return view;
    }


    private void addItem(String textoDoTitulo, String textoDaMensagem, int id) {
        CardView cardView = (CardView) LayoutInflater.from(getActivity())
                .inflate(R.layout.cardproduto, mensagens, false);

        ImageView imagem = cardView.findViewById(R.id.imagem);
        String url = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/"+ id;
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        imageLoader.displayImage(url, imagem);


        TextView titulo = cardView.findViewById(R.id.titulo);
        TextView menssagem = cardView.findViewById(R.id.mensagem);
        titulo.setText(textoDoTitulo);
        menssagem.setText(textoDaMensagem);
        mensagens.addView(cardView);
    }

}

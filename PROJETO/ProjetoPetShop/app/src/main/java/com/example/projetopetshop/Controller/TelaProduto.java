package com.example.projetopetshop.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class TelaProduto extends AppCompatActivity {
    private ViewGroup mensagens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produto);

        mensagens = findViewById(R.id.container);

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

    }

    private void addItem(String textoDoTitulo, String textoDaMensagem, int id) {
        CardView cardView = (CardView) LayoutInflater.from(this)
                .inflate(R.layout.cardproduto, mensagens, false);

        ImageView imagem = cardView.findViewById(R.id.imagem);
        String url = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/"+ id;
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(url, imagem);


        TextView titulo = cardView.findViewById(R.id.titulo);
        TextView menssagem = cardView.findViewById(R.id.mensagem);
        titulo.setText(textoDoTitulo);
        menssagem.setText(textoDaMensagem);
        mensagens.addView(cardView);
    }
}

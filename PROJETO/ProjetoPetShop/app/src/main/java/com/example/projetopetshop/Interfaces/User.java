package com.example.projetopetshop.Interfaces;

import com.example.projetopetshop.Model.Produto;
import com.example.projetopetshop.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface User {

   @POST("/android/rest/cliente")
   Call<Usuario> login(@Body Usuario usuario);

   @POST("/android/rest/cliente/cadastro")
   Call<Usuario> signup(@Body Usuario usuario);

   @GET("/android/rest/produto")
   Call<List<Produto>> listarProduto();

}


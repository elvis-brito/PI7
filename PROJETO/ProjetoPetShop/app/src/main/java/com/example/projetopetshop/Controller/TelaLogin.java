package com.example.projetopetshop.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetopetshop.Interfaces.User;
import com.example.projetopetshop.Model.Usuario;
import com.example.projetopetshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Button buttonCadastro = (Button) findViewById(R.id.buttonCadastro);
        Button button = (Button) findViewById(R.id.button);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaTela();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = findViewById(R.id.editTextUsusario);
                EditText password = findViewById(R.id.editTextSenha);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://oficinacordova.azurewebsites.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                User user = retrofit.create(User.class);
                Usuario usuario = new Usuario(email.getText().toString(), password.getText().toString());

                final Call<Usuario> call = user.login(usuario);

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        int code = response.code();

                        if (code == 200) {
                            Usuario usuario = response.body();
                            Toast.makeText(getBaseContext(), "Nome do usuário: " +
                                    usuario.getNomeCompletoCliente(), Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),

                                    Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(),

                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    public void proximaTela(){
        Intent intent = new Intent(this, TelaCadastro.class);
        startActivity(intent);
    }
}








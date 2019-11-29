package com.example.projetopetshop.Controller;

import androidx.appcompat.app.AppCompatActivity;

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

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        final Button cadastro =findViewById(R.id.buttonCadastrar);
        final EditText nomeCompleto =findViewById(R.id.editTextNomeCompleto);
        final EditText cpf =findViewById(R.id.editTextCPF);
        final EditText celular =findViewById(R.id.editTextCelular);
        final EditText telComercial =findViewById(R.id.editTextTelComercial);
        final EditText telResidencial =findViewById(R.id.editTextTelResidencial);
        final EditText email =findViewById(R.id.editTextEmail);
        final EditText senha =findViewById(R.id.editTextSenha);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                u.setNomeCompletoCliente(nomeCompleto.getText().toString());
                u.setCPFCliente(cpf.getText().toString());
                u.setCelularCliente(celular.getText().toString());
                u.setTelComercialCliente(telComercial.getText().toString());
                u.setTelResidencialCliente(telResidencial.getText().toString());
                u.setEmailCliente(email.getText().toString());
                u.setSenhaCliente(senha.getText().toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://oficinacordova.azurewebsites.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                User user = retrofit.create(User.class);
                //Usuario usuario = new Usuario();
                Call<Usuario> usuarioCadastro = user.signup(u);

                usuarioCadastro.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        int code = response.code();

                        Usuario u = response.body();
                        if (u != null && code == 200){
                            Toast.makeText(getBaseContext(), "Cadastro realizado com sucesso" ,
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getBaseContext(), "Cadastro não realizado: " + String.valueOf(code),

                                    Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        t.printStackTrace();

                    }
                });


            }
        });


    }
}

package com.example.projetopetshop.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetopetshop.R;
import com.google.android.material.navigation.NavigationView;


public class TelaInicial extends AppCompatActivity {


        private NavigationView navigationView;
        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle actionBarDrawerToggle;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tela_inicial);
            ListaProdutos fragment = new ListaProdutos();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            drawerLayout = findViewById(R.id.drawer);
            navigationView = findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    if (menuItem.isChecked()) {
                        menuItem.setChecked(false);
                    }
                    else {
                        menuItem.setChecked(true);
                    }
                    drawerLayout.closeDrawers();
                    if (menuItem.getItemId() == R.id.cliente) {
                        PesquisarProduto fragment = new PesquisarProduto();
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.frag_container, fragment).commit();
                        return true;
                    }else  if (menuItem.getItemId() == R.id.produto) {
                        ListaProdutos fragment = new ListaProdutos();
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.frag_container, fragment).commit();
                        return true;
                    } else  if (menuItem.getItemId() == R.id.usuario) {
                        EditarCadastro fragment = new EditarCadastro();
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.frag_container, fragment).commit();
                        return true;
                    }
                    return false;
                }
            });
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();


        }
        public boolean onOptionsItemSelected(MenuItem item){
            if (actionBarDrawerToggle.onOptionsItemSelected(item)){
                return true;
            }
            else if (item.getItemId() == R.id.sobre){
                Intent i = new Intent(TelaInicial.this, Sobre.class);
                startActivity(i);
                return true;
            }
            return  super.onOptionsItemSelected(item);
        }

        public boolean onCreateOptionsMenu(Menu menu) {
// Carrega o menu
            getMenuInflater().inflate(R.menu.menu_sobre, menu);
            return true;
        }
    }


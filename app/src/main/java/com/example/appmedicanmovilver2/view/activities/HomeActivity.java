package com.example.appmedicanmovilver2.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmedicanmovilver2.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    private UsuarioViewModel usuarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        usuarioViewModel = new ViewModelProvider(this).get(
                UsuarioViewModel.class
        );

        setSupportActionBar(binding.appBarHome.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.servicioFragment, R.id.medicosFragment, R.id.perfilFragment, R.id.reservaFragment, R.id.historialFragment,R.id.mascotasFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        mostrarInformacionDelUsuario();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void mostrarInformacionDelUsuario(){
        TextView tvnomusuario = binding.navView.getHeaderView(0)
                .findViewById(R.id.tvNombMenu);
        TextView tvemailusuario = binding.navView.getHeaderView(0)
                .findViewById(R.id.tvEmailMenu);
        usuarioViewModel.obtenerUsuario().observe(this,
                new Observer<Usuario>() {
                    @Override
                    public void onChanged(Usuario usuario) {
                        if (usuario != null) {
                            tvnomusuario.setText(usuario.getNombre()+
                                    " "+usuario.getApellido());
                            tvemailusuario.setText(usuario.getEmail());
                            // Resto del código...
                        } else {
                            // Manejar el caso en el que usuario es null
                        }
                    }
                }
        );
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            usuarioViewModel.eliminarUsuario();
            startActivity(new Intent(
                    getApplicationContext(),
                    MainActivity.class
            ));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
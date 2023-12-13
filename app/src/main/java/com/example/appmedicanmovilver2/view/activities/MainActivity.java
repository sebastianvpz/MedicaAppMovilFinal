package com.example.appmedicanmovilver2.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.databinding.ActivityMainBinding;
import com.example.appmedicanmovilver2.retrofit.request.LoginRequest;
import com.example.appmedicanmovilver2.retrofit.response.LoginResponse;
import com.example.appmedicanmovilver2.viewmodel.AuthViewModel;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {


    private ActivityMainBinding binding;
    private AuthViewModel authViewModel;

    private UsuarioViewModel usuarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        usuarioViewModel = new ViewModelProvider(this)
                .get(UsuarioViewModel.class);
        binding.btnIngresar.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);
        authViewModel.loginResponseMutableLiveData
                .observe(this, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(LoginResponse loginResponse) {
                        validarLogin(loginResponse);
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnIngresar){
            invocarLogin();
        }else {
            startActivity(
                    new Intent(MainActivity.this,
                            RegistroActivity.class)
            );
        }

    }


    public void invocarLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(binding.txtEmail.getText().toString());
        loginRequest.setContrasena(binding.txtPassword.getText().toString());
        authViewModel.autenticarUsuario(loginRequest);
    }

    public void validarLogin(LoginResponse loginResponse){
        if(loginResponse != null){
            startActivity(new Intent(MainActivity.this,
                    HomeActivity.class));
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setIdUsuario(loginResponse.getIdUsuario());
            nuevoUsuario.setNombre(loginResponse.getNombre());
            nuevoUsuario.setApellido(loginResponse.getApellido());
            nuevoUsuario.setCelular(loginResponse.getCelular());
            nuevoUsuario.setDni(loginResponse.getDni());
            nuevoUsuario.setDireccion(loginResponse.getDireccion());
            nuevoUsuario.setEmail(loginResponse.getEmail());
            nuevoUsuario.setContrasena(loginResponse.getContrasena());
            usuarioViewModel.insertarUsuario(nuevoUsuario);

        }else{
            Snackbar.make(binding.getRoot(), "Acceso Denegado",
                    Snackbar.LENGTH_LONG).show();
        }
    }


}
package com.example.appmedicanmovilver2.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.databinding.ActivityRegistroBinding;
import com.example.appmedicanmovilver2.retrofit.request.RegistroRequest;
import com.example.appmedicanmovilver2.retrofit.response.RegistroResponse;
import com.example.appmedicanmovilver2.viewmodel.AuthViewModel;
import com.example.appmedicanmovilver2.viewmodel.RegistroViewModel;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;
import com.google.android.material.snackbar.Snackbar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegistroBinding binding;

    private RegistroViewModel registroViewModel;
    private UsuarioViewModel usuarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registroViewModel = new ViewModelProvider(this)
                .get(RegistroViewModel.class);
        usuarioViewModel = new ViewModelProvider(this)
                .get(UsuarioViewModel.class);
        setContentView(binding.getRoot());
        binding.btnRegistrar.setOnClickListener(this);
        registroViewModel.registroResponseMutableLiveData
                .observe(this, new Observer<RegistroResponse>() {
                    @Override
                    public void onChanged(RegistroResponse registroResponse) {
                        validarRegistro(registroResponse);
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnRegistrar){
            if (validarCampos()) {
                invocarRegistro();
            }
        }

    }

    public void invocarRegistro(){
        RegistroRequest registroRequest = new RegistroRequest();
        registroRequest.setNombre(binding.txtName.getText().toString());
        registroRequest.setApellido(binding.txtLastName.getText().toString());
        registroRequest.setCelular(Integer.parseInt(binding.txtNumero.getText().toString()));
        registroRequest.setDireccion(binding.txtDireccion.getText().toString());
        registroRequest.setDni(Integer.parseInt(binding.txtDNI.getText().toString()));
        registroRequest.setEmail(binding.txtCorreo.getText().toString());
        registroRequest.setContrasena(binding.txtRegPassword.getText().toString());

        registroViewModel.registroUsuario(registroRequest);
    }

    private boolean validarCampos() {
        if (binding.txtName.getText().toString().isEmpty() ||
                binding.txtLastName.getText().toString().isEmpty() ||
                binding.txtNumero.getText().toString().isEmpty() ||
                binding.txtDireccion.getText().toString().isEmpty() ||
                binding.txtDNI.getText().toString().isEmpty() ||
                binding.txtCorreo.getText().toString().isEmpty() ||
                binding.txtRegPassword.getText().toString().isEmpty()) {

            Snackbar.make(binding.getRoot(), "Por favor, complete todos los campos",
                    Snackbar.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    public void validarRegistro(RegistroResponse registroResponse){

        if(registroResponse != null){
            startActivity(new Intent(RegistroActivity.this,
                    HomeActivity.class));
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setIdUsuario(registroResponse.getIdUsuario());
            nuevoUsuario.setNombre(registroResponse.getNombre());
            nuevoUsuario.setApellido(registroResponse.getApellido());
            nuevoUsuario.setCelular(registroResponse.getCelular());
            nuevoUsuario.setDni(registroResponse.getDni());
            nuevoUsuario.setDireccion(registroResponse.getDireccion());
            nuevoUsuario.setEmail(registroResponse.getEmail());
            nuevoUsuario.setContrasena(registroResponse.getContrasena());
            usuarioViewModel.insertarUsuario(nuevoUsuario);

        }else{
            Snackbar.make(binding.getRoot(), "Acceso Denegado",
                    Snackbar.LENGTH_LONG).show();
        }

    }
}
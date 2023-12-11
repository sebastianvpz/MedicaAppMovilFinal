package com.example.appmedicanmovilver2.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.databinding.ActivityRegistroBinding;
import com.example.appmedicanmovilver2.retrofit.request.RegistroRequest;
import com.example.appmedicanmovilver2.retrofit.response.RegistroResponse;
import com.example.appmedicanmovilver2.viewmodel.AuthViewModel;
import com.example.appmedicanmovilver2.viewmodel.RegistroViewModel;
import com.google.android.material.snackbar.Snackbar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegistroBinding binding;

    private RegistroViewModel registroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registroViewModel = new ViewModelProvider(this)
                .get(RegistroViewModel.class);
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
            invocarRegistro();
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

    public void validarRegistro(RegistroResponse registroResponse){

        if(registroResponse != null){
            startActivity(new Intent(RegistroActivity.this,
                    HomeActivity.class));

        }else{
            Snackbar.make(binding.getRoot(), "Acceso Denegado",
                    Snackbar.LENGTH_LONG).show();
        }

    }
}
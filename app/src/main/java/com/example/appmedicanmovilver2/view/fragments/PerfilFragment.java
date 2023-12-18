package com.example.appmedicanmovilver2.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.databinding.FragmentPerfilBinding;
import com.example.appmedicanmovilver2.repository.UsuarioRepository;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;
import com.google.android.material.snackbar.Snackbar;


public class PerfilFragment extends Fragment implements View.OnClickListener{

    private FragmentPerfilBinding binding;

    private UsuarioViewModel usuarioViewModel;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("PerfilFragment", "onCreateView() llamado");


        binding = FragmentPerfilBinding.inflate(inflater,container,
                false);
        usuarioViewModel = new ViewModelProvider(requireActivity())
                .get(UsuarioViewModel.class);

        usuarioViewModel.obtenerUsuario().observe(getViewLifecycleOwner(),usuario -> {
            if (usuario != null) {
                // Establecer los datos del usuario en los EditText usando ViewBinding
                binding.tvNamePer.setText(String.valueOf(usuario.getNombre()));
                binding.tvApellidoPer.setText(String.valueOf(usuario.getApellido()));
                binding.tvCelularPerf.setText(String.valueOf(usuario.getCelular()));
                binding.tvDniPerf.setText(String.valueOf(usuario.getDni()));
                binding.tvDireccionPerf.setText(String.valueOf(usuario.getDireccion()));
                binding.tvEmailPerf.setText(String.valueOf(usuario.getEmail()));
                binding.tvContraPerf.setText(String.valueOf(usuario.getContrasena()));

            }
        });

        usuarioViewModel.getActualizacionRemotaLiveData().observe(getViewLifecycleOwner(), new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                // Acciones a realizar después de la actualización remota
                Log.d("PerfilFragment", "Actualización remota recibida en el Fragmento");
            }
        });

        binding.btnModificar.setOnClickListener(this);

        // Inflate the layout for this fragment
        return binding.getRoot();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnModificar){
            invocarActualizar();
        }


    }


    public void invocarActualizar() {
        Log.d("PerfilFragment", "invocarActualizar() llamado");
        Log.d("PerfilFragment", "ViewModel hash: " + System.identityHashCode(usuarioViewModel));

        String nuevoNombre = binding.tvNamePer.getText().toString().trim();
        String nuevoApellido = binding.tvApellidoPer.getText().toString().trim();
        String nuevoCelularStr = binding.tvCelularPerf.getText().toString().trim();
        String nuevoDniStr = binding.tvDniPerf.getText().toString().trim();
        String nuevaDireccion = binding.tvDireccionPerf.getText().toString().trim();
        String nuevoEmail = binding.tvEmailPerf.getText().toString().trim();
        String nuevaContrasena = binding.tvContraPerf.getText().toString().trim();

        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoCelularStr.isEmpty() ||
                nuevoDniStr.isEmpty() || nuevaDireccion.isEmpty() || nuevoEmail.isEmpty() || nuevaContrasena.isEmpty()) {
            mostrarMensaje("Todos los campos son obligatorios");
            return;
        }

        int nuevoCelular;
        int nuevoDni;
        try {
            nuevoCelular = Integer.parseInt(nuevoCelularStr);
            nuevoDni = Integer.parseInt(nuevoDniStr);
        } catch (NumberFormatException e) {
            mostrarMensaje("Formato inválido para celular o DNI");
            return;
        }

        usuarioViewModel.obtenerUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuarioActual) {
                if (usuarioActual != null) {
                    // Compara la información introducida con la información actual
                    if (esInformacionIgual(usuarioActual, nuevoNombre, nuevoApellido, nuevoCelular, nuevoDni, nuevaDireccion, nuevoEmail, nuevaContrasena)) {
                        Snackbar.make(binding.getRoot(), "La información es la misma, no se realizaron cambios", Snackbar.LENGTH_LONG).show();
                    } else {
                        // Actualiza los datos del usuario
                        usuarioActual.setNombre(nuevoNombre);
                        usuarioActual.setApellido(nuevoApellido);
                        usuarioActual.setCelular(nuevoCelular);
                        usuarioActual.setDni(nuevoDni);
                        usuarioActual.setDireccion(nuevaDireccion);
                        usuarioActual.setEmail(nuevoEmail);
                        usuarioActual.setContrasena(nuevaContrasena);

                        // Actualiza localmente
                        usuarioViewModel.actualizarUsuario(usuarioActual);
                        Log.d("PerfilFragment", "Usuario actualizado localmente");

                        // Inicia la actualización remota
                        usuarioViewModel.actualizarUsuarioRemoto(usuarioActual);
                        Log.d("PerfilFragment", "Iniciando actualización remota");

                        Snackbar.make(binding.getRoot(), "Perfil actualizado con éxito", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean esInformacionIgual(Usuario usuario, String nombre, String apellido, int celular, int dni, String direccion, String email, String contrasena) {
        return usuario.getNombre().equals(nombre)
                && usuario.getApellido().equals(apellido)
                && usuario.getCelular() == celular
                && usuario.getDni() == dni
                && usuario.getDireccion().equals(direccion)
                && usuario.getEmail().equals(email)
                && usuario.getContrasena().equals(contrasena);
    }

    private void mostrarMensaje(String mensaje) {
        Snackbar.make(binding.getRoot(), mensaje, Snackbar.LENGTH_LONG).show();
    }
}
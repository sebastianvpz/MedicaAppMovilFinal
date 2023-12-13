package com.example.appmedicanmovilver2.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.databinding.FragmentAgregarMascotaBinding;
import com.example.appmedicanmovilver2.retrofit.UsuarioClient;
import com.example.appmedicanmovilver2.retrofit.request.MascotaRequest;
import com.example.appmedicanmovilver2.retrofit.request.UsuarioRequest;
import com.example.appmedicanmovilver2.viewmodel.AgregarMascotaViewModel;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;


public class AgregarMascotaFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private FragmentAgregarMascotaBinding binding;

    private String especie ="";

    private AgregarMascotaViewModel agregarMascotaViewModel;
    private UsuarioViewModel usuarioViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentAgregarMascotaBinding.inflate(inflater,container,
                false);

        ArrayAdapter<CharSequence> adapterSpinner =
                ArrayAdapter.createFromResource(
                        requireActivity(),
                        R.array.especies,
                        android.R.layout.simple_spinner_item
                );


        agregarMascotaViewModel = new ViewModelProvider(requireActivity())
                .get(AgregarMascotaViewModel.class);
        usuarioViewModel = new ViewModelProvider(requireActivity())
                .get(UsuarioViewModel.class);
        // Inflate the layout for this fragment
        binding.spnEspecie.setAdapter(adapterSpinner);
        binding.spnEspecie.setOnItemSelectedListener(this);
        binding.btnAgregarMascota.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAgregarMascota) {
            agregarMascota();
        }


    }

    private void agregarMascota() {

        usuarioViewModel.obtenerUsuario().observe(getViewLifecycleOwner(), usuario -> {
            if (usuario != null) {
                Long idUsuario = usuario.getIdUsuario();
                Log.d("AgregarMascota", "ID del Usuario: " + idUsuario);

                String nombre = binding.txtNomMascota.getText().toString();
                int edad = Integer.parseInt(binding.txtEdadMascot.getText().toString());


                Log.d("AgregarMascota", "Nombre: " + nombre);
                Log.d("AgregarMascota", "Edad: " + edad);

                // Crea un objeto MascotaRequest con los datos
                UsuarioRequest usuarioRequest = new UsuarioRequest(idUsuario);
                MascotaRequest mascotaRequest = new MascotaRequest();
                mascotaRequest.setNombre(nombre);
                mascotaRequest.setEspecie(especie);
                mascotaRequest.setEdad(edad);
                mascotaRequest.setSexo(obtenerSexo());
                mascotaRequest.setUsuario( usuarioRequest);

                Log.d("AgregarMascota", "MascotaRequest: " + mascotaRequest.toString());

                // Llama al método del ViewModel para agregar la mascota
                agregarMascotaViewModel.agregarMascota(mascotaRequest);

                // Observa el resultado y toma las acciones necesarias
                agregarMascotaViewModel.getMascotaAgregada().observe(getViewLifecycleOwner(), mascotaAgregada -> {
                    if (mascotaAgregada) {
                        Log.d("AgregarMascotaFragment", "Mascota Agregada: ");
                    }
                });

                agregarMascotaViewModel.getMensajeError().observe(getViewLifecycleOwner(), mensajeError -> {
                    Log.d("AgregarMascotaFragment", "Mascota No Agregada " + mensajeError);
                });
            }
        });
    }


    private String obtenerSexo() {
        String sexo = "";

        if (binding.rgSexo.getCheckedRadioButtonId() == R.id.rbMacho) {
            sexo = binding.rbMacho.getText().toString();
        }else {
            sexo = binding.rbHembra.getText().toString();
        }
        return  sexo;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

        if (i == 0) {
            especie = "";
        }else {
            especie = parent.getItemAtPosition(i).toString();
        }
        Log.d("AgregarMascota", "Especie seleccionada: " + especie);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }
}
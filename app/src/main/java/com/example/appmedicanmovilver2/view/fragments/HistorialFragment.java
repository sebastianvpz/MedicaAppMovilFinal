package com.example.appmedicanmovilver2.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.databinding.FragmentHistorialBinding;
import com.example.appmedicanmovilver2.retrofit.response.ConsultaResponse;
import com.example.appmedicanmovilver2.view.adapters.ConsultasAdapter;
import com.example.appmedicanmovilver2.viewmodel.ConsultaViewModel;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;

import java.util.List;


public class HistorialFragment extends Fragment {

    private FragmentHistorialBinding binding;
    private ConsultaViewModel consultaViewModel;
    private ConsultasAdapter consultasAdapter = new ConsultasAdapter();
    private UsuarioViewModel usuarioViewModel;

    private Long idUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistorialBinding.inflate(inflater,
            container,false);
        consultaViewModel = new ViewModelProvider(requireActivity())
                 .get(ConsultaViewModel.class);
        usuarioViewModel = new ViewModelProvider(requireActivity())
               .get(UsuarioViewModel.class);
        binding.rvConsultas.setLayoutManager(
                new LinearLayoutManager(requireActivity())
        );
        binding.rvConsultas.setAdapter(consultasAdapter);

        usuarioViewModel.obtenerUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    Long idUsuario = usuario.getIdUsuario();
                    consultaViewModel.getConsultasByUsuario(idUsuario);
                    Log.d("TuFragmento", "ID del usuario: " + idUsuario);

                    // Observar las consultas asociadas al usuario
                    consultaViewModel.getConsultasByUsuario(idUsuario).observe(getViewLifecycleOwner(), new Observer<List<ConsultaResponse>>() {
                        @Override
                        public void onChanged(List<ConsultaResponse> consultas) {
                            if (consultas != null) {
                                // Datos obtenidos con éxito
                                consultasAdapter.setConsultas(consultas);
                                consultasAdapter.notifyDataSetChanged();
                                // Ocultar el indicador de carga si lo tienes
                            } else {
                                // Manejar error o datos nulos
                                // Por ejemplo, mostrar un mensaje de error en la interfaz de usuario
                                // Ocultar el indicador de carga si lo tienes
                            }
                        }
                    });

                } else {
                    Log.e("TuFragmento", "El usuario es nulo");
                }
            }
        });



        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
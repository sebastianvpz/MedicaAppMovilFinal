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
import com.example.appmedicanmovilver2.databinding.FragmentMascotasBinding;
import com.example.appmedicanmovilver2.retrofit.response.MascotaResponse;
import com.example.appmedicanmovilver2.view.adapters.MascotaAdapter;
import com.example.appmedicanmovilver2.viewmodel.MascotaViewModel;
import com.example.appmedicanmovilver2.viewmodel.UsuarioViewModel;

import java.util.List;


public class MascotasFragment extends Fragment {

    private FragmentMascotasBinding binding;
    private MascotaViewModel mascotaViewModel;
    private UsuarioViewModel usuarioViewModel;
    private MascotaAdapter mascotaAdapter = new MascotaAdapter();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMascotasBinding.inflate(inflater,
                container,false);
        mascotaViewModel = new ViewModelProvider(requireActivity())
                .get(MascotaViewModel.class);
        usuarioViewModel = new ViewModelProvider(requireActivity())
                .get(UsuarioViewModel.class);
        binding.rvMascotas.setLayoutManager(
                new LinearLayoutManager(requireActivity())
        );
        binding.rvMascotas.setAdapter(mascotaAdapter);
        usuarioViewModel.obtenerUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    Long idUsuario = usuario.getIdUsuario();
                    mascotaViewModel.obtenerMascotasPorUsuario(idUsuario);
                    Log.d("TuFragmento", "ID del usuario: " + idUsuario);

                }else {
                    Log.e("TuFragmento", "El usuario es nulo");

                }
            }
        });

        mascotaViewModel.getMascotasLiveData().observe(getViewLifecycleOwner(), new Observer<List<MascotaResponse>>() {
            @Override
            public void onChanged(List<MascotaResponse> mascotaResponses) {
                mascotaAdapter.setMascotas(mascotaResponses);
                Log.d("TuFragmento", "Número de mascotas: " + mascotaResponses.size());

            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
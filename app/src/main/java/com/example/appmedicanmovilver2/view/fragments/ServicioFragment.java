package com.example.appmedicanmovilver2.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.databinding.FragmentServicioBinding;
import com.example.appmedicanmovilver2.retrofit.response.ServiciosResponse;
import com.example.appmedicanmovilver2.view.adapters.ServiciosAdapter;
import com.example.appmedicanmovilver2.viewmodel.ServiciosViewModel;

import java.util.List;


public class ServicioFragment extends Fragment {

    private FragmentServicioBinding binding;
    private ServiciosViewModel serviciosViewModel;
    private ServiciosAdapter serviciosAdapter = new ServiciosAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentServicioBinding.inflate(inflater,
                container,false);
        serviciosViewModel = new ViewModelProvider(requireActivity())
                .get(ServiciosViewModel.class);
        binding.rvServicios.setLayoutManager(
                new LinearLayoutManager(requireActivity())
        );
        binding.rvServicios.setAdapter(serviciosAdapter);
        serviciosViewModel.listarServicios();
        serviciosViewModel.listMutableLiveData.observe(
                getViewLifecycleOwner(),
                new Observer<List<ServiciosResponse>>() {
                    @Override
                    public void onChanged(List<ServiciosResponse> serviciosResponses) {
                        serviciosAdapter.setServicios(serviciosResponses);
                    }
                }
        );
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
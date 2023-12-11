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
import com.example.appmedicanmovilver2.databinding.FragmentMedicosBinding;
import com.example.appmedicanmovilver2.retrofit.response.MedicosResponse;
import com.example.appmedicanmovilver2.view.adapters.MedicosAdapter;
import com.example.appmedicanmovilver2.viewmodel.MedicosViewModel;

import java.util.List;


public class MedicosFragment extends Fragment {

    private FragmentMedicosBinding binding;
    private MedicosViewModel medicosViewModel;
    private MedicosAdapter medicosAdapter = new MedicosAdapter();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentMedicosBinding.inflate(inflater,
                container,false);
        medicosViewModel = new ViewModelProvider(requireActivity())
                .get(MedicosViewModel.class);
        binding.rvMedicos.setLayoutManager(
                new LinearLayoutManager(requireActivity())
        );
        binding.rvMedicos.setAdapter(medicosAdapter);
        medicosViewModel.listarMedicos();
        medicosViewModel.listMutableLiveData.observe(
                getViewLifecycleOwner(),
                new Observer<List<MedicosResponse>>() {
                    @Override
                    public void onChanged(List<MedicosResponse> medicosResponses) {
                        medicosAdapter.setMedicos(medicosResponses);
                    }
                }
        );
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
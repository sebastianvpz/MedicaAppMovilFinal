package com.example.appmedicanmovilver2.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.databinding.FragmentReservaBinding;
import com.example.appmedicanmovilver2.viewmodel.CitaViewModel;


public class ReservaFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    private FragmentReservaBinding binding;

    private CitaViewModel citaViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReservaBinding.inflate(inflater,container,
                false);

        citaViewModel = new ViewModelProvider(requireActivity())
                .get(CitaViewModel.class);
        binding.btnReservar.setOnClickListener(this);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
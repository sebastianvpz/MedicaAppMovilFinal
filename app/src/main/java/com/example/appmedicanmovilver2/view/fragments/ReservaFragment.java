package com.example.appmedicanmovilver2.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.appmedicanmovilver2.R;
import com.example.appmedicanmovilver2.databinding.FragmentReservaBinding;
import com.example.appmedicanmovilver2.retrofit.request.CitaRequest;
import com.example.appmedicanmovilver2.retrofit.response.MedicosResponse;
import com.example.appmedicanmovilver2.retrofit.response.ServiciosResponse;
import com.example.appmedicanmovilver2.viewmodel.CitaViewModel;
import com.example.appmedicanmovilver2.viewmodel.MedicosViewModel;
import com.example.appmedicanmovilver2.viewmodel.ServiciosViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ReservaFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    private FragmentReservaBinding binding;
    private CitaViewModel citaViewModel;
    private ServiciosViewModel serviciosViewModel;
    private MedicosViewModel medicosViewModel;
    private String fechaSeleccionada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReservaBinding.inflate(inflater, container, false);

        citaViewModel = new ViewModelProvider(requireActivity()).get(CitaViewModel.class);
        serviciosViewModel = new ViewModelProvider(requireActivity()).get(ServiciosViewModel.class);
        medicosViewModel = new ViewModelProvider(requireActivity()).get(MedicosViewModel.class);

        // Observa la lista de servicios y actualiza el Spinner cuando cambie
        serviciosViewModel.getListaServicios().observe(getViewLifecycleOwner(), new Observer<List<ServiciosResponse>>() {
            @Override
            public void onChanged(List<ServiciosResponse> servicios) {
                // Configura el adaptador personalizado para el Spinner de Servicios
                ServicioAdapter servicioAdapter = new ServicioAdapter(requireContext(), R.layout.item_servicios, servicios);
                binding.spnServicio.setAdapter(servicioAdapter);
            }
        });

        // Observa la lista de médicos y actualiza el Spinner cuando cambie
        medicosViewModel.getListaMedicos().observe(getViewLifecycleOwner(), new Observer<List<MedicosResponse>>() {
            @Override
            public void onChanged(List<MedicosResponse> medicos) {
                // Configura el adaptador personalizado para el Spinner de Médicos
                MedicoAdapter medicoAdapter = new MedicoAdapter(requireContext(), R.layout.item_medicos, medicos);
                binding.spnMedico.setAdapter(medicoAdapter);
            }
        });

        binding.btnReservar.setOnClickListener(this);

        // Configura el listener para el CalendarView
        binding.calVFecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Formatea la fecha seleccionada y guárdala en una variable del fragmento
                fechaSeleccionada = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            }
        });



        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnReservar) {
            if (validarCampos()) {
                ServiciosResponse servicioSeleccionado = (ServiciosResponse) binding.spnServicio.getSelectedItem();
                MedicosResponse medicoSeleccionado = (MedicosResponse) binding.spnMedico.getSelectedItem();
                String fechaHoraFormateada = fechaSeleccionada + "T" + obtenerHoraActual(); // Aquí debes obtener la hora actual

                citaViewModel.createCita(fechaHoraFormateada, medicoSeleccionado.getIdMedico(), servicioSeleccionado.getIdServicio());
                Snackbar.make(binding.getRoot(), "Reserva realizada con éxito", Snackbar.LENGTH_LONG).show();

            }
        }

    }

    private String obtenerHoraActual() {
        // Obtén la hora actual
        Date fechaActual = new Date();

        // Crea un formato de fecha para obtener la hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        // Formatea la hora actual
        return formatoHora.format(fechaActual);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean validarCampos() {
        if (fechaSeleccionada == null || fechaSeleccionada.isEmpty()) {
            mostrarMensaje("Por favor, seleccione una fecha para la reserva");
            return false;
        }

        if (binding.spnServicio.getSelectedItem() == null) {
            mostrarMensaje("Por favor, seleccione un servicio");
            return false;
        }

        if (binding.spnMedico.getSelectedItem() == null) {
            mostrarMensaje("Por favor, seleccione un médico");
            return false;
        }

        return true;
    }

    private void mostrarMensaje(String mensaje) {
        Snackbar.make(binding.getRoot(), mensaje, Snackbar.LENGTH_LONG).show();
    }



    // Adaptador personalizado para mostrar solo el nombre del médico
    private class MedicoAdapter extends ArrayAdapter<MedicosResponse> {
        private List<MedicosResponse> medicosList;

        public MedicoAdapter(Context context, int resource, List<MedicosResponse> objects) {
            super(context, resource, objects);
            this.medicosList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
            }

            TextView textViewName = convertView.findViewById(android.R.id.text1);
            textViewName.setText(medicosList.get(position).getNombre());

            return convertView;
        }
    }

    // Adaptador personalizado para mostrar solo el nombre del servicio
    private class ServicioAdapter extends ArrayAdapter<ServiciosResponse> {
        private List<ServiciosResponse> serviciosList;

        public ServicioAdapter(Context context, int resource, List<ServiciosResponse> objects) {
            super(context, resource, objects);
            this.serviciosList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
            }

            TextView textViewName = convertView.findViewById(android.R.id.text1);
            textViewName.setText(serviciosList.get(position).getNombre());

            return convertView;
        }
    }

}
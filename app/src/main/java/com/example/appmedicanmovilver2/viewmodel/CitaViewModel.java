package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.CitaClient;
import com.example.appmedicanmovilver2.retrofit.request.CitaRequest;
import com.example.appmedicanmovilver2.retrofit.request.MedicoRequest;
import com.example.appmedicanmovilver2.retrofit.request.ServicioRequest;
import com.example.appmedicanmovilver2.retrofit.response.CitaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitaViewModel extends AndroidViewModel {

    private final MutableLiveData<CitaResponse> citaResponseMutableLiveData;

    public CitaViewModel(@NonNull Application application) {
        super(application);
        citaResponseMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<CitaResponse> getCitaResponseLiveData() {
        return citaResponseMutableLiveData;
    }

    public void createCita(String fechaHora, Long idMedico, Long idServicio) {
        CitaClient citaClient = new CitaClient();

        // Crear instancias de las clases de solicitud anidadas
        MedicoRequest medicoRequest = new MedicoRequest(idMedico);
        ServicioRequest servicioRequest = new ServicioRequest(idServicio);

        // Crear la instancia de la solicitud de cita
        CitaRequest citaRequest = new CitaRequest(fechaHora, medicoRequest, servicioRequest);

        // Realizar la llamada a la API
        citaClient.getInstance().createCita(citaRequest).enqueue(new Callback<CitaResponse>() {
            @Override
            public void onResponse(Call<CitaResponse> call, Response<CitaResponse> response) {
                if (response.isSuccessful()) {
                    // La solicitud fue exitosa, actualiza el LiveData con la respuesta
                    citaResponseMutableLiveData.setValue(response.body());
                } else {
                    // La solicitud no fue exitosa, puedes manejar el error aquí
                    citaResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CitaResponse> call, Throwable t) {
                // Ocurrió un error en la comunicación con el servidor
                t.printStackTrace();
                citaResponseMutableLiveData.setValue(null);
            }
        });
    }
}

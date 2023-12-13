package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.MascotaClient;
import com.example.appmedicanmovilver2.retrofit.request.MascotaRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarMascotaViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> mascotaAgregada = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    private MascotaClient mascotaClient;

    public AgregarMascotaViewModel(@NonNull Application application) {
        super(application);
        mascotaClient = new MascotaClient();
    }

    public LiveData<Boolean> getMascotaAgregada() {
        return mascotaAgregada;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void agregarMascota(MascotaRequest mascotaRequest) {
        Call<Void> call = mascotaClient.getMascotaService().agregarMascota(mascotaRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    mascotaAgregada.setValue(true);
                } else {
                    String errorMessage = "Error al agregar la mascota. Código: " + response.code();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mensajeError.setValue("Error de red: " + t.getMessage());
            }
        });
    }
}

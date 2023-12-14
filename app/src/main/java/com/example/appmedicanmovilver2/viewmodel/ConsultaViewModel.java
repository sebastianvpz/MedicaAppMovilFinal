package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.ConsultaClient;
import com.example.appmedicanmovilver2.retrofit.response.ConsultaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultaViewModel extends AndroidViewModel {


    private MutableLiveData<List<ConsultaResponse>> consultas;

    public ConsultaViewModel(@NonNull Application application) {
        super(application);
        consultas = new MutableLiveData<>();
    }

    public LiveData<List<ConsultaResponse>> getConsultasByUsuario(Long idUsuario) {
        ConsultaClient consultaClient = new ConsultaClient();

        Call<List<ConsultaResponse>> call = consultaClient.getInstace().getConsultasByUsuario(idUsuario);
        call.enqueue(new Callback<List<ConsultaResponse>>() {
            @Override
            public void onResponse(Call<List<ConsultaResponse>> call, Response<List<ConsultaResponse>> response) {
                if (response.isSuccessful()) {
                    consultas.setValue(response.body());
                } else {
                    // Manejar error en la respuesta
                    // Por ejemplo, puedes establecer un valor en consultas para indicar un error.
                    consultas.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaResponse>> call, Throwable t) {
                // Manejar error de red
                // Por ejemplo, puedes establecer un valor en consultas para indicar un error de red.
                consultas.setValue(null);
            }
        });

        return consultas;
    }
}

package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.MascotaClient;
import com.example.appmedicanmovilver2.retrofit.response.MascotaResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaViewModel extends AndroidViewModel {

    private MutableLiveData<List<MascotaResponse>> mascotasLiveData = new MutableLiveData<>();
    private MascotaClient mascotaClient;

    public MascotaViewModel(@NonNull Application application) {
        super(application);
        mascotaClient = new MascotaClient();
    }

    public LiveData<List<MascotaResponse>> getMascotasLiveData() {
        return mascotasLiveData;
    }

    public void obtenerMascotasPorUsuario(Long idUsuario) {
        mascotaClient.getMascotaService().obtenerMascotasByUsuario(idUsuario)
                .enqueue(new Callback<List<MascotaResponse>>() {
                    @Override
                    public void onResponse(Call<List<MascotaResponse>> call, Response<List<MascotaResponse>> response) {
                        if (response.isSuccessful()) {
                            mascotasLiveData.postValue(response.body());
                        } else {
                            // Manejar el caso de respuesta no exitosa
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MascotaResponse>> call, Throwable t) {
                        // Manejar el caso de error
                    }
                });
    }
}

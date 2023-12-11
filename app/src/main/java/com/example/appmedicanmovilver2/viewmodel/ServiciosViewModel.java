package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.ServiciosClient;
import com.example.appmedicanmovilver2.retrofit.response.ServiciosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiciosViewModel extends AndroidViewModel {

    public MutableLiveData<List<ServiciosResponse>> listMutableLiveData
            = new MutableLiveData<>();
    public ServiciosViewModel(@NonNull Application application) {
        super(application);
    }

    public void listarServicios(){
        new ServiciosClient().getInstace().listarServicios()
                .enqueue(new Callback<List<ServiciosResponse>>() {
                    @Override
                    public void onResponse(Call<List<ServiciosResponse>> call, Response<List<ServiciosResponse>> response) {
                        listMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<ServiciosResponse>> call, Throwable t) {

                    }
                });
    }
}

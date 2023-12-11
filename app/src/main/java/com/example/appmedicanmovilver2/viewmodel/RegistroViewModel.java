package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.RegistroClient;
import com.example.appmedicanmovilver2.retrofit.request.RegistroRequest;
import com.example.appmedicanmovilver2.retrofit.response.RegistroResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroViewModel extends AndroidViewModel {

    public MutableLiveData<RegistroResponse> registroResponseMutableLiveData
            = new MutableLiveData<>();

    public RegistroViewModel(@NonNull Application application) {
        super(application);
    }

    public void registroUsuario(RegistroRequest registroRequest){
        new RegistroClient().getInstace().registro(registroRequest)
                .enqueue(new Callback<RegistroResponse>() {
                    @Override
                    public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {
                        registroResponseMutableLiveData
                                .setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<RegistroResponse> call, Throwable t) {

                    }
                });
    }
}

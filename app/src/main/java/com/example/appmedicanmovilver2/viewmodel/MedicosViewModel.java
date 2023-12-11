package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.appmedicanmovilver2.retrofit.MedicosClient;
import com.example.appmedicanmovilver2.retrofit.response.MedicosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicosViewModel extends AndroidViewModel {


    public MutableLiveData<List<MedicosResponse>> listMutableLiveData
            = new MutableLiveData<>();
    public MedicosViewModel(@NonNull Application application) {
        super(application);
    }

    public void listarMedicos(){
        new MedicosClient().getInstace().listarMedicos()
                .enqueue(new Callback<List<MedicosResponse>>() {
                    @Override
                    public void onResponse(Call<List<MedicosResponse>> call, Response<List<MedicosResponse>> response) {
                        listMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<MedicosResponse>> call, Throwable t) {

                    }
                });
    }
}

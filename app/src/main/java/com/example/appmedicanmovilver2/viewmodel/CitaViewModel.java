package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.retrofit.CitaClient;
import com.example.appmedicanmovilver2.retrofit.request.CitaRequest;
import com.example.appmedicanmovilver2.retrofit.request.MedicoRequest;
import com.example.appmedicanmovilver2.retrofit.request.ServicioRequest;
import com.example.appmedicanmovilver2.retrofit.request.UsuarioRequest;
import com.example.appmedicanmovilver2.retrofit.response.CitaResponse;
import com.example.appmedicanmovilver2.retrofit.response.MedicosResponse;
import com.example.appmedicanmovilver2.retrofit.response.ServiciosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitaViewModel extends AndroidViewModel {

    private ServiciosViewModel serviciosViewModel;
    private MedicosViewModel medicosViewModel;
    private UsuarioViewModel usuarioViewModel;

    private final MutableLiveData<CitaResponse> citaResponseMutableLiveData;

    public CitaViewModel(@NonNull Application application) {
        super(application);
        citaResponseMutableLiveData = new MutableLiveData<>();
        serviciosViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(ServiciosViewModel.class);
        medicosViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(MedicosViewModel.class);
        usuarioViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(UsuarioViewModel.class);
    }

    public LiveData<CitaResponse> getCitaResponseLiveData() {
        return citaResponseMutableLiveData;
    }

    public void createCita(String fechaHora, Long idMedico, Long idServicio) {
        CitaClient citaClient = new CitaClient();

        usuarioViewModel.obtenerUsuario().observeForever(new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    // Obtener el idUsuario del usuario
                    Long idUsuario = usuario.getIdUsuario();

                    // Crear instancias de las clases de solicitud anidadas
                    MedicoRequest medicoRequest = new MedicoRequest(idMedico);
                    ServicioRequest servicioRequest = new ServicioRequest(idServicio);
                    UsuarioRequest usuarioRequest = new UsuarioRequest(idUsuario);

                    // Crear la instancia de la solicitud de cita
                    CitaRequest citaRequest = new CitaRequest(fechaHora, medicoRequest, servicioRequest, usuarioRequest);

                    // Realizar la llamada a la API
                    citaClient.getInstance().createCita(citaRequest).enqueue(new Callback<CitaResponse>() {
                        // Resto del código...
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
        });
    }




    public LiveData<List<ServiciosResponse>> getListaServicios() {
        return serviciosViewModel.getListaServicios();
    }

    public LiveData<List<MedicosResponse>> getListaMedicos() {
        return medicosViewModel.getListaMedicos();
    }
}

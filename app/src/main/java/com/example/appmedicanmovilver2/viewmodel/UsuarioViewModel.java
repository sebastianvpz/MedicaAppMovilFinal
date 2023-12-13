package com.example.appmedicanmovilver2.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.appmedicanmovilver2.bd.entity.Usuario;
import com.example.appmedicanmovilver2.repository.UsuarioRepository;
import com.example.appmedicanmovilver2.retrofit.UsuarioClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> usuarioLiveData = new MutableLiveData<>();
    private MutableLiveData<Void> actualizacionRemotaLiveData = new MutableLiveData<>();
    // Cambiar a LiveData
    public LiveData<Void> getActualizacionRemotaLiveData() {
        return actualizacionRemotaLiveData;
    }
    private UsuarioRepository usuarioRepository;
    private UsuarioClient usuarioClient;

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
        usuarioClient = new UsuarioClient();

    }

    public LiveData<Usuario> obtenerUsuario() {
        return usuarioRepository.obtenerUsuario();
    }

    public Long obtenerIdUsuario() {
        Usuario usuario = usuarioRepository.obtenerUsuarioSync(); // Método sincrónico para obtener el usuario
        return usuario != null ? usuario.getIdUsuario() : null;
    }

    public void insertarUsuario(Usuario usuario){
        usuarioRepository.registrarUsuario(usuario);
    }
    public void actualizarUsuario(Usuario usuario){
        // Observa el LiveData actualizacionRemotaLiveData para realizar la actualización local después de la remota
        actualizacionRemotaLiveData.observeForever(new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                // Elimina el Observer después de que la actualización remota haya tenido éxito
                actualizacionRemotaLiveData.removeObserver(this);

                Log.d("PerfilFragment", "actualizarUsuario() local exitoso");
            }
        });
    }
    public void eliminarUsuario(){
        usuarioRepository.eliminarUsuario();
    }


    public void actualizarUsuarioRemoto(Usuario usuario) {
        Log.d("PerfilFragment", "actualizarUsuarioRemoto() llamado");
        Log.d("PerfilFragment", "actualizarUsuarioRemoto() llamado, idUsuario: " + usuario.getIdUsuario());

        Long idUsuario = usuario.getIdUsuario();
        usuarioClient.getUsuarioService().actualizarUsuario(idUsuario, usuario)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            actualizacionRemotaLiveData.postValue(null);
                            Log.d("UsuarioViewModel", "Actualización remota exitosa");
                        } else {
                            Log.e("UsuarioViewModel", "Error en la actualización remota: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("UsuarioViewModel", "Error en la llamada remota", t);
                    }
                });
    }




}

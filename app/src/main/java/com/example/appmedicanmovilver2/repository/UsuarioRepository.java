package com.example.appmedicanmovilver2.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.appmedicanmovilver2.bd.VetRoomDatabase;
import com.example.appmedicanmovilver2.bd.dao.UsuarioDao;
import com.example.appmedicanmovilver2.bd.entity.Usuario;

public class UsuarioRepository {
    public UsuarioDao usuarioDao;

    public UsuarioRepository(Application application){
        VetRoomDatabase db = VetRoomDatabase.getDatabase(application);
        usuarioDao = db.usuarioDao();
    }

    public LiveData<Usuario> obtenerUsuario(){return usuarioDao.obtener();}

    public void registrarUsuario(Usuario usuario) {
        new registrarAsyncTask(usuarioDao).execute(usuario);
    }


    private static class registrarAsyncTask
            extends AsyncTask<Usuario, Void, Void> {
        private UsuarioDao usuarioDao;
        registrarAsyncTask(UsuarioDao _usuarioDao){
            usuarioDao = _usuarioDao;
        }
        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.insertar(usuarios[0]);
            return null;
        }
    }

    public void actualizarUsuario(Usuario usuario){

        new actualizarAsyncTask(usuarioDao).execute(usuario);
    }

    private static class actualizarAsyncTask
            extends AsyncTask<Usuario, Void, Void>{
        private UsuarioDao usuarioDao;
        actualizarAsyncTask(UsuarioDao _usuarioDao){
            usuarioDao = _usuarioDao;
        }
        @Override
        protected Void doInBackground(Usuario... usuarios) {
            try {
                usuarioDao.actualizar(usuarios[0]);
                Log.d("UsuarioRepository", "Usuario actualizado localmente");
            } catch (Exception e) {
                Log.e("UsuarioRepository", "Error al actualizar usuario localmente", e);
            }
            return null;
        }
    }


    public void eliminarUsuario(){
        new eliminarAsyncTask(usuarioDao).execute();
    }
    private static class eliminarAsyncTask
            extends AsyncTask<Void, Void, Void>{
        private UsuarioDao usuarioDao;
        eliminarAsyncTask(UsuarioDao _usuarioDao){
            usuarioDao = _usuarioDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            usuarioDao.eliminar();
            return null;
        }
    }

}

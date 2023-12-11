package com.example.appmedicanmovilver2.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appmedicanmovilver2.bd.entity.Usuario;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertar(Usuario usuario);
    @Update
    void actualizar(Usuario usuario);
    @Query("DELETE FROM usuario")
    void eliminar();
    @Query("SELECT * FROM usuario LIMIT 1")
    LiveData<Usuario> obtener();

    @Query("SELECT * FROM usuario WHERE idUsuario = :userId")
    LiveData<Usuario> obtenerPorId(Long userId);


}

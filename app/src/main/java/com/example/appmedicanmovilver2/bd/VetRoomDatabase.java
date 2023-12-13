package com.example.appmedicanmovilver2.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appmedicanmovilver2.bd.dao.UsuarioDao;
import com.example.appmedicanmovilver2.bd.entity.Usuario;

@Database(entities = {Usuario.class}, version =  1)
public abstract class VetRoomDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
    private static volatile VetRoomDatabase INSTANCIA;
    public static VetRoomDatabase getDatabase(final Context context) {

        if (INSTANCIA == null) {
            synchronized (VetRoomDatabase.class){
                if (INSTANCIA== null){
                    INSTANCIA= Room.databaseBuilder(
                            context.getApplicationContext(),
                            VetRoomDatabase.class,
                            "vetbd"
                    ).build();
                }
            }
        }
        return INSTANCIA;
    }
}

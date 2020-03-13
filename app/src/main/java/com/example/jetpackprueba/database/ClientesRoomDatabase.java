package com.example.jetpackprueba.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jetpackprueba.dao.ClientesDao;
import com.example.jetpackprueba.entity.Clientes;

@Database(entities = Clientes.class, version = 1)
public abstract class ClientesRoomDatabase extends RoomDatabase {
    public abstract ClientesDao clientesDao();
    private static volatile ClientesRoomDatabase clientesRoomInstance;

    public static ClientesRoomDatabase getDatabase(final Context context) {
        if (clientesRoomInstance == null) {
            synchronized (ClientesRoomDatabase.class) {
                if (clientesRoomInstance == null) {
                    clientesRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ClientesRoomDatabase.class, "clientes_database")
                            .build();
                }
            }
        }
        return clientesRoomInstance;
    }
}

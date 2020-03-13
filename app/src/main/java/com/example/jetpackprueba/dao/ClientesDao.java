package com.example.jetpackprueba.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jetpackprueba.entity.Clientes;

import java.util.List;

@Dao
public interface ClientesDao {
    @Insert
    void insert(Clientes clientes);

    @Query("SELECT * FROM clientes")
    LiveData<List<Clientes>> getAllClientes();

    @Query("SELECT * FROM clientes WHERE id=:clientesId")
    LiveData<Clientes> getClientes(String clientesId);

    @Update
    void update(Clientes clientes);

    @Delete
    int delete(Clientes clientes);


}

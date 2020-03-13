package com.example.jetpackprueba.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jetpackprueba.dao.ClientesDao;
import com.example.jetpackprueba.database.ClientesRoomDatabase;
import com.example.jetpackprueba.entity.Clientes;

public class EditarClienteViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private ClientesDao clientesDao;
    private ClientesRoomDatabase clientesDB;
    public EditarClienteViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "Edit ViewModel");
        clientesDB = ClientesRoomDatabase.getDatabase(application);
        clientesDao = clientesDB.clientesDao();
    }
    public LiveData<Clientes> getClientes(String idCliente) {
        return clientesDao.getClientes(idCliente);
    }
}

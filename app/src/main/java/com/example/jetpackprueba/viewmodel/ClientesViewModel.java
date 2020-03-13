package com.example.jetpackprueba.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jetpackprueba.dao.ClientesDao;
import com.example.jetpackprueba.database.ClientesRoomDatabase;
import com.example.jetpackprueba.entity.Clientes;

import java.util.List;

public class ClientesViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private ClientesDao clientesDao;
    private ClientesRoomDatabase clientesDB;
    private LiveData<List<Clientes>> mAllClientes;
    public ClientesViewModel(@NonNull Application application) {
        super(application);
        clientesDB = ClientesRoomDatabase.getDatabase(application);
        clientesDao = clientesDB.clientesDao();
        mAllClientes = clientesDao.getAllClientes();
    }
    public void insert(Clientes clientes) {
        new InsertAsyncTask(clientesDao).execute(clientes);
    }

    public LiveData<List<Clientes>> getAllClientes() {
        return mAllClientes;
    }

    public void update(Clientes clientes) {
        new UpdateAsyncTask(clientesDao).execute(clientes);
    }

    public void delete(Clientes clientes) {
        new DeleteAsyncTask(clientesDao).execute(clientes);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class OperationsAsyncTask extends AsyncTask<Clientes , Void, Void> {

        ClientesDao mAsyncTaskDao;

        OperationsAsyncTask(ClientesDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Clientes... notes) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(ClientesDao mClientesDao) {
            super(mClientesDao);
        }

        @Override
        protected Void doInBackground(Clientes... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(ClientesDao clientesDao) {
            super(clientesDao);
        }

        @Override
        protected Void doInBackground(Clientes... notes) {
            mAsyncTaskDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(ClientesDao clientesDao) {
            super(clientesDao);
        }

        @Override
        protected Void doInBackground(Clientes... notes) {
            mAsyncTaskDao.delete(notes[0]);


            return null;
        }
    }
}

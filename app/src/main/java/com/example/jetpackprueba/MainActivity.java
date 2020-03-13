package com.example.jetpackprueba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jetpackprueba.adapters.ClientesListAdapter;
import com.example.jetpackprueba.entity.Clientes;
import com.example.jetpackprueba.viewmodel.ClientesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ClientesListAdapter.OnDeleteClickListener {
    private ClientesViewModel clientesViewModel;
    private ClientesListAdapter clientesListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnAgregarCliente = (ImageButton) findViewById(R.id.btnAgregarCliente);
        btnAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarClienteFragment insertarClienteFragment=new InsertarClienteFragment();
                insertarClienteFragment.show(getSupportFragmentManager(),"Insertar Clientes");
                insertarClienteFragment.setProceInsertar(new InsertarClienteFragment.procedimientosInserttar() {
                    @Override
                    public void onInsertar(Clientes clientes) {
                        clientesViewModel.insert(clientes);
                    }
                });

            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        clientesListAdapter = new ClientesListAdapter(getBaseContext(), this);

        clientesViewModel = ViewModelProviders.of(this).get(ClientesViewModel.class);




        clientesListAdapter.setEliminarPro(new ClientesListAdapter.procediimientoEliminar() {
            @Override
            public void onEliminar(Clientes clientes) {
                clientesViewModel.delete(clientes);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Dato Eliminado", Toast.LENGTH_SHORT);

                toast1.show();
            }
        });
        clientesListAdapter.setEditarPro(new ClientesListAdapter.procediimientoEditar() {
            @Override
            public void onEdit(String idCliente) {
                EditarClienteFragment  editarClienteFragment= EditarClienteFragment.newInstance(idCliente);
                editarClienteFragment.show(getSupportFragmentManager(),"Editar");
                editarClienteFragment.setEditarPro(new EditarClienteFragment.procedimientosEditar() {
                    @Override
                    public void onEditar(Clientes cliente) {
                        clientesViewModel.update(cliente);
                    }
                });
            }
        });
        clientesViewModel.getAllClientes().observe(this, new Observer<List<Clientes>>() {
            @Override
            public void onChanged(@Nullable List<Clientes> notes) {
                // noteListAdapter.setNotes(notes);
                clientesListAdapter.setNotes(notes);
            }
        });
        recyclerView.setAdapter(clientesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    }

    @Override
    public void OnDeleteClickListener(Clientes myNote) {

    }
}

package com.example.jetpackprueba;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.jetpackprueba.entity.Clientes;
import com.example.jetpackprueba.viewmodel.EditarClienteViewModel;

import java.util.UUID;

public class EditarClienteFragment  extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idCliente";


    // TODO: Rename and change types of parameters
    private String idCliente;
    private EditarClienteViewModel editarClienteViewModel;
    private LiveData<Clientes> clienteLive;
    private procedimientosEditar editarPro;
    public EditarClienteFragment() {
        // Required empty public constructor
    }


    public static EditarClienteFragment newInstance(String idCliente) {
        EditarClienteFragment fragment = new EditarClienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, idCliente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idCliente = getArguments().getString(ARG_PARAM1);
        }
        // setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_editar_cliente, container, false);
        final EditText editTextNombres = (EditText) view.findViewById(R.id.editTextNombres);
        final EditText editTextApellidos = (EditText) view.findViewById(R.id.editTextApellidos);
        final EditText editTextCedula = (EditText) view.findViewById(R.id.editTextCedula);
        final EditText editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        final EditText editTextDireccion = (EditText) view.findViewById(R.id.editTextDireccion);
        final EditText editTextTelefono = (EditText) view.findViewById(R.id.editTextTelefono);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getDialog().getWindow().setBackgroundDrawable(getActivity().getDrawable(R.color.opcity_50_porciento));
        }
        Button btnAceptar = (Button) view.findViewById(R.id.Aceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clientes cliente = new Clientes(idCliente,editTextNombres.getText().toString(),editTextApellidos.getText().toString(),editTextCedula.getText().toString(),editTextEmail.getText().toString(),editTextDireccion.getText().toString(),editTextTelefono.getText().toString());
                getEditarPro().onEditar(cliente);
                dismiss();


            }
        });
        Button btnCancelar =(Button) view.findViewById(R.id.Cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        editarClienteViewModel = ViewModelProviders.of(this).get(EditarClienteViewModel.class);
        clienteLive = editarClienteViewModel.getClientes(idCliente);
        clienteLive.observe(this, new Observer<Clientes>() {
            @Override
            public void onChanged(@Nullable Clientes cliente) {
                editTextNombres.setText(cliente.getNombres());
                editTextApellidos.setText(cliente.getApellidos());
                editTextCedula.setText(cliente.getCedula());
                editTextEmail.setText(cliente.getCorreo());
                editTextDireccion.setText(cliente.getDireccion());
                editTextTelefono.setText(cliente.getTelefono());

            }
        });
        return view;
    }

    public interface procedimientosEditar{
        void onEditar(Clientes cliente);

    }

    public procedimientosEditar getEditarPro() {
        return editarPro;
    }

    public void setEditarPro(procedimientosEditar editarPro) {
        this.editarPro = editarPro;
    }
}

package com.example.jetpackprueba;


import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jetpackprueba.entity.Clientes;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertarClienteFragment  extends DialogFragment {
    private procedimientosInserttar proceInsertar;

    public InsertarClienteFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        // setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_insertar_cliente, container, false);
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
                final String clientes_id = UUID.randomUUID().toString();
                Clientes clientes = new Clientes(clientes_id,editTextNombres.getText().toString(),editTextApellidos.getText().toString(),editTextCedula.getText().toString(),editTextEmail.getText().toString(),editTextDireccion.getText().toString(),editTextTelefono.getText().toString());
                getProceInsertar().onInsertar(clientes);
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
        return view ;
    }
    public interface procedimientosInserttar{
        void onInsertar(Clientes clientes);

    }

    public procedimientosInserttar getProceInsertar() {
        return proceInsertar;
    }

    public void setProceInsertar(procedimientosInserttar proceInsertar) {
        this.proceInsertar = proceInsertar;
    }
}

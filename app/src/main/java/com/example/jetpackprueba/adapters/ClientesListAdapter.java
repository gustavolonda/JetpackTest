package com.example.jetpackprueba.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackprueba.R;
import com.example.jetpackprueba.entity.Clientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ClientesListAdapter  extends RecyclerView.Adapter<ClientesListAdapter.ClientesViewHolder> {

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Clientes myNote);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Clientes> mNotes;
    private OnDeleteClickListener onDeleteClickListener;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Clientes>> expandableListDetail;
     TextView editTextNombres;
     TextView editTextApellidos;
     TextView editTextCedula ;
     TextView editTextEmail ;
     TextView editTextDireccion ;

     TextView editTextTelefono;
    procediimientoEditar editarPro;
    procediimientoEliminar eliminarPro;

    public ClientesListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_adaptador_acordion, parent, false);
        ClientesViewHolder viewHolder = new ClientesViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {

        if (mNotes != null) {
            Clientes note = mNotes.get(position);
            holder.setData(note,position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
           // holder.noteItemView.setText(R.string.cliente);
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public void setNotes(List<Clientes> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {

        private TextView nombresItemView,apellidosItemView;
        private int mPosition;
        private ImageView imgDelete, imgEdit;
        public HashMap<String, List<Clientes>> getTituloClientes(Clientes clientes) {
            System.out.println("getTituloDispositivos");
            //Para el orden LinkedHashMap
            HashMap<String, List<Clientes>> expandableListDetail = new LinkedHashMap<String, List<Clientes>>();
           {
                List<Clientes> subitem = new ArrayList<Clientes>();
                subitem.add(clientes);
                expandableListDetail.put(clientes.getCedula(), subitem);
            }


            return expandableListDetail;
        }
        public void LoadClientes(Clientes clientes){
            System.out.println("LoadDispositivos");
            expandableListDetail =  getTituloClientes(clientes);
            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
            expandableListAdapter = new AdaptadorClientes(mContext, expandableListTitle, expandableListDetail);
            expandableListView.setAdapter(expandableListAdapter);
            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousGroup = -1;
                @Override
                public void onGroupExpand(int groupPosition) {
                    //Toast.makeText(getContext(),expandableListTitle.get(groupPosition) + " List Expanded.",Toast.LENGTH_SHORT).show();



                    {
                        if(groupPosition != previousGroup)
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                }
            });


            expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    //Toast.makeText(getContext(), expandableListTitle.get(groupPosition) + " List Collapsed.",Toast.LENGTH_SHORT).show();



                }
            });

            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    //Toast.makeText(getContext(), expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });




        }

        public ClientesViewHolder(View itemView) {
            super(itemView);
            //nombresItemView = itemView.findViewById(R.id.txtNombresLista);
            //apellidosItemView = itemView.findViewById(R.id.txtApellidosLista);
            //expandableListView = (ExpandableListView) itemView.findViewById(R.id.expandableListView);
            imgDelete 	 = itemView.findViewById(R.id.ivRowDelete);
            imgEdit 	 = itemView.findViewById(R.id.ivRowEdit);
            editTextNombres = (TextView) itemView.findViewById(R.id.editTextNombres);
            editTextApellidos = (TextView) itemView.findViewById(R.id.editTextApellidos);
            editTextCedula = (TextView) itemView.findViewById(R.id.editTextCedula);
             editTextEmail = (TextView) itemView.findViewById(R.id.editTextEmail);
             editTextDireccion = (TextView) itemView.findViewById(R.id.editTextDireccion);
            editTextTelefono = (TextView) itemView.findViewById(R.id.editTextTelefono);
        }

        public void setData(Clientes clientes, int position) {
           // nombresItemView.setText(nombres);
           // LoadClientes( clientes);
            //apellidosItemView.setText(apellidos);
            editTextNombres.setText(clientes.getNombres());
            editTextApellidos.setText(clientes.getApellidos());
            editTextCedula.setText(clientes.getCedula());
            editTextEmail.setText(clientes.getCorreo());
            editTextDireccion.setText(clientes.getDireccion());
            editTextTelefono.setText(clientes.getTelefono());
            mPosition = position;
        }

        public void setListeners() {
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(mContext, EditNoteActivity.class);
                    intent.putExtra("note_id", mNotes.get(mPosition).getId());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
                */

                    getEditarPro().onEdit(mNotes.get(mPosition).getId());
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPosition));
                    }*/
                   getEliminarPro().onEliminar(mNotes.get(mPosition));
                }
            });
        }
    }

    public interface procediimientoEditar{
        void onEdit(String idCliente);

    }

    public procediimientoEditar getEditarPro() {
        return editarPro;
    }

    public void setEditarPro(procediimientoEditar editarPro) {
        this.editarPro = editarPro;
    }
    public interface procediimientoEliminar{
        void onEliminar(Clientes clientes);

    }

    public procediimientoEliminar getEliminarPro() {
        return eliminarPro;
    }

    public void setEliminarPro(procediimientoEliminar eliminarPro) {
        this.eliminarPro = eliminarPro;
    }
}

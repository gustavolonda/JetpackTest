package com.example.jetpackprueba.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.jetpackprueba.R;
import com.example.jetpackprueba.entity.Clientes;

import java.util.HashMap;
import java.util.List;

public class AdaptadorClientes  extends BaseExpandableListAdapter {
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<Clientes>> expandableListDetail;
    public AdaptadorClientes(Context context, List<String> expandableListTitle,
                                     HashMap<String, List<Clientes>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }
    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);

        }
        String listTitle = (String) getGroup(groupPosition);
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
       // Drawable arrright = context.getResources().getDrawable(R.drawable.arrowright);
       // Drawable arrdown = context.getResources().getDrawable(R.drawable.arrowdown);
        if(Par(groupPosition))
        {
           // listTitleTextView.setBackgroundResource(R.drawable.tabla_celda_par);
            //System.out.println("par "+ listTitle);
        }
        else{
            //listTitleTextView.setBackgroundResource(R.drawable.tabla_celda_impar);
            //System.out.println("impar "+ listTitle);
        }
        Drawable arrright = context.getResources().getDrawable(R.drawable.arrowright);
        Drawable arrdown = context.getResources().getDrawable(R.drawable.arrowdown);

        if (null != arrright) {
            arrright.setBounds(0, 0, 40, 40);
            arrdown.setBounds(0, 0, 40, 40);
        }
        if (isExpanded) {
            listTitleTextView.setCompoundDrawables(null, null, arrright, null);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon
            listTitleTextView.setCompoundDrawables(null, null, arrdown, null);
        }
        return convertView;
    }
    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {




        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_adaptador_acordion, null);
        }

         TextView editTextNombres = (TextView) convertView.findViewById(R.id.editTextNombres);
         TextView editTextApellidos = (TextView) convertView.findViewById(R.id.editTextApellidos);
         TextView editTextCedula = (TextView) convertView.findViewById(R.id.editTextCedula);
         TextView editTextEmail = (TextView) convertView.findViewById(R.id.editTextEmail);
         TextView editTextDireccion = (TextView) convertView.findViewById(R.id.editTextDireccion);
         TextView editTextTelefono = (TextView) convertView.findViewById(R.id.editTextTelefono);
        Clientes clientes= (Clientes) getChild( groupPosition,  childPosition);
        editTextNombres.setText(clientes.getNombres());
        editTextApellidos.setText(clientes.getApellidos());
        editTextCedula.setText(clientes.getCedula());

        editTextEmail.setText(clientes.getCorreo());
        editTextDireccion.setText(clientes.getDireccion());
        editTextTelefono.setText(clientes.getTelefono());


        return convertView;
    }


    static boolean  Par(int n){
        if (n%2==0)
            return true;
        else

            return false;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}

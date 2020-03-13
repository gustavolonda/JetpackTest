package com.example.jetpackprueba.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "clientes")
public class Clientes implements Serializable {
    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getNombres() {
        return nombres;
    }

    public void setNombres(@NonNull String nombres) {
        this.nombres = nombres;
    }

    @NonNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "nombres")
    private String nombres;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;
    @NonNull
    @ColumnInfo(name = "cedula")
    private String cedula;
    @NonNull
    @ColumnInfo(name = "correo")
    private String correo;
    @NonNull
    @ColumnInfo(name = "direccion")
    private String direccion;
    @NonNull
    @ColumnInfo(name = "telefono")
    private String telefono;
    @NonNull
    public String getCedula() {
        return cedula;
    }

    public void setCedula(@NonNull String cedula) {
        this.cedula = cedula;
    }

    @NonNull
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(@NonNull String correo) {
        this.correo = correo;
    }

    @NonNull
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NonNull String direccion) {
        this.direccion = direccion;
    }

    @NonNull
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        this.telefono = telefono;
    }

    public Clientes(@NonNull String id, @NonNull String nombres, @NonNull String apellidos, @NonNull String cedula, @NonNull String correo, @NonNull String direccion, @NonNull String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}

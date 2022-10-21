package com.example.vjcontact.entidades;
import com.google.gson.annotations.SerializedName;

public class Contact {
    public int id;
    @SerializedName("name")
    public String Nombre;
    @SerializedName("lastname")
    public String Apellido;
    public String Telefono;
    public String ImagenUrl;
}

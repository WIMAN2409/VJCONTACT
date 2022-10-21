package com.example.vjcontact.entidades;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {

    //Guardar
    @POST("contacts")
    Call<Void> create(@Body Contact contact);

    //Actualizar
    @PUT("contacts/{idContact}")
    Call<Void> update(@Body Contact contact, @Path("idContact") int id);
}

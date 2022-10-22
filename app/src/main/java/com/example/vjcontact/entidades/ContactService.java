package com.example.vjcontact.entidades;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {

    //List
    @GET("contacts")
    Call<List<Contact>> listUsers();


    //Guardar
    @POST("contacts")
    Call<Void> create(@Body Contact contact);

    //Actualizar
    @PUT("contacts/{idContact}")
    Call<Void> update(@Body Contact contact, @Path("idContact") String id);

    //eliminar
    @DELETE("contacts/{idContact}")
    Call<Void> delete(@Path("idContact") String id);


}

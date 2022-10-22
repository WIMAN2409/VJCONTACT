package com.example.vjcontact;

import com.example.vjcontact.entidades.Contact;
import com.example.vjcontact.entidades.ContactService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static final String BASE_URL = "https://6352dcc0a9f3f34c374a79ab.mockapi.io/";

    private final ContactService service;


    public Repository() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ContactService.class);
    }

    // todo, methods API REST Models

    public Call<List<Contact>> listUser() {
      return  service.listUsers();
    }

    public Call<Void> createUser(Contact contact) {
        return service.create(contact);
    }

    public Call<Void>  updateUser(Contact contact, String id) {
       return service.update(contact, id);
    }

    public Call<Void> deleteUser(Contact contact, String id) {
       return service.delete(contact, id);
    }

}

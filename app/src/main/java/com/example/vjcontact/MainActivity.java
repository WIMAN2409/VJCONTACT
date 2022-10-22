package com.example.vjcontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vjcontact.adapter.ListUserAdapter;
import com.example.vjcontact.entidades.Contact;
import com.example.vjcontact.entidades.ContactService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://6352dcc0a9f3f34c374a79ab.mockapi.io/";
    EditText etName, etLasName;
    Button btnEnviar, btnEditar;

    RecyclerView rvListUser;

    List<Contact> users;
    ListUserAdapter userAdapter;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListUser = findViewById(R.id.list_item);
        FloatingActionButton btnAdd = findViewById(R.id.addNewUser);

        repository = new Repository(); // first init repository call API

        getListUsers(); // call API

        btnAdd.setOnClickListener(view -> {
            startActivity(new Intent(this,EditUserActivity.class));
        });
    }

    public void getListUsers() {
       repository.listUser().enqueue(new Callback<List<Contact>>() {
           @Override
           public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
               if (response.isSuccessful()){
                   users = response.body();
                   userAdapter = new ListUserAdapter(users);

                   rvListUser.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   rvListUser.setAdapter(userAdapter);

               }else {
                   Toast.makeText(MainActivity.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<Contact>> call, Throwable t) {

           }
       });
    }

}
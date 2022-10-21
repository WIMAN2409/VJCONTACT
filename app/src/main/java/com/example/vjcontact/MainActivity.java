package com.example.vjcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vjcontact.entidades.Contact;
import com.example.vjcontact.entidades.ContactService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etName, etLasName;
    Button btnEnviar, btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    etName = findViewById(R.id.etNombre);
    etLasName = findViewById(R.id.etApellido);


    btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = etName.getText().toString();
            String lastname = etLasName.getText().toString();

            Contact contact = new Contact();
            contact.Nombre = name;
            contact.Apellido = lastname;

            EnviarContacto(contact);
            Limpiar();
        }
    });

    btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = etName.getText().toString();
            String lastname = etLasName.getText().toString();

            Contact contact = new Contact();
            contact.Nombre = name;
            contact.Apellido = lastname;

            ActualizarContacto(contact, 1);

        }
    });

    Contact contact = new Contact();
    contact.Nombre = "Joel";
    contact.Apellido = "Huamán";

}

    public  void EnviarContacto(Contact contact){
        Retrofit retrofit = new Retrofit.Builder() //Configurando Retrofit
                .baseUrl("https://6352dcc0a9f3f34c374a79ab.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContactService service = retrofit.create(ContactService.class);
        service.create(contact).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("MAIN_APP", "Responde" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public  void ActualizarContacto(Contact contact, int idContact){
        Retrofit retrofit = new Retrofit.Builder() //Configurando Retrofit
                .baseUrl("https://6352dcc0a9f3f34c374a79ab.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContactService service = retrofit.create(ContactService.class);
        service.update(contact, idContact).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("MAIN_APP", "Responde" + response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }



    public void Limpiar(){

        etLasName.setText("");
        etName.setText("");

    }

}
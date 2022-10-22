package com.example.vjcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vjcontact.entidades.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    Repository repository;
    Contact user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        TextView txtName = findViewById(R.id.txt_name);
        TextView txtLastName = findViewById(R.id.txt_lastname);
        Button btnSave = findViewById(R.id.button_save);

        repository = new Repository();

        if(getIntent().getExtras() != null) { // check parameters EXTRA
            // TODO, methods and functions for EDIT
            user = (Contact) getIntent().getSerializableExtra("USER_EXTRA");
            txtName.setText(user.getName());
            txtLastName.setText(user.getLastname());

        }else{
            // TODO, methods and functions for CREATE

        }

        btnSave.setOnClickListener(view -> { // click save update user
            Toast.makeText(this, "Guardando ...", Toast.LENGTH_LONG).show();
            user.setName(txtName.getText().toString());
            user.setLastname(txtLastName.getText().toString());
            repository.updateUser(user,user.getId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        finish(); // finish activity
                        startActivity(new Intent(view.getContext(),MainActivity.class)); // start Activity refresh RecyclerView
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {}
            });
        });
    }
}
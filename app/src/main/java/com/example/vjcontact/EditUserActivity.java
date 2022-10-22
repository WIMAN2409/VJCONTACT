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
    private String TYPE_ACTION = "edit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        TextView txtName = findViewById(R.id.txt_name);
        TextView txtLastName = findViewById(R.id.txt_lastname);
        TextView txtTittle = findViewById(R.id.txtTitle);
        Button btnSave = findViewById(R.id.button_save);

        repository = new Repository();

        if(getIntent().getExtras() != null) { // check parameters EXTRA
            // TODO, methods and functions for EDIT
            user = (Contact) getIntent().getSerializableExtra("USER_EXTRA");
            txtName.setText(user.getName());
            txtLastName.setText(user.getLastname());

            TYPE_ACTION = "edit";
        }else{
            txtTittle.setText("Crear Usuario");
            // TODO, methods and functions for CREATE

            TYPE_ACTION = "create";
        }

        btnSave.setOnClickListener(view -> { // click create or update user
            Toast.makeText(this, "Guardando ...", Toast.LENGTH_LONG).show();

            if (TYPE_ACTION.equals("create")){
                Contact user = new Contact();
                user.setName(txtName.getText().toString());
                user.setLastname(txtLastName.getText().toString());

                repository.createUser(user).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            refresh();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }else{
                user.setName(txtName.getText().toString());
                user.setLastname(txtLastName.getText().toString());
                repository.updateUser(user,user.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            refresh();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {t.printStackTrace();}
                });
            }
        });
    }

    private void refresh(){
        finish(); // finish activity
        startActivity(new Intent(getApplicationContext(),MainActivity.class));// start Activity refresh RecyclerView
    }
}
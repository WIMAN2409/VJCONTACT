package com.example.vjcontact.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vjcontact.EditUserActivity;
import com.example.vjcontact.R;
import com.example.vjcontact.entidades.Contact;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {
    List<Contact> usersData;
    public ListUserAdapter(List<Contact> users) {
       this.usersData = users;
    }

    @NonNull
    @Override
    public ListUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.ViewHolder holder, int position) {
        Contact user = usersData.get(position);
        holder.txtUserName.setText(user.getName()+ " "+user.getLastname());
        holder.btnEdit.setOnClickListener(view -> {
            Toast.makeText(view.getContext(),    "Editar Usuario : "+user.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), EditUserActivity.class);
            intent.putExtra("USER_EXTRA",user);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserName;
        ImageView btnEdit;
        ImageView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txt_name);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}

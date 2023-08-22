package com.example.complaintregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OfficerAdapter extends RecyclerView.Adapter<OfficerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<OfficerData> officerData;
    public OfficerAdapter(Context context,ArrayList<OfficerData> officerData) {
        this.officerData = officerData;
        this.context = context;
    }

    @NonNull
    @Override
    public OfficerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.officer_card, parent, false);
        return new OfficerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficerAdapter.ViewHolder holder, int position) {
        holder.name.setText("Name:"+officerData.get(position).getName());
        holder.phone.setText("Phone No.:"+officerData.get(position).getPhone());
        holder.role.setText("Role:"+officerData.get(position).getRole());
        holder.email.setText("Email:"+officerData.get(position).getEmail());
        holder.id.setText("ID:"+officerData.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return officerData.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, phone, role, id, email;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            role = itemView.findViewById(R.id.role);
            email = itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.id);
        }
    }
}

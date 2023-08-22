package com.example.complaintregistration;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {
    private ArrayList<Complaint> complaintArrayList;
    private Context context;
    private  String curUid;

    public ComplaintAdapter(ArrayList<Complaint> complaintArrayList, Context context, String curUid) {
        this.complaintArrayList = complaintArrayList;
        this.context = context;
        this.curUid = curUid;
    }

    @NonNull
    @Override
    public ComplaintAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_card, parent, false);
        return new ComplaintAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ViewHolder holder, int position) {
        holder.name.setText(complaintArrayList.get(position).getName());
        holder.address.setText(complaintArrayList.get(position).getAddress());
        holder.date.setText(complaintArrayList.get(position).getDate());
        holder.compType.setText(complaintArrayList.get(position).getCompType());
        holder.comp.setText(complaintArrayList.get(position).getComp());
        holder.status.setText("Status:"+complaintArrayList.get(position).getStatus());
        holder.compID.setText("ID:"+complaintArrayList.get(position).getCompID());

        holder.delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setTitle("Warning");
            builder.setMessage("Are you sure? Your request will be permanently deleted!!");
            builder.setPositiveButton("No", (dialog, which) -> {

            });
            builder.setNegativeButton("Yes",((dialog, which) -> {
                DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference().child("Complaints").child(curUid).child(complaintArrayList.get(position).getCompID());
                reqRef.removeValue();
                Toast.makeText(context, "Permanently Deleted!!!", Toast.LENGTH_SHORT).show();
            }));
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return complaintArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, address, date, compType, comp,status,compID;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.date);
            compType = itemView.findViewById(R.id.type);
            comp = itemView.findViewById(R.id.cmp);
            compID = itemView.findViewById(R.id.compID);
            status = itemView.findViewById(R.id.status);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

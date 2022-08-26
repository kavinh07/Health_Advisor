package com.example.healthadvisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ShowAppAdapter extends RecyclerView.Adapter<ShowAppAdapter.MyViewHolder> {

    Context context;
    ArrayList<ShowApp> applist;

    public ShowAppAdapter(Context context, ArrayList<ShowApp> applist, AppointmentActivity appointmentActivity) {
        this.context = context;
        this.applist = applist;
    }

    public ShowAppAdapter(Context context, ArrayList<ShowApp> applist, DoctorAppointmentActivity doctorAppointmentActivity) {
        this.context = context;
        this.applist = applist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.applist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ShowApp app= applist.get(position);
        holder.patName.setText(app.getPatName());
        holder.docName.setText(app.getDocName());
        holder.slot.setText(app.getSlot());
        holder.appId.setText(app.getAppId());

    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView patName, docName, slot, appId;
        Button delete;
        private ShowAppAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patName= itemView.findViewById(R.id.patName);
            docName= itemView.findViewById(R.id.doctrName);
            slot= itemView.findViewById(R.id.slotTime);
            appId= itemView.findViewById(R.id.appId);
            delete= itemView.findViewById(R.id.appDltBtn);

        }

    }
}

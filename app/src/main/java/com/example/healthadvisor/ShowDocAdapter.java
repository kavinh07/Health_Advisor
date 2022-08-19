package com.example.healthadvisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowDocAdapter extends RecyclerView.Adapter<ShowDocAdapter.MyViewHolder> {

    Context context;

    ArrayList<ShowDoc> list;

    public ShowDocAdapter(Context context, ArrayList<ShowDoc> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowDocAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDocAdapter.MyViewHolder holder, int position) {

        ShowDoc doc= list.get(position);
        holder.docName.setText(doc.getDocName());
        holder.slot1.setText(doc.getSlot1());
        holder.slot2.setText(doc.getSlot2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView docName, slot1, slot2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            docName= itemView.findViewById(R.id.docName);
            slot1= itemView.findViewById(R.id.firstSlotTime);
            slot2= itemView.findViewById(R.id.secondSlotTime);
        }
    }
}

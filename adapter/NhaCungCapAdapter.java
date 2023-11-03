package com.example.doanbanhang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanbanhang.R;
import com.example.doanbanhang.data.NhaCungCap;

import java.util.ArrayList;

public class NhaCungCapAdapter extends RecyclerView.Adapter<NhaCungCapAdapter.NhaCungCapVH> {
    ArrayList<NhaCungCap> NhaCungCaps;
    NhaCungCapAdapter.Listener listener;
    Context context;
    int recyclerViewId;
    public NhaCungCapAdapter(int recyclerViewId, NhaCungCapAdapter.Listener listener, ArrayList<NhaCungCap> NhaCungCaps, Context context) {
        this.recyclerViewId = recyclerViewId;
        this.listener = listener;
        this.NhaCungCaps = NhaCungCaps;
        this.context = context;
    }
    @NonNull
    @Override
    public NhaCungCapAdapter.NhaCungCapVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nhacungcap_item, parent,false);
        return new NhaCungCapAdapter.NhaCungCapVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NhaCungCapAdapter.NhaCungCapVH holder, @SuppressLint("RecyclerView") int position) {
        NhaCungCap NhaCungCap = NhaCungCaps.get(position);
        holder.setIsRecyclable(false);
        holder.Ten.setText(NhaCungCap.getTen());
        holder.Des.setText(NhaCungCap.getDes());
        holder.diachi.setText(NhaCungCap.getDiachi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClickListener4(recyclerViewId, NhaCungCap, NhaCungCaps.size());
                SharedPreferences sharedPref = context.getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", String.valueOf(position));
                editor.apply();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("count", NhaCungCaps.size());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener4(recyclerViewId, NhaCungCap, v);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {

        return NhaCungCaps.size();

    }



    class NhaCungCapVH extends RecyclerView.ViewHolder{


        TextView Ten,Des,diachi;

        public NhaCungCapVH
                (@NonNull View itemView) {
            super(itemView);
            Ten =itemView.findViewById(R.id.Rtennhacungcap);
            Des=itemView.findViewById(R.id.RDesNhacungcap);
            diachi=itemView.findViewById(R.id.RDiachinhachungcap);
        }
    }

    public interface Listener {
        void onItemClickListener4(int recyclerViewId, NhaCungCap NhaCungCap, int size);
        void onItemLongClickListener4(int recyclerViewId, NhaCungCap NhaCungCap, View view);
    }
}

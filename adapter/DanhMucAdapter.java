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
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.data.DanhMuc;

import java.util.ArrayList;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.DanhMucVH> {
    ArrayList<DanhMuc> DanhMucs;
    DanhMucAdapter.Listener listener;
    Context context;
    int recyclerViewId;
    public DanhMucAdapter(int recyclerViewId, DanhMucAdapter.Listener listener, ArrayList<DanhMuc> DanhMucs, Context context) {
        this.recyclerViewId = recyclerViewId;
        this.listener = listener;
        this.DanhMucs = DanhMucs;
        this.context = context;
    }
    @NonNull
    @Override
    public DanhMucAdapter.DanhMucVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhmuc_item, parent,false);
        return new DanhMucAdapter.DanhMucVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DanhMucAdapter.DanhMucVH holder, @SuppressLint("RecyclerView") int position) {
        DanhMuc DanhMuc = DanhMucs.get(position);
        holder.setIsRecyclable(false);
        holder.Ten.setText(DanhMuc.getTenDanhmuc());
        holder.ID.setText(DanhMuc.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClickListener4(recyclerViewId, DanhMuc, DanhMucs.size());
                SharedPreferences sharedPref = context.getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", String.valueOf(position));
                editor.apply();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("count", DanhMucs.size());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener4(recyclerViewId, DanhMuc, v);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {

        return DanhMucs.size();

    }



    class DanhMucVH extends RecyclerView.ViewHolder{


        TextView ID,Ten;

        public DanhMucVH
                (@NonNull View itemView) {
            super(itemView);
            ID =itemView.findViewById(R.id.RID);
            Ten=itemView.findViewById(R.id.Rtendanhmuc);
        }
    }

    public interface Listener {
        void onItemClickListener4(int recyclerViewId, DanhMuc DanhMuc, int size);
        void onItemLongClickListener4(int recyclerViewId, DanhMuc DanhMuc, View view);
    }
}

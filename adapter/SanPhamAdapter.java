package com.example.doanbanhang.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doanbanhang.R;
import com.example.doanbanhang.data.Sanpham;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamVH> {
    ArrayList<Sanpham> sanphams;
    Listener listener;
    Context context;
    int recyclerViewId;
    public SanPhamAdapter(int recyclerViewId, Listener listener, ArrayList<Sanpham> sanphams, Context context) {
        this.recyclerViewId = recyclerViewId;
        this.listener = listener;
        this.sanphams = sanphams;
        this.context = context;
    }
    @NonNull
    @Override
    public SanPhamVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent,false);
        return new SanPhamVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SanPhamVH holder, @SuppressLint("RecyclerView") int position) {
        Sanpham sanpham = sanphams.get(position);
        holder.Name.setText(sanpham.getTensp());
        holder.Gia.setText(Integer.toString(sanpham.getGia()));
        holder.Des.setText(sanpham.getDessp());


        byte[] data = Base64.decode(sanpham.getImage(), Base64.DEFAULT);
        Bitmap bm;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        bm = BitmapFactory.decodeByteArray(data, 0, data.length, opt);
        holder.imageSp.setImageBitmap(bm);



        holder.setIsRecyclable(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener4(recyclerViewId, sanpham, sanphams.size());
                SharedPreferences sharedPref = context.getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", String.valueOf(position));
                editor.apply();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("count", sanphams.size());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener4(recyclerViewId, sanpham, v);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return sanphams.size();
    }



    class SanPhamVH extends RecyclerView.ViewHolder{


        TextView Name, Des,Gia;
        ImageView imageSp;

        public SanPhamVH
                (@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Rtendanhmuc);
            Des =itemView.findViewById(R.id.RUsername);
            Gia=itemView.findViewById(R.id.RPassword);
            imageSp = itemView.findViewById(R.id.imageSp);
        }
    }

    public interface Listener {
        void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size);
        void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view);
    }
}

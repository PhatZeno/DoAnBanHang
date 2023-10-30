package com.example.doanbanhang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanbanhang.R;
import com.example.doanbanhang.data.Sanpham;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearhViewHolder> implements Filterable {

    private List<Sanpham> mListSanpham;
    private List<Sanpham> mListSanphamOld;
    Context context;
    Listener listener;

    public SearchAdapter(Listener listener,List<Sanpham> mListSanpham, Context context) {
        this.listener=listener;
        this.mListSanpham = mListSanpham;
        this.mListSanphamOld = mListSanpham;
        this.context=context;
    }
    @NonNull
    @Override
    public SearhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new SearhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearhViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Sanpham sanpham = mListSanpham.get(position);

        if(sanpham == null) {
            return ;
        }
        holder.Name.setText(sanpham.getTensp());
        holder.Des.setText(sanpham.getDessp());
        holder.Gia.setText(String.valueOf(sanpham.getGia()));
    }

    @Override
    public int getItemCount() {
        if(mListSanpham != null) {
            return mListSanpham.size();
        }
        return 0;
    }


    public class SearhViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Des,Gia;
        public SearhViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Rtendanhmuc);
            Des =itemView.findViewById(R.id.RUsername);
            Gia=itemView.findViewById(R.id.RPassword);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()) {
                    mListSanpham = mListSanphamOld;
                }
                else {
                    List<Sanpham> list = new ArrayList<>();
                    for(Sanpham sanpham : mListSanphamOld) {
                        if(sanpham.getTensp().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(sanpham);
                        }
                    }

                    mListSanpham = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListSanpham;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListSanpham = (List<Sanpham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public interface Listener {
        void onItemClickListener(Sanpham sanpham);
        void onItemLongClickListener(Sanpham sanpham, View view);
    }

}
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
import com.example.doanbanhang.data.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {
    ArrayList<User> Users;
    UserAdapter.Listener listener;
    Context context;
    int recyclerViewId;
    public UserAdapter(int recyclerViewId, UserAdapter.Listener listener, ArrayList<User> Users, Context context) {
        this.recyclerViewId = recyclerViewId;
        this.listener = listener;
        this.Users = Users;
        this.context = context;
    }
    @NonNull
    @Override
    public UserAdapter.UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent,false);
        return new UserAdapter.UserVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserVH holder, @SuppressLint("RecyclerView") int position) {
        User User = Users.get(position);
        holder.Name.setText(User.getName());
        holder.Password.setText(User.getUsername());
        holder.Username.setText(User.getPassword());
        holder.setIsRecyclable(false);
        holder.role.setText(User.getRole());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener4(recyclerViewId, User, Users.size());
                SharedPreferences sharedPref = context.getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", String.valueOf(position));
                editor.apply();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("count", Users.size());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener4(recyclerViewId, User, v);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {

        return Users.size();

    }



    class UserVH extends RecyclerView.ViewHolder{


        TextView Name, Username, Password,role;

        public UserVH
                (@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Rtendanhmuc);
            Username =itemView.findViewById(R.id.RUsername);
            Password =itemView.findViewById(R.id.RPassword);
            role=itemView.findViewById(R.id.Role);
        }
    }

    public interface Listener {
        void onItemClickListener4(int recyclerViewId, User User, int size);
        void onItemLongClickListener4(int recyclerViewId, User User, View view);
    }
}

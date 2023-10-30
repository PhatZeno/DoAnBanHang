package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.doanbanhang.adapter.SearchAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class SearchProcActivity extends AppCompatActivity implements SearchAdapter.Listener {
    RecyclerView recyclerViewTimKiem;
    DBHelper dbHelper;
    ArrayList<Sanpham> sanphamArrayList;
    SearchAdapter searchApdater;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_proc);
        recyclerViewTimKiem =findViewById(R.id.recyclerviewsearchsanpham);
        dbHelper = new DBHelper(SearchProcActivity.this);
        sanphamArrayList = dbHelper.getallSp();
        sanphamArrayList.clear();
        sanphamArrayList = dbHelper.getallSp();
        searchApdater = new SearchAdapter(SearchProcActivity.this, sanphamArrayList, SearchProcActivity.this);
        recyclerViewTimKiem.setAdapter(searchApdater);
        recyclerViewTimKiem.setLayoutManager(new LinearLayoutManager(SearchProcActivity.this, LinearLayoutManager.VERTICAL, false));
        editText = (EditText)findViewById(R.id.editTextTextPersonNames);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sanphamArrayList.clear();
                sanphamArrayList.addAll(dbHelper.searchSanpham(editable.toString()));
                searchApdater.notifyDataSetChanged();
                if (editable.toString().isEmpty()) {
                    recyclerViewTimKiem.setVisibility(View.GONE);
                }

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        EditText editTextB = findViewById(R.id.editTextTextPersonNames);
        editTextB.requestFocus();
    }

    @Override
    public void onItemClickListener(Sanpham sanpham) {

    }

    @Override
    public void onItemLongClickListener(Sanpham sanpham, View view) {

    }
}
package com.example.menumakanan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private FloatingActionButton fabAdd;
    private MenuAdapter adapter;
    private List<MenuModel> listMenu = new ArrayList<>();

    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("menu_makanan");

        rvList = findViewById(R.id.rv_list);
        fabAdd = findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMenuActivity.class));
            }
        });

        adapter = new MenuAdapter(this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        getMenu();
    }

    private void getMenu() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listMenu.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MenuModel menuModel = ds.getValue(MenuModel.class);
                    assert menuModel != null;
                    menuModel.setId(ds.getKey());
                    listMenu.add(menuModel);
                }
                adapter.setData(listMenu);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.example.menumakanan;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMenuActivity extends AppCompatActivity {

    private TextInputEditText etNamaMenu;
    private TextInputEditText etHarga;
    private TextInputEditText etDetailMenu;
    private Button btnAdd;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        etNamaMenu = findViewById(R.id.et_nama_menu);
        etHarga = findViewById(R.id.et_harga);
        etDetailMenu = findViewById(R.id.et_detail_menu);
        btnAdd = findViewById(R.id.btn_add);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("menu_makanan");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaMenu = etNamaMenu.getText().toString();
                int harga = Integer.parseInt(etHarga.getText().toString());
                String detailMenu = etDetailMenu.getText().toString();

                MenuModel menuModel = new MenuModel(namaMenu, harga, detailMenu);
                saveToDatabase(menuModel);
            }
        });
    }

    private void saveToDatabase(MenuModel menuModel) {
        String key = dbRef.push().getKey();
        dbRef.child(key).setValue(menuModel);
    }
}

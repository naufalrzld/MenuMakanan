package com.example.menumakanan;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddMenuActivity extends AppCompatActivity {

    private TextInputEditText etNamaMenu;
    private TextInputEditText etHarga;
    private TextInputEditText etDetailMenu;
    private Button btnAdd;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    private Intent dataIntent;
    private boolean isEdit;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        dataIntent = getIntent();

        etNamaMenu = findViewById(R.id.et_nama_menu);
        etHarga = findViewById(R.id.et_harga);
        etDetailMenu = findViewById(R.id.et_detail_menu);
        btnAdd = findViewById(R.id.btn_add);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("menu_makanan");

        isEdit = dataIntent.getBooleanExtra("isEdit", false);
        if (isEdit) {
            MenuModel menuModel = dataIntent.getParcelableExtra("data_menu");
            key = menuModel.getId();
            etNamaMenu.setText(menuModel.getNamaMenu());
            etHarga.setText(String.valueOf(menuModel.getHarga()));
            etDetailMenu.setText(menuModel.getDetailMenu());
            btnAdd.setText("Simpan");
        }

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
        if (isEdit) {
            Map<String, Object> map = new HashMap<>();
            map.put("/"+key, menuModel);

            dbRef.updateChildren(map);
        } else {
            String key = dbRef.push().getKey();
            dbRef.child(key).setValue(menuModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Data berhasil di simpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    private void delete() {
        dbRef.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Data baerhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem delete = menu.findItem(R.id.delete);
        delete.setVisible(isEdit);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

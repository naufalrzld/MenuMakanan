package com.example.menumakanan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuModel> listMenu;

    public MenuAdapter(Context context) {
        this.context = context;
        listMenu = new ArrayList<>();
    }

    public void setData(List<MenuModel> listMenu) {
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder viewHolder, int i) {
        final MenuModel menuModel = listMenu.get(i);

        viewHolder.tvNamaMenu.setText(menuModel.getNamaMenu());
        viewHolder.tvHarga.setText(String.valueOf(menuModel.getHarga()));
        viewHolder.tvDetailMenu.setText(menuModel.getDetailMenu());
        viewHolder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddMenuActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("data_menu", menuModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvItem;
        ImageView ivMenu;
        TextView tvNamaMenu, tvHarga, tvDetailMenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.cv_item);
            ivMenu = itemView.findViewById(R.id.iv_menu);
            tvNamaMenu = itemView.findViewById(R.id.tv_nama_menu);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvDetailMenu = itemView.findViewById(R.id.tv_detail_menu);
        }
    }
}

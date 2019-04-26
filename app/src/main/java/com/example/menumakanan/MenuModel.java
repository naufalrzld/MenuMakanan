package com.example.menumakanan;

public class MenuModel {
    private String namaMenu;
    private int harga;
    private String detailMenu;

    public MenuModel() {

    }

    public MenuModel(String namaMenu, int harga, String detailMenu) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.detailMenu = detailMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getDetailMenu() {
        return detailMenu;
    }

    public void setDetailMenu(String detailMenu) {
        this.detailMenu = detailMenu;
    }
}

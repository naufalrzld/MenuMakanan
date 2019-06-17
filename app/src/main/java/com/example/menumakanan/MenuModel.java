package com.example.menumakanan;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuModel implements Parcelable {
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.namaMenu);
        dest.writeInt(this.harga);
        dest.writeString(this.detailMenu);
    }

    protected MenuModel(Parcel in) {
        this.id = in.readString();
        this.namaMenu = in.readString();
        this.harga = in.readInt();
        this.detailMenu = in.readString();
    }

    public static final Creator<MenuModel> CREATOR = new Creator<MenuModel>() {
        @Override
        public MenuModel createFromParcel(Parcel source) {
            return new MenuModel(source);
        }

        @Override
        public MenuModel[] newArray(int size) {
            return new MenuModel[size];
        }
    };
}

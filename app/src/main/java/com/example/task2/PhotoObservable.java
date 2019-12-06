package com.example.task2;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoObservable extends BaseObservable {

    @SerializedName("id")
    @Expose
    private String Photoid;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("server")
    @Expose
    private String server;
    @SerializedName("farm")
    @Expose
    private Integer farm;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ispublic")
    @Expose
    private Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    private Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    private Integer isfamily;
    @SerializedName("url_s")
    @Expose
    private String urlS;
    @SerializedName("height_s")
    @Expose
    private Integer heightS;
    @SerializedName("width_s")
    @Expose
    private Integer widthS;

    @Bindable
    public String getPhotoid() {
        return Photoid;
    }

    public void setPhotoid(String Photoid) {
        this.Photoid = Photoid;
    }

    @Bindable
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
        notifyPropertyChanged(com.example.task2.BR.owner);
    }

    @Bindable
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
        notifyPropertyChanged(com.example.task2.BR.secret);
    }

    @Bindable
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
        notifyPropertyChanged(com.example.task2.BR.server);
    }

    @Bindable
    public Integer getFarm() {
        return farm;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
        notifyPropertyChanged(com.example.task2.BR.farm);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(com.example.task2.BR.title);
    }

    @Bindable
    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
        notifyPropertyChanged(com.example.task2.BR.ispublic);
    }

    @Bindable
    public Integer getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
        notifyPropertyChanged(com.example.task2.BR.isfriend);
    }

    @Bindable
    public Integer getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
        notifyPropertyChanged(com.example.task2.BR.isfamily);
    }

    @Bindable
    public String getUrlS() {
        return urlS;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
        notifyPropertyChanged(com.example.task2.BR.urlS);
    }

    @Bindable
    public Integer getHeightS() {
        return heightS;
    }

    public void setHeightS(Integer heightS) {
        this.heightS = heightS;
        notifyPropertyChanged(com.example.task2.BR.heightS);
    }

    @Bindable
    public Integer getWidthS() {
        return widthS;
    }

    public void setWidthS(Integer widthS) {
        this.widthS = widthS;
        notifyPropertyChanged(com.example.task2.BR.widthS);
    }

    public static final DiffUtil.ItemCallback<PhotoObservable> callBack = new DiffUtil.ItemCallback<PhotoObservable>() {
        @Override
        public boolean areItemsTheSame(@NonNull PhotoObservable oldItem, @NonNull PhotoObservable newItem) {
            return oldItem.Photoid==newItem.Photoid;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PhotoObservable oldItem, @NonNull PhotoObservable newItem) {
            return true;
        }
    };
}

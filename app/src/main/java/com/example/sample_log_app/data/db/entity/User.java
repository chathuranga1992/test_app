package com.example.sample_log_app.data.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.sample_log_app.GenericEntity;
import com.example.sample_log_app.data.rest.domain.Image;


@Entity(tableName = "User")
public class User extends GenericEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "user_id")
    private String _id;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "dob")
    private String dob;
    @ColumnInfo(name = "createdAt")
    private String createdAt;
    @ColumnInfo(name = "zipCode")
    private String zipCode;

    @Embedded public Image image;

    @Ignore
    public User() {

    }

    public User(String _id, String gender, String name, String email,
                String phone, String dob, Image image, String createdAt, String zipCode) {
        this._id = _id;
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.image =image;
        this.createdAt = createdAt;
        this.zipCode = zipCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

package org.primeit.qrcodeandbarcodescanner.data.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "histories")
public class HistoryModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "data")
    public String data;

    @ColumnInfo(name = "createdAt")
    public Long createdAt;

    @ColumnInfo(name = "type")
    public String type;

    public HistoryModel(String data, Long createdAt, String type) {
        this.data = data;
        this.createdAt = createdAt;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package org.prime.qrandbarcodescanner.data.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "History_database")
public class HistoryModel {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "history_url")
    public String url;

    @ColumnInfo(name = "history_date")
    public String date;

    @ColumnInfo(name = "history_type")
    public String type;

    public HistoryModel(String url, String date, String type) {
        this.url = url;
        this.date = date;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

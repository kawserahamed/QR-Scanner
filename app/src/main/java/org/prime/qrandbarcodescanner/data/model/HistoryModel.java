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

    public HistoryModel() {
    }

    public HistoryModel(String url, String date) {
        this.url = url;
        this.date = date;
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

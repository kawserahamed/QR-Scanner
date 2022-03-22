package org.prime.qrandbarcodescanner.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "History_database")
public class History {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "history_url")
    public String url;

    @ColumnInfo(name = "history_date")
    public String date;
}

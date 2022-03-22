package org.prime.qrandbarcodescanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.prime.qrandbarcodescanner.dao.HistoryDao;
import org.prime.qrandbarcodescanner.model.History;

@Database(entities = {History.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
    public static HistoryDatabase INSTANCE;

    public static HistoryDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HistoryDatabase.class,
                    "History_database").allowMainThreadQueries().build();

        }

        return INSTANCE;
    }

}

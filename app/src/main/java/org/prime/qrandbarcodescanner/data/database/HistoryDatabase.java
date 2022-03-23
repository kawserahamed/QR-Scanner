package org.prime.qrandbarcodescanner.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.prime.qrandbarcodescanner.data.model.HistoryModel;

@Database(entities = {HistoryModel.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();

    public static HistoryDatabase INSTANCE;

    public static HistoryDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HistoryDatabase.class,
                    "History_database").fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}

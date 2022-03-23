package org.prime.qrandbarcodescanner.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.prime.qrandbarcodescanner.data.model.HistoryModel;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM History_database")
    LiveData<List<HistoryModel>> getAllHistory();

    @Insert
    void insertNotes(HistoryModel... history);

    @Delete
    void deleteNotes(HistoryModel historyModel);

    @Update
    void updateNotes(HistoryModel history);

}

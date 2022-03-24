package org.primeit.qrandbarcodescanner.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.primeit.qrandbarcodescanner.data.model.HistoryModel;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM histories ORDER BY id desc")
    LiveData<List<HistoryModel>> getAllHistory();

    @Insert
    void insertHistory(HistoryModel... historyModel);

    @Delete
    void deleteHistory(HistoryModel historyModel);

    @Update
    void updateHistory(HistoryModel history);

}

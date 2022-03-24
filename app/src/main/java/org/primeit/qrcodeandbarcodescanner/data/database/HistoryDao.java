package org.primeit.qrcodeandbarcodescanner.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.primeit.qrcodeandbarcodescanner.data.model.HistoryModel;

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

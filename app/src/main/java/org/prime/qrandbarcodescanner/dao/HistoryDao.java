package org.prime.qrandbarcodescanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.prime.qrandbarcodescanner.model.History;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM History_database")
    LiveData<List<History>> getAllHistory();

    @Insert
    void insertNotes(History... history);

    @Query("DELETE FROM History_database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(History history);

}

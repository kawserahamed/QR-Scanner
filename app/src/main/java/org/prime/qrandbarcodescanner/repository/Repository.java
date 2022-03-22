package org.prime.qrandbarcodescanner.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.prime.qrandbarcodescanner.dao.HistoryDao;
import org.prime.qrandbarcodescanner.database.HistoryDatabase;
import org.prime.qrandbarcodescanner.model.History;

import java.util.List;

public class Repository {
    public HistoryDao historyDao;
    public LiveData<List<History>> getAllHistory;

    public Repository(Context context) {
        HistoryDatabase database = HistoryDatabase.getDatabaseInstance(context);
        historyDao = database.historyDao();
        getAllHistory = historyDao.getAllHistory();

    }

    public void insertHistory(History history) {
        historyDao.insertNotes(history);

    }

    public void deleteHistory(int id) {
        historyDao.deleteNotes(id);

    }

    public void updateHistory(History history) {
        historyDao.updateNotes(history);

    }


}

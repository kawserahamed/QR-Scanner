package org.prime.qrandbarcodescanner.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.prime.qrandbarcodescanner.data.database.HistoryDao;
import org.prime.qrandbarcodescanner.data.database.HistoryDatabase;
import org.prime.qrandbarcodescanner.data.model.HistoryModel;

import java.util.List;

public class HistoryRepository {
    public HistoryDao historyDao;
    public LiveData<List<HistoryModel>> getAllHistory;

    public HistoryRepository(Context context) {
        HistoryDatabase database = HistoryDatabase.getDatabaseInstance(context);
        historyDao = database.historyDao();
        getAllHistory = historyDao.getAllHistory();

    }

    public void insertHistory(HistoryModel history) {
        historyDao.insertHistory(history);

    }

    public void deleteHistory(HistoryModel historyModel) {
        historyDao.deleteHistory(historyModel);

    }

    public void updateHistory(HistoryModel history) {
        historyDao.updateHistory(history);

    }


}

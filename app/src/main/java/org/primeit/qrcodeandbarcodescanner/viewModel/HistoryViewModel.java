package org.primeit.qrcodeandbarcodescanner.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.primeit.qrcodeandbarcodescanner.data.model.HistoryModel;
import org.primeit.qrcodeandbarcodescanner.data.repository.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    public HistoryRepository repository;
    public LiveData<List<HistoryModel>> getAllHistory;


    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        getAllHistory = repository.getAllHistory;
    }

    public void insert(HistoryModel history) {
        repository.insertHistory(history);
    }

    public void delete(HistoryModel historyModel) {
        repository.deleteHistory(historyModel);
    }

    public void update(HistoryModel history) {
        repository.insertHistory(history);
    }
}

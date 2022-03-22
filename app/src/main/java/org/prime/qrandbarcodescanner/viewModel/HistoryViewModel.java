package org.prime.qrandbarcodescanner.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.data.repository.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    public HistoryRepository repository;
    public LiveData<List<HistoryModel>> getAllHistory;


    public HistoryViewModel(@NonNull Application application) {
        super(application);

        repository = new HistoryRepository(application);
        getAllHistory = repository.getAllHistory;
    }

    public void insert(HistoryModel history){
        repository.insertHistory(history);
    }
    public void delete(int id){
        repository.deleteHistory(id);
    }
    public void update(HistoryModel history){
        repository.insertHistory(history);
    }
}

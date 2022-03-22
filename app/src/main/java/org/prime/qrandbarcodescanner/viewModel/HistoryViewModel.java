package org.prime.qrandbarcodescanner.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.prime.qrandbarcodescanner.model.History;
import org.prime.qrandbarcodescanner.repository.Repository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<History>> getAllHistory;


    public HistoryViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        getAllHistory = repository.getAllHistory;
    }

    public void insert(History history){
        repository.insertHistory(history);
    }
    public void delete(int id){
        repository.deleteHistory(id);
    }
    public void update(History history){
        repository.insertHistory(history);
    }
}

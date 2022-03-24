package org.prime.qrandbarcodescanner.listener;

import org.prime.qrandbarcodescanner.data.model.HistoryModel;

public interface HistoryListeners {

    void onDeleteClicked(HistoryModel historyModel, int position);
}

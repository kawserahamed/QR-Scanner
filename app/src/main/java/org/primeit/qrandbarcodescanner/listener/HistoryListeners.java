package org.primeit.qrandbarcodescanner.listener;

import org.primeit.qrandbarcodescanner.data.model.HistoryModel;

public interface HistoryListeners {

    void onDeleteClicked(HistoryModel historyModel, int position);
}

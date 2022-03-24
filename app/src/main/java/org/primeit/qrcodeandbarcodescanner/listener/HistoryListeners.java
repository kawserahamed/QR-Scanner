package org.primeit.qrcodeandbarcodescanner.listener;

import org.primeit.qrcodeandbarcodescanner.data.model.HistoryModel;

public interface HistoryListeners {

    void onDeleteClicked(HistoryModel historyModel, int position);
}

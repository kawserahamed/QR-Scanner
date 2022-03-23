package org.prime.qrandbarcodescanner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.prime.qrandbarcodescanner.R;
import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.lesteners.HistoryLesteners;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.historyViewHolder> {
    Context context;
    List<HistoryModel> historyList;
    HistoryLesteners historyLesteners;

    public HistoryAdapter(Context context, List<HistoryModel> historyList, HistoryLesteners historyLesteners) {
        this.context = context;
        this.historyList = historyList;
        this.historyLesteners = historyLesteners;
    }

    public HistoryAdapter() {
    }

    @NonNull
    @Override
    public historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new historyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyViewHolder holder, int position) {
        HistoryModel history = historyList.get(position);
        holder.url.setText(history.url);
        holder.date.setText(history.date);


        holder.deleteButton.setOnClickListener(view -> historyLesteners
                .onHistoryClicked(history, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class historyViewHolder extends RecyclerView.ViewHolder {
        TextView url;
        TextView date;
        ImageView deleteButton;

        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.textUrl);
            date = itemView.findViewById(R.id.textDate);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }
    }
}

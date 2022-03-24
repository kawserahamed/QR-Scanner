package org.prime.qrandbarcodescanner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.prime.qrandbarcodescanner.R;
import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.listener.HistoryListeners;
import org.prime.qrandbarcodescanner.ui.ResultActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.historyViewHolder> {
    Context context;
    List<HistoryModel> historyList;
    HistoryListeners historyListeners;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy, hh:mm a", Locale.getDefault());

    public HistoryAdapter(Context context, List<HistoryModel> historyList, HistoryListeners historyListeners) {
        this.context = context;
        this.historyList = historyList;
        this.historyListeners = historyListeners;
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
        holder.url.setText(history.data);
        holder.date.setText(dateFormat.format(history.createdAt));
        if (history.type.equals("QR_CODE")) {
            holder.codeType.setImageResource(R.drawable.qrcode);
        } else {
            holder.codeType.setImageResource(R.drawable.barcode);
        }

        holder.deleteButton.setOnClickListener(view -> historyListeners
                .onDeleteClicked(history, holder.getAdapterPosition()));

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, ResultActivity.class)
                .putExtra("result", history.data)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class historyViewHolder extends RecyclerView.ViewHolder {
        TextView url;
        TextView date;
        ImageView deleteButton;
        ImageView codeType;

        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.textUrl);
            date = itemView.findViewById(R.id.textDate);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            codeType = itemView.findViewById(R.id.codeType);

        }
    }
}

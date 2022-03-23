package org.prime.qrandbarcodescanner.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import org.prime.qrandbarcodescanner.R;
import org.prime.qrandbarcodescanner.data.model.HistoryModel;
import org.prime.qrandbarcodescanner.viewModel.HistoryViewModel;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.historyViewHolder> {
    Context context;
    List<HistoryModel> historyList;

    public HistoryAdapter(Context context, List<HistoryModel> historyList) {
        this.context = context;
        this.historyList = historyList;
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
        AlertDialog.Builder builder;
        HistoryViewModel historyViewModel;
        historyViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(HistoryViewModel.class);
        builder = new AlertDialog.Builder(context);
        HistoryModel history = historyList.get(position);
        holder.url.setText(history.url);
        holder.date.setText(history.date);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(holder.url.toString()));
        context.startActivity(intent);

        holder.deleteButton.setOnClickListener(view -> {

            builder.setMessage(R.string.are_you_sure)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes, (dialog, id) -> historyViewModel.delete(history.id))
                    .setNegativeButton(R.string.no, (dialog, id) -> {
                        dialog.cancel();
                    });
            AlertDialog alert = builder.create();
            alert.setTitle(R.string.delete);
            alert.show();
        });

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

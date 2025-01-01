package dev.chsr.passwordmanager;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder> {

    private List<PasswordItem> passwordList;

    public PasswordAdapter(List<PasswordItem> passwordList) {
        this.passwordList = passwordList;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.password_list_item, parent, false);
        return new PasswordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PasswordViewHolder holder, int position) {
        PasswordItem passwordItem = passwordList.get(position);
        holder.keyTextView.setText(passwordItem.getKey());
    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }

    public static class PasswordViewHolder extends RecyclerView.ViewHolder {
        TextView keyTextView;

        public PasswordViewHolder(View itemView) {
            super(itemView);
            keyTextView = itemView.findViewById(R.id.password_item_key);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), PasswordActivity.class);
                intent.putExtra("position", getAdapterPosition());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

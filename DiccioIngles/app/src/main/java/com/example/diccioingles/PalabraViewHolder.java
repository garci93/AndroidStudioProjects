package com.example.diccioingles;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PalabraViewHolder extends RecyclerView.ViewHolder{
    public PalabraViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(String palabra) {
        TextView textView = itemView.findViewById(R.id.textView);
        textView.setText(palabra);
    }
}

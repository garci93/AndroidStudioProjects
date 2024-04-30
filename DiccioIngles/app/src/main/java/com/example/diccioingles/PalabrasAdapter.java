package com.example.diccioingles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PalabrasAdapter extends RecyclerView.Adapter<PalabraViewHolder>{

    private List<String> palabras;

    public PalabrasAdapter(List<String> palabras) {
        this.palabras = palabras;
    }
    @NonNull
    @Override
    public PalabraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_consultar, parent, false);
        return new PalabraViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PalabraViewHolder holder, int position) {
        String palabra = palabras.get(position);
        holder.bind(palabra);
    }

    @Override
    public int getItemCount() {
        return palabras.size();
    }
}

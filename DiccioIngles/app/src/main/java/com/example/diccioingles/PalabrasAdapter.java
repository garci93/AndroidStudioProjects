package com.example.diccioingles;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PalabraViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

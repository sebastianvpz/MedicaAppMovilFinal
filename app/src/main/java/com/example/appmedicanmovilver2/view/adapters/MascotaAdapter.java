package com.example.appmedicanmovilver2.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmedicanmovilver2.databinding.ItemMascotasBinding;
import com.example.appmedicanmovilver2.retrofit.response.MascotaResponse;

import java.util.ArrayList;
import java.util.List;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.ViewHolder> {

    List<MascotaResponse> mascotaResponseList = new ArrayList<>();

    @NonNull
    @Override
    public MascotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMascotasBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaAdapter.ViewHolder holder, int position) {

        final MascotaResponse mascotaResponse = mascotaResponseList
                .get(position);
        holder.binding.tvNameMascota.setText(mascotaResponse.getNombre());
        holder.binding.tvEspecie.setText(mascotaResponse.getEspecie());
        holder.binding.tvEdad.setText(String.valueOf(mascotaResponse.getEdad()));
        holder.binding.tvSexo.setText(mascotaResponse.getSexo());

    }

    @Override
    public int getItemCount() {
        return mascotaResponseList.size();
    }

    public void setMascotas(List<MascotaResponse> mascotas){
        mascotaResponseList.clear();
        mascotaResponseList.addAll(mascotas);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMascotasBinding binding;
        public ViewHolder(ItemMascotasBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

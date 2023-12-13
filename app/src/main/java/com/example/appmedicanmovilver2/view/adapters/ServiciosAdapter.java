package com.example.appmedicanmovilver2.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmedicanmovilver2.databinding.ItemServiciosBinding;
import com.example.appmedicanmovilver2.retrofit.response.ServiciosResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ViewHolder> {

    List<ServiciosResponse> serviciosResponseList = new ArrayList<>();


    @NonNull
    @Override
    public ServiciosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemServiciosBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ServiciosAdapter.ViewHolder holder, int position) {
        final ServiciosResponse mascotaResponse = serviciosResponseList
                .get(position);
        holder.binding.tvServiceName.setText(mascotaResponse.getNombre());
        holder.binding.tvDescripcion.setText(mascotaResponse.getDescripcion());
        holder.binding.tvCosto.setText(String.valueOf(mascotaResponse.getCosto()));
        holder.binding.tvServiceHorario.setText(mascotaResponse.getHorario());
        Glide.with(holder.binding.getRoot())
                .load(mascotaResponse.getImagen())
                .into(holder.binding.ivServiceImage);
    }
    @Override
    public int getItemCount() {
        return serviciosResponseList.size();
    }

    public void setServicios(List<ServiciosResponse> servicios){
        serviciosResponseList.clear();
        serviciosResponseList.addAll(servicios);
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiciosBinding binding;
        public ViewHolder(ItemServiciosBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


}

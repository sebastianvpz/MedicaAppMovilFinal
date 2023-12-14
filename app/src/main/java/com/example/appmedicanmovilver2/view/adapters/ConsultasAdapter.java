package com.example.appmedicanmovilver2.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmedicanmovilver2.databinding.ItemConsultasBinding;
import com.example.appmedicanmovilver2.retrofit.response.ConsultaResponse;
import com.example.appmedicanmovilver2.retrofit.response.MascotaResponse;

import java.util.ArrayList;
import java.util.List;

public class ConsultasAdapter extends RecyclerView.Adapter<ConsultasAdapter.ViewHolder> {

    List<ConsultaResponse> consultaResponseList = new ArrayList<>();

    @NonNull
    @Override
    public ConsultasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemConsultasBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultasAdapter.ViewHolder holder, int position) {

        final ConsultaResponse consultaResponse = consultaResponseList
                .get(position);
        holder.binding.tvServicio.setText(String.valueOf(consultaResponse.getServicio().getNombre()));
        holder.binding.tvConsulDescripcion.setText(String.valueOf(consultaResponse.getDescripcion()));
        holder.binding.tvFecha.setText(String.valueOf(consultaResponse.getFecha()));
        holder.binding.tvMascota.setText(String.valueOf(consultaResponse.getMascota().getNombre()));
        holder.binding.tvRazon.setText(String.valueOf(consultaResponse.getRazon()));
        holder.binding.tvConsulDiagnostico.setText(String.valueOf(consultaResponse.getDiagnostico()));


    }

    @Override
    public int getItemCount() {
        return consultaResponseList.size();
    }

    public void setConsultas(List<ConsultaResponse> consultas){
        consultaResponseList.clear();
        consultaResponseList.addAll(consultas);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemConsultasBinding binding;
        public ViewHolder(ItemConsultasBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

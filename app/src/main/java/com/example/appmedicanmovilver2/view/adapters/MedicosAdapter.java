package com.example.appmedicanmovilver2.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmedicanmovilver2.databinding.ItemMedicosBinding;
import com.example.appmedicanmovilver2.retrofit.response.MedicosResponse;

import java.util.ArrayList;
import java.util.List;

public class MedicosAdapter extends RecyclerView.Adapter<MedicosAdapter.ViewHolder> {

    List<MedicosResponse> medicosResponseList = new ArrayList<>();

    @NonNull
    @Override
    public MedicosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMedicosBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicosAdapter.ViewHolder holder, int position) {
        final MedicosResponse medicosResponse = medicosResponseList
                .get(position);
        holder.binding.tvNombre.setText(medicosResponse.getNombre());
        holder.binding.tvApellido.setText(medicosResponse.getApellidos());
        holder.binding.tvEspecialidad.setText(medicosResponse.getEspecialidad());
        holder.binding.tvHorario.setText(medicosResponse.getHorario());
        Glide.with(holder.binding.getRoot())
                .load(medicosResponse.getImagen())
                .into(holder.binding.ivMedico);

    }

    @Override
    public int getItemCount() {
        return medicosResponseList.size();
    }

    public void setMedicos(List<MedicosResponse> medicos){
        medicosResponseList.clear();
        medicosResponseList.addAll(medicos);
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemMedicosBinding binding;
        public ViewHolder(ItemMedicosBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

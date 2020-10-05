package com.example.crudsql_lite.objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.crudsql_lite.R;
import objetos2.Dto;

import java.util.List;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ArticulosViewHolder>{
    private Context mCtx;
    private List<Dto> articulosList;

    public AdaptadorArticulos(Context mCtx, List<Dto> articulosList) {
        this.mCtx = mCtx;
        this.articulosList = articulosList;
    }

    @Override
    public ArticulosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_recyclerview, null);
        return new ArticulosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticulosViewHolder holder, int position) {
        Dto dto = articulosList.get(position);
        holder.textViewCodigo1.setText(String.valueOf(dto.getCodigo()));
        holder.textViewDescripcion1.setText(dto.getDescripcion());
        holder.textViewPrecio1.setText(String.valueOf(dto.getPrecio()));
        holder.textViewOtro.setText(String.valueOf("Registro #:" + (position+1)) + "/" +
                getItemCount());
    }

    @Override
    public int getItemCount() {
        return articulosList.size();
    }

    public static class ArticulosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodigo1, textViewDescripcion1, textViewPrecio1, textViewOtro;
        public ArticulosViewHolder(View itemView) {
            super(itemView);
            textViewCodigo1 = itemView.findViewById(R.id.textViewCodigo1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
            textViewPrecio1= itemView.findViewById(R.id.textViewPrecio1);
            textViewOtro = itemView.findViewById(R.id.textViewOtro);
//imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

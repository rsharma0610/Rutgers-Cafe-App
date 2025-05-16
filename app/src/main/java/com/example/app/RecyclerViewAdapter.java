package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Creates the adapter class to be used by the recycler viewer in the donut view
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<DonutModel> donutModels;
    public RecyclerViewAdapter(Context context, ArrayList<DonutModel> donutModels, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.donutModels = donutModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvFlavor.setText(donutModels.get(position).getFlavor());
        holder.tvType.setText(donutModels.get(position).getType());
        holder.tvPrice.setText(donutModels.get(position).getPrice());
        holder.imageView.setImageResource(donutModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return donutModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvFlavor, tvType, tvPrice;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvFlavor = itemView.findViewById(R.id.donutFlavor);
            tvType = itemView.findViewById(R.id.donutType);
            tvPrice = itemView.findViewById(R.id.donutPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

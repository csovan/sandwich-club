package com.udacity.sandwichclub.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.ui.activity.DetailActivity;
import com.udacity.sandwichclub.utils.GlideApp;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

import static com.udacity.sandwichclub.ui.activity.DetailActivity.EXTRA_POSITION;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichViewHolder> {

    private Context context;
    private List<Sandwich> sandwichList;

    public SandwichAdapter(Context context, List<Sandwich> sandwichList) {
        this.context = context;
        this.sandwichList = sandwichList;
    }

    @NonNull
    @Override
    public SandwichAdapter.SandwichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item sandwich layout
        return new SandwichViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_sandwich, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SandwichAdapter.SandwichViewHolder holder, final int position) {

        // Get sandwich picture
        GlideApp.with(context)
                .asBitmap()
                .load(sandwichList.get(position).getImage())
                .transform(new CenterCrop(), new RoundedCorners(16))
                .into(holder.imageViewSandwichPicture);

        // Get sandwich name
        if (!sandwichList.get(position).getMainName().trim().isEmpty()){
            holder.textViewSandwichName.setText(sandwichList.get(position).getMainName());
        }else {
            holder.textViewSandwichName.setText("");
        }

        // Get sandwich description
        if (!sandwichList.get(position).getDescription().trim().isEmpty()){
            holder.textViewSandwichDescription.setText(sandwichList.get(position).getDescription());
        }else {
            holder.textViewSandwichDescription.setText("");
        }

        // Get list item position and pass it onto detail activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(EXTRA_POSITION, holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sandwichList.size();
    }

    class SandwichViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewSandwichPicture;
        TextView textViewSandwichName;
        TextView textViewSandwichDescription;

        SandwichViewHolder(View itemView) {
            super(itemView);

            imageViewSandwichPicture = itemView.findViewById(R.id.image_view_sandwich_picture);
            textViewSandwichName = itemView.findViewById(R.id.text_view_sandwich_name);
            textViewSandwichDescription = itemView.findViewById(R.id.text_view_sandwich_description);

        }
    }
}

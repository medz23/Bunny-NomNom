package com.example.bunnynomnom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private ArrayList<MealItem> mealItems = new ArrayList<>();
    private Context context;

    public MealAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {

        holder.mealType.setText(mealItems.get(position).getMealType());
        holder.mealRatio.setText(mealItems.get(position).getMealRatio());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Deleted recipe" + position, Toast.LENGTH_SHORT).show();
                mealItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mealItems.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealItems.size();
    }

    public void setMealItems(ArrayList<MealItem> mealItems) {
        this.mealItems = mealItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealType, mealRatio;
        private ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealType = (TextView) itemView.findViewById(R.id.mealTypeId);
            mealRatio = (TextView) itemView.findViewById(R.id.ratioSizeId);
            deleteButton = (ImageView) itemView.findViewById(R.id.buttonDelete);
        }
    }
}

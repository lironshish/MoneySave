package com.example.moneysave.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface CategoryListener{
        void inputAmount(Goal category, int position);

    }

    private Activity activity;
    private ArrayList<Goal> categories = new ArrayList<>();
    private CategoryListener categoryListener;


    public Category_Adapter(Activity activity, ArrayList<Goal> categories) {
        this.activity = activity;
        this.categories = categories;
    }

    public void setCategoryListener(CategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category, parent, false);
        CategoryHolder holder = new CategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CategoryHolder holder = (CategoryHolder) viewHolder;
        Goal category = getCategory(position);

        holder.category_LBL_title.setText(category.getName());
        int resourceId = activity.getResources().getIdentifier(category.getImage(), "drawable", activity.getPackageName());
        holder.category_IMG_image.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public Goal getCategory(int position){
        return categories.get(position);
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        private MaterialTextView category_LBL_title;
        private AppCompatImageView category_IMG_image;
        private TextInputEditText category_EDT_amount;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            category_LBL_title = itemView.findViewById(R.id.category_LBL_title);
            category_IMG_image = itemView.findViewById(R.id.category_IMG_image);
            category_EDT_amount = itemView.findViewById(R.id.category_EDT_amount);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (categoryListener != null) {
                        categoryListener.inputAmount(getCategory(getAdapterPosition()), getAdapterPosition());
                    }
                }

        });
        }
    }
}
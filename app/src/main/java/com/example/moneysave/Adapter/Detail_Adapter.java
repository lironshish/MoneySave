package com.example.moneysave.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Objects.Detail;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Detail_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity activity;
    private ArrayList<Detail> details = new ArrayList<>();


    public Detail_Adapter(Activity activity, ArrayList<Detail> details) {
        this.activity = activity;
        this.details = details;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail, parent, false);
        DetailHolder holder = new DetailHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final DetailHolder holder = (DetailHolder) viewHolder;
        Detail detail = getDetail(position);

        holder.detail_TXT_description.setText(detail.getDescription());
        holder.detail_TXT_Account.setText((Integer)detail.getAmount());
        int resourceId = activity.getResources().getIdentifier(detail.getImage(), "drawable", activity.getPackageName());
        holder.detail_IMG_category.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public Detail getDetail(int position){
        return details.get(position);
    }

    class DetailHolder extends RecyclerView.ViewHolder {

        private MaterialTextView detail_TXT_description;
        private MaterialTextView detail_TXT_Account;
        private AppCompatImageView detail_IMG_category;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            detail_TXT_description = itemView.findViewById(R.id.detail_TXT_description);
            detail_TXT_Account = itemView.findViewById(R.id.detail_TXT_Account);
            detail_IMG_category = itemView.findViewById(R.id.detail_IMG_category);


        }
    }
}
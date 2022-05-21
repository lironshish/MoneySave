package com.example.moneysave.Adapter;

import android.app.Activity;
import android.os.FileUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Goal_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public interface GoalListener{
        void clicked(Goal goal, int position);
    }

    private Activity activity;
    private ArrayList<Goal> goals = new ArrayList<>();
    private Goal_Adapter.GoalListener goalListener;


    public Goal_Adapter(Activity activity, ArrayList<Goal> goals) {
        this.activity = activity;
        this.goals = goals;
    }

    public void setGoalListener(Goal_Adapter.GoalListener goalListener) {
        this.goalListener = goalListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_goal, parent, false);
        GoalHolder holder = new GoalHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final GoalHolder holder = (GoalHolder) viewHolder;
        Goal goal = getGoal(position);

        holder.goal_TXT_category.setText(goal.getName());
        holder.goal_TXT_progressbar.setText("lolo");
        holder.goal_progressBar.setProgress(goal.getMoneyWested()/goal.getMoneyPerMonth());

        int resourceId = activity.getResources().getIdentifier(goal.getImage(), "drawable", activity.getPackageName());
        holder.goal_IMG_pic.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public Goal getGoal(int position){
        return goals.get(position);
    }

class GoalHolder extends RecyclerView.ViewHolder {

    private MaterialTextView goal_TXT_progressbar;
    private ProgressBar goal_progressBar;
    private MaterialTextView goal_TXT_category;
    private ImageView goal_IMG_pic;


    public GoalHolder(@NonNull View itemView) {
        super(itemView);
        goal_TXT_progressbar = itemView.findViewById(R.id.goal_TXT_progressbar);
        goal_progressBar = itemView.findViewById(R.id.goal_progressBar);
        goal_TXT_category = itemView.findViewById(R.id.goal_TXT_category);
        goal_IMG_pic = itemView.findViewById(R.id.goal_IMG_pic);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goalListener != null) {
                   goalListener.clicked(getGoal(getAdapterPosition()), getAdapterPosition());
                }
            }

        });
    }
}
}
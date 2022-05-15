package com.example.moneysave.Acivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class AddGoalActivity extends AppCompatActivity {

    private EditText enter_goal_name;
    private EditText enter_money;
    private MaterialButton submit_add_account;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        findViews();
        InitButtons();
        InitAppBar();
    }

    public void findViews(){
        enter_goal_name = findViewById(R.id.enter_goal_name);
        enter_money = findViewById(R.id.enter_money);
        toolbar = findViewById(R.id.toolbar);
    }

    public void InitButtons(){
        submit_add_account = findViewById(R.id.submit_add_account);
        submit_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goal newGoal = addGoal();
                //TODO: Save newGoal on DATABASE
                backToAccountActivity();
            }
        });
    }
    public Goal addGoal(){
        Goal newGoal = new Goal(enter_goal_name.getText().toString(),Integer.valueOf(enter_money.getText().toString()));
        return newGoal;
    }

    private void backToAccountActivity(){
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    private void InitAppBar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
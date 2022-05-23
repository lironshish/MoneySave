package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddGoalActivity extends AppCompatActivity {

    private EditText enter_goal_name;
    private EditText enter_money;
    private MaterialButton submit_add_account;
    private MaterialToolbar toolbar;
    private FloatingActionButton fab_return;


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
        fab_return = findViewById(R.id.fab_return);

    }

    public void InitButtons(){
        submit_add_account = findViewById(R.id.submit_add_account);
        submit_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Goal newGoal = addGoal();
                backToAccountActivity();
            }
        });

        fab_return.setOnClickListener(view -> {
            Intent myIntent = new Intent(AddGoalActivity.this, AccountActivity.class);
            AddGoalActivity.this.startActivity(myIntent);
            AddGoalActivity.this.finish();
        });
    }
    public Goal addGoal(){
        Goal newGoal = new Goal(enter_goal_name.getText().toString(),Integer.valueOf(enter_money.getText().toString()));
        DataManager.getDataManager().getActiveAccount().addCategory(newGoal.setImage("ic_default"));
        MyServices.getInstance().makeToast(enter_goal_name.getText().toString() + " goal saved");
        return newGoal;
    }

    private void backToAccountActivity(){
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent( AddGoalActivity.this ,AccountActivity.class));
        finish();
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
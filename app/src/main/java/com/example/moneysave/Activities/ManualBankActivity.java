package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneysave.Objects.Detail;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManualBankActivity extends AppCompatActivity {

    private ImageButton manual_FAB_balance;
    private ImageButton manual_FAB_revenue;
    private ImageButton manual_FAB_expenses;
    private MaterialToolbar manual_toolbar;

    private boolean popUpRevenuOpen = false;
    private boolean popUpExpenseOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        findViews();
        InitButtons();
        InitToolBar();
    }

    private void findViews(){
        manual_toolbar = findViewById(R.id.manual_toolbar);
    }

    private void InitButtons(){
        manual_FAB_balance = findViewById(R.id.manual_FAB_balance);
        manual_FAB_revenue = findViewById(R.id.manual_FAB_revenue);
        manual_FAB_expenses = findViewById(R.id.manual_FAB_expenses);


        manual_FAB_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        manual_FAB_revenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   addPopUpWindowRevenue(view);
            }
        });

        manual_FAB_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    addPopUpWindowExpense(view);
            }
        });
    }

    private void addPopUpWindowExpense(View view){
        popUpExpenseOpen = true;
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_expenses, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        EditText title = popupView.findViewById(R.id.title);
        EditText howMuch = popupView.findViewById(R.id.how_much);
        Spinner spinner = popupView.findViewById(R.id.spinner);
        FloatingActionButton fab_add= popupView.findViewById(R.id.fab_add_expense);
        addCategorySpinner(spinner);

        fab_add.setOnClickListener(view1 -> {
            String amount = howMuch.getText().toString();
            String expenseTitle = title.getText().toString();
            String category = spinner.getSelectedItem().toString();
            if(expenseTitle.isEmpty()) {
                MyServices.getInstance().makeToast("Title cannot be empty!");
                return;
            }
            if(amount.isEmpty()){
                MyServices.getInstance().makeToast("Amount cannot be empty!");
                return;
            }
            if(category.isEmpty() || category.equals("Categoty")){
                MyServices.getInstance().makeToast("Must choose a categoty ");
                return;
            }
            Detail detail = new Detail().setAmount(Float.parseFloat(amount)).setDescription(expenseTitle).setCategory(new Goal().setName(category));
            DataManager.getDataManager().addCategoryDetail(DataManager.getDataManager().getActiveAccount(), detail.getCategory(), detail);
            popupWindow.dismiss();
        });

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener((view1, motionEvent) -> {
            popUpExpenseOpen = false;
            popupWindow.dismiss();
            return true;
        });
    }

    private void addPopUpWindowRevenue(View view){
        popUpRevenuOpen = true;
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_revenue, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        EditText title = popupView.findViewById(R.id.title);
        EditText howMuch = popupView.findViewById(R.id.how_much);
        FloatingActionButton fab_add= popupView.findViewById(R.id.fab_add_revenue);

        fab_add.setOnClickListener(view1 -> {
            String amount = howMuch.getText().toString();
            String expenseTitle = title.getText().toString();
            if(expenseTitle.isEmpty()) {
                MyServices.getInstance().makeToast("Title cannot be empty!");
                return;
            }
            if(amount.isEmpty()){
                MyServices.getInstance().makeToast("Amount cannot be empty!");
                return;
            }

            Detail detail = new Detail().setAmount(Float.parseFloat(amount)).setDescription(expenseTitle).setCategory(new Goal().setName("Revenue"));
            //TODO 21/5/2022 : send Detail Object!
        });




        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener((view1, motionEvent) -> {
            popUpRevenuOpen = false;
            popupWindow.dismiss();
            return true;
        });
    }

    private void addCategorySpinner(Spinner spinner){

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Category");
        categories.add("Food");
        categories.add("Leisure and recreation");
        categories.add("Car");
        categories.add("Apartment");
        categories.add("Clothing and footwear");
        categories.add("Various expenses");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void InitToolBar(){
        manual_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
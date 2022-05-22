package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.example.moneysave.Objects.Detail;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ManualBankActivity extends AppCompatActivity {

    private ImageButton manual_FAB_balance;
    private ImageButton manual_FAB_revenue;
    private ImageButton manual_FAB_expenses;
    private MaterialTextView manual_TXT_revenues;
    private MaterialTextView manual_TXT_expenses;
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

    private void findViews() {
        manual_toolbar = findViewById(R.id.manual_toolbar);
    }

    private void InitButtons() {
        manual_FAB_balance = findViewById(R.id.manual_FAB_balance);
        manual_FAB_revenue = findViewById(R.id.manual_FAB_revenue);
        manual_FAB_expenses = findViewById(R.id.manual_FAB_expenses);
        manual_TXT_expenses = findViewById(R.id.manual_TXT_expenses);
        manual_TXT_revenues = findViewById(R.id.manual_TXT_revenues);

        manual_FAB_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManualBankActivity.this, DetailsActivity.class));
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

    private void addPopUpWindowExpense(View view) {
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
        FloatingActionButton fab_add = popupView.findViewById(R.id.fab_add_expense);
        addCategorySpinner(spinner);

        fab_add.setOnClickListener(view1 -> {
            String amount = howMuch.getText().toString();
            String expenseTitle = title.getText().toString();
            String category = spinner.getSelectedItem().toString();
            if (expenseTitle.isEmpty()) {
                MyServices.getInstance().makeToast("Title cannot be empty!");
                return;
            }
            if (amount.isEmpty()) {
                MyServices.getInstance().makeToast("Amount cannot be empty!");
                return;
            }
            if (category.isEmpty() || category.equals("Category")) {
                MyServices.getInstance().makeToast("Must choose a category ");
                return;
            }
            MyServices.getInstance().makeToast("your update complete");
            Goal real_category = getCategoryByName(category);
            real_category.setMoneyWested(real_category.getMoneyWested()+Integer.parseInt(amount));
            Detail detail = new Detail().setAmount(Float.parseFloat(amount)).setDescription(expenseTitle).setCategory(category);
            DataManager.getDataManager().addCategoryDetail(DataManager.getDataManager().getActiveAccount(), real_category, detail);
            DataManager.getDataManager().getActiveBankAccount().getDetails().add(detail);
            DataManager.getDataManager().updateAccountInfo(DataManager.getDataManager().getActiveAccount());
            DataManager.getDataManager().getActiveBankAccount().setExpenses(DataManager.getDataManager().getActiveBankAccount().getExpenses()+detail.getAmount());
            //manual_TXT_expenses.setText((int) DataManager.getDataManager().getActiveBankAccount().getExpenses());

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

    private Goal getCategoryByName(String category) {
        ArrayList<Goal> real_categories = DataManager.getDataManager().getAccountCategories(DataManager.getDataManager().getActiveAccount());
        for (int i = 0; i < real_categories.size(); i++) {
            if( real_categories.get(i).getName().equals(category))
                return real_categories.get(i);
        }
        return null;
    }

    private void addPopUpWindowRevenue(View view) {
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
        FloatingActionButton fab_add = popupView.findViewById(R.id.fab_add_revenue);

        fab_add.setOnClickListener(view1 -> {
            String amount = howMuch.getText().toString();
            String expenseTitle = title.getText().toString();
            if (expenseTitle.isEmpty()) {
                MyServices.getInstance().makeToast("Title cannot be empty!");
                return;
            }
            if (amount.isEmpty()) {
                MyServices.getInstance().makeToast("Amount cannot be empty!");
                return;
            }
            Goal real_category = getCategoryByName("Revenue");
            MyServices.getInstance().makeToast("your update complete");
            Detail detail = new Detail().setAmount(Float.parseFloat(amount)).setDescription(expenseTitle).setCategory("Revenue");
            DataManager.getDataManager().addCategoryDetail(DataManager.getDataManager().getActiveAccount(),real_category , detail);
            DataManager.getDataManager().getActiveBankAccount().getDetails().add(detail);
            DataManager.getDataManager().updateAccountInfo(DataManager.getDataManager().getActiveAccount());
            DataManager.getDataManager().getActiveBankAccount().setExpenses(DataManager.getDataManager().getActiveBankAccount().getRevenues()+detail.getAmount());
            //manual_TXT_revenues.setText(DataManager.getDataManager().getActiveBankAccount().getRevenues());
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

    private void addCategorySpinner(Spinner spinner) {

        // Spinner Drop down elements
          ArrayList<Goal> real_categories = DataManager.getDataManager().getAccountCategories(DataManager.getDataManager().getActiveAccount());
          ArrayList<String> categories = new ArrayList<>();
            categories.add("Category");
            real_categories.forEach(c -> categories.add(c.getName()));
//        new ArrayList<String>();
//        categories.add("Category");
//        categories.add("Food");
//        categories.add("Leisure and recreation");
//        categories.add("Car");
//        categories.add("Apartment");
//        categories.add("Clothing and footwear");
//        categories.add("Various expenses");


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

    private void InitToolBar() {
        manual_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
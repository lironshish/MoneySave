//package com.example.moneysave.Acivities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.Spinner;
//
//import com.example.moneysave.R;
//import com.google.android.material.appbar.MaterialToolbar;
//import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ManualBankActivity extends AppCompatActivity {
//
//    private ExtendedFloatingActionButton manual_FAB_balance;
//    private ExtendedFloatingActionButton manual_FAB_revenue;
//    private ExtendedFloatingActionButton manual_FAB_expenses;
//    private MaterialToolbar manual_toolbar;
//
//    private boolean popUpRevenuOpen = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_manual_bank);
//
//        findViews();
//        InitButtons();
//    }
//
//    private void findViews(){
//        manual_toolbar = findViewById(R.id.manual_toolbar);
//    }
//
//    private void InitButtons(){
//        manual_FAB_balance = findViewById(R.id.manual_FAB_balance);
//        manual_FAB_revenue = findViewById(R.id.manual_FAB_revenue);
//        manual_FAB_expenses = findViewById(R.id.manual_FAB_expenses);
//
//
//        manual_FAB_balance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addCategorySpinner();
//            }
//        });
//
//
//
//        manual_FAB_revenue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               if(!popUpRevenuOpen) {
//                   addCategorySpinner();
//                   addPopUpWindowRevenue(view);
//               }
//            }
//        });
//
//
//
//        manual_FAB_expenses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addCategorySpinner();
//            }
//        });
//    }
//
//
//
//    private void addPopUpWindowRevenue(View view){
//        popUpRevenuOpen = true;
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_revenue, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//    }
//
//    private void addCategorySpinner(){
//        // Spinner element
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        // Spinner click listener
//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
//
//        // Spinner Drop down elements
//        List<String> categories = new ArrayList<String>();
//        categories.add("Food");
//        categories.add("Leisure and recreation");
//        categories.add("Car");
//        categories.add("Apartment");
//        categories.add("Clothing and footwear");
//        categories.add("Various expenses");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);
//    }
//
//    private void InitToolBar(){
//        manual_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }
//}
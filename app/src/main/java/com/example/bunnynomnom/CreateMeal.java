package com.example.bunnynomnom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateMeal extends AppCompatActivity {
    Spinner spinnerVeg;
    Spinner spinnerQ;
    FloatingActionButton floatingActionButton;

    String[] vegetables = {"Select Vegetable", "Daily", "Bell peppers", "Bok choy",
            "Brussels sprouts", "Carrot tops", "Cucumber", " Endive", "Escarole", "Fennel",
            "Okra leaves", "Radicchio", "Radish tops", "  Watercress", "Wheatgrass", " Zucchini",
            "Herbs:", " - basil", " - cilantro", " - dill", " - mint", " - oregano", " - parsley", " - rosemary", " - sage", " - thyme",
            "Lettuces:", " - romaine", " - green leaf", " - red leaf", " - Boston bibb", " - butter",
            "Sprouts:", " - alfalfa", " - radish", " - clover"};

    String[] quantity = {"Quantity", "1 piece", "200 g", "100 g", "50 g", "3 g"};

    private RecyclerView mealRecyclerView;
    private RelativeLayout relativeLayout;
    ArrayList<MealItem> mealItems = new ArrayList<>();
    ArrayAdapter<String> adapterMeal;
    ArrayAdapter<String> adapterQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        mealRecyclerView = findViewById(R.id.recyclerFoodie);
        relativeLayout = findViewById(R.id.recyRel);
        spinnerVeg = findViewById(R.id.spinnerMancare);
        floatingActionButton = findViewById(R.id.flotingAddBt);
        spinnerQ = findViewById(R.id.spinnerCantitati);

        String vegPos = "";
        String qtyPos = "";
        vegSpinner();
        quantitySpinner();



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String veg = spinnerVeg.getSelectedItem().toString();
                String qty = spinnerQ.getSelectedItem().toString();

                mealItems.add(new MealItem(qty, veg));

                MealAdapter recAdapter = new MealAdapter(CreateMeal.this);
                recAdapter.setMealItems(mealItems);
                mealRecyclerView.setAdapter(recAdapter);


            }
        });

        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void vegSpinner() {
        adapterMeal = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, vegetables);
        spinnerVeg.setAdapter(adapterMeal);

    }

    public void quantitySpinner() {


        adapterQuantity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, quantity);
        spinnerQ.setAdapter(adapterQuantity);
    }
}
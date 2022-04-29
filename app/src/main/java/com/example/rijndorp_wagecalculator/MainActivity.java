package com.example.rijndorp_wagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button calculate, goBack;
    EditText name, hours;
    TextView calcName, calcHours, calcWage, allWage, otWage, otHours;

    double hourlyWage, totalWage, totalHours, overTimeHours, overTimeWage, otwage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        spinner = findViewById(R.id.spinner1);
        //Input
        calculate = findViewById(R.id.calculateButton);
        name = findViewById(R.id.editName);
        hours = findViewById(R.id.editHours);

        //Output
        calculate.setOnClickListener(view -> nextLayout());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.worktype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);

    }
    public void nextLayout () {
        setContentView(R.layout.calculate);
        totalHours = Double.parseDouble(hours.getText().toString());
        if (totalHours > 8) {
            overTimeHours = totalHours - 8;
        }
        else {
            overTimeHours = 0;
        }


        otwage = (overTimeHours*overTimeWage);
        totalWage = (totalHours*hourlyWage) + (overTimeHours*overTimeWage) - ((totalHours-8)*hourlyWage);
        String doubleTotalWage = Double.toString(totalWage);
        String doubleOTHours = Double.toString(overTimeHours);
        String doubleOTWage = Double.toString(otwage);
        //Button
        goBack = findViewById(R.id.goBackBTN);
        goBack.setOnClickListener(view -> mainActivity());
        //TextView
        calcName = findViewById(R.id.calcName);
        calcHours = findViewById(R.id.calcHours);
        calcWage = findViewById(R.id.totalWage);
        otHours = findViewById(R.id.otHours);
        otWage = findViewById(R.id.otWage);

        //SetText
        calcName.setText(name.getText());
        calcHours.setText(hours.getText());
        calcWage.setText(doubleTotalWage);
        otHours.setText(doubleOTHours);
        otWage.setText(doubleOTWage);
    }
    public void mainActivity () {
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        spinner = findViewById(R.id.spinner1);
        //Input
        calculate = findViewById(R.id.calculateButton);
        name = findViewById(R.id.editName);
        hours = findViewById(R.id.editHours);

        //Output
        calculate.setOnClickListener(view -> nextLayout());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.worktype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text, Toast.LENGTH_SHORT);

        switch(i) {
            case 0:
                hourlyWage = 90;
                overTimeWage = 100;
                break;
            case 1:
                hourlyWage = 75;
                overTimeWage = 90;
                break;
            case 2:
                hourlyWage = 100;
                overTimeWage = 115;
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        hourlyWage = 90;
        overTimeWage = 100;
    }
}
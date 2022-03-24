package pl.edu.pwr.lab1_1.i269691;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    Double bmi;
    public BMIViewModel model;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SIZE = "SizeArray";
    public static final String ARR = "myArray";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(BMIViewModel.class);
        LoadData();
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();
        if (getIntent().hasExtra("bmi")) {
            double bmi = getIntent().getDoubleExtra("bmi",0);
        }
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView5.setText(' '+Double.toString(model.bmis[model.i-1]));
        if (model.bmis[model.i-1] != 0){
            if (model.bmis[model.i-1] < 18.5) {
                textView6.setText("Underweight");
                textView6.setTextColor(Color.YELLOW);
            } else if (model.bmis[model.i-1] >= 18.5 && model.bmis[model.i-1] < 25) {
                textView6.setText("Healthy");
                textView6.setTextColor(Color.GREEN);
            } else if (model.bmis[model.i-1] >= 25 && model.bmis[model.i-1] < 30){
                textView6.setText("Overweight");
                textView6.setTextColor(Color.YELLOW);
            } else {
                textView6.setText("Obese");
                textView6.setTextColor(Color.RED);
            }
        }

    }
    public void LoadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        int s = sharedPreferences.getInt(SIZE,0);
        model.i = sharedPreferences.getInt("Iterator",0);
        if (s==0) return;
        for(int i = 0; i<10;i++) {
            model.bmis[i] = sharedPreferences.getFloat(ARR+i,0);
        }
    }
    protected void onStart() {

        super.onStart();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.back:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
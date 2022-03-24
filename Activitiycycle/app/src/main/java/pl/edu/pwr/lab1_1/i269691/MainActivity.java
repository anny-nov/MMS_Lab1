package pl.edu.pwr.lab1_1.i269691;

import static pl.edu.pwr.lab1_1.i269691.BMI_Counter.BMI_CATEGORY_HEALTHY;
import static pl.edu.pwr.lab1_1.i269691.BMI_Counter.BMI_CATEGORY_OBESE;
import static pl.edu.pwr.lab1_1.i269691.BMI_Counter.BMI_CATEGORY_OVERWEIGHT;
import static pl.edu.pwr.lab1_1.i269691.BMI_Counter.BMI_CATEGORY_UNDERWEIGHT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashSet;


public class MainActivity extends AppCompatActivity {
    boolean metric = true;
    Button button;
    private EditText HeightInput;
    private EditText MassInput;
    private TextView BMIRes;
    private TextView MassLb;
    private TextView HeightInch;
    private TextView textView2;
    private TextView textView8;
    public BMIViewModel model;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SIZE = "SizeArray";
    public static final String ARR = "myArray";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider(this).get(BMIViewModel.class);
        Log.d("TAG", "The onCreate() event is started");
        button = (Button) findViewById(R.id.button);
        BMIRes = (TextView) findViewById(R.id.BMIRes);
        MassLb = (TextView) findViewById(R.id.MassLb);
        HeightInch = (TextView) findViewById(R.id.HeightInch);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView8 = (TextView) findViewById(R.id.textView8);
        HeightInput = (EditText) findViewById(R.id.HeightInput);
        MassInput = (EditText) findViewById(R.id.MassInput);
        HeightInch.setVisibility(View.GONE);
        MassLb.setVisibility(View.GONE);
        if(model.current == 0)
            BMIRes.setVisibility(View.GONE);
        else
            displayBMI(model.current);
        LoadData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.imp:
                textView2.setVisibility(View.INVISIBLE);
                textView8.setVisibility(View.INVISIBLE);
                MassLb.setVisibility(View.VISIBLE);
                HeightInch.setVisibility(View.VISIBLE);
                metric = false;
                return true;
            case R.id.met:
                MassLb.setVisibility(View.INVISIBLE);
                HeightInch.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView8.setVisibility(View.VISIBLE);
                metric = true;
                return true;
            case R.id.auth:
                Intent intent = new Intent(this,AboutAuthor.class);
                startActivity(intent);
                return true;
            case R.id.his:
                Intent intent3 = new Intent(this,HistoryActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        button.setOnClickListener(view -> {
                    if (HeightInput.length() == 0 || MassInput.length() == 0) {
                        Toast.makeText(MainActivity.this, "Enter Weight and Height to Calculate BMI", Toast.LENGTH_SHORT).show();
                    } else {
                        model.height = Double.parseDouble(HeightInput.getText().toString());
                        model.weight = Double.parseDouble(MassInput.getText().toString());
                        if (model.height <= 0 || model.weight <= 0)
                            Toast.makeText(MainActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
                        else {
                            if (metric) {
                                model.Count_Metric();
                                displayBMI(model.current);
                            } else {
                                model.Count_Imperial();
                                displayBMI(model.current);
                            }
                            SaveData();

                        }
                    }

            });
        BMIRes.setOnTouchListener((view2, motionEvent2) -> {
            Intent intent2 = new Intent(MainActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("bmi",model.current);
            intent2.putExtras(bundle);
            startActivity(intent2);

            return false;
        });
        Log.d("TAG", "The onStart() event is started");
    }
    public void SaveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Iterator",model.i);
        editor.putInt(SIZE,model.bmis.length);
        for(int i=0;i<model.bmis.length;i++)
            editor.putFloat(ARR+i,model.bmis[i]);
        editor.apply();
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
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "The onPause() event is started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "The onStop() event is started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "The onDestroy() event is started");
    }
    private void displayBMI(double bmi) {
        BMIRes.setVisibility(View.VISIBLE);


        BMIRes.setText(String.format("%.2f", bmi));

        String bmiCategory = BMI_Counter.getInstance().classifyBMI(bmi);
        //categoryTextView.setText(bmiCategory);

        switch (bmiCategory) {
            case BMI_CATEGORY_UNDERWEIGHT:
                BMIRes.setTextColor(Color.YELLOW);
                break;
            case BMI_CATEGORY_HEALTHY:
                BMIRes.setTextColor(Color.GREEN);
                break;
            case BMI_CATEGORY_OVERWEIGHT:
                BMIRes.setTextColor(Color.YELLOW);
                break;
            case BMI_CATEGORY_OBESE:
                BMIRes.setTextColor(Color.RED);
                break;
        }
    }
}



package pl.edu.pwr.lab1_1.i269691;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    public BMIViewModel model;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SIZE = "SizeArray";
    public static final String ARR = "myArray";

    TextView bmi1;
    String history = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        model = new ViewModelProvider(this).get(BMIViewModel.class);
        LoadData();
        bmi1 = (TextView) findViewById(R.id.bmi1);
        for(int i = 0;i<model.bmis.length;i++)
        {
            history = history + model.bmis[i] + "\n";
        }
        bmi1.setText(history);
    }

    public void LoadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int s = sharedPreferences.getInt(SIZE, 0);
        model.i = sharedPreferences.getInt("Iterator", 0);
        if (s == 0) return;
        for (int i = 0; i < 10; i++) {
            model.bmis[i] = sharedPreferences.getFloat(ARR + i, 0);
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
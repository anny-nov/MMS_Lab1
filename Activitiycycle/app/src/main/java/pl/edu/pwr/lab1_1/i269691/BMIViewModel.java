package pl.edu.pwr.lab1_1.i269691;

import androidx.lifecycle.ViewModel;

public class BMIViewModel extends ViewModel {
    float[] bmis = new float[10];
    public double current = 0;
    public double height;
    public double weight;
    int i = 0;
    public void Count_Metric(){
        current = BMI_Counter.getInstance().calculateBMIMetric(height, weight);
        Add();
        i++;
    }
    public void Count_Imperial(){
        current = BMI_Counter.getInstance().calculateBMIImperial(height, weight);
        Add();
        i++;
    }
    private void Add(){
        if(i<9) {
            bmis[i] = (float)current;
        }
        else {
            for (int j = 0; j<9;j++)
            {
                bmis[j] = bmis[j+1];
            }
            bmis[9] = (float)current;
        }
    }
}

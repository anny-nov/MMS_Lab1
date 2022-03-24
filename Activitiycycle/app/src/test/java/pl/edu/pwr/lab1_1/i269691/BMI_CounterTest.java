package pl.edu.pwr.lab1_1.i269691;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class BMI_CounterTest extends TestCase {


    public void tearDown() throws Exception {
    }

    @Test
    public void testCalculateBMIMetric() {
        int mass = 68; int height = 163;
        double bmi = BMI_Counter.getInstance().calculateBMIMetric(height,mass);
        assertEquals(25.594,bmi,0.001);
    }

    @Test
    public void testCalculateBMIImperial() {
        int mass = 150; int height = 64;
        double bmi = BMI_Counter.getInstance().calculateBMIImperial(height,mass);
        assertEquals(25.745,bmi,0.001);
    }

    @Test
    public void testClassifyBMI() {
        double bmi1 = 16.7, bmi2 = 19.8, bmi3 = 25.6, bmi4 = 32;
        assertEquals("Underweight",BMI_Counter.getInstance().classifyBMI(bmi1));
        assertEquals("Healthy Weight Range",BMI_Counter.getInstance().classifyBMI(bmi2));
        assertEquals("Overweight",BMI_Counter.getInstance().classifyBMI(bmi3));
        assertEquals("Obese",BMI_Counter.getInstance().classifyBMI(bmi4));
    }
}
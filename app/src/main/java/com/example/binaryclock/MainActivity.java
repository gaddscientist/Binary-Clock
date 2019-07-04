package com.example.binaryclock;

// Default imports
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// Additional imports
import android.widget.TextClock;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView clockDisplay;
    private TextClock textClock;
    private String hourTens;
    private String hourOnes;
    private String minuteTens;
    private String minuteOnes;
    private String secondTens;
    private String secondOnes;
    private SimpleDateFormat hoursSdf = new SimpleDateFormat("hh");
    private SimpleDateFormat minutesSdf = new SimpleDateFormat("mm");
    private SimpleDateFormat secondsSdf = new SimpleDateFormat("ss");
    private Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Pulls the current time
        currentTime = Calendar.getInstance().getTime();
        // Assigns objects to their corresponding views
        textClock = findViewById(R.id.textClock);
        clockDisplay = findViewById(R.id.textView);


        hourTens = decimalToBinary(Integer.toString(Integer.parseInt((hoursSdf.format(currentTime.getTime()))) / 10));
        hourOnes = decimalToBinary(Integer.toString(Integer.parseInt((hoursSdf.format(currentTime.getTime()))) % 10));
        minuteTens = decimalToBinary(Integer.toString(Integer.parseInt((minutesSdf.format(currentTime.getTime()))) / 10));
        minuteOnes = decimalToBinary(Integer.toString(Integer.parseInt((minutesSdf.format(currentTime.getTime()))) % 10));
        secondTens = decimalToBinary(Integer.toString(Integer.parseInt((secondsSdf.format(currentTime.getTime()))) / 10));
        secondOnes = decimalToBinary(Integer.toString(Integer.parseInt((secondsSdf.format(currentTime.getTime()))) % 10));


        // DELETE
        printTest(hourTens);
        printTest(hourOnes);
        printTest(minuteTens);
        printTest(minuteOnes);
        printTest(secondTens);
        printTest(secondOnes);


        int test = 171;
        // Sets the text view to the concatenation of the hour, minute, and second strings
        // clockDisplay.setText(hour + " " + minute + " " + second);
        clockDisplay.setText(decimalToBinary(Integer.toString(test)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Function to convert a decimal number to a 4 digit binary number
    private String decimalToBinary(String decimal) {
        String binary = "";
        int remainder;
        int dec = Integer.parseInt(decimal);

        if (dec == 0) { return "0000";}

        while (dec != 0) {
          remainder = dec % 2;
          dec = dec / 2;
          binary = remainder + binary;
        }

        while (binary.length() < 4) {
            binary = "0" + binary;
        }

        return binary;
    }

    // DELETE
    private void printTest(String string){
        System.out.println("Binary String: " + string);
    }
}

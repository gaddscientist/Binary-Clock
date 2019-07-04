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

import de.hdodenhof.circleimageview.CircleImageView;

// TODO: FORCE ORIENTATION
public class MainActivity extends AppCompatActivity {
    private TextView clockDisplay;
    private TextClock textClock;
    private String hourTens;
    private String hourOnes;
    private String minuteTens;
    private String minuteOnes;
    private String secondTens;
    private String secondOnes;
    private SimpleDateFormat hours12Sdf = new SimpleDateFormat("hh");
    private SimpleDateFormat hours24Sdf = new SimpleDateFormat("HH");
    private SimpleDateFormat minutesSdf = new SimpleDateFormat("mm");
    private SimpleDateFormat secondsSdf = new SimpleDateFormat("ss");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    private Date currentTime;

    private CircleImageView ht1;
    private CircleImageView ht2;
    private CircleImageView ho1;
    private CircleImageView ho2;
    private CircleImageView ho3;
    private CircleImageView ho4;

    private CircleImageView mt1;
    private CircleImageView mt2;
    private CircleImageView mt3;
    private CircleImageView mo1;
    private CircleImageView mo2;
    private CircleImageView mo3;
    private CircleImageView mo4;

    private CircleImageView st1;
    private CircleImageView st2;
    private CircleImageView st3;
    private CircleImageView so1;
    private CircleImageView so2;
    private CircleImageView so3;
    private CircleImageView so4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       ht1 = findViewById(R.id.hour_tens_1);
       ht2 = findViewById(R.id.hour_tens_2);
       ho1 = findViewById(R.id.hour_ones_1);
       ho2 = findViewById(R.id.hour_ones_2);
       ho3 = findViewById(R.id.hour_ones_3);
       ho4 = findViewById(R.id.hour_ones_4);

       mt1 = findViewById(R.id.minute_tens_1);
       mt2 = findViewById(R.id.minute_tens_2);
       mt3 = findViewById(R.id.minute_tens_3);
       mo1 = findViewById(R.id.minute_ones_1);
       mo2 = findViewById(R.id.minute_ones_2);
       mo3 = findViewById(R.id.minute_ones_3);
       mo4 = findViewById(R.id.minute_ones_4);

       st1 = findViewById(R.id.second_tens_1);
       st2 = findViewById(R.id.second_tens_2);
       st3 = findViewById(R.id.second_tens_3);
       so1 = findViewById(R.id.second_ones_1);
       so2 = findViewById(R.id.second_ones_2);
       so3 = findViewById(R.id.second_ones_3);
       so4 = findViewById(R.id.second_ones_4);

        // Pulls the current time
        currentTime = Calendar.getInstance().getTime();
        // Assigns objects to their corresponding views
        textClock = findViewById(R.id.textClock);
        clockDisplay = findViewById(R.id.textView);

        // TODO: ADD 24 HOUR FUNCTIONALITY
        hourTens = Integer.toString(Integer.parseInt((hours12Sdf.format(currentTime.getTime()))) / 10);
        hourOnes = Integer.toString(Integer.parseInt((hours12Sdf.format(currentTime.getTime()))) % 10);
        minuteTens = Integer.toString(Integer.parseInt((minutesSdf.format(currentTime.getTime()))) / 10);
        minuteOnes = Integer.toString(Integer.parseInt((minutesSdf.format(currentTime.getTime()))) % 10);
        secondTens = Integer.toString(Integer.parseInt((secondsSdf.format(currentTime.getTime()))) / 10);
        secondOnes = Integer.toString(Integer.parseInt((secondsSdf.format(currentTime.getTime()))) % 10);

        lightHours(hourTens, hourOnes);
        lightMinutes(minuteTens, minuteOnes);
        lightSeconds(secondTens, secondOnes);


        /**
        // TODO: ADD 24 HOUR FUNCTIONALITY
        hourTens = decimalToBinary(Integer.toString(Integer.parseInt((hours12Sdf.format(currentTime.getTime()))) / 10));
        hourOnes = decimalToBinary(Integer.toString(Integer.parseInt((hours12Sdf.format(currentTime.getTime()))) % 10));
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
        */

        // Sets the text view to the timestamp when the app was opened
        clockDisplay.setText(simpleDateFormat.format(currentTime.getTime()));

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

        // Pads nibble with appropriate number of 0's
        while (binary.length() < 4) {
            binary = "0" + binary;
        }

        return binary;
    }

    // DELETE
    private void printTest(String string){
        System.out.println("Binary String: " + string);
    }


   private void lightHours(String tens, String ones) {
        int t = Integer.parseInt(tens);
        int o = Integer.parseInt(ones);
        if (t >= 2) {
            ht2.setColorFilter(getResources().getColor(R.color.colorBlue));
            //ht2.setImageResource(getResources().getColor(R.color.colorBlue));
        }
        else {
            ht2.setColorFilter(getResources().getColor(R.color.colorRed));
        }

        if (t == 1) {
            ht1.setColorFilter(getResources().getColor(R.color.colorBlue));
        }
        else {
            ht1.setColorFilter(getResources().getColor(R.color.colorRed));
        }


        if (o >= 8) {
            ho4.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 8;
        }
        else {
            ho4.setColorFilter(getResources().getColor(R.color.colorRed));
        }

        if (o >= 4) {
            ho3.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 4;
        }
        else {
            ho3.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (o >= 2) {
            ho2.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 2;
        }
        else {
            ho2.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (o >= 1) {
            ho1.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 1;
        }
        else {
            ho1.setColorFilter(getResources().getColor(R.color.colorRed));
        }

   }

   private void lightMinutes(String tens, String ones) {
        int t = Integer.parseInt(tens);
        int o = Integer.parseInt(ones);
        if (t >= 4) {
            mt1.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 4;
        }
        else {
            mt1.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (t >= 2) {
            mt2.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 2;
        }
        else {
            mt2.setColorFilter(getResources().getColor(R.color.colorRed));
        }

        if (t == 1) {
            mt1.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 1;
        }
        else {
            mt1.setColorFilter(getResources().getColor(R.color.colorRed));
        }


        if (o >= 8) {
            mo4.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 8;
        }
        else {
            mo4.setColorFilter(getResources().getColor(R.color.colorRed));
        }

        if (o >= 4) {
            mo3.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 4;
        }
        else {
            mo3.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (o >= 2) {
            mo2.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 2;
        }
        else {
            mo2.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (o >= 1) {
            mo1.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 1;
        }
        else {
            mo1.setColorFilter(getResources().getColor(R.color.colorRed));
        }


   }

   private void lightSeconds(String tens, String ones) {
        int t = Integer.parseInt(tens);
        int o = Integer.parseInt(ones);
        if (t >= 4) {
            st1.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 4;
        }
        else {
            st1.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (t >= 2) {
            st2.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 2;
        }
        else {
            st2.setColorFilter(getResources().getColor(R.color.colorRed));
        }

        if (t == 1) {
            st1.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 1;
        }
        else {
            st1.setColorFilter(getResources().getColor(R.color.colorRed));
        }


        if (o >= 8) {
            so4.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 8;
        }
        else {
            so4.setColorFilter(getResources().getColor(R.color.colorRed));
        }

        if (o >= 4) {
            so3.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 4;
        }
        else {
            so3.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (o >= 2) {
            so2.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 2;
        }
        else {
            so2.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        if (o >= 1) {
            so1.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 1;
        }
        else {
            so1.setColorFilter(getResources().getColor(R.color.colorRed));
        }
   }
}

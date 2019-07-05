package com.example.binaryclock;

// Default imports
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

// Additional imports
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import de.hdodenhof.circleimageview.CircleImageView;

// TODO: Change app icon
// TODO: Allow for 24 hour
// TODO: Allow for toggling digital clock
// TODO: Remove reference clock from top
public class MainActivity extends AppCompatActivity implements Runnable {

    // Creates a thread for the updater to run on
    Thread runner;
    // Declares a Runnable() object to continuously update the time
    final Runnable updater = new Runnable() {
        @Override
        // Dictates the method to be called on the running thread
        public void run() {
            update();
        }
    };

    // Global boolean to store whether the digital clock is displayed
    boolean toggle;

    // Declares a Handler() to handle the updater Runnable() in the message queue
    // When the updater Runnable() executes, its own run() method is started calling the update() method
    final Handler handler = new Handler();

    // Hour circle object declarations
    private CircleImageView ht1, ht2, ho1, ho2, ho3, ho4;

    // Minute circle object declarations
    private CircleImageView mt1, mt2, mt3, mo1, mo2, mo3, mo4;

    // Second circle object declarations
    private CircleImageView st1, st2, st3, so1, so2, so3, so4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Hour circle object instantiations
        ht1 = findViewById(R.id.hour_tens_1);
        ht2 = findViewById(R.id.hour_tens_2);
        ho1 = findViewById(R.id.hour_ones_1);
        ho2 = findViewById(R.id.hour_ones_2);
        ho3 = findViewById(R.id.hour_ones_3);
        ho4 = findViewById(R.id.hour_ones_4);

        // Minute circle object instantiations
        mt1 = findViewById(R.id.minute_tens_1);
        mt2 = findViewById(R.id.minute_tens_2);
        mt3 = findViewById(R.id.minute_tens_3);
        mo1 = findViewById(R.id.minute_ones_1);
        mo2 = findViewById(R.id.minute_ones_2);
        mo3 = findViewById(R.id.minute_ones_3);
        mo4 = findViewById(R.id.minute_ones_4);

        // Second circle object instantiations
        st1 = findViewById(R.id.second_tens_1);
        st2 = findViewById(R.id.second_tens_2);
        st3 = findViewById(R.id.second_tens_3);
        so1 = findViewById(R.id.second_ones_1);
        so2 = findViewById(R.id.second_ones_2);
        so3 = findViewById(R.id.second_ones_3);
        so4 = findViewById(R.id.second_ones_4);


        // Calls update() method to correct 1000ms delay on display
        update();


        // Checks to ensure that the Runnable() object is not null and starts it if it is
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Instantiates MenuItems to corresponding settings items
        MenuItem off = menu.findItem(R.id.toggle_digital_off);
        MenuItem on = menu.findItem(R.id.toggle_digital_on);

        // Sets visibility of menu items based on boolean toggle
        on.setVisible(toggle);
        off.setVisible(!toggle);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        int id = item.getItemId();
        TextView hourToggle = (TextView) findViewById(R.id.hour);
        TextView minuteToggle = (TextView) findViewById(R.id.minute);
        TextView secondToggle = (TextView) findViewById(R.id.second);

        // If toggle off is pressed, textview visibility is hidden
        if (id == R.id.toggle_digital_off) {
            hourToggle.setVisibility(View.GONE);
            minuteToggle.setVisibility(View.GONE);
            secondToggle.setVisibility(View.GONE);
            toggle = true;
            invalidateOptionsMenu();
        }
        // If toggle on is pressed, textview visibility is shown
        else if (id == R.id.toggle_digital_on) {
            hourToggle.setVisibility(View.VISIBLE);
            minuteToggle.setVisibility(View.VISIBLE);
            secondToggle.setVisibility(View.VISIBLE);
            toggle = false;
            invalidateOptionsMenu();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to convert a decimal number to a 4 digit binary number
     * @param decimal   the String to be converted
     * @return  the converted binary string.
     */
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

    /**
     * Method to light the cirlces in the hours columns
     * @param tens    the String in binary of the tens digit for the hour
     * @param ones    the String in binary of the ones digit for the hour
     */
   private void lightHours(String tens, String ones) {
        // Converts strings to ints
        int t = Integer.parseInt(tens);
        int o = Integer.parseInt(ones);

        if (t >= 2) {
            ht2.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        else {
            ht2.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (t == 1) {
            ht1.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        else {
            ht1.setColorFilter(getResources().getColor(R.color.blank));
        }


        if (o >= 8) {
            ho4.setColorFilter(getResources().getColor(R.color.colorRed));
            o %= 8;
        }
        else {
            ho4.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 4) {
            ho3.setColorFilter(getResources().getColor(R.color.colorRed));
            o %= 4;
        }
        else {
            ho3.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 2) {
            ho2.setColorFilter(getResources().getColor(R.color.colorRed));
            o %= 2;
        }
        else {
            ho2.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 1) {
            ho1.setColorFilter(getResources().getColor(R.color.colorRed));
        }
        else {
            ho1.setColorFilter(getResources().getColor(R.color.blank));
        }
   }

    /**
     * Method to light the minutes columns
     * @param tens    the String in binary of the tens digit for the minute
     * @param ones    the String in binary of the ones digit for the minute
     */
   private void lightMinutes(String tens, String ones) {
        // Converts strings to ints
        int t = Integer.parseInt(tens);
        int o = Integer.parseInt(ones);

        if (t >= 4) {
            mt3.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 4;
        }
        else {
            mt3.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (t >= 2) {
            mt2.setColorFilter(getResources().getColor(R.color.colorBlue));
            t %= 2;
        }
        else {
            mt2.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (t == 1) {
            mt1.setColorFilter(getResources().getColor(R.color.colorBlue));
        }
        else {
            mt1.setColorFilter(getResources().getColor(R.color.blank));
        }


        if (o >= 8) {
            mo4.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 8;
        }
        else {
            mo4.setColorFilter(getResources().getColor(R.color.blank));
        }

        if (o >= 4) {
            mo3.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 4;
        }
        else {
            mo3.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 2) {
            mo2.setColorFilter(getResources().getColor(R.color.colorBlue));
            o %= 2;
        }
        else {
            mo2.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 1) {
            mo1.setColorFilter(getResources().getColor(R.color.colorBlue));
        }
        else {
            mo1.setColorFilter(getResources().getColor(R.color.blank));
        }
   }

    /**
     * Method to light the seconds columns
     * @param tens    the String in binary of the tens digit for the second
     * @param ones    the String in binary of the ones digit for the second
     */
   private void lightSeconds(String tens, String ones) {
        // Converts strings to ints
        int t = Integer.parseInt(tens);
        int o = Integer.parseInt(ones);

        if (t >= 4) {
            st3.setColorFilter(getResources().getColor(R.color.colorGreen));
            t %= 4;
        }
        else {
            st3.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (t >= 2) {
            st2.setColorFilter(getResources().getColor(R.color.colorGreen));
            t %= 2;
        }
        else {
            st2.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (t == 1) {
            st1.setColorFilter(getResources().getColor(R.color.colorGreen));
        }
        else {
            st1.setColorFilter(getResources().getColor(R.color.blank));
        }


        if (o >= 8) {
            so4.setColorFilter(getResources().getColor(R.color.colorGreen));
            o %= 8;
        }
        else {
            so4.setColorFilter(getResources().getColor(R.color.blank));
        }

        if (o >= 4) {
            so3.setColorFilter(getResources().getColor(R.color.colorGreen));
            o %= 4;
        }
        else {
            so3.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 2) {
            so2.setColorFilter(getResources().getColor(R.color.colorGreen));
            o %= 2;
        }
        else {
            so2.setColorFilter(getResources().getColor(R.color.blank));
        }
        if (o >= 1) {
            so1.setColorFilter(getResources().getColor(R.color.colorGreen));
        }
        else {
            so1.setColorFilter(getResources().getColor(R.color.blank));
        }
   }

    /**
     * Method to update the displayed digital and binary clocks
     */
   private void update() {

       // Variable declarations for pulling current time info
       String hourTens, hourOnes, minuteTens, minuteOnes, secondTens, secondOnes;
       SimpleDateFormat hours12Sdf = new SimpleDateFormat("hh");
       SimpleDateFormat hours24Sdf = new SimpleDateFormat("HH");
       SimpleDateFormat minutesSdf = new SimpleDateFormat("mm");
       SimpleDateFormat secondsSdf = new SimpleDateFormat("ss");
       // TODO: Delete following line. Not used(probably)
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
       Date currentTime;

       // Digital clock variable declarations
       TextView digitalHour;
       TextView digitalMinute;
       TextView digitalSecond;
       TextClock textClock;

       // Pulls the current time
       currentTime = Calendar.getInstance().getTime();

       // TODO: Clock set texts

       // TODO: ADD 24 HOUR FUNCTIONALITY
       // Pulls each digit from the current hour, minute, and second as integers
       hourTens = Integer.toString(Integer.parseInt((hours12Sdf.format(currentTime.getTime()))) / 10);
       hourOnes = Integer.toString(Integer.parseInt((hours12Sdf.format(currentTime.getTime()))) % 10);
       minuteTens = Integer.toString(Integer.parseInt((minutesSdf.format(currentTime.getTime()))) / 10);
       minuteOnes = Integer.toString(Integer.parseInt((minutesSdf.format(currentTime.getTime()))) % 10);
       secondTens = Integer.toString(Integer.parseInt((secondsSdf.format(currentTime.getTime()))) / 10);
       secondOnes = Integer.toString(Integer.parseInt((secondsSdf.format(currentTime.getTime()))) % 10);

        // Updates objects to their corresponding views
        textClock = findViewById(R.id.textClock);
        digitalHour = findViewById(R.id.hour);
        digitalMinute = findViewById(R.id.minute);
        digitalSecond = findViewById(R.id.second);

        // Sets the digital clock text views
        digitalHour.setText(hours12Sdf.format(currentTime.getTime()) + ":");
        digitalMinute.setText(minutesSdf.format(currentTime.getTime()) + ":");
        digitalSecond.setText(secondsSdf.format(currentTime.getTime()) + " ");

       // Calls function to light the appropriate circles
       lightHours(hourTens, hourOnes);
       lightMinutes(minuteTens, minuteOnes);
       lightSeconds(secondTens, secondOnes);
   }


    /**
     * Implementation of the run() method
     * Thread.sleep is used to ensure it doesn't run more often than needed
     */
   @Override
    public void run() {
        while (runner != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            // Sends the Runnable() updater to the message queue
            handler.post(updater);
        }
   }
}

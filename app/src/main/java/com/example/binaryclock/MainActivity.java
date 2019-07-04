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
    private String hour = "";
    private String minute = "";
    private String second = "";
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

        currentTime = Calendar.getInstance().getTime();
        textClock = findViewById(R.id.textClock);
        clockDisplay = findViewById(R.id.textView);

        hour = hoursSdf.format(currentTime.getTime());
        minute = minutesSdf.format(currentTime.getTime());
        second = secondsSdf.format(currentTime.getTime());

        clockDisplay.setText(hour + " " + minute + " " + second);


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
}

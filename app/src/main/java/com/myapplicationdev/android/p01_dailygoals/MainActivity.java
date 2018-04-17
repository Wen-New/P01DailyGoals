package com.myapplicationdev.android.p01_dailygoals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    RadioGroup rg1, rg2, rg3;
    RadioButton rb1, rb2, rb3;
    EditText etRJ;
    Button btn;

    private int selectedButtonId1;
    private int selectedButtonId2;
    private int selectedButtonId3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the TextView Questions
        tv1 = (TextView) findViewById(R.id.textViewRead);
        tv2 = (TextView) findViewById(R.id.textViewTime);
        tv3 = (TextView) findViewById(R.id.textViewAttempt);
        tv4 = (TextView) findViewById(R.id.textViewRJ);

        // Get the RadioGroup object
        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg3 = (RadioGroup) findViewById(R.id.radioGroup3);

        // Get the Id of the selected radio button in the RadioGroup
        selectedButtonId1 = rg1.getCheckedRadioButtonId();
        selectedButtonId2 = rg2.getCheckedRadioButtonId();
        selectedButtonId3 = rg3.getCheckedRadioButtonId();

        // Get the radio button object from the Id we had gotten above
        rb1 = (RadioButton) findViewById(selectedButtonId1);
        rb2 = (RadioButton) findViewById(selectedButtonId2);
        rb3 = (RadioButton) findViewById(selectedButtonId3);

        // Get the EditText that user keys in name
        etRJ = (EditText) findViewById(R.id.editTextRJ);

        btn = (Button) findViewById(R.id.buttonOK);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();

                String[] info = {tv1.getText().toString() + " : " + rb1.getText().toString() + "\n",tv2.getText().toString() + " : " + rb2.getText().toString() + "\n",tv3.getText().toString() + " : " + rb3.getText().toString() + "\n",tv4.getText().toString() + " : " + etRJ.getText().toString()};

                prefEdit.putInt("rb1", selectedButtonId1);
                prefEdit.putInt("rb2", selectedButtonId2);
                prefEdit.putInt("rb3", selectedButtonId3);
                prefEdit.putString("rj", etRJ.getText().toString());
                prefEdit.commit();

                // Create an intent to start another activity called
                Intent i = new Intent(MainActivity.this,
                        SummaryActivity.class);

                // Pass the String array holding the name & age to new activity
                i.putExtra("info", info);

                // Start the new activity
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String rj = prefs.getString("rj", "");
        int rb1 = prefs.getInt("rb1", 0);
        int rb2 = prefs.getInt("rb2", 0);
        int rb3 = prefs.getInt("rb3", 0);
        etRJ.setText(rj);
        rg1.check(rb1);
        rg2.check(rb2);
        rg3.check(rb3);
        prefs.getString("rj", rj);
        prefs.getInt("rb1", rb1);
        prefs.getInt("rb2", rb2);
        prefs.getInt("rb3", rb3);
    }

}

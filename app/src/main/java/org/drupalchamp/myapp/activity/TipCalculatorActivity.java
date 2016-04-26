package org.drupalchamp.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.drupalchamp.myapp.R;

import java.text.NumberFormat;
import java.util.Locale;

public class TipCalculatorActivity extends AppCompatActivity implements TextView.OnEditorActionListener, SeekBar.OnSeekBarChangeListener,
        RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, View.OnKeyListener{

    //define instance variable for the widgets
    private EditText billAmountEditText;
    private TextView percentTextView, tipTextView, totalTextView, perPersonLabel, perPersonTextView;
    private SeekBar percentSeekBar;
    private RadioGroup roundingRadioGroup;
    private RadioButton roundNonRadio, roundTipRadio, roundTotalRadio, roundBothRadio;
    private Spinner splitSpinner;

    //define rounding constants
    private final int ROUND_NONE = 0 ;
    private final int ROUND_TIP = 1 ;
    private final int ROUND_TOTAL = 2 ;
    private final int ROUND_BOTH = 3 ;

    //define instance variable for tip percent
    private float tipPercent = .15f;
    private String billAmountString = "";
    private int rounding = ROUND_NONE ;
    private int split = 1;
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this,"Handle Exception",Toast.LENGTH_LONG).show();
        }


        //get references to the widgets
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        perPersonLabel = (TextView) findViewById(R.id.contributeTextView);
        perPersonTextView = (TextView) findViewById(R.id.contribute);
        percentSeekBar = (SeekBar) findViewById(R.id.seekBar);
        roundingRadioGroup = (RadioGroup) findViewById(R.id.choose);
        roundNonRadio = (RadioButton) findViewById(R.id.rb1);
        roundTipRadio = (RadioButton) findViewById(R.id.rb2);
        roundTotalRadio = (RadioButton) findViewById(R.id.rb3);
        roundBothRadio = (RadioButton) findViewById(R.id.rb4);
        splitSpinner = (Spinner) findViewById(R.id.spinner);

        //setArrayAdapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.split_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        splitSpinner.setAdapter(adapter);

        //set the listeners
        billAmountEditText.setOnEditorActionListener(this);
        billAmountEditText.setOnKeyListener(this);
        percentSeekBar.setOnSeekBarChangeListener(this);
        percentSeekBar.setOnKeyListener(this);
        roundingRadioGroup.setOnCheckedChangeListener(this);
        roundingRadioGroup.setOnKeyListener(this);
        splitSpinner.setOnItemSelectedListener(this);

        //getShared preference object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        //calculate the tip and display the results
        calculateAndDisplay();

    }

    private void calculateAndDisplay() {
        //get the bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")){
            billAmount = 0;
        }else{
            billAmount = Float.parseFloat(billAmountString);
        }
        //get tip percent
        int progress = percentSeekBar.getProgress();
        tipPercent = (float) progress/100;

        //calculate tip and total
        float tipAmount = 0;
        float totalAmount = 0;
        if (rounding == ROUND_NONE){
            tipAmount = billAmount * tipPercent;
            totalAmount = billAmount + tipAmount;
        }
        else if (rounding == ROUND_TIP){
            tipAmount = StrictMath.round(billAmount * tipPercent);
            totalAmount = billAmount + tipAmount;
            tipPercent = tipAmount / billAmount;
        }
        else if (rounding == ROUND_TOTAL){
            float tipNotRounded = billAmount * tipPercent;
            totalAmount = StrictMath.round(billAmount + tipNotRounded);
            tipAmount = totalAmount - billAmount;
            tipPercent = tipAmount / billAmount;
        }
        else if (rounding == ROUND_BOTH){
            float tipNotRounded = billAmount * tipPercent;
            totalAmount = StrictMath.round(billAmount + tipNotRounded);
            int totalRound = StrictMath.round(billAmount);
            tipAmount = totalAmount - totalRound;
            tipPercent = tipAmount / billAmount;
        }

        //calculate split amount and show/hide split amount widgets
        float splitAmount = 0;
        if (split == 1){ //no split -hide widgets
            perPersonLabel.setVisibility(View.GONE);
            perPersonTextView.setVisibility(View.GONE);
        }else {//split - calculate amount and show widgets
            splitAmount = totalAmount / split;
            perPersonLabel.setVisibility(View.VISIBLE);
            perPersonTextView.setVisibility(View.VISIBLE);
        }
        //display the results with formatting
        Locale locale = new Locale("en", "IN");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));
        perPersonTextView.setText(currency.format(splitAmount));

        NumberFormat percent = NumberFormat.getPercentInstance(locale);
        percentTextView.setText(percent.format(tipPercent));
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

    @Override
    protected void onPause() {
        //save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("billAmountString", billAmountString);
        editor.putFloat("tipPercent", tipPercent);
        editor.putInt("rounding", rounding);
        editor.putInt("split", split);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //get the instance variables
        billAmountString = savedValues.getString("billAmountString","");
        tipPercent = savedValues.getFloat("tipPercent", 0.15f);
        rounding = savedValues.getInt("rounding", ROUND_NONE);
        split = savedValues.getInt("split",1);

        //set the bill amount on its widget
        billAmountEditText.setText(billAmountString);

        //set the tip percent on its widget
        int Progress = Math.round(tipPercent * 100);
        percentSeekBar.setProgress(Progress);

        //set rounding on radio button
        if (rounding == ROUND_NONE){
            roundNonRadio.setChecked(true);
        }else if (rounding == ROUND_TIP){
            roundTipRadio.setChecked(true);
        }else if (rounding == ROUND_TOTAL){
            roundTotalRadio.setChecked(true);
        }else if (rounding == ROUND_BOTH){
            roundBothRadio.setChecked(true);
        }
        //set split on spinner
        int position = split - 1;
        splitSpinner.setSelection(position);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        split = position + 1;
        calculateAndDisplay();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                calculateAndDisplay();

                //hide the soft key
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(billAmountEditText.getWindowToken(), 0);

                //consume the event
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (view.getId() == R.id.seekBar){
                    calculateAndDisplay();
                }
                break;
        }
        //don't consume the event
        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        percentTextView.setText(progress + "%");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        calculateAndDisplay();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb1:
                rounding = ROUND_NONE;
                break;
            case R.id.rb2:
                rounding = ROUND_TIP;
                break;
            case R.id.rb3:
                rounding = ROUND_TOTAL;
                break;
            case R.id.rb4:
                rounding = ROUND_BOTH;
                break;
        }
        calculateAndDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(), CardActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

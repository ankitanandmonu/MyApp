package org.drupalchamp.myapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.drupalchamp.myapp.CalculatorBrain;
import org.drupalchamp.myapp.DatabaseActivity;
import org.drupalchamp.myapp.R;

import java.text.DecimalFormat;

/**
 * Created by ANKIT ANAND
 * Date: 3/3/2016
 * Time: 10:16 AM
 */
public class Calculator extends AppCompatActivity implements View.OnClickListener {
    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAdd, buttonSubtract, buttonMultiply,
            buttonDivide, buttonToggleSign, buttonDecimalPoint, buttonEquals, buttonClear, buttonClearMemory, buttonAddToMemory,
            buttonSubtractFromMemory, buttonRecallMemory;
    ImageButton orientations;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@###########");
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        mCalculatorBrain = new CalculatorBrain();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(9);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonToggleSign = (Button) findViewById(R.id.buttonToggleSign);
        buttonDecimalPoint = (Button) findViewById(R.id.buttonDecimalPoint);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClearMemory = (Button) findViewById(R.id.buttonClearMemory);
        buttonAddToMemory = (Button) findViewById(R.id.buttonAddToMemory);
        buttonSubtractFromMemory = (Button) findViewById(R.id.buttonSubtractFromMemory);
        buttonRecallMemory = (Button) findViewById(R.id.buttonRecallMemory);

        orientations = (ImageButton) findViewById(R.id.orientation);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonToggleSign.setOnClickListener(this);
        buttonDecimalPoint.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonClearMemory.setOnClickListener(this);
        buttonAddToMemory.setOnClickListener(this);
        buttonSubtractFromMemory.setOnClickListener(this);
        buttonRecallMemory.setOnClickListener(this);
        //orientation.setOnClickListener(this);
        //screenOrientation.setOnClickListener(this);
        buttonEquals.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getApplicationContext(), DatabaseActivity.class));
                return true;
            }

        });


        // The following buttons only exist in layout-land (Landscape mode) and require extra attention.
        // The messier option is to place the buttons in the regular layout too and set android:visibility="invisible".
        if (findViewById(R.id.buttonSquareRoot) != null) {
            findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSquared) != null) {
            findViewById(R.id.buttonSquared).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonInvert) != null) {
            findViewById(R.id.buttonInvert).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSine) != null) {
            findViewById(R.id.buttonSine).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonCosine) != null) {
            findViewById(R.id.buttonCosine).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonTangent) != null) {
            findViewById(R.id.buttonTangent).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonCosec) != null) {
            findViewById(R.id.buttonCosec).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSec) != null) {
            findViewById(R.id.buttonSec).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonCot) != null) {
            findViewById(R.id.buttonCot).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonCubed) != null){
            findViewById(R.id.buttonCubed).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonPercent) != null){
            findViewById(R.id.buttonPercent).setOnClickListener(this);
        }


        orientations= (ImageButton) findViewById(R.id.orientation);
        orientations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                }

        });

    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            // digit was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }

            mCalculatorBrain.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", mCalculatorBrain.getResult());
        outState.putDouble("MEMORY", mCalculatorBrain.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        mCalculatorBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculatorBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
    }
}

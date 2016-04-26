package org.drupalchamp.myapp;

/**
 * Created by ANKIT ANAND
 * Date: 3/3/2016
 * Time: 10:22 AM
 */
public class CalculatorBrain {
    // 3 + 6 = 9
    // 3 & 6 are called the operand.
    // The + is called the operator.
    // 9 is the result of the operation.
    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;
    private int fact = 1;

    // operator types
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C" ;
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";
    public static final String SQUAREROOT = "√";
    public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";
    public static final String SINE = "sin";
    public static final String COSINE = "cos";
    public static final String TANGENT = "tan";
    public static final String COSECANT = "Cosec";
    public static final String SECANT = "Sec";
    public static final String COTANGENT = "cot";
    public static final String FACTORIAL = "X!";
    public static final String PERCENT = "%";
    public static final String CUBE = "X^3";

    public CalculatorBrain() {
        mWaitingOperand = 0;
        mOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }
    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    // used on screen orientation change
    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    // used on screen orientation change
    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }
    public double performOperation(String operator) {

        switch (operator){
            case CLEAR:
                mOperand = 0;
                mWaitingOperator="";
                mWaitingOperand = 0;
                break;
            case CLEARMEMORY:
                mCalculatorMemory=0;
                break;
            case ADDTOMEMORY:
                mCalculatorMemory = mCalculatorMemory + mOperand;
                break;
            case SUBTRACTFROMMEMORY:
                mCalculatorMemory = mCalculatorMemory - mOperand;
                break;
            case RECALLMEMORY:
                mOperand = mCalculatorMemory;
                break;
            case SQUAREROOT:
                mOperand = Math.sqrt(mOperand);
                break;
            case SQUARED:
                mOperand = mOperand * mOperand;
                break;
            case INVERT:
                if (mOperand != 0){
                    mOperand = 1 / mOperand;
                }
                break;
            case TOGGLESIGN:
                mOperand = -mOperand;
                break;
            case SINE:
                mOperand = Math.sin(Math.toRadians(mOperand));
                break;
            case COSINE:
                mOperand = Math.cos(Math.toRadians(mOperand));
                break;
            case TANGENT:
                mOperand = Math.tan(Math.toRadians(mOperand));
                break;
            case COSECANT:
                mOperand = 1/Math.sin(Math.toRadians(mOperand));
                break;
            case SECANT:
                mOperand = 1/Math.cos(Math.toRadians(mOperand));
                break;
            case COTANGENT:
                mOperand = 1/Math.tan(Math.toRadians(mOperand));
                break;
            case PERCENT:
                mOperand = mOperand / 100;
                break;
            case CUBE:
                mOperand = mOperand * mOperand * mOperand;

                break;
            default:
                performWaitingOperation();
                mWaitingOperator = operator;
                mWaitingOperand = mOperand;
                break;
        }
        return mOperand;
    }

    protected void performWaitingOperation() {

        if (mWaitingOperator.equals(ADD)) {
            mOperand = mWaitingOperand + mOperand;
        } else if (mWaitingOperator.equals(SUBTRACT)) {
            mOperand = mWaitingOperand - mOperand;
        } else if (mWaitingOperator.equals(MULTIPLY)) {
            mOperand = mWaitingOperand * mOperand;
        } else if (mWaitingOperator.equals(DIVIDE)) {
            if (mOperand != 0) {
                mOperand = mWaitingOperand / mOperand;
            }
        }

    }
}

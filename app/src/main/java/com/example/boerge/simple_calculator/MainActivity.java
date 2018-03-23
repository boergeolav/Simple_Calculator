package com.example.boerge.simple_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText1);
    }

    public void reset(View view) {
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setText("");
    }

    public void enterSymbol(View view) {
        String expression = editText.getText().toString();
        StringBuilder sb = new StringBuilder(expression);
        char c = ((Button)view).getText().charAt(0);
        int operatorIndex = indexOfOperator(expression);
        // If trying to insert an operator and the expression already has an operator,
        // just replace the old one.
        if(isOperator(c) && operatorIndex >= 0) {
            sb.setCharAt(operatorIndex, c);
        }
        // Otherwise, just insert the symbol or operator at the end of the expression.
        else {
            sb.append(c);
        }
        editText.setText(sb.toString());
    }

    public void calculate(View view) {
        String expression = editText.getText().toString();
        int operatorIndex = indexOfOperator(expression);
        // Check whether the expression has and operator and two operands.
        if(operatorIndex >= 1 && operatorIndex < expression.length()-1) {
            char operator = expression.charAt(operatorIndex);
            String[] operands = expression.split("[\\+\\-\\*\\/]");
            double operand1 = Double.parseDouble(operands[0]);
            double operand2 = Double.parseDouble(operands[1]);
            double result = 0.0;
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if ((int)operand2 != 0)
                        result = operand1 / operand2;
                    break;
                default:
                    break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(result);
            editText.setText(sb.toString());
        }
    }

    /**
     *
     * @param expression
     * @return index of operator in the expression, -1 if the expression has no operator.
     */
    private int indexOfOperator(String expression) {
        for(int i = 0; i < expression.length(); i++) {
            if(isOperator(expression.charAt(i)))
                return i;
        }
        return -1;
    }

    /**
     *
     * @param c
     * @return true if c is an operator, false if not.
     */
    private boolean isOperator(char c) {
        if(c == '+' || c == '-' || c == '*' || c == '/')
            return true;
        return false;
    }
}

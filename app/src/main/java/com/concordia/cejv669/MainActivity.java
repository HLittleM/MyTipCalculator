package com.concordia.cejv669;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    public static float GSTPERCENT = (float) 0.05;
    public static float QSTERCENT = (float) 0.097;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_cal = findViewById(R.id.btn_calculate);
        bt_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputPrice=findViewById(R.id.edit_price);
                String inputPriceS = inputPrice.getText().toString();
                EditText inputDinnerNumber=findViewById(R.id.edit_dinnerNumber);
                String inputDinnerNumberS = inputDinnerNumber.getText().toString();
                EditText inputTipPercent=findViewById(R.id.edit_TipPercent);
                String inputTipPercentS = inputTipPercent.getText().toString();
                TextView displayResult=findViewById(R.id.lbl_result);
                float price=0;
                int dinnerNumber=1;
                float tipPercent=0;
                if (PositiveFloatValidator(inputPriceS)) {
                    price=Float.parseFloat(inputPriceS);
                }
                else
                {
                    displayResult.setText("Price should be a positive number.");
                }
                if (PositiveIntValidator(inputDinnerNumberS)) {
                    dinnerNumber=Integer.parseInt(inputDinnerNumberS);
                }
                else
                {
                    displayResult.setText("Person should be more than 0.");
                }
                if (PositiveFloatValidator(inputTipPercentS)) {
                    tipPercent = (Float.parseFloat(inputTipPercentS))/100;
                }
                else
                {
                    displayResult.setText("Person should be more than 0.");
                }
                if(PositiveFloatValidator(inputPriceS)&& PositiveIntValidator(inputDinnerNumberS) && PositiveFloatValidator(inputTipPercentS))
                {
                    displayResult.setText(calculate(price, tipPercent, dinnerNumber));

                }
            }
        });
    }

    public String calculate(float price, float tipPercent, int dinnerNumber) {

        NumberFormat formatter= NumberFormat.getNumberInstance().getCurrencyInstance();
        float GSTAmount = price * GSTPERCENT;
        float QSTAmount = price * QSTERCENT;
        float totalAmount = price + GSTAmount + QSTAmount ;
        float tipAmount = totalAmount * tipPercent;
        float tipPerPerson = tipAmount / dinnerNumber;
        return ("The price of the meal is " + formatter.format(price) + ".\nGST amount is " + formatter.format(GSTAmount) +
                ".\nQST amount is " + formatter.format(QSTAmount) + ".\nTotal price is " + formatter.format(totalAmount) +
                ".\nTip amount is " + formatter.format(tipAmount) + ".\nTip per person is " + formatter.format(tipPerPerson )+ ".");
    }

    public boolean IntValidator(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean FloatValidator(String input) {
        try {
            Float.parseFloat(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean PositiveIntValidator(String input) {
        if (IntValidator(input) && Integer.parseInt(input) > 0)
            return true;
        else
            return false;
    }

    public boolean PositiveFloatValidator(String input) {
        if (FloatValidator(input) && Float.parseFloat(input) > 0)
            return true;
        else
            return false;
    }
}

package com.example.lumi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class CalculatorActivity extends Activity {

    EditText etNumber1, etNumber2;
    Spinner spinnerOperation;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // Настройка операций
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"+", "-", "*", "/"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperation.setAdapter(adapter);

        // Обработка нажатия на кнопку
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1 = Double.parseDouble(etNumber1.getText().toString());
                    double num2 = Double.parseDouble(etNumber2.getText().toString());
                    String operation = spinnerOperation.getSelectedItem().toString();
                    double result = 0;

                    switch (operation) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                tvResult.setText("Ошибка: деление на 0");
                                return;
                            }
                            result = num1 / num2;
                            break;
                    }

                    tvResult.setText("Результат: " + result);

                } catch (NumberFormatException e) {
                    tvResult.setText("Ошибка ввода чисел");
                }
            }
        });
    }
}

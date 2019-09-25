package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private int num1, num2;
    EditText got_num_1;
    EditText got_num_2;
    TextView result;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        this.getIntent();

        got_num_1 = findViewById(R.id.num1);
        got_num_2 = findViewById(R.id.num2);

        result = findViewById(R.id.res);
        error = findViewById(R.id.err_no_data);
        Button b_add = findViewById(R.id.b_plus);
        Button b_sub = findViewById(R.id.b_sub);
        Button b_mult = findViewById(R.id.b_mult);
        Button b_div = findViewById(R.id.b_div);


        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (has_data()) {
                    remove_error();
                    get_nums();
                    result.setText(Integer.toString(num1 + num2));
                }
                else
                    set_error();
            }
        });

        b_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (has_data()) {
                    remove_error();
                    get_nums();
                    result.setText(Integer.toString(num1 - num2));
                }
                else
                    set_error();
            }
        });

        b_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (has_data()) {
                    remove_error();
                    get_nums();
                    result.setText(Integer.toString(num1 * num2));
                }
                else
                    set_error();
            }
        });

        b_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (has_data()) {
                    remove_error();
                    get_nums();
                    result.setText(Integer.toString(num1 / num2));
                }
                else
                    set_error();
            }
        });


    }

    public void get_nums() {
        num1 = Integer.parseInt(got_num_1.getText().toString());
        num2 = Integer.parseInt(got_num_2.getText().toString());
    }

    public boolean has_data() {
        return !got_num_1.getText().toString().matches("")
                && !got_num_2.getText().toString().matches("");
    }

    public void set_error() {
        error.setText("Please, enter both operands");
        result.setText("");
    }

    public void remove_error() {
        error.setText("");
    }

}

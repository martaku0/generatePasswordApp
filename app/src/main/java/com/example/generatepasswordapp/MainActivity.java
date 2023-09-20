package com.example.generatepasswordapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnGenerate;
    private TextView pass;
    private EditText minValue;
    private EditText maxValue;
    private CheckBox lowLetters;
    private CheckBox upLetters;
    private CheckBox nums;
    private CheckBox special;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public char getRandomLetter() {
        int a = 97;
        int z = 122;
        int rand = getRandomNumber(a, z);
        char letter = (char) rand;
        return letter;
    }

    public char getRandomSpecial() {
        char[] arr_spec = {' ',  '!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '_', '{', '|', '}',};
        int temp = getRandomNumber(0, arr_spec.length-1);
        char rand_spec = arr_spec[temp];
        return rand_spec;
    }


    public int booleanToIntParse(boolean a) {
        return (a) ? 1 : 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGenerate = findViewById(R.id.btnGenerate);
        pass = findViewById(R.id.pass);
        minValue = findViewById(R.id.minValue);
        maxValue = findViewById(R.id.maxValue);
        lowLetters = findViewById(R.id.lowLetters);
        upLetters = findViewById(R.id.upLetters);
        nums = findViewById(R.id.nums);
        special = findViewById(R.id.special);


        btnGenerate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                if(!minValue.getText().toString().equals("") && !maxValue.getText().toString().equals("")){

                    int min = Integer.parseInt(minValue.getText().toString());
                    int max = Integer.parseInt(maxValue.getText().toString());
                    if(min <= max){
                        boolean low = lowLetters.isChecked();
                        boolean up = upLetters.isChecked();
                        boolean num = nums.isChecked();
                        boolean spec = special.isChecked();

                        if(low == true || up == true || num == true || spec == true){
                            String message = "min " + min + " max " + max + " low " + low + " up " + up + " num " + num + " spec " + spec;
                            Log.d("Click",message);

                            String genPass = generatePassword(min, max, low, up, num, spec);
                            pass.setText(genPass);

                        }
                        else{
                            Log.d("Click","Wrong");
                        }
                    }
                    else{
                        Log.d("Click","Wrong");
                    }
                }
                else{
                    Log.d("Click","Wrong");
                }



            }
        });
    }

    private String generatePassword(int min, int max, boolean low, boolean up, boolean num, boolean spec){
        int length;
        String password = "";
        if(min == max){
            length = min;
        }
        else{
            length = getRandomNumber(min, max);
        }

        int options = 0;
        if(low == true){
            options++;
        }
        if(up == true){
            options++;
        }
        if(num == true){
            options++;
        }
        if(spec == true){
            options++;
        }

        for(int i = 0; i<length; i++){
            int temp = getRandomNumber(1,5);
            switch(temp){
                case 1:
                    if(low != true){
                        i--;
                        break;
                    }
                    password += String.valueOf(getRandomLetter()).toLowerCase();
                    break;
                case 2:
                    if(up != true){
                        i--;
                        break;
                    }
                    password += String.valueOf(getRandomLetter()).toUpperCase();
                    break;
                case 3:
                    if(num != true){
                        i--;
                        break;
                    }
                    int t = getRandomNumber(0,9);
                    password += Integer.toString(t);
                    break;
                case 4:
                    if(spec != true){
                        i--;
                        break;
                    }
                    password += String.valueOf(getRandomSpecial());
                    break;
                default:
                    Log.d("Generator","Wrong");
                    break;
            }
        }

        return password;
    }

}
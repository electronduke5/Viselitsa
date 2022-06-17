package com.example.viselitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String[] WORDS = new String[] {
            "Вольт",
            "Роза",
            "Крот",
            "Балл",
            "Кондитер",
            "Банк",
            "Лаваш",
            "Бревно"

    };

    private String word;
    private String entered;
    private int lifes = 10;
    private boolean win = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();
        word = WORDS[random.nextInt(WORDS.length)].toLowerCase(Locale.ROOT);

        char[] chars = new char[word.length()];
        Arrays.fill(chars, '.');
        entered = String.valueOf(chars);

        TextView enteredText = findViewById(R.id.wordText);
        TextView letterText = findViewById(R.id.letterTxt);
        TextView lifesText = findViewById(R.id.txtLives);

        findViewById(R.id.btn).setOnClickListener(view -> {
            if (lifes == 0 || win) return;

            String letter = letterText.getText().toString().toLowerCase(Locale.ROOT);
            letterText.setText("");
            enteredText.setText(SetLetter(letter));

            lifesText.setText(String.format("Жизни: %d", lifes));

            if (!entered.contains(".")) {
                win = true;
                Toast.makeText(this, "Вы победили!!!!", Toast.LENGTH_SHORT).show();
            }

            if (lifes == 0) {
                Toast.makeText(this, "YOU LOOSE =(", Toast.LENGTH_SHORT).show();
            }
        });

        enteredText.setText(entered);

    }

    private String SetLetter(String letter){
        if(letter.length() != 1 ){
            Toast.makeText(this,"Вам нужно ввести букву", Toast.LENGTH_SHORT).show();
            return entered;
        }
        if(!hasLetter(letter)) {
            Toast.makeText(this,"Такой буквы нет в это слове", Toast.LENGTH_SHORT).show();
            lifes--;
            return entered;
        }

        for(int i = 0; i< word.length(); i++){
            if(word.charAt(i) == letter.charAt(0)){
                char[] chars = entered.toCharArray();
                chars[i] = letter.charAt(0);
                entered = String.valueOf(chars);
            }
        }

//        int index;
//        while((index = entered.lastIndexOf(letter)) != 1){
//            char[] chars = entered.toCharArray();
//            chars[index] = letter.charAt(0);
//            entered = String.valueOf(chars);
//        }

        return entered;
    }

    private boolean hasLetter(String letter){
        return word.contains(letter);
    }
}
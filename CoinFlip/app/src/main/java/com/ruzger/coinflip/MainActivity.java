package com.ruzger.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageCoin;
    private Button buttonHead;
    private Button buttonTails;
    private TextView textRolls;
    private TextView textWins;
    private TextView textLose;

    private int Wins = 0;
    private int Lose = 0;
    private int Rolls = 0;

    Random rnd = new Random();
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();

        buttonHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessResult(0);
            }
        });

        buttonTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessResult(1);
            }
        });
    }

    // 0 = head; 1 = tails
    void guessResult(int guess) {
        int generated = rnd.nextInt(2);
        if(generated == guess) {
            Wins++;
            Toast.makeText(MainActivity.this, "Ügyes vagy! Eltaláltad!", Toast.LENGTH_SHORT).show();
        } else {
            Lose++;
            Toast.makeText(MainActivity.this, "Sajnos nem nyert!", Toast.LENGTH_SHORT).show();

        }

        Rolls++;

        switch (generated) {
            case 0:
                imageCoin.setImageResource(R.drawable.heads);
                break;
            case 1:
                imageCoin.setImageResource(R.drawable.tails);
                break;
            default:
                imageCoin.setImageResource(R.drawable.shrug);
                break;
        }

        textWins.setText("Győzelem: " + String.valueOf(Wins));
        textLose.setText("Vereség: " + String.valueOf(Lose));
        textRolls.setText("Dobások: " + String.valueOf(Rolls));

        if(Rolls == 5) {
            endOfGameRoutine();
        }
    }

    void endOfGameRoutine() {
        if (Wins == 5) {
            alertDialog.setTitle("Győzelem");
        } else {
            alertDialog.setTitle("Vereség");
        }
        alertDialog.setMessage("Szeretnél újra játszani?");
        alertDialog.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGame();
            }
        });

        alertDialog.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    void resetGame() {
        Wins = 0;
        Rolls = 0;
        Lose = 0;
        textWins.setText("Győzelem: " + String.valueOf(Wins));
        textLose.setText("Vereség: " + String.valueOf(Lose));
        textRolls.setText("Dobások: " + String.valueOf(Rolls));
        imageCoin.setImageResource(R.drawable.heads);
    }

    void onInit() {
        imageCoin = findViewById(R.id.imageCoin);
        buttonHead = findViewById(R.id.buttonHead);
        buttonTails = findViewById(R.id.buttonTails);
        textRolls = findViewById(R.id.textRolls);
        textWins = findViewById(R.id.textWins);
        textLose = findViewById(R.id.textLose);
        alertDialog = new AlertDialog.Builder(MainActivity.this);

    }
}
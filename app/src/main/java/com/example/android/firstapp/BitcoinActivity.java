package com.example.android.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.android.firstapp.R.layout.activity_bitcoin;

/**
 * Created by Yatch on 10/6/2017.
 */

public class BitcoinActivity extends AppCompatActivity {

    public double myBitcoin = 0.04903691;
    public double myInvestment = 200;
    String myBitcoinString = Double.toString(myBitcoin);


    /////////////////////////
    ////    onCreate    /////
    /////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Retrieve Bitcoin value from MainActivity
        Intent intent = getIntent();
        String value = intent.getStringExtra(MainActivity.BTC_KEY);
        String cryptoType = intent.getStringExtra(MainActivity.CRYPTO_KEY);

        super.onCreate(savedInstanceState);
        setContentView(activity_bitcoin);

        //Set up variables
        double convertedBitcoin = Double.parseDouble(value.substring(1));
        double myUSD = Double.parseDouble(calculateMyBitcoin(myBitcoin, convertedBitcoin).substring(1));
        double profitMargin = myUSD - myInvestment;

        //Display Bitcoin's current value
        TextView bitcoinHeader = (TextView) findViewById(R.id.btcHeader);
        bitcoinHeader.setText("Bitcoin " + value);

        //Display my Bitcoin
        TextView btcAmount = (TextView) findViewById(R.id.myBTC);
        myBitcoinString.concat(" BTC");
        btcAmount.setText(myBitcoinString);

        //Display Bitcoin in dollars
        TextView convertedBTC = (TextView) findViewById(R.id.converted_btc);
        convertedBTC.setText(calculateMyBitcoin(myBitcoin, convertedBitcoin));

        //Calculate and display Profit/Loss
        TextView profit = (TextView) findViewById(R.id.profit_value);
        profit.setText("$" + Double.toString(Math.round(profitMargin * 100) / 100.0d));
        if (profitMargin > 0) {
            profit.setTextColor(this.getResources().getColor(R.color.green));
        } else {
            profit.setTextColor(this.getResources().getColor(R.color.darkred));
        }

        //Configure spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinArray, android.R.layout.simple_spinner_item);
        // Apply the adapter to the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }


    public String calculateMyBitcoin(double myBTC, double liveBitcoin) {

        double calculatedBTC = liveBitcoin * myBTC;
        calculatedBTC = Math.round(calculatedBTC * 100) / 100.0d;

        return "$" + Double.toString(calculatedBTC);
    }
}


package com.example.android.firstapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView bitcoinValue;
    private TextView etherValue;
    private TextView rippleValue;
    private TextView litecoinValue;
    private TextView dashValue;
    private TextView nemValue;
    private TextView neoValue;
    private TextView moneroValue;
    private TextView omisegoValue;
    private TextView qtumValue;
    private TextView zcashValue;


    /////////////////////////
    ////    onCreate    /////
    /////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //locate buttons and textviews from XML file
        Button btnHit = (Button)findViewById(R.id.btnHit);
        ImageButton btcHit = (ImageButton)findViewById(R.id.btcButton);

        /*
        ImageButton ethHit = (ImageButton)findViewById(R.id.ethButton);
        ImageButton xrpHit = (ImageButton)findViewById(R.id.xrpButton);
        ImageButton ltcHit = (ImageButton)findViewById(R.id.ltcButton);
        ImageButton nemHit = (ImageButton)findViewById(R.id.nemButton);
        ImageButton neoHit = (ImageButton)findViewById(R.id.neoButton);
        ImageButton xmrHit = (ImageButton)findViewById(R.id.xmrButton);
        ImageButton omgHit = (ImageButton)findViewById(R.id.omgButton);
        ImageButton zecHit = (ImageButton)findViewById(R.id.zecButton);
        ImageButton dashHit = (ImageButton)findViewById(R.id.dashButton);
        ImageButton qtumHit = (ImageButton)findViewById(R.id.qtumButton);
        */

        bitcoinValue = (TextView)findViewById(R.id.bitcoin_value);
        etherValue = (TextView)findViewById(R.id.ethereum_value);
        rippleValue = (TextView)findViewById(R.id.ripple_value);
        litecoinValue = (TextView)findViewById(R.id.litecoin_value);
        dashValue = (TextView)findViewById(R.id.dash_value);
        nemValue = (TextView)findViewById(R.id.nem_value);
        neoValue = (TextView)findViewById(R.id.neo_value);
        moneroValue = (TextView)findViewById(R.id.monero_value);
        omisegoValue = (TextView)findViewById(R.id.omisego_value);
        qtumValue = (TextView)findViewById(R.id.qtum_value);
        zcashValue = (TextView)findViewById(R.id.zcash_value);

        fetchPrices();

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "giggity", Toast.LENGTH_SHORT).show();
                fetchPrices();
            }
        });

        btcHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStarters(v);
            }
        });




    }


    /////////////////////////
    ////     onStart    /////
    /////////////////////////
    @Override
    protected void onStart(){
        super.onStart();
    }


    ////////////////////////
    ////    onResume    ////
    ////////////////////////
    @Override
    protected void onResume(){
        super.onResume();
        fetchPrices();

    }


    public void fetchPrices(){

        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD", "btc");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD", "eth");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=XRP&tsyms=USD", "xrp");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=LTC&tsyms=USD", "ltc");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=DASH&tsyms=USD", "das");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=XEM&tsyms=USD", "xem");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=NEO&tsyms=USD", "neo");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=XMR&tsyms=USD", "xmr");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=OMG&tsyms=USD", "omg");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=QTUM&tsyms=USD", "qtm");
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=ZEC&tsyms=USD", "zec");

    }


    public void updateBTC(){
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD", "btc");
    }


    /////////////////////////
    ////     Intents     ////
    /////////////////////////
    public void displayStarters(View view){
        Intent intent = new Intent(this, BitcoinActivity.class);
        startActivity(intent);
    }


    //Every new AsyncTask must extend AsyncTask
    public class JSONTask extends AsyncTask<String,String, String>{

        //The main portion of the background process
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String currency = new String(params[1]);

            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                String value = parentObject.getString("USD");
                return currency + "$" + value;

                //Watch out for all those exceptions!
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null) {
                    connection.disconnect();
                }
                try {
                    if(reader!=null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //After doInBackground is finished
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            String currency = result.substring(0,3);
            result = result.substring(3);

            if(currency.equals("btc")){
                bitcoinValue.setText(result);
            }
            else if (currency.equals("eth")){
                etherValue.setText(result);
            }
            else if (currency.equals("xrp")){
                rippleValue.setText(result);
            }
            else if (currency.equals("ltc")){
                litecoinValue.setText(result);
            }
            else if (currency.equals("das")){
                dashValue.setText(result);
            }
            else if (currency.equals("xem")){
                nemValue.setText(result);
            }
            else if (currency.equals("neo")){
                neoValue.setText(result);
            }
            else if (currency.equals("xmr")){
                moneroValue.setText(result);
            }
            else if (currency.equals("omg")){
                omisegoValue.setText(result);
            }
            else if (currency.equals("qtm")){
                qtumValue.setText(result);
            }
            else if (currency.equals("zec")){
                zcashValue.setText(result);
            }
        }
    }
}

package com.example.android.firstapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public static final String BTC_KEY = "btc_key";
    public static final String CRYPTO_KEY = "crypto_key";
    private static final String TAG = "MainActivity";

    private TextView bitcoinTextView;
    private TextView etherTextView;
    private TextView rippleTextView;
    private TextView litecoinTextView;
    private TextView dashTextView;
    private TextView nemTextView;
    private TextView neoTextView;
    private TextView moneroTextView;
    private TextView omisegoTextView;
    private TextView qtumTextView;
    private TextView zcashTextView;

    private String Bitcoin;
    private String Ether;
    private String Ripple;
    private String LiteCoin;
    private String Dash;
    private String NEM;
    private String Neo;
    private String Monero;
    private String Qtum;
    private String OmiseGo;
    private String ZCash;

    DatabaseHelper mDatabaseHelper;


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
        ImageButton ethHit = (ImageButton)findViewById(R.id.ethButton);

        /*
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

        bitcoinTextView = (TextView)findViewById(R.id.bitcoin_value);
        etherTextView = (TextView)findViewById(R.id.ethereum_value);
        rippleTextView = (TextView)findViewById(R.id.ripple_value);
        litecoinTextView = (TextView)findViewById(R.id.litecoin_value);
        dashTextView = (TextView)findViewById(R.id.dash_value);
        nemTextView = (TextView)findViewById(R.id.nem_value);
        neoTextView = (TextView)findViewById(R.id.neo_value);
        moneroTextView = (TextView)findViewById(R.id.monero_value);
        omisegoTextView = (TextView)findViewById(R.id.omisego_value);
        qtumTextView = (TextView)findViewById(R.id.qtum_value);
        zcashTextView = (TextView)findViewById(R.id.zcash_value);

        mDatabaseHelper = new DatabaseHelper(this);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("Updating");
                fetchPrices();
            }
        });

        btcHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayBitcoin(v);
            }
        });


        ethHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                displayDatabase(v);
            }
        });
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

        String load = "Loading";

        // Set all prices to display "Loading"
        bitcoinTextView.setText(load);
        etherTextView.setText(load);
        rippleTextView.setText(load);
        litecoinTextView.setText(load);
        dashTextView.setText(load);
        nemTextView.setText(load);
        neoTextView.setText(load);
        moneroTextView.setText(load);
        omisegoTextView.setText(load);
        qtumTextView.setText(load);
        zcashTextView.setText(load);

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


    /////////////////////////
    ////     Intents     ////
    /////////////////////////
    public void displayBitcoin(View view){
        Intent intent = new Intent(this, BitcoinActivity.class);
        new JSONTask().execute("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD", "btc");
        intent.putExtra(BTC_KEY, Bitcoin);
        intent.putExtra(CRYPTO_KEY, "BTC");
        startActivity(intent);
    }

    public void displayDatabase(View view){
        Intent intent = new Intent(this, ListDataActivity.class);
        startActivity(intent);
    }



    ////////////////////////////
    ////     AsyncTasks     ////
    ////////////////////////////
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

            Log.d(TAG, "onPostExecute: the String is " + result);
            String currency = result.substring(0,3);
            result = result.substring(3);
            AddData(currency);

            if(currency.equals("btc")){
                bitcoinTextView.setText(result);
                Bitcoin = result;
            }
            else if (currency.equals("eth")){
                etherTextView.setText(result);
            }
            else if (currency.equals("xrp")){
                rippleTextView.setText(result);
            }
            else if (currency.equals("ltc")){
                litecoinTextView.setText(result);
            }
            else if (currency.equals("das")){
                dashTextView.setText(result);
            }
            else if (currency.equals("xem")){
                nemTextView.setText(result);
            }
            else if (currency.equals("neo")){
                neoTextView.setText(result);
            }
            else if (currency.equals("xmr")){
                moneroTextView.setText(result);
            }
            else if (currency.equals("omg")){
                omisegoTextView.setText(result);
            }
            else if (currency.equals("qtm")){
                qtumTextView.setText(result);
            }
            else if (currency.equals("zec")){
                zcashTextView.setText(result);
            }
        }
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData)
            Log.d(TAG, "Inserted " + newEntry + " to database");
        else
            Log.d(TAG, "Something went wrong with adding :(");

        Log.d(TAG, "New entry is: " + newEntry);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
package com.example.examenandroidfundamentals;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnIr;
    ImageView mBanderaP;
    final int TEXT_REQUEST = 1;
    final String LOG_TAG = "MIESTADO";
    ImageView mBanderaLand;
    Button mSpainB;
    Button mEngB;
    Button mFrB;
    TextView mDesc;
    int index = 0;
    SharedPreferences mSharedPref;
    final String prefFile = "com.example.exaneabdroidfundamentals";
    final String RESOURCE_KEY = "resourceIndex";
    Imagen[] images = {
            new Imagen() {
                @Override
                public int returnResourceId() {
                    return R.drawable.ic_espana;
                }
            },
            new Imagen() {
                @Override
                public int returnResourceId() {
                    return R.drawable.ic_reino_unido;
                }
            },
            new Imagen() {
                @Override
                public int returnResourceId() {
                    return R.drawable.ic_francia;
                }
            }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int orientation = getResources().getConfiguration().orientation;
        mSharedPref = getSharedPreferences(prefFile,MODE_PRIVATE);
        if(orientation ==  Configuration.ORIENTATION_PORTRAIT){

            btnIr = findViewById(R.id.button);
            mBanderaP = findViewById(R.id.imageView);
            btnIr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchSecondActivity();
                }
            });


            //Restore preferences
            index = mSharedPref.getInt(RESOURCE_KEY,0);
            mBanderaP.setImageResource(images[index].returnResourceId());

        }else{
            mBanderaLand = findViewById(R.id.imageView2);
            mSpainB = findViewById(R.id.button2);
            mEngB = findViewById(R.id.button4);
            mFrB = findViewById(R.id.button3);
            mDesc = findViewById(R.id.textView);
            mSpainB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = 0;
                    mDesc.setText(R.string.descCountry);
                    mBanderaLand.setImageResource(images[index].returnResourceId());
                }
            });
            mEngB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = 1;
                    mDesc.setText(R.string.descCountryEng);
                    mBanderaLand.setImageResource(images[index].returnResourceId());
                }
            });
            mFrB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = 2;
                    mDesc.setText(R.string.descCountryFr);
                    mBanderaLand.setImageResource(images[index].returnResourceId());

                }
            });
            index = mSharedPref.getInt(RESOURCE_KEY,0);
            mBanderaLand.setImageResource(images[index].returnResourceId());
        }
    }
    public void launchSecondActivity(){
        Log.d(LOG_TAG,"Buttonclicked");
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("msg","Activity Main Calling");
        startActivityForResult(intent,TEXT_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(MainActivity2.EXTRA_REPLY);
                Log.d(LOG_TAG,reply);
                index = Integer.parseInt(reply);
                mBanderaP.setImageResource(images[index].returnResourceId());
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putInt(RESOURCE_KEY,index);
        editor.apply();
    }
}
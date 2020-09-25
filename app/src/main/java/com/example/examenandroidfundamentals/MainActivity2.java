package com.example.examenandroidfundamentals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
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
    ImageView mBandera2;
    Button mSpainB;
    Button mEngB;
    Button mFrB;
    final String LOG_TAG = "MIESTADO";
    int index = 0;
    static final String EXTRA_REPLY = "indexValue";
    TextView mDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String intentVal = null;
        if(intent != null){
            intentVal = intent.getStringExtra("msg");
            Log.d(LOG_TAG,intentVal);
        }
        mBandera2 = findViewById(R.id.imageView3);
        mSpainB = findViewById(R.id.button5);
        mEngB = findViewById(R.id.button7);
        mFrB = findViewById(R.id.button6);
        mDesc = findViewById(R.id.textView2);

        mSpainB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                mDesc.setText(R.string.descCountry);
                mBandera2.setImageResource(images[index].returnResourceId());
            }
        });
        mEngB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                mDesc.setText(R.string.descCountryEng);
                mBandera2.setImageResource(images[index].returnResourceId());
            }
        });
        mFrB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                mDesc.setText(R.string.descCountryFr);
                mBandera2.setImageResource(images[index].returnResourceId());

            }
        });

    }
    public void returnReplay(View view){
        String reply = String.valueOf(index);
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,reply);
        setResult(RESULT_OK,replyIntent);
        Log.d(LOG_TAG,"End Second Activity");
        finish();
    }

}
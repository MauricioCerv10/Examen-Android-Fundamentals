package com.example.examenandroidfundamentals;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnIr;
    ImageView mBanderaP;
    final int TEXT_REQUEST = 1;
    final String LOG_TAG = "MIESTADO";
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

        }else{
            //mLogo = findViewById(R.id.imageView2);
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
                mBanderaP.setImageResource(images[Integer.parseInt(reply)].returnResourceId());
            }
        }
    }
}
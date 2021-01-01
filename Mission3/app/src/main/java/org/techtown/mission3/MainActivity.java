package org.techtown.mission3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewUp;
    ImageView imageViewDown;
    Button button;
    BitmapDrawable bitmap;
    int change = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        imageViewUp = findViewById(R.id.upView);
        imageViewDown = findViewById(R.id.downView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(change ==0) {
                    Resources res = getResources();
                    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.cat);
                    imageViewUp.setImageDrawable(bitmap);
                    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.dog);
                    imageViewDown.setImageDrawable(bitmap);
                    change = 1;
                }
                else if(change == 1) {
                    Resources res = getResources();
                    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.dog);
                    imageViewUp.setImageDrawable(bitmap);
                    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.cat);
                    imageViewDown.setImageDrawable(bitmap);
                    change = 0;
                }
            }
        });
    }
}
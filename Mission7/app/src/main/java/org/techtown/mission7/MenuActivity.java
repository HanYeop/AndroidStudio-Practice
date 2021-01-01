package org.techtown.mission7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button customButton = findViewById(R.id.button2);
        Button resultButton = findViewById(R.id.button3);
        Button mdButton = findViewById(R.id.button4);

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name","고객 관리 메뉴 클릭됨!");

                setResult(Activity.RESULT_OK, intent); // 돌려줄 결과 저장
                finish();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name","매출 관리 메뉴 클릭됨!");

                setResult(Activity.RESULT_OK, intent); // 돌려줄 결과 저장
                finish();
            }
        });

        mdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name","상품 관리 메뉴 클릭됨!");

                setResult(Activity.RESULT_OK, intent); // 돌려줄 결과 저장
                finish();
            }
        });
    }
}
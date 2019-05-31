package com.wesuper.allstar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.wesuper.allstar.event.FirstEvent;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener((v) -> {
            EventBus.getDefault().post(new FirstEvent("这是一个事件"));
        });
    }
}

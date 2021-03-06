package com.xun.smalltoutiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.wequick.small.Small;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                Small.openUri("main/ffsttmain", LaunchActivity.this);
                finish();
            }
        });
    }
}

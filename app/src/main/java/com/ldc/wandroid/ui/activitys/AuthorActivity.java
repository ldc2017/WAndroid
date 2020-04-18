package com.ldc.wandroid.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ldc.wandroid.R;

public class AuthorActivity extends AppCompatActivity {


    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AuthorActivity.class);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
    }
}

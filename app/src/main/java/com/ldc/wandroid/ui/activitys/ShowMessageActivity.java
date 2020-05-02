package com.ldc.wandroid.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ldc.wandroid.R;
import com.ldc.wandroid.databinding.ActivityShowMessageBinding;

public class ShowMessageActivity extends AppCompatActivity {
    private ActivityShowMessageBinding binding;


    public final static String KEY_TITLE = "KEY_TITLE";
    public final static String KEY_MESSAGE = "KEY_MESSAGE";
    public final static String KEY_EXTRA = "KEY_EXTRA";


    //获取数据
    private void handleIntent(Intent intent) {
        if (null == intent) {
            return;
        }
        //
        final String mmTitle = intent.getStringExtra(KEY_TITLE);
        final String mmMessage = intent.getStringExtra(KEY_MESSAGE);
        final String mmExtra = intent.getStringExtra(KEY_EXTRA);
        //
        binding.tvMessage.setText(String.format("消息:\n\n\t%s\n\n扩展:\n\n\t%s", mmMessage, mmExtra));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != binding) {
            binding.unbind();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_message);
        handleIntent(getIntent());
        binding.topBar.tvTitle.setText("消息展示");
        binding.topBar.lineMore.setVisibility(View.GONE);
        binding.topBar.lineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

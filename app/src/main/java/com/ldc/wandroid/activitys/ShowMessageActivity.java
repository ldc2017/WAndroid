package com.ldc.wandroid.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ldc.wandroid.R;
import com.ldc.wandroid.databinding.ActivityShowMessageBinding;

public class ShowMessageActivity extends AppCompatActivity {
    ActivityShowMessageBinding binding;


    private static volatile String str_title = "";
    private static volatile String str_message = "";
    private static volatile String str_extra = "";

    public static void actionStart(Context context, final String m_title, String m_message, String m_extra) {
        Intent intent = new Intent(context, ShowMessageActivity.class);
        str_title = m_title;
        str_message = m_message;
        str_extra = m_extra;
        context.startActivity(intent);
    }

    //获取数据
    private void handleIntent(Intent intent) {
        if (null == intent) {
            return;
        }
        str_title = intent.getStringExtra("m_title");
        str_message = intent.getStringExtra("m_message");
        str_extra = intent.getStringExtra("m_extra");
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
        binding.tvMessage.setText(String.format("消息:\n\n\t%s\n\n扩展:\n\n\t%s", str_message, str_extra));
    }
}

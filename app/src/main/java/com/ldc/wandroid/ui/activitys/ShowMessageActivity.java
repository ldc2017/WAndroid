package com.ldc.wandroid.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ldc.wandroid.R;
import com.ldc.wandroid.databinding.ActivityShowMessageBinding;

public class ShowMessageActivity extends AppCompatActivity {
    private ActivityShowMessageBinding binding;


    private final static String str_title = "KEY_TITLE";
    private final static String str_message = "KEY_MESSAGE";
    private final static String str_extra = "KEY_EXTRA";
    //
    private volatile String mmTitle;
    private volatile String mmMessage;
    private volatile String mmExtra;

    public static void actionStart(Context context, final String m_title, String m_message, String m_extra) {
        Intent intent = new Intent(context, ShowMessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(str_title, m_title);
        intent.putExtra(str_message, m_message);
        intent.putExtra(str_extra, m_extra);
        context.startActivity(intent);
    }

    //获取数据
    private void handleIntent(Intent intent) {
        if (null == intent) {
            return;
        }
        mmTitle = intent.getStringExtra(str_title);
        mmMessage = intent.getStringExtra(str_message);
        mmExtra = intent.getStringExtra(str_extra);
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
        binding.tvMessage.setText(String.format("消息:\n\n\t%s\n\n扩展:\n\n\t%s", mmMessage, mmExtra));
    }
}

package com.ldc.wandroid.net;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.Utils;
import com.ldc.wandroid.model.BaseModel;
import com.ldc.wandroid.ui.activitys.FirstActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RequestLogStatusInterceptor implements Interceptor {
    private static final String TAG = "RequestLogStatusInterce";
    private final static int errorCode = -1001;//登录失败
    //
    private final HandlerThread handlerThread;
    private final Handler mHandler;
    private static final int handle_code = 0x000;
    private final Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (handle_code == msg.what) {
                String str_json = (String) msg.obj;
                if (TextUtils.isEmpty(str_json)) return false;
                handlerJson(str_json);
                return true;
            }
            return false;
        }
    };

    public RequestLogStatusInterceptor() {
        handlerThread = new HandlerThread("LoginStatusInterceptor");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper(), callback);

    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        final Response response = chain.proceed(request);
        final MediaType mediaType = response.body().contentType();
        final String content = response.body().string();
        //
        Message message = mHandler.obtainMessage(handle_code);
        message.obj = content;
        mHandler.sendMessage(message);
        //
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }

    // 处理json
    private void handlerJson(final String json) {
        try {
            if (!TextUtils.isEmpty(json)) {
                BaseModel dt = GsonUtils.fromJson(json, BaseModel.class);
                if (errorCode == dt.getErrorCode()) {
                    Intent intent = new Intent(Utils.getApp(), FirstActivity.class);
                    ActivityUtils.startActivity(intent);
                    ActivityUtils.getTopActivity().finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

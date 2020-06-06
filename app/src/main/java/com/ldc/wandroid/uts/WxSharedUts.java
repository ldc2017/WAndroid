package com.ldc.wandroid.uts;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blankj.utilcode.util.ConvertUtils;
import com.ldc.wandroid.R;
import com.ldc.wandroid.service.InitSDKIntentService;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

public class WxSharedUts {
    private static class Single {
        private final static WxSharedUts uts = new WxSharedUts();
    }

    private WxSharedUts() {
    }

    public static WxSharedUts getInstance() {
        return Single.uts;
    }

    public enum WxScene {

        WXSceneSession(SendMessageToWX.Req.WXSceneSession), WXSceneTimeline(SendMessageToWX.Req.WXSceneTimeline), WXSceneFavorite(SendMessageToWX.Req.WXSceneFavorite), WXSceneSpecifiedContact(SendMessageToWX.Req.WXSceneSpecifiedContact);

        final int code;

        WxScene(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public boolean wx_shared_text(final String text, WxScene wxScene) {
        if (!isAvailable()) return false;
        try {
            //初始化一个 WXTextObject 对象，填写分享的文本内容
            WXTextObject textObj = new WXTextObject();
            textObj.text = text;

            //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObj;
            msg.description = text;

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("text");
            req.message = msg;
            req.scene = wxScene.getCode();
            //调用api接口，发送数据到微信
            InitSDKIntentService.getWxApi().sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean wx_shared_image(Bitmap bmp, WxScene wxScene) {
        if (!isAvailable()) return false;
        try {
            SendMessageToWX.Req req = new SendMessageToWX.Req();

            WXImageObject imageObject = new WXImageObject(bmp);
            WXMediaMessage msg = new WXMediaMessage(imageObject);
            msg.mediaObject = imageObject;

            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);

            msg.thumbData = ConvertUtils.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG,60);
            req.transaction = buildTransaction("img");

            req.message = msg;
            req.scene = wxScene.getCode();

            InitSDKIntentService.getWxApi().sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //网页类型分享
    public boolean wx_shared_url(Activity activity, final String str_url, final String str_title, String str_desc, WxScene wxScene) {
        if (null == activity) return false;
        if (!isAvailable()) return false;
        try {
            //初始化一个WXWebpageObject，填写url
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = str_url;

            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = str_title;
            msg.description = str_desc;
            Bitmap thumbBmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.icon_wx_app_shared);
            msg.thumbData = ConvertUtils.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG,60);

            //构造一个Req
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = wxScene.getCode();

            //调用api接口，发送数据到微信
            InitSDKIntentService.getWxApi().sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private boolean isAvailable() {
        return InitSDKIntentService.getWxApi().isWXAppInstalled();
    }
}

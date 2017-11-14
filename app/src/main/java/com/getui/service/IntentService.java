package com.getui.service;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * Created by lkzw on 2017/3/28.
 */

public class IntentService extends GTIntentService {

    public IntentService(){

    }

    @Override
    public void onReceiveServicePid(Context context, int i) {
        Log.e(TAG, "onReceiveServicePid -> " + i);

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Log.e(TAG, "onReceiveMessageData");
        /*String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();*/
        byte[] payload = msg.getPayload();


        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.e(TAG, "receiver payload = " + data);

            // 测试消息为了观察数据变化

        }

    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {
        Log.e(TAG, "onReceiveOnlineState -> " + (b ? "online" : "offline"));
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {
        Log.e(TAG, "onReceiveCommandResult -> " + gtCmdMessage);

    }
}

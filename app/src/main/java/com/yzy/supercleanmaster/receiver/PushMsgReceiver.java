package com.yzy.supercleanmaster.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

/**
 * Created by lkzw on 2017/5/10.
 */

public class PushMsgReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        switch (bundle.getInt(PushConsts.CMD_ACTION)) {

            case PushConsts.GET_MSG_DATA:
                // 获取透传数据
                // String appid = bundle.getString("appid");
                byte[] payload = bundle.getByteArray("payload");

                /*String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");

                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
                System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));*/

                if (payload != null) {
                    String data = new String(payload);
                    /*if (data!=null) {
                        showNotification(context, data);
                    }*/
                    Log.d("GetuiSdkDemo", "receiver payload : " + data);

                    /*payloadData.append(data);
                    payloadData.append("\n");

                    if (MainActivity.tLogView != null) {

                        //showNotification(context, data);// 将透传过来的消息显示在通知栏

                        MainActivity.tLogView.append(data + "\n");
                    }*/
                }
                break;
            default:
                break;

        }
    }
}

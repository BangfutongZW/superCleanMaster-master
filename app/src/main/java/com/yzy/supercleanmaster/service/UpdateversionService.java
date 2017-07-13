/**
 * 
 */
package com.yzy.supercleanmaster.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.ui.MainActivity;


public class UpdateVersionService  extends Service {
	   public static final String Install_Apk = "Install_Apk";  
	    /********download progress step*********/  
	    private static final int down_step_custom = 1;  
	      
	    private static final int TIMEOUT = 10 * 1000;// ��ʱ  
	    private static String down_url;  
	    private static final int DOWN_OK = 1;  
	    private static final int DOWN_ERROR = 0;  
	      
	    private String app_name;  
	    private String updateVersion = "";
	      
	    private NotificationManager notificationManager;  
	    private Notification notification;  
	    private Intent updateIntent;  
	    private PendingIntent pendingIntent;  
	    private RemoteViews contentView;  
	   // public static Context mContext = null;
	          
	    @Override  
	    public IBinder onBind(Intent arg0) {  
	        return null;  
	    }
	    @Override  
	    public int onStartCommand(Intent intent, int flags, int startId) {  
	          
	        app_name = intent.getStringExtra("Key_App_Name");  
	        down_url = intent.getStringExtra("Key_Down_Url");  
	        updateVersion = intent.getStringExtra("Key_Update_Version");  

	        FileUtil.createFile(app_name);  
	          
	        if(FileUtil.isCreateFileSucess == true){
	            createNotification();  
	            createThread();  
	        }else{  
	            Toast.makeText(this, "升级时请勿插入数据线", Toast.LENGTH_SHORT).show();
	            /***************stop service************/  
	            stopSelf();
	            if(notificationManager!=null){
	            	
	            	notificationManager.cancelAll();
	            }
	        }  
	          
	        return super.onStartCommand(intent, flags, startId);  
	    }  

	    @SuppressLint("HandlerLeak")
		private final Handler handler = new Handler() {  
	        @Override  
	        public void handleMessage(Message msg) {  
	            switch (msg.what) {  
	            case DOWN_OK:  
	                  

	                installApk();   
	            	showToast("success");
	                
	                stopService(updateIntent);  
	            	notificationManager.cancelAll();
	                /***stop service*****/  
	                stopSelf();  
	                break;  
	                  
	            case DOWN_ERROR:  

	            	showToast("error");
	            	stopService(updateIntent);
	            	notificationManager.cancelAll();
	                stopSelf();  
	                break;  
	                  
	            default:  
	                //stopService(updateIntent);  
	                /******Stop service******/  
	                stopService(updateIntent);  
	            	notificationManager.cancelAll();
	                stopSelf();  
	                break;  
	            }  
	        }  
	    };  
	      
	    private void installApk() {  
	        // TODO Auto-generated method stub
	        Uri uri = Uri.fromFile(FileUtil.updateFile);  
	        Intent intent = new Intent(Intent.ACTION_VIEW);  

	        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
	          
	        intent.setDataAndType(uri,"application/vnd.android.package-archive");                     
	        UpdateVersionService.this.startActivity(intent);            
	    }  
	      
	    /**
		 * @param string
		 */
		protected void showToast(String string) {
			if("success".equals(string)){
				Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "下载失败", Toast.LENGTH_SHORT).show();
			}
		}
	    public void createThread() {  
	        new DownLoadThread().start();  
	    }  
	      
	    private class DownLoadThread extends Thread{  
	        @Override  
	        public void run() {  
	            // TODO Auto-generated method stub  
	            Message message = new Message();  
	            try {                                 
	                long downloadSize = downloadUpdateFile(down_url,FileUtil.updateFile.toString());  
	                if (downloadSize > 0) {                    
	                    // down success                                       
	                    message.what = DOWN_OK;  
	                    handler.sendMessage(message);                                                                         
	                }  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	                message.what = DOWN_ERROR;  
	                handler.sendMessage(message);  
	            }                         
	        }         
	    }  

	    public void createNotification() {  

	        
	        notification = new Notification();
	        notification.icon= R.drawable.icon;
	        notification.tickerText="下载通知";
	        notification.flags = Notification.FLAG_ONGOING_EVENT;   

	        contentView = new RemoteViews(getPackageName(),R.layout.content_view);  
	        contentView.setTextViewText(R.id.content_view_text1,"0%");  
	        contentView.setImageViewResource(R.id.content_view_image, R.drawable.ic_launcher);
	        contentView.setProgressBar(R.id.content_view_progress, 100, 0, false);  
	        notification.contentView = contentView;  

	        updateIntent = new Intent(this, MainActivity.class);
//	        updateIntent = new Intent(this, mContext.getClass());  
	      	updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);  
	      	//updateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	      	pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);  
	      	notification.contentIntent = pendingIntent;  
	          
	        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);  
	        notificationManager.notify(R.layout.content_view, notification); 
	    }  
	  

	    public long downloadUpdateFile(String down_url, String file)throws Exception {  
	          
	        int down_step = down_step_custom;
	        int totalSize;
	        long downloadCount = 0;
	        int updateCount = 0;
	          
	        InputStream inputStream;  
	        OutputStream outputStream;  
	  
	        URL url = new URL(down_url);  
	        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();  
	        httpURLConnection.setConnectTimeout(TIMEOUT);  
	        httpURLConnection.setReadTimeout(TIMEOUT);
	        totalSize = httpURLConnection.getContentLength();  
	          
	        if (httpURLConnection.getResponseCode() == 404) {  
	            throw new Exception("fail!");
	        }  
	          
	        inputStream = httpURLConnection.getInputStream();  
	        outputStream = new FileOutputStream(file, false);
	          
	        byte buffer[] = new byte[1024];  
	        int readsize = 0;  
	          
	        while ((readsize = inputStream.read(buffer)) != -1) {
	                          
	            outputStream.write(buffer, 0, readsize);  
	            downloadCount += readsize;
	            double minus = (downloadCount * 100 / totalSize) - down_step;
	            if (updateCount == 0 || minus >= updateCount) {  
	                updateCount += down_step;
	                contentView.setTextViewText(R.id.content_view_text1,updateVersion + updateCount + "%");  
	                contentView.setProgressBar(R.id.content_view_progress, 100 , updateCount, false);            
	                notification.contentView = contentView;  
	                notificationManager.notify(R.layout.content_view, notification);             
	            }  
	        } 
	        
	        if (httpURLConnection != null) {  
	            httpURLConnection.disconnect();  
	        }  
	        inputStream.close();  
	        outputStream.close();  
	          
	        return downloadCount;  
	    }
}

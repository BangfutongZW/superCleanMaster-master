/**
 * 
 */
package com.yzy.supercleanmaster.service;

import java.io.File;
import java.io.IOException;


import android.os.Environment;
public class FileUtil {  
    
    public static File updateDir = null;  
    public static File updateFile = null;
    public static final String updateApplication = "updateBft";  
      
    public static boolean isCreateFileSucess;  

    public static void createFile(String app_name) {  
          
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
        		) {  
            isCreateFileSucess = true;  
              
            updateDir = new File(Environment.getExternalStorageDirectory()+ "");  
            updateFile = new File(updateDir + "/" + app_name + ".apk");  
            //updateFile = new File(updateDir + "/2.apk");  
  
            
            if (!updateDir.exists()) {  
                updateDir.mkdirs();  
            }  
            if (!updateFile.exists()) {  
                try {  
                    updateFile.createNewFile();  
                } catch (IOException e) {  
                    isCreateFileSucess = false;
                    e.printStackTrace();  
                }  
            }  
  
        }else{  
            isCreateFileSucess = false;  
        }  
    }  
}  

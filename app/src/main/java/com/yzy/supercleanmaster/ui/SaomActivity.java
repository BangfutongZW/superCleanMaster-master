package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.ui.CaptureActivity;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseActivity;


public class SaomActivity extends BaseActivity {
    TextView scan_tv;
    Button scan_btn;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saom);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        scan_btn=(Button)findViewById(R.id.scan_btn);
        scan_tv=(TextView)findViewById(R.id.scan_tv);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("baoshi","start");
               Intent intent=new Intent(SaomActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            if (data!=null){
                String content=data.getStringExtra("scan_result");

                scan_tv.setText("解码结果： \n" + content);


            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

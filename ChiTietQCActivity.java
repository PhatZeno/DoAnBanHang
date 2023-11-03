package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class ChiTietQCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_qcactivity);

        WebView mView2 = (WebView) findViewById(R.id.webView1);

        mView2.getSettings().setJavaScriptEnabled(true);
        mView2.loadUrl("https://www.thegioididong.com/tin-tuc/minigame-co-gia-tri-giai-thuong-len-den-250-trieu-choi-ngay-1536832");
    }
}
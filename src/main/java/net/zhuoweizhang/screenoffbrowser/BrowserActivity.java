/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.zhuoweizhang.screenoffbrowser;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Simple activity that shows the requested HTML page. This utility is
 * purposefully very limited in what it supports, including no network or
 * JavaScript.
 */
public class BrowserActivity extends Activity {
    private static final String TAG = "HTMLViewer";

    private WebView mWebView;
    private View mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mWebView = (WebView) findViewById(R.id.webview);
        mLoading = findViewById(R.id.loading);

        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new ViewClient());

        WebSettings s = mWebView.getSettings();
        s.setUseWideViewPort(true);
        s.setSupportZoom(true);
        s.setBuiltInZoomControls(true);
        s.setDisplayZoomControls(false);
        s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setBlockNetworkLoads(false);

        s.setJavaScriptEnabled(true);
        s.setDefaultTextEncodingName("utf-8");

        final Intent intent = getIntent();
        //if (intent.hasExtra(Intent.EXTRA_TITLE)) {
        //    setTitle(intent.getStringExtra(Intent.EXTRA_TITLE));
        //}
		String theUrl = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (theUrl == null) {
			finish();
			return; // WHAT
		}

		mWebView.loadUrl(theUrl);

        //mWebView.loadUrl(String.valueOf(intent.getData()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (!getIntent().hasExtra(Intent.EXTRA_TITLE)) {
                BrowserActivity.this.setTitle(title);
            }
        }
    }

    private class ViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            mLoading.setVisibility(View.GONE);
        }
    }
}

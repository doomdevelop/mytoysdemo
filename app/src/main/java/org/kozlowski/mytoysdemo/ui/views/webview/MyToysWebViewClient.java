package org.kozlowski.mytoysdemo.ui.views.webview;

import android.net.Uri;
import android.provider.SyncStateContract;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.kozlowski.mytoysdemo.util.Constants;

/**
 * Created by and on 12.12.16.
 */

public class MyToysWebViewClient extends WebViewClient {
    public MyToysWebViewClient() {
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().contains(Constants.BASE_HOST)) {
            view.loadUrl(url);
            return false;
        }
        return true;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }
}

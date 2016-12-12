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
        Log.d("WEBView ","load hist: "+Uri.parse(url).getHost());
        if (Uri.parse(url).getHost().contains(Constants.BASE_HOST)) {
            Log.d("WEBView ","load url: "+url);
            view.loadUrl(url);
            return false;
        }
        Log.e("WEBView ","wrong url: "+url);
        return true;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        Log.d("WEBView ",error.toString());
    }
}

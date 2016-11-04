package bwf.androiddemos.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by lizhangfeng on 16/5/11.
 * description:
 */
public class WebViewUtils {

    /**
     * 初始化webview
     */
    public static void initWebView(WebView webView) {
        webView.requestFocus();
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setInitialScale(35);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings settings = webView.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSaveFormData(false);
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAppCacheEnabled(true);// 开启缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 缓存优先模式
        settings.setAppCacheMaxSize(8 * 1024 * 1024);// 设置最大缓存为8M
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDefaultTextEncodingName("gbk");

    }

    /**
     * 实现普通的js交互
     * BridgeWebView webView
     */
    public static void addJavaScriptInterface(final Context context) {
        //点击图片
//        webView.registerHandler("click_pic", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {
//                WebViewBean bean = JSON.parseObject(data, WebViewBean.class);
//                if (bean != null) {
//                    App.getApp().setPhotoList(bean.pics);
//                    Intent intent = new Intent(context, ArticlePhotoBrowserActivity.class);
//                    intent.putExtra(ArticlePhotoBrowserActivity.CURRENT, StringUtil.isEmpty(bean.index) ? 0 : Integer.parseInt(bean.index));
//                    context.startActivity(intent);
//                }
//            }
//        });
//        //点击头像
//        webView.registerHandler("click_avatar", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {
//                WebViewBean bean = JSON.parseObject(data, WebViewBean.class);
//                if (bean != null) {
//                    OpenUtils.OpenUserPagerActivity(context, bean.uid);
//                }
//            }
//        });
//        //o获取webview高度
//        webView.registerHandler("load_complete", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {
//
//            }
//        });

    }



}

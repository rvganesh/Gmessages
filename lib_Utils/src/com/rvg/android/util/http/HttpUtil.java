package com.rvg.android.util.http;

import java.io.IOException;

import android.os.Build;

import com.github.kevinsawicki.http.HttpRequest;
import com.rvg.android.util.Constants;

/**
 * Thin wrapper around the <a href="https://github.com/kevinsawicki/http-request"><em>http-request</em></a> library.
 */
public class HttpUtil {
    private static final int CONNECT_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 10000;

    private static final String HTTP_USER_AGENT = System.getProperties().getProperty("http.agent") + " " + Constants.TAG + Constants.VERSION;

    static {
        // See http://developer.android.com/reference/java/net/HttpURLConnection.html
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            HttpRequest.keepAlive(false);
        }
    }

    public static void setDefaultOptions(HttpRequest req) throws IOException {
        req.userAgent(HTTP_USER_AGENT);
        req.connectTimeout(CONNECT_TIMEOUT);
        req.readTimeout(READ_TIMEOUT);
        req.acceptGzipEncoding();
        req.uncompress(true);
        req.trustAllCerts();
        req.trustAllHosts();
    }

    public static HttpRequest get(String url) throws IOException {
        HttpRequest res = HttpRequest.get(url);
        setDefaultOptions(res);
        return res;
    }

    public static HttpRequest post(String url) throws IOException {
        HttpRequest res = HttpRequest.get(url);
        setDefaultOptions(res);
        return res;
    }
}

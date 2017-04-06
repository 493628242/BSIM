package com.wangjiyuan.im.utils;

import android.util.Log;


import com.wangjiyuan.im.config.UrlConfig;
import com.wangjiyuan.im.http.HttpInterfaces;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wjy on 2016/11/15.
 */

public class HttpUtils {
    private static volatile HttpUtils singleton;

    private HttpInterfaces iApi;
    private Retrofit retrofit;

    private HttpUtils() {
        init();
    }

    /**
     * 获取HttpUtil对象 单例模式
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (singleton == null) {
            synchronized (HttpUtils.class) {
                if (singleton == null) {
                    singleton = new HttpUtils();
                }
            }
        }
        return singleton;
    }

    /**
     * 初始化Retrofit
     */
    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client())
                .build();

    }

    /**
     * 获取HttpInterface接口对象
     *
     * @return
     */
    public HttpInterfaces getHttpInterfaces() {
        if (iApi == null)
            iApi = retrofit.create(HttpInterfaces.class);
        return iApi;
    }

    /**
     * 网络连接使用okhttp
     *
     * @return
     */
    private OkHttpClient client() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        return chain.proceed(request);
                    }
                })
                .build();

        return client;
    }
}

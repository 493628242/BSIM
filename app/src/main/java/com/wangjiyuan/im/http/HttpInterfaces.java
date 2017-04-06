package com.wangjiyuan.im.http;

import com.wangjiyuan.im.bean.User;
import com.wangjiyuan.im.config.SharedConfig;
import com.wangjiyuan.im.config.UrlConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wjy on 2017/2/1.
 */
public interface HttpInterfaces {
    /**
     * 用户登录的接口
     *
     * @param phonenumber
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConfig.LOGIN)
    Observable<User> Login(@Field(SharedConfig.PHONE_NUBER) String phonenumber,
                           @Field(SharedConfig.PASSWORD) String password);

    @FormUrlEncoded
    @POST(UrlConfig.VERIFY)
    Call<User> Verify(@Field(SharedConfig.PHONE_NUBER) String phonenumber,
                      @Field(SharedConfig.TOKEN) String token);


}

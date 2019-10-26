package com.example.odm.basemvvm.Net;

import com.example.odm.basemvvm.BannerBean;
import com.example.odm.basemvvm.Entity.ResponseResult;
import com.example.odm.basemvvm.NavDataBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * description: Retrofit接口类
 * author: ODM
 * date: 2019/10/26
 */
public interface RetrofitApiService {

    public static final String BASE_URL = "https://www.wanandroid.com/";
    //wanAndroid的
    @GET("banner/json")
    Observable<ResponseResult<List<BannerBean>>> getBanner();

    @GET("navi/json")
    Observable<ResponseResult<List<NavDataBean>>>  getNavData();


    /**
     * Retrofit 上传文件,前面的sequence是单表单@Part("sequence") RequestBody sequence
     *  多表单就用 @FieldMap Map<String, String> userMaps
     * @param url
     * @param sequence
     * @param file
     * @return
     */
    @POST
    @Multipart
    Observable<ResponseBody> uploadPic(@Url String url, @Part("sequence") RequestBody sequence , @Part MultipartBody.Part file);


    /**
     * Retrofit下载文件     10以上用@streaming。不会造成oom
     *
     * @param url
     * @return
     */
    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);

    /**
     * Retrofit下载文件
     * @param url
     * @param range
     * @return
     */
    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url, @Header("RANGE") String range);

}

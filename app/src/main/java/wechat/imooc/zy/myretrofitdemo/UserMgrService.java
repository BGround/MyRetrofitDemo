package wechat.imooc.zy.myretrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zy on 2019/3/1.
 */

public interface UserMgrService {

    @GET("login")
    Call<UserInfoModel> login(@Query("username") String username,@Query("pwd") String pwd);


}

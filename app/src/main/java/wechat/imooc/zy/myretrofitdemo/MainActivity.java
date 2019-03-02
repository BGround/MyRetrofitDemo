package wechat.imooc.zy.myretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "zhangya";

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void OnClick() {
        //建立retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //建立userModel和userMgrService对象
        UserMgrService userMgrService = retrofit.create(UserMgrService.class);

        //调用登录方法
        final Call<UserInfoModel>  call = userMgrService.login("BGround", "829912abc");

        //同步发送请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<UserInfoModel> response = call.execute();
                    Log.d(TAG, "run: "+response.body().code);
                    Toast.makeText(getApplicationContext(),"response = "+response.body().code,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //发送异步请求
        call.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                try {
                    Response<UserInfoModel> response2 = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"response = "+response.body().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {

            }
        });

    }
}

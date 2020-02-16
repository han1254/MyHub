package com.example.myhub.mvvm.view.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.base.utils.statusbar.StatusBarUtil;
import com.example.myhub.R;

public class SplashActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorVerdure));

//        textView = findViewById(R.id.tv);
//        textView.setText("text");
//        btn = findViewById(R.id.btn);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        Uri uri = getIntent().getData();
//        if ( uri != null && uri.toString().startsWith(AppConfig.REDIRECT_URL)) {
//            String code = uri.getQueryParameter("code");
//            HttpServiceFactory.getClientService().getAccessToken(AppConfig.OPENHUB_CLIENT_ID, AppConfig.OPENHUB_CLIENT_SECRET, code)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new DefaultObserver<AccessToken>(new NetWorkExceptionController() {
//                        @Override
//                        public void badNetWorkError() {
//
//                        }
//
//                        @Override
//                        public void connectError() {
//
//                        }
//
//                        @Override
//                        public void timeOutError() {
//
//                        }
//
//                        @Override
//                        public void parseError() {
//
//                        }
//
//                        @Override
//                        public void unknownError() {
//
//                        }
//                    }) {
//                        @Override
//                        public void onSuccess(AccessToken accessToken) {
//                            textView.setText(accessToken.toString());
//                            getPersonalInfo(accessToken.getAccessToken());
//                        }
//
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//        }
//    }
//
//    private void getPersonalInfo(String accessToken) {
//        HttpServiceFactory.getUserService(accessToken)
//                .getPersonInfo(true)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<Response<User>>(new NetWorkExceptionController() {
//                    @Override
//                    public void badNetWorkError() {
//
//                    }
//
//                    @Override
//                    public void connectError() {
//
//                    }
//
//                    @Override
//                    public void timeOutError() {
//
//                    }
//
//                    @Override
//                    public void parseError() {
//
//                    }
//
//                    @Override
//                    public void unknownError() {
//
//                    }
//                }) {
//                    @Override
//                    public void onSuccess(Response<User> userResponse) {
//                        textView.setText(userResponse.body().getId());
//                    }
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }


}

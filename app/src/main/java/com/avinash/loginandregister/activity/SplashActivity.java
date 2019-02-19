package com.avinash.loginandregister.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.avinash.loginandregister.R;
import com.avinash.loginandregister.helper.SessionManager;


public class SplashActivity extends AppCompatActivity {
    Button txtLogIn, txtSignUp;
    LinearLayout llbottomtxt;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        session = new SessionManager(getApplicationContext());

        Log.e("Statues",String.valueOf(session.isLoggedIn()));

        txtLogIn = findViewById(R.id.txtLogIn);
        txtSignUp = findViewById(R.id.txtSignUp);
        llbottomtxt = findViewById(R.id.llbottomtxt);


        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
                finish();
            }
        });

        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
        if (session.isLoggedIn()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            llbottomtxt.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.activity_trans_right_out, R.anim.activity_trans_right_out);
    }

    private boolean isNetworkAvailableK3() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        } else {
            //Toast.makeText(MainActivity.this, "Internet Require", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void reduceSize(View v) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(v,
                "scaleX", 0.97f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(v,
                "scaleY", 0.97f);
        scaleDownX.setDuration(50);
        scaleDownY.setDuration(50);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDown.start();
    }

    public void regainSize(View v) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(
                v, "scaleX", 1f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(
                v, "scaleY", 1f);
        scaleDownX.setDuration(50);
        scaleDownY.setDuration(50);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDown.start();
    }
}

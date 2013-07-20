package com.netease.enfinite.usermanager;

import com.netease.enfinite.R;
import com.netease.enfinite.slidemenu.SlidingMenuFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.social.core.BaiduSocialException;
import com.baidu.social.core.BaiduSocialListener;
import com.baidu.social.core.Utility;
import com.baidu.sociallogin.BaiduSocialLogin;

public class LoginActivity extends Activity {

	
	private static final String Tag = "LoginActivity";
	private Button loginButton ;
	private RelativeLayout login_with_weibo;
	private RelativeLayout login_with_qq ;
	
	private BaiduSocialLogin socialLogin;
	private final static String appKey = BaiduSocialShareConfig.mbApiKey;
	final Handler handler = new Handler(Looper.getMainLooper());

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		View actionbar_view = (View)getLayoutInflater().inflate(R.layout.style_actionbar1, null);
		
		ImageView actionbarImageView = (ImageView)actionbar_view.findViewById(R.id.actionbar_image);
		actionbarImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginFail();
			}
		});
		
		TextView titileTextView = (TextView)actionbar_view.findViewById(R.id.acitonbar_title);
		titileTextView.setText(R.string.string_login);
		bar.setCustomView(actionbar_view);
		setContentView(R.layout.login_page);
		
		loginButton = (Button)findViewById(R.id.login);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				loginOK();
			}
		});
		
		// 实例化baidu社会化登录，传入appkey
		socialLogin = BaiduSocialLogin.getInstance(this, appKey);

		// 设置支持腾讯微博单点登录的appid
		socialLogin.supportQQSso(BaiduSocialShareConfig.QQ_SSO_APP_KEY);

		// 设置支持新浪微博单点登录的appid
		socialLogin.supportWeiBoSso(BaiduSocialShareConfig.SINA_SSO_APP_KEY);
		
		//weibo 登陆
		login_with_weibo = (RelativeLayout)findViewById(R.id.login_with_weibo);
		login_with_weibo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login_with_weibo();
			}
		});
		
		//qq登陆
		login_with_qq = (RelativeLayout)findViewById(R.id.login_with_qq);
		login_with_qq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login_with_qq();
			}
		});
		
	}
	
	//QQ login
	protected void login_with_qq() {
		// TODO Auto-generated method stub
		if (socialLogin.isAccessTokenValid(Utility.SHARE_TYPE_QZONE)) {
			socialLogin.getUserInfoWithShareType(LoginActivity.this,
					Utility.SHARE_TYPE_QZONE, new UserInfoListener());
		} else
			socialLogin.authorize(LoginActivity.this,
					Utility.SHARE_TYPE_QZONE, new UserInfoListener());

	}
	
	//weibo login
	protected void login_with_weibo() {
		// TODO Auto-generated method stub
		if (socialLogin.isAccessTokenValid(Utility.SHARE_TYPE_SINA_WEIBO)) {
			socialLogin.getUserInfoWithShareType(LoginActivity.this,
					Utility.SHARE_TYPE_SINA_WEIBO,
					new UserInfoListener());
		} else
			socialLogin.authorize(LoginActivity.this,
					Utility.SHARE_TYPE_SINA_WEIBO,
					new UserInfoListener());

	}

	//验证用户的用户名和密码是否正确 
	private boolean vefifyUser(){
		return false ;
	}
	
	class UserInfoListener implements  BaiduSocialListener {

		@Override
		public void onAuthComplete(Bundle values) {
			// TODO Auto-generated method stubis
		}

		@Override
		public void onApiComplete(String responses) {
			// TODO Auto-generated method stub
			final String responseStr = responses;
			handler.post(new Runnable() {
				@Override
				public void run() {
					//info = (EditText) findViewById(R.id.editText1);
					//info.setText(Utility.decodeUnicode(responseStr));
				}
			});
		}
		@Override
		public void onError(BaiduSocialException e) {
			final String error = e.toString();
			handler.post(new Runnable() {
				@Override
				public void run() {
					Utility.showAlert(LoginActivity.this,
						getResources().getString(R.string.error), error);
				}
			});
		}

	}
	
	/**
	 * 登陆成功
	 */
	private void loginOK(){
		Intent intent = new Intent();
		String userName = "LCD";  
        
        Bundle bundle = new Bundle();  
          
        bundle.putString("UserName", userName);  
          
        intent.putExtras(bundle);  
          
        this.setResult(SlidingMenuFragment.RESULT_LOGIN_OK, intent);  
        
        Log.i(Tag,"login ok");
        //结束本Activity B  
        finish();  
	}
	
	/**
	 * 登陆失败
	 */
	private void loginFail(){
		 this.setResult(SlidingMenuFragment.RESULT_LOGIN_FAIL);  
	        finish();  
	}
	
}

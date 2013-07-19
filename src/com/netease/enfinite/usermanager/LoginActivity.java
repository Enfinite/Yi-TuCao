package com.netease.enfinite.usermanager;

import com.netease.enfinite.R;
import com.netease.enfinite.R.string;
import com.netease.enfinite.slidemenu.SlidingMenuFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity {

	
	private static final String Tag = "LoginActivity";
	private Button loginButton ;
	
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
		
	}
	
	//验证用户的用户名和密码是否正确 
	private boolean vefifyUser(){
		return false ;
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

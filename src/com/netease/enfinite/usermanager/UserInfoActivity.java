package com.netease.enfinite.usermanager;

import com.netease.enfinite.R;
import com.netease.enfinite.slidemenu.SlidingMenuFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends Activity {

	public static final int USER_IMAGE_UDPDATE = 1; //
	public static final int USER_NAME_UDPDATE = 2;
	public static final int USER_INFO_NO_UPDATE = 0;
	public static final int LOGOUT_CODE = 3;
	
	private EditText userName;
	private ImageView userImage;
	
	private RelativeLayout bind_sina;
	private RelativeLayout bind_qqzone;
	private RelativeLayout bind_douban;
	
	private Button logout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		
		userImage = (ImageView)findViewById(R.id.userImage);
		userName = (EditText)findViewById(R.id.userName);
		userName.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(v.getId()==R.id.userName)
					userName.setFocusable(true);
				if(event.getAction() == MotionEvent.ACTION_OUTSIDE)
					userName.setFocusable(false); //edit text lose focus
				return false;
			}
		});
		/**
		 * °ó¶¨ÐÂÀË
		 */
		bind_sina = (RelativeLayout)findViewById(R.id.bind_sina);
		bind_sina.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(UserInfoActivity.this, "hello", Toast.LENGTH_LONG).show();
			}
		});
		
		/**
		 * °ó¶¨qqzone
		 */
		bind_qqzone = (RelativeLayout)findViewById(R.id.bind_qqzone);
		bind_qqzone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(UserInfoActivity.this, "hello1", Toast.LENGTH_LONG).show();
			}
		});
		
		/**
		 * °ó¶¨¶¹°ê
		 */
		bind_douban = (RelativeLayout)findViewById(R.id.bind_douban);
		bind_douban.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(UserInfoActivity.this, "hello2", Toast.LENGTH_LONG).show();
			}
		});
		
		logout = (Button)findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				backLogout();
			}
		});
		
		
		initActionBar();
	}

	private void initActionBar(){
		ActionBar bar = getActionBar();
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		View actionbar_view = (View)getLayoutInflater().inflate(R.layout.style_actionbar1, null);
		
		ImageView actionbarImageView = (ImageView)actionbar_view.findViewById(R.id.actionbar_image);
		actionbarImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				backNoUpdate();
			}
		});
		
		TextView titileTextView = (TextView)actionbar_view.findViewById(R.id.acitonbar_title);
		titileTextView.setText(R.string.string_user_info);
		bar.setCustomView(actionbar_view);
	}
	
	private void backNoUpdate(){
		setResult(USER_INFO_NO_UPDATE);
		finish();
	}
	
	private void backWithUpdate(){
		
	}
	//ÍË³öµ±Ç°µÇÂ½×´Ì¬
	private void backLogout(){
		setResult(LOGOUT_CODE);
		finish();
	}
}

package com.netease.enfinite.usermanager;

import com.netease.enfinite.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private EditText uName ;
	private EditText uPassWord ;
	private EditText uPassWordAgain ;
	
	private Button register;
	
	public static final int REGISTER_OK = 1;
	public static final int REGISTER_FAIL = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page);
		uName = (EditText) findViewById(R.id.userName);
		uPassWord = (EditText) findViewById(R.id.userPassword);
		uPassWordAgain = (EditText) findViewById(R.id.userPasswordAgain);
		
		/**
		 * ÓÃ»§×¢²á
		 */
		register = (Button)findViewById(R.id.register);
		register.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(uName.getText() != null && uPassWord !=null && uPassWordAgain !=null){
					
				}
			}
		});
	}
	

}

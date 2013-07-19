package com.netease.enfinite.usermanager;

import com.netease.enfinite.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		View actionbar_view = (View)getLayoutInflater().inflate(R.layout.style_actionbar1, null);
		TextView titileTextView = (TextView)actionbar_view.findViewById(R.id.acitonbar_title);
		titileTextView.setText(R.string.string_login);
		bar.setCustomView(actionbar_view);
		setContentView(R.layout.login_page);
	}
	
}

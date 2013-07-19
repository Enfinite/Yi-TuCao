package com.netease.enfinite.slidemenu;



import com.netease.enfinite.R;
import com.netease.enfinite.usermanager.LoginActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SlidingMenuFragment extends Fragment {
	
	private Button loginButton;
	private Button registerButton ;
	private Spinner uNameSpinner;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {             
         View view = inflater.inflate(R.layout.user_fragment_layout, container, false); 
         
         loginButton = (Button)view.findViewById(R.id.login);
         loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
			}
		});
       
         registerButton = (Button)view.findViewById(R.id.register);
         registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Press register1", Toast.LENGTH_LONG).show();
			}
		});
         
         
         /**
          * 登录以后初始化用户名以及用户头像
          */
         uNameSpinner = (Spinner)view.findViewById(R.id.uNameSpinner);
         ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,
        		 new String[]{"Enfinite"});
         uNameSpinner.setAdapter(spinnerAdapter);
         uNameSpinner.setClickable(false); //不会弹出下拉菜单
         uNameSpinner.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Press uName", Toast.LENGTH_LONG).show();
				return false;
			}
		});
         return view;
    }     
	
	@Override
    public void onCreate(Bundle savedInstanceState) {     
        super.onCreate(savedInstanceState);   
    }
	
}

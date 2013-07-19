package com.netease.enfinite;



import java.util.ArrayList;
import java.util.HashMap;

import com.netease.enfinite.slidemenu.ListViewAdapter;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SlidingMenuFragment extends Fragment {
	
	private Button loginButton;
	private Button registerButton ;
	private Spinner uNameSpinner;
	
	private ListView listView1;
	private ListView listView2;
	private ListView listView3;
	
	private ArrayList<HashMap<String, Object>> arrayList1;
	private ArrayList<HashMap<String, Object>> arrayList2;
	private ArrayList<HashMap<String, Object>> arrayList3;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {             
         View view = inflater.inflate(R.layout.user_fragment_layout, container, false);
        
         loginButton = (Button)view.findViewById(R.id.login);
         loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Press login", Toast.LENGTH_LONG).show();
			}
		});
         //loginButton.setBackgroundResource(R.drawable.shape_login);
         registerButton = (Button)view.findViewById(R.id.register);
         registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Press register", Toast.LENGTH_LONG).show();
			}
		});
         
         listView1 = (ListView)view.findViewById(R.id.slidemenu_list1);
         listView2 = (ListView)view.findViewById(R.id.slidemenu_list2);
         listView3 = (ListView)view.findViewById(R.id.slidemenu_list3);
         initSlideMenuItem();
         
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
	
	private void initSlideMenuItem(){
		 initListView1();
		 initListView2();
		 initListView3();
	}
	private void initListView1(){
		arrayList1 = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
		tempHashMap1.put("TextViewResID", getResources().getString(R.string.string_item1));
		tempHashMap1.put("ImageViewResID", R.drawable.ic_right_arrow);
		//tempHashMap.put("CheckBoxShow", 0);
		arrayList1.add(tempHashMap1);
		HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
		tempHashMap2.put("TextViewResID", getResources().getString(R.string.string_item2));
		tempHashMap2.put("ImageViewResID", R.drawable.ic_right_arrow);
		arrayList1.add(tempHashMap2);
		HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
		tempHashMap3.put("TextViewResID", getResources().getString(R.string.string_item3));
		tempHashMap3.put("ImageViewResID", R.drawable.ic_right_arrow);
		//tempHashMap.put("CheckBoxShow", 0);
		arrayList1.add(tempHashMap3);
		listView1.setAdapter(new ListViewAdapter(getActivity(), arrayList1));
	}
	private void initListView2(){
		arrayList2 = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
		tempHashMap1.put("TextViewResID", getResources().getString(R.string.string_item4));
		//tempHashMap.put("CheckBoxShow", 0);
		arrayList2.add(tempHashMap1);
		HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
		tempHashMap2.put("TextViewResID", getResources().getString(R.string.string_item5));
		tempHashMap2.put("CheckBoxShow", 1);
		arrayList2.add(tempHashMap2);
		listView2.setAdapter(new ListViewAdapter(getActivity(), arrayList2));
	}
	private void initListView3(){
		arrayList3 = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
		tempHashMap1.put("TextViewResID", getResources().getString(R.string.string_item6));
		tempHashMap1.put("ImageViewResID", R.drawable.ic_right_arrow);
		//tempHashMap.put("CheckBoxShow", 0);
		arrayList3.add(tempHashMap1);
		HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
		tempHashMap2.put("TextViewResID", getResources().getString(R.string.string_item7));
		tempHashMap2.put("ImageViewResID", R.drawable.ic_right_arrow);
		//tempHashMap.put("CheckBoxShow", 0);
		arrayList3.add(tempHashMap2);
		HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
		tempHashMap3.put("TextViewResID", getResources().getString(R.string.string_item8));
		tempHashMap3.put("ImageViewResID", R.drawable.ic_right_arrow);
		//tempHashMap.put("CheckBoxShow", 0);
		arrayList3.add(tempHashMap3);
		listView3.setAdapter(new ListViewAdapter(getActivity(), arrayList3));
	}
}

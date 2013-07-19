package com.netease.enfinite.slidemenu;

import java.util.ArrayList;
import java.util.HashMap;

import com.netease.enfinite.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	 private LayoutInflater layoutInflater;  
	 private Context context; 
	 private ArrayList<HashMap<String, Object>> data;  
	
	public final class ViewHolder{
		public TextView mTextView;
		public ImageView mImageView;
		public CheckBox mCheckBox;
	}

	public ListViewAdapter(Context context,ArrayList<HashMap<String,Object>> data) {
		// TODO Auto-generated constructor stub
        this.context = context;  
        this.data = data;  
        this.layoutInflater = LayoutInflater.from(context);  
    }  
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(arg1==null){
			viewHolder = new ViewHolder();
			arg1 = layoutInflater.inflate(R.layout.slidemenu_listview_item, null);
			viewHolder.mTextView = (TextView)arg1.findViewById(R.id.list_item_text);
			viewHolder.mImageView = (ImageView)arg1.findViewById(R.id.list_item_image);
			viewHolder.mCheckBox = (CheckBox)arg1.findViewById(R.id.list_item_checkbox);
			arg1.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder)arg1.getTag();
		}
		//绑定数据以及事件促发
		//System.out.println(data);
		String textString = (String)(data.get(arg0).get("TextViewResID"));
		viewHolder.mTextView.setText(textString);
		Integer imageResId = (Integer) (data.get(arg0).get("ImageViewResID"));
		if(imageResId !=null){
			viewHolder.mImageView.setVisibility(View.VISIBLE);
			viewHolder.mImageView.setBackgroundResource(imageResId); //显示imageview
		}
		Integer showFlag = (Integer)(data.get(arg0).get("CheckBoxShow"));
		if(showFlag != null) //显示checkbox
			viewHolder.mCheckBox.setVisibility(View.VISIBLE);
		else 
			viewHolder.mCheckBox.setVisibility(View.GONE);//不显示checkbox
		
		//System.out.println(""+textString+imageResId+showFlag);
		
		return arg1;
	}

}

/**
 * ÍÂ²ÛÖ°³¡----Ò³Ãæ
 */
package com.netease.enfinite;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ZhiChang_Fragment extends Fragment {
	
	public ZhiChang_Fragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_zhichang,
				container, false);
		TextView dummyTextView = (TextView) rootView
				.findViewById(R.id.section_label);
		dummyTextView.setText(getString(R.string.string_ZhiChang));
		return rootView;
	}
}

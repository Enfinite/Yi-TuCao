/**
 * ÍÂµÃ¾ªÑÞ----Ò³Ãæ
 */
package com.netease.enfinite;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JingYan_Fragment extends Fragment {
	
	 public JingYan_Fragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_jingyan,
				container, false);
		TextView dummyTextView = (TextView) rootView
				.findViewById(R.id.section_label);
		dummyTextView.setText(getString(R.string.string_JingYan));
		return rootView;
	}
}

/**
 * ÍÂµÃ»ðÈÈ----Ò³Ãæ
 */
package com.netease.enfinite;

import java.util.Arrays;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HuoRe_Fragment extends Fragment implements OnRefreshListener<ListView>{

	private static final String[] STRINGS = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance",
		"Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
		"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
		"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
		"Allgauer Emmentaler" };
	
	PullToRefreshListView plv;
	
	public HuoRe_Fragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*View rootView = inflater.inflate(R.layout.fragment_huore,
				container, false);
		TextView dummyTextView = (TextView) rootView
				.findViewById(R.id.section_label);
		dummyTextView.setText(getString(R.string.string_HuoRe));*/
		
		PullToRefreshListView plv = (PullToRefreshListView) inflater.inflate(
				R.layout.fragment_huore, container, false);
		
		ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				Arrays.asList(STRINGS));
		plv.setAdapter(adapter);

		plv.setOnRefreshListener(this);

		// Now just add ListView to ViewPager and return it
		//container.addView(plv, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		return plv;
	}
	
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetDataTask(refreshView).execute();
	}

	private static class GetDataTask extends AsyncTask<Void, Void, Void> {

		PullToRefreshBase<?> mRefreshedView;

		public GetDataTask(PullToRefreshBase<?> refreshedView) {
			mRefreshedView = refreshedView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mRefreshedView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}

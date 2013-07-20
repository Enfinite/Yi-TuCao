package com.netease.enfinite;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.netease.enfinite.slidemenu.SlidingMenuFragment;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	
	private static final String TAG_STRING = "MainActivity";
	
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	PagerTabStrip mPagerTitleStrip;
	
	public static int mTimes = 0;
	public static final int pagerCount = 6;
	
	/**
	 * 侧边栏菜单及其显示动画相关的成员变量
	 */
	/*private CanvasTransformer mTransformer;
	private Interpolator mInterp;*/
	SlidingMenu mSlidingMenu;
	
	private View actionbar_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mPagerTitleStrip = (PagerTabStrip)findViewById(R.id.pager_title_strip);
		mPagerTitleStrip.setDrawFullUnderline(false);
		

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(),initiateFragment(pagerCount));

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(pagerCount*1000);//默认显示第二个tab页面
		
		initiateSlideMenu(); //初始化侧边栏菜单
	
		initActionBar();//初始化自定义的actionbar
		
		setContentView(R.layout.list_item_general);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			mSlidingMenu.toggle(true);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		Log.i(TAG_STRING, "onActivityResult "+arg0+" "+arg1);
		super.onActivityResult(arg0, arg1, arg2);
	}

	/**
	 * 初始化各个页面的Fragment
	 * @param size 页面的数量
	 * @return  包含所有Fragment的List
	 */
	public List<Fragment> initiateFragment(int size){
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(new HuoRe_Fragment());
		list.add(new JingYan_Fragment());
		list.add(new ShenTu_Fragment());
		list.add(new ShengHuo_Fragment());
		list.add(new ZhiChang_Fragment());
		list.add(new XinXian_Fragment());
		return list;
	}
	
	/**
	 * 初始化自定义的actionbar
	 */
	private void initActionBar(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		bar.setDisplayHomeAsUpEnabled(true);//ActionBar左上角显示箭头
		bar.setDisplayShowHomeEnabled(false);//左上角不显示应用图标
		/*bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionbar_view = (View)getLayoutInflater().inflate(R.layout.style_actionbar, null);
		ImageView actionbarImage = (ImageView)actionbar_view.findViewById(R.id.actionbar_image);
		actionbarImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.actionbar_image:
					mSlidingMenu.toggle(true);
					break;
				default:
					break;
			}
			}
		});
		bar.setCustomView(actionbar_view);*/
	}

	/**
	 * 初始化侧边栏菜单的方法
	 */
	private void initiateSlideMenu(){
	   /*mInterp = new Interpolator() {
			@Override
			public float getInterpolation(float t) {
				t -= 1.0f;
				return t * t * t + 1.0f;
			}		
		};*/
		
		/*mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.translate(0, canvas.getHeight()*(1-mInterp.getInterpolation(percentOpen)));
			}			
		};*/
		
		/*mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}	
		};*/
		
		mSlidingMenu = new SlidingMenu(getApplicationContext());
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeEnabled(true);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		mSlidingMenu.setSlidingEnabled(true);
		//mSlidingMenu.setBehindCanvasTransformer(mTransformer);
		mSlidingMenu.setMenu(R.layout.slidingmenu);
		
		getFragmentManager()
		.beginTransaction().replace(R.id.content_frame, new SlidingMenuFragment())
		.commit();
	}
	
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		private final String Tag = "SectionsPagerAdapter";
		private int mCount;
		private List<Fragment> mFragmentList;
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			Log.i(Tag, "destroy item "+ position);
			super.destroyItem(container, position % mCount, object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			Log.i(Tag, "instantiate item "+ position);
			return super.instantiateItem(container, position % mCount);
		}

		public SectionsPagerAdapter(FragmentManager fm , List<Fragment> list) {
			super(fm);
			mFragmentList = list;
			mCount = list.size();
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Log.i(Tag, "get item "+ position);
			mTimes++;
			/*Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, mTimes);
			fragment.setArguments(args);
			return fragment;*/
			return mFragmentList.get(position % mCount);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
		 return Integer.MAX_VALUE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Log.i(Tag, "position = " + position);
			if(position >= mCount) 
				position = position % mCount;
			Locale l = Locale.getDefault();
			switch (position ) {
			case 0:
				return getString(R.string.string_HuoRe).toUpperCase(l);
			case 1:
				return getString(R.string.string_JingYan).toUpperCase(l);
			case 2:
				return getString(R.string.string_ShenTu).toUpperCase(l);
			case 3:
				return getString(R.string.string_ShengHuo).toUpperCase(l);
			case 4:
				return getString(R.string.string_ZhiChang).toUpperCase(l);
			case 5:
				return getString(R.string.string_XinXian).toUpperCase(l);
			default :
				return null;
			}
			
		}
	}
}

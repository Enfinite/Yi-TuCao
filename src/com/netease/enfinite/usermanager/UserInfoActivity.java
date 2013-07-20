package com.netease.enfinite.usermanager;

import java.io.File;

import com.baidu.sharesdk.BaiduShareException;
import com.baidu.sharesdk.BaiduSocialShare;
import com.baidu.sharesdk.ShareListener;
import com.baidu.sharesdk.Utility;
import com.netease.enfinite.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
	
	private BaiduSocialShare socialShare;
	private final static String appKey = BaiduSocialShareConfig.mbApiKey;
	
	final Handler handler = new Handler(Looper.getMainLooper());
	
	private Bitmap photo = null;
	
	private String[] items = new String[] { "ѡ�񱾵�ͼƬ", "����" };
	/* ͷ������ */
	private static final String IMAGE_FILE_NAME = "faceImage.jpg";

	/* ������ */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		
		userImage = (ImageView)findViewById(R.id.userImage);
		if(UserInfo.getUser_image() != null)
			userImage.setImageDrawable(UserInfo.getUser_image());
		userImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog();
			}
		});
		userName = (EditText)findViewById(R.id.userName);
		userName.setText(UserInfo.getUser_name());
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
		
		//ʵ����baidu��ữ��������appkey
		socialShare = BaiduSocialShare.getInstance(this, appKey);

		//����֧����Ѷ΢�������¼��appid
		socialShare.supportQQSso(BaiduSocialShareConfig.QQ_SSO_APP_KEY);
				
		//����֧������΢�������¼��appid
		 socialShare.supportWeiBoSso(BaiduSocialShareConfig.SINA_SSO_APP_KEY);
		
		/**
		 * ������
		 */
		bind_sina = (RelativeLayout)findViewById(R.id.bind_sina);
		//֮ǰ�Ѿ���Ȩ��½����
		if(socialShare.isAccessTokenValid(Utility.SHARE_TYPE_SINA_WEIBO))
			bind_sina.setBackgroundDrawable(getResources().getDrawable(R.drawable.sina_share_bind_shape));
		bind_sina.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				auth_sinaweibo();
			}
		});
		
		/**
		 * ��qqzone
		 */
		bind_qqzone = (RelativeLayout)findViewById(R.id.bind_qqzone);
		if (socialShare.isAccessTokenValid(Utility.SHARE_TYPE_QZONE))
			bind_qqzone.setBackgroundDrawable(getResources().getDrawable(R.drawable.qqzone_share_bind_shape));
		bind_qqzone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				auth_qzone();
			}
		});
		
		/**
		 * �󶨶���
		 */
		bind_douban = (RelativeLayout)findViewById(R.id.bind_douban);
		bind_douban.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bind_douban.setBackgroundColor(getResources().getColor(R.color.douban_bind));
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
		UserInfo.setUser_name(userName.getText().toString());
		setResult(USER_INFO_NO_UPDATE);
		finish();
	}
	
	private void backWithUpdate(){
		/*if(photo!= null){
			Intent intent = new Intent();
			intent.putExtra("image", photo);
			setResult(USER_IMAGE_UDPDATE, intent);
			}*/
		UserInfo.setUser_name(userName.getText().toString());
		setResult(USER_IMAGE_UDPDATE);
		finish();
		}
	//�˳���ǰ��½״̬
	private void backLogout(){
		UserInfo.setUser_image(null);
		UserInfo.setUser_name(null);
		setResult(LOGOUT_CODE);
		finish();
	}
	
	//���sinaWeibo������֤��Ȩ  ���Ѿ���Ȩ�����е�������󣬻���ñ�����������token����
		public void auth_sinaweibo() {
			if (!socialShare.isAccessTokenValid(Utility.SHARE_TYPE_SINA_WEIBO)) {
				socialShare.authorize(this, Utility.SHARE_TYPE_SINA_WEIBO,
						new ShareListener() {
							@Override
							public void onAuthComplete(Bundle values) {
								handler.post(new Runnable() {
									@Override
									public void run() {
										//bind_sina.setBackgroundColor(getResources().getColor(R.color.weibo_bind));
										bind_sina.setBackgroundDrawable(getResources().getDrawable(R.drawable.sina_share_bind_shape));
									}
								});
							}

							@Override
							public void onApiComplete(String responses) {

							}

							@Override
							public void onError(BaiduShareException e) {
								final String errorMsg = e.toString();
								handler.post(new Runnable() {
									@Override
									public void run() {
										Utility.showAlert(UserInfoActivity.this,
										getResources().getString(R.string.error), errorMsg);
									}
								});
							}

						});
			} else {
				socialShare.cleanAccessToken(Utility.SHARE_TYPE_SINA_WEIBO);
				bind_sina.setBackgroundDrawable(getResources().getDrawable(R.drawable.share_bind_button_shape));
			}

		}
		
		//���QQ�ռ������֤��Ȩ  ���Ѿ���Ȩ�����е�������󣬻���ñ�����������token����
		public void auth_qzone() {

			if (!socialShare.isAccessTokenValid(Utility.SHARE_TYPE_QZONE)) {

				socialShare.authorize(this, Utility.SHARE_TYPE_QZONE,
						new ShareListener() {
							@Override
							public void onAuthComplete(Bundle values) {
								handler.post(new Runnable() {
									@Override
									public void run() {
										bind_qqzone.setBackgroundDrawable(getResources().getDrawable(R.drawable.qqzone_share_bind_shape));
									}
								});
							}

							@Override
							public void onApiComplete(String responses) {

							}

							@Override
							public void onError(BaiduShareException e) {
								final String errorMsg = e.toString();
								handler.post(new Runnable() {
									@Override
									public void run() {
										Utility.showAlert(UserInfoActivity.this,
										getResources().getString(R.string.error), errorMsg);
									}
								});
							}

						});
			} else {
				socialShare.cleanAccessToken(Utility.SHARE_TYPE_QZONE);
				bind_qqzone.setBackgroundDrawable(getResources().getDrawable(R.drawable.share_bind_button_shape));
			}

		}
		
		/**
		 * ͷ����
		 */
		
		
		/**
		 * ��ʾѡ��Ի���
		 */
		private void showDialog() {

			new AlertDialog.Builder(this)
					.setTitle("����ͷ��")
					.setItems(items, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case 0:
								Intent intentFromGallery = new Intent();
								intentFromGallery.setType("image/*"); // �����ļ�����
								intentFromGallery
										.setAction(Intent.ACTION_GET_CONTENT);
								startActivityForResult(intentFromGallery,
										IMAGE_REQUEST_CODE);
								break;
							case 1:

								Intent intentFromCapture = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								// �жϴ洢���Ƿ�����ã����ý��д洢
								if (Tools.hasSdcard()) {

									intentFromCapture.putExtra(
											MediaStore.EXTRA_OUTPUT,
											Uri.fromFile(new File(Environment
													.getExternalStorageDirectory(),
													IMAGE_FILE_NAME)));
								}

								startActivityForResult(intentFromCapture,
										CAMERA_REQUEST_CODE);
								break;
							}
						}
					})
					.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).show();

		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			//����벻����ȡ��ʱ��
			if (resultCode != RESULT_CANCELED) {

				switch (requestCode) {
				case IMAGE_REQUEST_CODE:
					startPhotoZoom(data.getData());
					break;
				 case CAMERA_REQUEST_CODE:
					 if (Tools.hasSdcard()){
						 startPhotoZoom(Uri.fromFile(new File(Environment
								 .getExternalStorageDirectory(),IMAGE_FILE_NAME)));
						 } else 
						 {
							 Toast.makeText(UserInfoActivity.this, "δ�ҵ��洢�����޷��洢��Ƭ��",
									 Toast.LENGTH_LONG).show();
							 }
					 break;
				case RESULT_REQUEST_CODE:
					if (data != null) {
						getImageToView(data);
					}
					break;
				}
			}
			super.onActivityResult(requestCode, resultCode, data);
		}

		/**
		 * �ü�ͼƬ����ʵ��
		 * 
		 * @param uri
		 */
		public void startPhotoZoom(Uri uri) {

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, "image/*");
			// ���òü�
			intent.putExtra("crop", "true");
			
			// outputX outputY �ǲü�ͼƬ���
			intent.putExtra("outputX", 100);
			intent.putExtra("outputY", 100);
			// aspectX aspectY �ǿ�ߵı���
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			
			intent.putExtra("return-data", true);
			startActivityForResult(intent, 2);
		}

		/**
		 * ����ü�֮���ͼƬ����
		 * 
		 * @param picdata
		 */
		private void getImageToView(Intent data) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				photo = extras.getParcelable("data");
				BitmapDrawable drawable = new BitmapDrawable(photo);
				UserInfo.setUser_image(drawable);
				userImage.setImageDrawable(drawable);
			}
		}
}

package com.dqt.blog.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.dqt.app.BaseActivity;
import com.dqt.comm.imageselector.PictureSelectorActivity;
import com.dqt.comm.view.ScreenTools;
import com.dqt.ctrl.ActivityCtrl;
import com.sunday.app.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;

public class BlogEditActivity extends BaseActivity {
	
//	private final int IMAGE_OPEN = 1;
	private BlogEditActivity activity;
	private GridView gridView1;
	private static String pathImages[];
	private Bitmap bmp;
	private ArrayList<HashMap<String, Object>> imageItem;
	private SimpleAdapter simpleAdapter;
	private HashMap<String, Object> defIcon;
	private EditText et_gps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blog_edit_main);
		activity = this;
        gridView1 = (GridView) findViewById(R.id.gridView1);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gridview_addpic);
        imageItem = new ArrayList<HashMap<String, Object>>();
        defIcon = new HashMap<String, Object>();
        defIcon.put("itemImage", bmp);
//        imageItem.add(defIcon);
        simpleAdapter = new SimpleAdapter(this, 
        		imageItem, R.layout.blog_edit_addimg, 
                new String[] { "itemImage"}, new int[] { R.id.imageView1}); 
        simpleAdapter.setViewBinder(new ViewBinder() {  
		    @Override  
		    public boolean setViewValue(View view, Object data,  
		            String textRepresentation) {  
		        // TODO Auto-generated method stub  
		        if(view instanceof ImageView && data instanceof Bitmap){  
		            ImageView i = (ImageView)view;  
		            i.setImageBitmap((Bitmap) data);  
		            return true;  
		        }  
		        return false;  
		    }
        });  
        gridView1.setAdapter(simpleAdapter);
        gridView1.setOnItemClickListener(new OnItemClickListener() {
  			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
  				if (imageItem.size() == 10) {
  					Toast.makeText(BlogEditActivity.this, "test", Toast.LENGTH_SHORT).show();
  				}
  				else if (position == (imageItem.size() - 1)) {
  					Toast.makeText(BlogEditActivity.this, "test test", Toast.LENGTH_SHORT).show();
  					/* 直接打开系统相册
  					Intent intent = new Intent(Intent.ACTION_PICK,       
  	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
  	                startActivityForResult(intent, IMAGE_OPEN);
  					*/
  					/*
  	                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT  
  					intent.addCategory(Intent.CATEGORY_OPENABLE);  
  					intent.setType("image/jpeg");
  	            	startActivityForResult(intent, IMAGE_OPEN);
  	            	*/
  	            	ActivityCtrl.getInstance().gotoActivityForResult(activity, 
  	            									PictureSelectorActivity.class, 
  	            									PictureSelectorActivity.IMAGE_OPEN);
  	            	
  				} else {
  					dialog(position);
  				}
				
			}
  		});
        resetImage();
        /* GPS */
        et_gps = (EditText) findViewById(R.id.et_gps);
        registerGPS();
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        super.onActivityResult(requestCode, resultCode, data);        
        if(resultCode == RESULT_OK && requestCode == PictureSelectorActivity.IMAGE_OPEN) {
        	/*
            Uri uri = data.getData();  
            pathImage = ScreenTools.getImageAbsolutePath(this, uri);
            */
            Bundle bundle = data.getExtras();
            if (null != bundle) {
            	String[] uriArray = bundle.getStringArray(PictureSelectorActivity.PICTURE_SELECTOR_RESTULT);
            	pathImages = uriArray;
            }
            resetImage();
        }
    }
    
    private void resetLayout() {
        int lineCount = (imageItem.size() - 1) / 4 + 1;
    	ScreenTools.setLayoutSize(gridView1, -1, 
    							(getResources().getDimensionPixelSize(R.dimen.add_img_height)) * lineCount);
    }
    
    @Override
	protected void onResume() {
		super.onResume();
	}

    protected void resetImage() {
    	imageItem.clear();
    	imageItem.add(defIcon);
		if (null != pathImages) {
			for (int i = 0; i < pathImages.length; i++) {
				String pathImage = pathImages[i];
				if (!TextUtils.isEmpty(pathImage)) {
					Bitmap addbmp = decodeSampledBitmapFromResource(pathImage, 100, 100);
					HashMap<String, Object> map = new HashMap<String, Object>();
			        map.put("itemImage", addbmp);
			        
			        imageItem.add(imageItem.size() - 1, map);
			        
			        simpleAdapter = new SimpleAdapter(this, 
			        		imageItem, R.layout.blog_edit_addimg, 
			                new String[] {"itemImage"}, new int[] { R.id.imageView1}); 
			        simpleAdapter.setViewBinder(new ViewBinder() {  
					    @Override  
					    public boolean setViewValue(View view, Object data,  
					            String textRepresentation) {  
					        // TODO Auto-generated method stub  
					        if(view instanceof ImageView && data instanceof Bitmap){  
					            ImageView i = (ImageView)view;  
					            i.setImageBitmap((Bitmap) data);  
					            return true;  
					        }  
					        return false;  
					    }
			        }); 
			        gridView1.setAdapter(simpleAdapter);
			        simpleAdapter.notifyDataSetChanged();
				}
			}
		}
		resetLayout();
	}
    
    public static Bitmap decodeSampledBitmapFromResource(String path,   
            int reqWidth, int reqHeight) {   
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小   
        final BitmapFactory.Options options = new BitmapFactory.Options();   
        options.inJustDecodeBounds = true;   
		BitmapFactory.decodeFile(path, options);

        // 调用上面定义的方法计算inSampleSize值   
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);   
        // 使用获取到的inSampleSize值再次解析图片   
        options.inJustDecodeBounds = false;   
        return BitmapFactory.decodeFile(path, options);
    }
    
    public static int calculateInSampleSize(BitmapFactory.Options options,   
            int reqWidth, int reqHeight) {   
        // 源图片的高度和宽度   
        final int height = options.outHeight;   
        final int width = options.outWidth;   
        int inSampleSize = 1;   
        if (height > reqHeight || width > reqWidth) {   
            // 计算出实际宽高和目标宽高的比率   
            final int heightRatio = Math.round((float) height / (float) reqHeight);   
            final int widthRatio = Math.round((float) width / (float) reqWidth);   
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高   
            // 一定都会大于等于目标的宽和高。   
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;   
        }   
        return inSampleSize;   
    } 
    
    protected void dialog(final int position) {
    	AlertDialog.Builder builder = new Builder(this);
    	builder.setMessage("确定移除吗？");
    	builder.setTitle("移除图片");
    	builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.dismiss();
    			imageItem.remove(position);
    	        simpleAdapter.notifyDataSetChanged();
    		}
    	});
    	builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.dismiss();
    			}
    		});
    	builder.create().show();
    }
    
    /* GPS -------------------------------------------------------------------------*/
    private void updateGps(Location loc) {
    	if (null != loc) {
    		et_gps.setText("经度:" + loc.getLongitude() + ",纬度:" + loc.getLatitude());
    	} else {
    		et_gps.setText("");
    	}
    }
    
    public  LocationManager lm;  
    private static final String TAG="GPS Services"; 
    
    private void registerGPS(){  
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);  
        //判断GPS是否正常启动  
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  
            Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show();  
            //返回开启GPS导航设置界面  
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);     
            startActivityForResult(intent,0);   
            return;  
        }  
          
        //为获取地理位置信息时设置查询条件  
        String bestProvider = lm.getBestProvider(getCriteria(), true);  
        //获取位置信息  
        //如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER  
        Location location= lm.getLastKnownLocation(bestProvider);  
//        Location location= lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);   
        updateGps(location);  
        
        
        //监听状态  
        lm.addGpsStatusListener(listener);  
        //绑定监听，有4个参数      
        //参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种  
        //参数2，位置信息更新周期，单位毫秒      
        //参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息      
        //参数4，监听      
        //备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新     
          
        // 1秒更新一次，或最小位移变化超过1米更新一次；  
        //注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置  
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);  
//        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);  
    }
    
    //位置监听  
    private LocationListener locationListener=new LocationListener() {  
          
        /** 
         * 位置信息变化时触发 
         */  
        public void onLocationChanged(Location location) {  
            Log.i(TAG, "时间："+location.getTime());   
            Log.i(TAG, "经度："+location.getLongitude());   
            Log.i(TAG, "纬度："+location.getLatitude());   
            Log.i(TAG, "海拔："+location.getAltitude()); 
            updateGps(location);
        }  
          
        /** 
         * GPS状态变化时触发 
         */  
        public void onStatusChanged(String provider, int status, Bundle extras) {  
            switch (status) {  
            //GPS状态为可见时  
            case LocationProvider.AVAILABLE:  
                Log.i(TAG, "当前GPS状态为可见状态");  
                break;  
            //GPS状态为服务区外时  
            case LocationProvider.OUT_OF_SERVICE:  
                Log.i(TAG, "当前GPS状态为服务区外状态");  
                break;  
            //GPS状态为暂停服务时  
            case LocationProvider.TEMPORARILY_UNAVAILABLE:  
                Log.i(TAG, "当前GPS状态为暂停服务状态");  
                break;  
            }  
        }  
      
        /** 
         * GPS开启时触发 
         */  
        public void onProviderEnabled(String provider) {  
            Location location=lm.getLastKnownLocation(provider);  
            updateGps(location);
        }  
      
        /** 
         * GPS禁用时触发 
         */  
        public void onProviderDisabled(String provider) {
        	updateGps(null);
        }  
    };  
    
  //状态监听  
    GpsStatus.Listener listener = new GpsStatus.Listener() {  
        public void onGpsStatusChanged(int event) {  
            switch (event) {  
            //第一次定位  
            case GpsStatus.GPS_EVENT_FIRST_FIX:  
                Log.i(TAG, "第一次定位");  
                break;  
            //卫星状态改变  
            case GpsStatus.GPS_EVENT_SATELLITE_STATUS:  
                Log.i(TAG, "卫星状态改变");  
                //获取当前状态  
                GpsStatus gpsStatus=lm.getGpsStatus(null);  
                //获取卫星颗数的默认最大值  
                int maxSatellites = gpsStatus.getMaxSatellites();  
                //创建一个迭代器保存所有卫星   
                Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();  
                int count = 0;       
                while (iters.hasNext() && count <= maxSatellites) {       
                    GpsSatellite s = iters.next();       
                    count++;       
                }     
                System.out.println("搜索到："+count+"颗卫星");  
                break;  
            //定位启动  
            case GpsStatus.GPS_EVENT_STARTED:  
                Log.i(TAG, "定位启动");  
                break;  
            //定位结束  
            case GpsStatus.GPS_EVENT_STOPPED:  
                Log.i(TAG, "定位结束");  
                break;  
            }  
        };  
    };  
    
    /** 
     * 返回查询条件 
     * @return 
     */  
    private Criteria getCriteria(){  
        Criteria criteria=new Criteria();  
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细   
        criteria.setAccuracy(Criteria.ACCURACY_FINE);      
        //设置是否要求速度  
        criteria.setSpeedRequired(false);  
        // 设置是否允许运营商收费    
        criteria.setCostAllowed(false);  
        //设置是否需要方位信息  
        criteria.setBearingRequired(false);  
        //设置是否需要海拔信息  
        criteria.setAltitudeRequired(false);  
        // 设置对电源的需求    
        criteria.setPowerRequirement(Criteria.POWER_LOW);  
        return criteria;  
    }  
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		lm.removeUpdates(locationListener);
	}
}

//package com.app.alldemo.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.util.Log;
//
//import com.jp.google.buy.util.IabHelper;
//import com.jp.google.buy.util.IabResult;
//import com.jp.google.buy.util.Inventory;
//import com.jp.google.buy.util.Purchase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class GoogleBuyUtil {
//	private static final String TAG="GoogleBuyUtil";
//	/** 应用程序的公钥 */
//	private String packName;
//	public final static String base64EncodedPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqrjaOiy128XOiW+vGfp7hzEZ685QDvuw3kVeboecT5tI2GQXg6PpZs0mbQ5d5gOoFcEijz5bcjgzrahgVAvPPvlXZ0E88M96u913srdTNHgHURZGCmICiLN56WGGm0iJf64pWp8qVPYdy9/0HUAeMpIXB5sjv6QnbpiIOWE4y0h4yM0UwDXaJIQs1TM8o0tKFAD20FT43PgpN9eWuEjMqnE+TS3RLIdrGmknKcOMqMO4gGay5v7z8LcYwY3ZfBkO+TwA9yR+zzs2jUp3DYdUCoeSJbIw7U0XXuVM1V2lS5poi+U+XzuI6F5sBtzGAINJ9jbwsh+8RTjaxgr73yYTrwIDAQAB";
//	private IabHelper mHelper;
//	// 商品列表
//    private ArrayList<String> list=new ArrayList<String>();
//    private static final String SKU_BUY1 = "dianzanen_200credits_discountprice";//购买商品的名称
//    private static final String SKU_BUY2 = "dianzanen_200credits";//购买商品的名称
//    private static final String SKU_BUY3 = "dianzanen_500credits";//购买商品的名称
//    private static final String SKU_BUY4 = "dianzanen_1000credits";//购买商品的名称
// // (arbitrary) request code for the purchase flow
//    private static final int RC_REQUEST = 10001;
//	public static GoogleBuyUtil instance = null;
//	public static GoogleBuyUtil getInstance(){
//		synchronized(GoogleBuyUtil.class){
//			if(instance == null){
//				instance = new GoogleBuyUtil();
//			}
//		}
//		return instance;
//	}
//	/**
//	 * 判断本机是否安装了指定包名对应的应用
//	 *
//	 * @param packageName 指定包名
//	 *
//	 * */
//	public boolean apkInstalled(Context context,String packageName) {
//		PackageInfo packageInfo = null;
//		try {
//			packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
//			if(packageInfo != null){
//				packageInfo = null;
//				return true;
//			}
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			packageInfo = null;
//		}
//		Log.v(TAG, "本机未安装包名为'" + packageName + "'的应用");
//		return false;
//	}
//	/**获取应用包名*/
//	public String getPageName(Context context){
//		return context.getApplicationContext().getPackageName();
//	}
//	/**
//	 * 初始化
//	 */
//	public void init(Context context){
//		if (apkInstalled(context, "com.android.vending")) {
//			this.packName=getPageName(context);
//			mHelper = new IabHelper(context, base64EncodedPublicKey);
//		}else {
//			Log.v(TAG, "没安装google商店");
//		}
//	}
//	/**
//	 * 初始化in-app biling数据
//	 */
//	private void startSetUp(){
//		Log.e(TAG,"startSetUp()");
//		mHelper.enableDebugLogging(true);
//		// 检查是否有权限和连接到Google Billing service是否成功
//		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
//            public void onIabSetupFinished(IabResult result) {
//            	Log.e(TAG,"onIabSetupFinished()");
//                if (result.isSuccess()) {
//                	Log.e(TAG,"查询成功");
//                	list.add(SKU_BUY1);
//                	list.add(SKU_BUY2);
//                	list.add(SKU_BUY3);
//                	list.add(SKU_BUY4);
//                	mHelper.queryInventoryAsync(mGotInventoryListener,list);
//                }else {
//                	Log.v(TAG, "查询失败");
//				}
//            }
//        });
//	}
//
//	// 查询库存（inventory）完成接口
//    IabHelper.QueryInventoryFinishedListener mGotInventoryListener=new IabHelper.QueryInventoryFinishedListener() {
//
//        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
//            if(result.isFailure()) {
//                return;
//            }
//            Log.d(TAG, "查询操作完成");
//            //返回所有购买清单
//            List<Purchase> purchases=inventory.getAllPurchases();
//            Log.e(TAG,"已购买的商品"+purchases.size());
//            Purchase gasPurchase=inventory.getPurchase(SKU_BUY1);
//            if(gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
//                mHelper.consumeAsync(inventory.getPurchase(SKU_BUY1), mConsumeFinishedListener);
//            }
//            Purchase gasPurchase2=inventory.getPurchase(SKU_BUY2);
//            if(gasPurchase2 != null && verifyDeveloperPayload(gasPurchase2)) {
//            	mHelper.consumeAsync(inventory.getPurchase(SKU_BUY2), mConsumeFinishedListener);
//            }
//            Purchase gasPurchase3=inventory.getPurchase(SKU_BUY3);
//            if(gasPurchase3 != null && verifyDeveloperPayload(gasPurchase3)) {
//            	mHelper.consumeAsync(inventory.getPurchase(SKU_BUY3), mConsumeFinishedListener);
//            }
//            Purchase gasPurchase4=inventory.getPurchase(SKU_BUY4);
//            if(gasPurchase4 != null && verifyDeveloperPayload(gasPurchase4)) {
//            	Log.d(TAG, "属于SKU_GAS");
//            	mHelper.consumeAsync(inventory.getPurchase(SKU_BUY4), mConsumeFinishedListener);
//            }
//        }
//    };
//    /** Verifies the developer payload of a purchase. */
//    boolean verifyDeveloperPayload(Purchase p) {
//        if(packName.equalsIgnoreCase(p.getDeveloperPayload())) {
//            return true;
//        }
//        return false;
//    }
//  //消耗商品的回调
//  	IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
//  		public void onConsumeFinished(Purchase purchase, IabResult result) {
//  			Log.e(TAG,"消耗商品");
//  		}
//  	};
//  	/**
//	 * 购买
//	 * 必须调用自Activity的UI线程。
//  	 * @param activity
//  	 * @param sku 购买商品的名称
//  	 */
//	private void pay(Activity activity,String sku){
//		//必须定义payload，且区分其它应用的payload
//		mHelper.launchPurchaseFlow(activity, sku, RC_REQUEST,
//				new IabHelper.OnIabPurchaseFinishedListener(){
//			 		//支付方法回调的监听
//					@Override
//					public void onIabPurchaseFinished(IabResult result,
//							Purchase info) {
//						if(result.isSuccess()){
//			        		//购买成功
//			        		Log.v(TAG,"支付成功");
//							if (info.getSku().equals(SKU_BUY2)) {
//								Log.e(TAG,"购买商品2");
//							}else if(info.getSku().equals(SKU_BUY3)){
//								Log.e(TAG,"购买商品3");
//							}else if(info.getSku().equals(SKU_BUY4)){
//								Log.e(TAG,"购买商品4");
//							}else if(info.getSku().equals(SKU_BUY1)){
//								//只购买一次
//								Log.e(TAG,"购买商品1");
//							}
//							//消耗掉
//							mHelper.consumeAsync(info, mConsumeFinishedListener);
//			            }else {
//			            	Log.v(TAG, "支付失败");
//						}
//					}
//		}, packName);
//	}
//}

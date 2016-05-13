package com.app.alldemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.text.ClipboardManager;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.LinkMovementMethod;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.alldemo.R;

/**
 * 显示键盘
 * 隐藏键盘
 * 是否接入了网络
 * 检查连接网络类型 wifi:wifi连接类型；mobile：移动手机网；""没网
 * 复制
 * 粘贴
 * 发送邮件
 * 去google市场，对应用评价
 * 将px值转换为dip或dp值，保证尺寸大小不变
 * TextView显示表情
 * popu上边
 * popu下边
 * viewhold的一种写法
 * 手动计算list高度
 * view的延迟效果，有时可增加动画的流畅性
 * 改变图片的rgb值
 * 图片泛黄
 * 颜色矩阵研究
 */
public class ViewUtils {
    private static ViewUtils instance;

    public static ViewUtils getInstance() {
        if (instance == null) {
            instance = new ViewUtils();
        }
        return instance;
    }

    /**
     * 显示键盘
     */
    public void showKeybord(EditText editText) {
        InputMethodManager inputManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean result = inputManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        if (!result) {
            editText.requestFocus();
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeybord(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()){
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * 是否接入了网络
     */
    public boolean checkNetWorkStatus(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 检查连接网络类型 wifi:wifi连接类型；mobile：移动手机网；""没网
     *
     * @param context
     * @return
     */
    public String networkStatus(Context context) {
        String status = "";
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo infos = manager.getActiveNetworkInfo();
            if (infos != null) {
                if (infos.getType() == ConnectivityManager.TYPE_WIFI) {
                    status = "wifi";
                } else if (infos.getType() == ConnectivityManager.TYPE_MOBILE) {
                    status = "mobile";
                }
            }
        }
        return status;
    }

    /**
     * 复制
     */
    @SuppressWarnings("deprecation")
    public boolean copy(String content, Context context) {
        // 得到剪贴板管理器
        try {
            ClipboardManager cmb = (ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(content.trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 粘贴
     */
    @SuppressWarnings("deprecation")
    public String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * 发送邮件
     *
     * @param context
     * @param title   标题
     * @param message 内容
     * @param appName 名字
     */
    public void sendEamil(Context context, String title, String message,
                          String emaildress, String appName) {
        // 系统邮件系统的动作为android.content.Intent.ACTION_SEND
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // 设置邮件默认地址
        email.putExtra(Intent.EXTRA_EMAIL, emaildress);
        // 设置邮件默认标题
        email.putExtra(Intent.EXTRA_SUBJECT, title);
        // 设置要默认发送的内容
        email.putExtra(Intent.EXTRA_TEXT, message);
        // 调用系统的邮件系统
        context.startActivity(Intent.createChooser(email, appName));
    }

    /**
     * 去google市场，对应用评价
     *
     * @param context
     * @param packName
     * 应用的包名
     * @return
     */
    private final String COMMENT_URL_HEAD = "https://play.google.com/store/apps/details?id=";
    private String googlePackName = "com.android.vending";

    public boolean gotoGoogleComment(Context context, String packName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    googlePackName, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null) {
            // 未安装google play客户端
            return false;
        } else {
            String url = COMMENT_URL_HEAD + packName;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setPackage(googlePackName);
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            context.startActivity(Intent.createChooser(intent,
                    context.getText(R.string.app_name)));
            return true;
        }
    }
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * （DisplayMetrics类中属性density）
     * @return
     */
    public int dp2px(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }
    /**
     * TextView显示表情
     *
     * @param context
     * @param textView
     * @param htmlContent
     */
    public void viewHTmlText(final Context context, TextView textView, String htmlContent) {
        CharSequence charSequence = Html.fromHtml(htmlContent, new ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                // 获得系统资源的信息，比如图片信息  
                Drawable drawable = context.getResources().getDrawable(getResourceId(source));
                if (source.equals("face3")) {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2,
                            drawable.getIntrinsicHeight() / 2);
                } else {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight());
                }
                return drawable;
            }
        }, null);
        textView.setText(charSequence);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private int getResourceId(String source) {
        if (source.equals("face1")) {
            return R.drawable.face1;
        } else if (source.equals("face2")) {
            return R.drawable.face2;
        } else if (source.equals("face3")) {
            return R.drawable.face3;
        } else if (source.equals("face4")) {
            return R.drawable.face4;
        } else if (source.equals("face5")) {
            return R.drawable.face5;
        } else {
            return R.drawable.face1;
        }
    }

    /**
     * popu上边
     * @param activity
     * @param v
     */
    public void popuUp(Activity activity,View v){
        View view = activity.getLayoutInflater().inflate(R.layout.popu_up, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
        // 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 获得位置
        v.getLocationOnScreen(location);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        int x=(location[0] + v.getWidth() / 2) - popupWidth / 2;
        int y=location[1] - popupHeight;
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y);
    }

    public void popuLeft(Activity activity,View v){
        View view = activity.getLayoutInflater().inflate(R.layout.popu_left, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
        // 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 获得位置
        v.getLocationOnScreen(location);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        int x=location[0]-popupWidth;
        int y=(location[1]+v.getHeight()/2)-popupHeight/2;
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y);
    }

    /**
     * popu下边
     * @param activity
     * @param v
     */
    public void popuDown(Activity activity,View v){
        View view = activity.getLayoutInflater().inflate(R.layout.popu_up, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int[] location = new int[2];
        // 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 获得位置
        v.getLocationOnScreen(location);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.showAsDropDown(v);
    }

    /**
     * viewhold的一种写法
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    /**
     * 手动计算list高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * view的延迟效果，有时可增加动画的流畅性
     * @param view
     */
    private void postDelayedTest(final View view) {
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }, 1000);
    }

    /**
     * 改变图片的rgb值
     * @param context
     * @param resId
     * @return
     * float[] src = new float[]{
        R=a*R, b*G, c*B, d*A, e,//红色,第五列最后一个值表示红色颜色的偏移量
        G=f*R, g*G, h*B, i*A, j//绿色
        B=k*R, l*G, m*B, n*A, o,//蓝色
        A=p*R, q*G, r*B, s*A, t//透明度
        };
        V(虚拟坐标，不起任何作用)
          颜色矩阵                           单位
         R   G  B   A   V               R    G    B    A    V
        M11 M12 M13 M14 M15            1.0  0.0  0.0  0.0  0.0
        M21 M22 M23 M24 M25            0.0  1.0  0.0  0.0  0.0
        M31 M32 M33 M34 M35            0.0  0.0  1.0  0.0  0.0
        M41 M42 M43 M44 M45            0.0  0.0  0.0  1.0  0.0
        M51 M52 M53 M54 M55            0.0  0.0  0.0  0.0  1.0
        计算公式:
        r = R * m11 + G * m21 + B * m31 + A * m41 + m51 * 255;
        g = R * m12 + G * m22 + B * m32 + A * m42 + m52 * 255;
        b = R * m13 + G * m23 + B * m33 + A * m43 + m53 * 255;
        a = R * m14 + G * m24 + B * m34 + A * m44 + m54 * 255;
     分：1.颜色缩放；2：颜色剪切；3：颜色旋转；4：颜色平移
     */
    public Bitmap getRGBBitmap(Context context, int resId) {
        float r = 153f / 255f;//0.0f~2.0f;1为保持原图的RGBA值。
        float g = 202f / 154f;
        float b = 109f / 153f;
        float a = 1;//透明度
        float[] src = new float[]{
                r, 0, 0, 0, 0,//红色,第五列最后一个值表示红色颜色的偏移量
                0, g, 0, 0, 0,//绿色
                0, 0, b, 0, 0,//蓝色
                0, 0, 0, a, 0//透明度
        };
        Bitmap baseBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        // 获取一个与baseBitmap大小一致的可编辑的空图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Paint paint = new Paint();
        // 定义ColorMatrix，并指定RGBA矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(src);
        // 设置Paint的颜色滤镜
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // 通过指定了RGBA矩阵的Paint把原图画到空白图片上
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
        return afterBitmap;
    }

    /**
     * 图片泛黄
     * @param context
     * @param resId
     * @return
     */
    public Bitmap getYellowBitmap(Context context, int resId) {
        float r = 1;//0.0f~2.0f;1为保持原图的RGBA值。
        float g = 1;
        float b = 1;
        float a = 1;//透明度
        //红色和绿色的分量增加一百，即黄色增加一百
        float[] src = new float[]{
                r, 0, 0, 0, 100,//红色,第五列最后一个值表示红色颜色的偏移量
                0, g, 0, 0, 100,//绿色
                0, 0, b, 0, 0,//蓝色
                0, 0, 0, a, 0//透明度
        };
        Bitmap baseBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        // 获取一个与baseBitmap大小一致的可编辑的空图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Paint paint = new Paint();
        // 定义ColorMatrix，并指定RGBA矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(src);
        // 设置Paint的颜色
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // 通过指定了RGBA矩阵的Paint把原图画到空白图片上
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
        return afterBitmap;
    }

    /**
     * 颜色矩阵研究
     * @param context
     * @param resId
     * @param src
     * @return
     */
    public Bitmap getColorBitmap(Context context, int resId,float[] src) {
        Bitmap baseBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        // 获取一个与baseBitmap大小一致的可编辑的空图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Paint paint = new Paint();
        // 定义ColorMatrix，并指定RGBA矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(src);
        // 设置Paint的颜色
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // 通过指定了RGBA矩阵的Paint把原图画到空白图片上
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
        return afterBitmap;
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        MyLog.e("", "result:" + result);
        return result;
    }
}

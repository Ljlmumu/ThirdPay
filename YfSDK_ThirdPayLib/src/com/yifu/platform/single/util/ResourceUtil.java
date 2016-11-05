package com.yifu.platform.single.util;

/**
 * 
 */

import android.content.Context;
import android.util.Log;

/**
 * @author yinchao
 */
public class ResourceUtil {
	private static boolean debug = false;

	public static int getLayoutId(Context paramContext, String paramString) {
		showRes(paramContext,"layout",paramString);
		return paramContext.getResources().getIdentifier(paramString, "layout",
				paramContext.getPackageName());
	}

	public static int getStringId(Context paramContext, String paramString) {
		showRes(paramContext,"string",paramString);
		return paramContext.getResources().getIdentifier(paramString, "string",
				paramContext.getPackageName());
	}

	public static int getDrawableId(Context paramContext, String paramString) {
		showRes(paramContext,"style",paramString);
		return paramContext.getResources().getIdentifier(paramString,
				"drawable", paramContext.getPackageName());
	}

	public static int getStyleId(Context paramContext, String paramString) {
		showRes(paramContext,"style",paramString);
		return paramContext.getResources().getIdentifier(paramString, "style",
				paramContext.getPackageName());
	}

	public static int getId(Context paramContext, String paramString) {
		showRes(paramContext,"id",paramString);
		return paramContext.getResources().getIdentifier(paramString, "id",
				paramContext.getPackageName());
	}

	public static int getColorId(Context paramContext, String paramString) {
		showRes(paramContext,"color",paramString);
		return paramContext.getResources().getIdentifier(paramString, "color",
				paramContext.getPackageName());
	}

	// add by lhm
	public static int getRawId(Context paramContext, String paramString) {
		showRes(paramContext,"raw",paramString);
		return paramContext.getResources().getIdentifier(paramString, "raw",
				paramContext.getPackageName());
	}
	
	//add by GMF
	private static void showRes(Context context,String style,String name){
		if(debug&&style.equals("layout")){
		//	Log.e("获取控件布局", context.getClass().getSimpleName()+","+style+":"+name);
		}
	}
	
}
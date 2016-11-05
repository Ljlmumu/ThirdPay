
package com.yifu.platform.single.util;

import java.util.Hashtable;

import android.os.Environment;
import android.util.Log;

public class MyLogger {

	public boolean mIsLoggerEnable = true & Constants.DEBUG;
	private final static String LOG_TAG = "yfPlatform";
	private static Hashtable<String, MyLogger> sLoggerTable;
	private String mClassName;

	static {
		sLoggerTable = new Hashtable<String, MyLogger>();
	}

	public static MyLogger getLogger(String className) {
		MyLogger classLogger = (MyLogger) sLoggerTable.get(className);
		if (classLogger == null) {
			classLogger = new MyLogger(className);
			sLoggerTable.put(className, classLogger);
		}
		return classLogger;
	}

	private MyLogger(String name) {
		mClassName = name;
	}
	
	public void v(String log) {
		if (mIsLoggerEnable) {
			Log.v(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
		}
		if(YFUtil.bugMode)
			Log.v(LOG_TAG,"{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
	}

	public void d(String log) {
		if (mIsLoggerEnable) {
			Log.d(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
		}
		if(YFUtil.bugMode)
			Log.d(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
	}
	
	public void i(String log) {
		if (mIsLoggerEnable) {
			Log.i(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
		}
	}

	public void i(String log, Throwable tr) {
		if (mIsLoggerEnable) {
			Log.i(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log + "\n" + Log.getStackTraceString(tr));
		}
	}

	public void w(String log) {
		if (mIsLoggerEnable) {
			Log.w(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
		}
	}

	public void w(String log, Throwable tr) {
		if (mIsLoggerEnable) {
			Log.w(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log + "\n" + Log.getStackTraceString(tr));
		}
	}

	public void e(String log) {
		if (mIsLoggerEnable) {
			Log.e(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log);
		}
	}

	public void e(String log, Throwable tr) {
		if (mIsLoggerEnable) {
			Log.e(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + log + "\n" + Log.getStackTraceString(tr));
		}
	}
	/**
	 * 将日志写到本地
	 * @param str 0：文件夹名称；
	 */
	public void writeLog(String str){
		if(YFUtil.bugMode){
			Log.d(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[" + mClassName + ":] " + str);
			YFUtil.writeFile(YFUtil.CACHE_BUG_MODE, "yifu.txt",str );
		}
	}
	
	public void writeStatsticTestLog(String str){
		if (mIsLoggerEnable) {
			YFUtil.writeStatsticTestFile(Environment.getExternalStorageDirectory().toString(), "Statstic.txt",str );
		}
	}
	
	/**
	 * 分段显示log
	 * 
	 * @param log
	 * @param showCount每段显示的字符长度
	 */
	public static void showLog(String log,int showCount){  
        if(log.length() >showCount){  
            String show = log.substring(0, showCount);  
//          System.out.println(show);  
            Log.i(LOG_TAG, show+"");  
            if((log.length() - showCount)>showCount){//剩下的文本还是大于规定长度  
                String partLog = log.substring(showCount,log.length());  
                showLog(partLog, showCount);  
            }else{  
                String surplusLog = log.substring(showCount, log.length());  
//              System.out.println(surplusLog);  
                Log.i(LOG_TAG, surplusLog+"");  
            }  
              
        }else{  
//          System.out.println(log);  
            Log.i(LOG_TAG, log+"");  
        }  
    }

}
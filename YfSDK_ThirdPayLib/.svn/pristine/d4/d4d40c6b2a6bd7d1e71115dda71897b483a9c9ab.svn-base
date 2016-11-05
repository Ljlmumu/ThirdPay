package com.yifu.platform.single.util;

/**
 * 
 * @Description: 
 */
public class YFEventHelper {
	private static long mLastClickTime;
	
	public static boolean isFastDoubleClick(){
		return isFastDoubleClick(2000);
	}
	
	/**
	 * 
	 * @return
	 */
    public static boolean isFastDoubleClick(final long spanmills) {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        
        if ( 0 < timeD && timeD < spanmills) {   
            return true;   
        }   
        
        mLastClickTime = time;   
        
        return false;   
    }
}

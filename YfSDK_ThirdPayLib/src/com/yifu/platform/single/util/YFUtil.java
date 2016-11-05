package com.yifu.platform.single.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.xml.sax.InputSource;

import com.yifu.platform.single.codec.AES;
import com.yifu.platform.single.setting.YFSingleSDKSettings;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

@SuppressLint("NewApi")
public class YFUtil {

	public static String CACHE_DOWNLOAD_DIR = "yifu_singleSDK_download";
	public static String CACHE_ADS_DIR = Environment.getExternalStorageDirectory() + "/yifu/singleSDK/ads/";
	public static String CACHE_LOGO_DIR = Environment.getExternalStorageDirectory() + "/yifu/singleSDK/logo/";
	public static String CACHE_TAG45_DIR = Environment.getExternalStorageDirectory() + "/yifu/singleSDK/suspension/";
	public static String CACHE_BUG_MODE = Environment.getExternalStorageDirectory() + "/yifu/singleSDK/bug";
	public static String CACHE_GAMEPLUS_DIR = Environment.getExternalStorageDirectory() + "/yifu/singleSDK/gameplus/";
	public static String CACHE_STATISTICS_DIR = Environment.getExternalStorageDirectory()
			+ "/yifu/singleSDK/statistics/";

	public static String CACHE_ADS_FILE = "yfadsdata";
	public static String CACHE_LOGO_FILE = "yflogodata";
	public static String CACHE_TAG45_FILE = "suspensiondata";
	public static String CACHE_GAMEPLUS_FILE = "gameplusdata";
	public static String CACHE_STATISTICS_FILE = "statisticsdata";
	public static String CACHE_STATISTICS_FILE_DOWN = "statisticsdata_down";

	public static boolean bugMode = false;

	private static MyLogger mLogger = MyLogger.getLogger(YFUtil.class.getSimpleName());

	public static boolean isBugMode() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File dirPath = new File(CACHE_BUG_MODE);
			if (dirPath != null && dirPath.exists()) {
				bugMode = true;
			} else {
				bugMode = false;
			}
		} else
			bugMode = false;
		return bugMode;
	}

	public static boolean isServiceWorked(Context context, String sName) {
		ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString().equals(sName)) {
				return true;
			}
		}
		return false;
	}

	public static String getApplicationName(Context context) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			applicationInfo = null;
		}
		return packageManager.getApplicationLabel(applicationInfo).toString();
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentTime() {

		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

		String time = sdf.format(date);

		return time;
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentTime(String strFormat) {
		if (strFormat == null) {
			return null;
		}
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);

		String time = sdf.format(date);

		return time;
	}

	/**
	 * format the date for huafubao yyyyMMdd
	 **/
	public static String convertDateToHuafubaoFormat() {
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return format.format(date);
	}

	public static String convertDateToHuafubaoFormat(String time) {
		Date date = new Date(Long.valueOf(time));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return format.format(date);
	}

	// 2013-02-27
	/**
	 * 字符串编码
	 * 
	 * @param input
	 * @return
	 */
	public static String encodeString(String input) {
		AES myaes = AES.getInstance();
		return myaes.aesEncrypt(input);
	}

	/**
	 * 字符串解码
	 * 
	 * @param input
	 * @return
	 */
	public static String decodeString(String input) {
		AES myaes = AES.getInstance();
		return myaes.aesDecrypt(new String(input));
	}

	/**
	 * Get the date of today.
	 * 
	 * @return
	 */
	public static String getCurrentDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	/**
	 * Get the time of today
	 * 
	 * */
	public static String getCurrentTimeForCountOnlineTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	public static int getAndroidSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
		}
		return version;
	}

	public static boolean isSDCardAvailable() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询下载状态
	 * */
	public static int queryDownloadStatus(Context context, long downloadId) {
		DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		// 关键：通过ID向下载管理查询下载情况，返回一个cursor
		Query myDownloadQuery = new Query();
		myDownloadQuery.setFilterById(downloadId);
		Cursor cursor = downloadManager.query(myDownloadQuery);
		int status = -1;
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
			}
			cursor.close();
		}

		return status;
	}

	/**
	 * 跳转到浏览器h5的推荐页面
	 * */
	public static void startRecommendPageByBrowser(Context context, String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		StringBuffer completeUrl = new StringBuffer();
		completeUrl.append(url).append("?").append(Constants.PARAM_YF_SINGLE_FR).append("=")
				.append(Constants.YF_SINGLE_FR).append("&").append(Constants.PARAM_YF_SINGLE_GV).append("=")
				.append(PhoneUtil.getGameVersionCode(context)).append("&").append(Constants.PARAM_YF_SINGLE_SV)
				.append("=").append(Constants.SDK_VERSION).append("&").append(Constants.JSON_CHANNEL).append("=")
				.append(YFSingleSDKSettings.SDK_CHANNELID).append("&").append(Constants.JSON_APPID).append("=")
				.append(YFSingleSDKSettings.SDK_APPID);

		intent.setData(Uri.parse(completeUrl.toString()));
		context.startActivity(intent);
	}

	/**
	 * 安装APK
	 * */
	public static void installApk(Context context, File file) {
		if (file.toString().toLowerCase().endsWith(Constants.YF_FILE_SUFFIX)) {
			try {
				PackageManager pm = context.getPackageManager();
				PackageInfo packageInfo = pm.getPackageArchiveInfo(file.getAbsolutePath(),
						PackageManager.GET_ACTIVITIES);
				if (packageInfo != null) {
					ApplicationInfo appInfo = packageInfo.applicationInfo;
					if (appInfo != null) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.setDataAndType(Uri.fromFile(file), Constants.YF_APPLICATION_ARCHIVE);
						context.startActivity(intent);
					} else {
						Toast.makeText(context, ResourceUtil.getStringId(context, "yf_txt_apk_error"),
								Toast.LENGTH_SHORT).show();
						file.delete();
					}
				} else {
					Toast.makeText(context, ResourceUtil.getStringId(context, "yf_txt_apk_error"), Toast.LENGTH_SHORT)
							.show();
					file.delete();
				}
			} catch (Exception e) {
				Toast.makeText(context, ResourceUtil.getStringId(context, "yf_txt_apk_error"), Toast.LENGTH_SHORT)
						.show();
				file.delete();
			}
		}
	}

	/**
	 * 根据包名检测应用是否已经安装和是否需要升级
	 * */
	public static boolean queryAPKInstallStatus(Context context, String packageName, int mversionCode) {
		boolean flag = false;
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			mLogger.d("queryAPKInstallStatus  NameNotFoundException");
			packageInfo = null;
		}
		if (packageInfo != null) {
			int versionCode = packageInfo.versionCode;

			mLogger.d("queryAPKInstallStatus  versionCode = " + versionCode);

			if (versionCode >= mversionCode)
				flag = true;
		}

		mLogger.d("queryAPKInstallStatus  flag = " + flag);

		return flag;
	}

	public static boolean queryAPKInstallStatus(Context context, String packageName) {
		boolean flag = false;
		if(context==null){
			mLogger.d("queryAPKInstallStatus  context is null");
			return flag;
		}
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			mLogger.d("queryAPKInstallStatus  NameNotFoundException");
			packageInfo = null;
		}
		if (packageInfo != null) {
			flag = true;
		}

		mLogger.d("queryAPKInstallStatus  flag = " + flag);
		return flag;
	}

	public static long convertByteSizeToM(long bytes) {
		long mSize = bytes / 1024 / 1024;
		return mSize;
	}

	public static String convertByte2MB(long bytes) {
		return String.valueOf(convertByteSizeToM(bytes)) + "MB";
	}

	public synchronized static void writeStatsticTestFile(String dir, String fileName, String str) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File dirPath = new File(dir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			try {
				// 打开文件
				// File file = new File(dirPath, fileName);
				String path = dir + "/" + fileName;
				RandomAccessFile randomFile = new RandomAccessFile(path, "rw");
				// 文件长度，字节数
				long fileLength = randomFile.length();
				// 将写文件指针移到文件尾。
				randomFile.seek(fileLength);
				randomFile.writeBytes(str);
				randomFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public synchronized static void deleteFile(String dir, String fileName) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File dirPath = new File(dir);
			if (dirPath.exists()) {
				File file = new File(dirPath, fileName);
				file.delete();
			}
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param dir
	 *            路径
	 * @param fileName
	 *            文件名
	 * @param object
	 *            实体
	 */
	public synchronized static void writeFile(String dir, String fileName, Object object) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File dirPath = new File(dir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			// 打开文件
			File file = new File(dirPath, fileName);

			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
				file.delete();
				file.createNewFile();
				// 保存文件
				fos = new FileOutputStream(file);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(object);
				oos.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						oos.close();
						fos.close();
						oos = null;
						fos = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	/**
	 * 读取文件
	 * 
	 * @param dir
	 *            目录
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public synchronized static Object readFile(String dir, String fileName) {

		Object object = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			File file = new File(dir + fileName);
			if (file.exists()) {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				object = ois.readObject();
				mLogger.d("read file is = " + object.toString());
			} else {
				mLogger.d("file is not exists");
			}
		} catch (Exception e) {
			mLogger.d("-----read file is exception");
			return object;
		} finally {
			if (ois != null) {
				try {
					ois.close();
					ois = null;
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		return object;
	}

	/**
	 * 保存图片
	 * 
	 * @param dir
	 *            路径
	 * @param fileName
	 *            文件名
	 * @param data
	 *            数据
	 */
	public synchronized static void saveImage(String dir, String imgurl) {

		String filename = imgurl;
		File imgFile;
		File dirPath = new File(dir);

		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		if (filename != null) {
			filename = getImgFileName(filename);
		} else {
			return;
		}
		if (filename != null) {
			imgFile = new File(dir, filename);
			if (imgFile.exists()) {
				return;
			}
		} else {
			return;
		}

		FileOutputStream fos = null;
		try {
			InputSource is = new InputSource(new URL(imgurl).openStream());
			InputStream inputStream = is.getByteStream();
			byte b[] = new byte[1024];
			byte[] datas = null;
			int tmp = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((tmp = inputStream.read(b, 0, b.length)) > 0) {
				bos.write(b, 0, tmp);
			}
			datas = bos.toByteArray();

			if (datas != null && datas.length > 0) {
				imgFile.createNewFile();
				fos = new FileOutputStream(imgFile);
				fos.write(datas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取图片
	 * 
	 * @param dir
	 *            目录
	 * @param fileName
	 *            文件名
	 * @param opt
	 *            选项
	 * @return
	 */
	public synchronized static Bitmap readImage(String dir, String imageurl, Options opt) {
		String fileName = null;
		fileName = getImgFileName(imageurl);

		if (fileName == null || fileName.trim().length() <= 0) {
			return null;
		}
		File file = null;
		InputStream in = null;
		Bitmap bitmap = null;
		try {
			file = new File(dir, fileName);
			if (!file.exists()) {
				return null;
			}
			in = new FileInputStream(file);
			bitmap = BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bitmap;
	}

	/**
	 * 随机下载次数
	 * 
	 * @return 5000-10000
	 */
	public static int createRandomDownloadNum() {
		Random random = new Random();
		return random.nextInt(5000) + 5000;
	}

	public static void startDownloadApkWork(Context context, String apkName, String gameName, String downloadUrl,
			String packageNmae,String gameId) {
		if (YFUtil.isSDCardAvailable() && YFUtil.getAndroidSDKVersion() >= 9) {
			// 调用系统的下载管理器
			Toast.makeText(context, ResourceUtil.getStringId(context, "yf_btn_start_download"), Toast.LENGTH_LONG)
					.show();
			startDownloadApkBySystemDownloadManager(context, apkName, gameName, downloadUrl, packageNmae,gameId);
		} else {
			startDownloadApkByBrowser(context, downloadUrl);
		}
	}

	/**
	 * 通过浏览器来进行下载
	 * */
	public static void startDownloadApkByBrowser(Context context, String downloadUrl) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(Uri.parse(downloadUrl));
		context.startActivity(intent);
	}

	/**
	 * android2.3以后调用系统的下载管理程序
	 * */
	public static void startDownloadApkBySystemDownloadManager(Context context, String apkName, String gameName,
			String downloadUrl, String packageNmae,String gameId) {

		try {

			DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
			Uri uri = Uri.parse(downloadUrl);
			DownloadManager.Request request = new Request(uri);

			// 设置允许使用的网络类型，这里是移动网络和wifi都可以
			request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
					| DownloadManager.Request.NETWORK_WIFI);

			// 禁止发出通知，既后台下载，如果要使用这一句必须声明一个权限：android.permission.DOWNLOAD_WITHOUT_NOTIFICATION
			// request.setShowRunningNotification(false);
			// 发出通知，既后台下载
			request.setShowRunningNotification(true);
			// 显示下载界面
			request.setVisibleInDownloadsUi(true);
			if (gameName != null) {
				request.setTitle(gameName);
			}
			if ("".equals(apkName)) {
				apkName = System.currentTimeMillis() + ".apk";
			}
			mLogger.d("startDownloadApkBySystemDownloadManager  apkName = " + apkName + " packageNmae = " + packageNmae);

			request.setDestinationInExternalPublicDir(YFUtil.CACHE_DOWNLOAD_DIR, apkName);
			// request.allowScanningByMediaScanner();
			long id = downloadManager.enqueue(request);
			SharePreferenceUtil.getInstance(context).saveString(String.valueOf(id), packageNmae);
			SharePreferenceUtil.getInstance(context).saveString(String.valueOf(id), apkName);
			SharePreferenceUtil.getInstance(context).saveString(String.valueOf(id)+"id", gameId);
			SharePreferenceUtil.getInstance(context).saveLong(packageNmae, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			startDownloadApkByBrowser(context, downloadUrl);
		}
	}

	/**
	 * 得到图片文件名称
	 * 
	 * @param imgurl
	 * @return
	 */
	public static String getImgFileName(String imgurl) {
		String filename = imgurl;
		if (filename != null) {
			int index = filename.lastIndexOf("/");
			if (index != -1) {
				filename = filename.substring(index + 1);
				index = filename.lastIndexOf(".");
				if (index != -1) {
					filename = filename.substring(0, index);
				}
			}
		}
		return filename;
	}
	public static void setStatisticsCacheDown(String key,String gameId) {
		Map<String, String> map = (HashMap) YFUtil.readFile(YFUtil.CACHE_STATISTICS_DIR, YFUtil.CACHE_STATISTICS_FILE_DOWN);
		if (map == null) {
			map = new HashMap<String, String>();
			map.put(key, gameId);
		} else {
			StringBuilder id = new StringBuilder(map.get(key));
			if (id != null) {
				id.append(","+gameId);
			}
			map.put(key, id.toString());
			
		}

		YFUtil.writeFile(YFUtil.CACHE_STATISTICS_DIR, YFUtil.CACHE_STATISTICS_FILE_DOWN, map);
	}

	public static void setStatisticsCache(String key) {
		Map<String, Integer> map = (HashMap) YFUtil.readFile(YFUtil.CACHE_STATISTICS_DIR, YFUtil.CACHE_STATISTICS_FILE);
		if (map == null) {
			map = new HashMap<String, Integer>();
			map.put(key, 1);
		} else {
			Integer num = map.get(key);
			if (num == null) {
				num = 1;
			} else {
				num += 1;
			}
			map.put(key, num);

		}
		YFUtil.writeFile(YFUtil.CACHE_STATISTICS_DIR, YFUtil.CACHE_STATISTICS_FILE, map);
	}

	public static String getDayMaxFlagByChannel(String currentUdid, String channel) {

		StringBuffer uniqueUdid = new StringBuffer();
		uniqueUdid.append(currentUdid);
		uniqueUdid.append(Constants.YF_PARAM_PAID_MID);
		uniqueUdid.append(channel);

		return uniqueUdid.toString();
	}
	/**
     * 判断安装微信/支付宝
     * @param context
     * @return
     */
    public static boolean isAppAvilible(Context context,String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
       
        return false;
    }
}

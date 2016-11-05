
package com.yifu.platform.single.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONException;

import com.yifu.platform.single.internal.YFPlatformInternal;
import com.yifu.platform.single.json.AbstractJSONHelper;
import com.yifu.platform.single.YFErrorCode;
import com.yifu.platform.single.codec.AES;
import com.yifu.platform.single.json.JSONHelper;
import com.yifu.platform.single.net.NetMessage.NetMessageType;
import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.util.FileHelper;
import com.yifu.platform.single.util.MyLogger;
import com.yifu.platform.single.util.YFUtil;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ASIHttpRequest implements Runnable {

	private MyLogger mLogger = MyLogger.getLogger(ASIHttpRequest.class.getName());
	private FutureTask<Object> mTask = new FutureTask<Object>(this, null);
	private static final int BUFFER_SIZE = 1024 * 10; // 10k byte
	// default support resume broken transfer
	private String mUrl;
	private boolean mStop = false;
	private String mDownloadDstFilePath;
	private int mTimeOutSeconds = 60 * 1000;
	private String mAccpetEcoding;
	private boolean mIsDownLoad = false;
	private String mContentType;
	private String mRequestMethod;
	private String mRequestData;
	private Handler mCbkHandler;
	private int mRequestTag;
	// indicate whether support Resume broken transfer
	private boolean mIsSupportBt = true;
	private int mRepeatCount = 0;

	/**
	 * 服务器数据解析接口
	 */
	private AbstractJSONHelper jsonHelper;
	
	public ASIHttpRequest(AbstractJSONHelper jsonHelper,Handler mCbkHandler) {
		this.jsonHelper = jsonHelper;
		this.mCbkHandler = mCbkHandler;
	}
	
	public ASIHttpRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public Handler getCbkHandler() {
		return this.mCbkHandler;
	}

	public void setCbkHandler(Handler cbkhandler) {
		this.mCbkHandler = cbkhandler;

	}

	public int getRequestTag() {
		return this.mRequestTag;
	}

	public void setRequestTag(int requestTag) {
		this.mRequestTag = requestTag;
	}

	public String getRequestData() {
		return mRequestData;
	}

	public void setRequestData(String requestdata) {
		this.mRequestData = requestdata;
	}

	public String getRequestMethod() {

		return mRequestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.mRequestMethod = requestMethod;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		this.mUrl = url;
	}

	public boolean getStop() {
		return mStop;
	}

	public void cancelRequest() {
		this.mStop = true;
	}

	public String getDownloadDstFilePath() {
		return mDownloadDstFilePath;
	}

	public void setDownloadDstFilePath(String downloadDstFilePath) {
		this.mDownloadDstFilePath = downloadDstFilePath;
		this.mIsDownLoad = true;
	}

	public int getTimeOutSeconds() {
		return mTimeOutSeconds;
	}

	public void setTimeOutSeconds(int mTimeOutSeconds) {
		this.mTimeOutSeconds = mTimeOutSeconds;
	}

	public String getAccpetEcoding() {
		return mAccpetEcoding;
	}

	public void setAccpetEcoding(String mAccpetEcoding) {
		this.mAccpetEcoding = mAccpetEcoding;
	}

	public boolean isIsDownLoad() {
		return mIsDownLoad;
	}

	public void setIsDownLoad(boolean isDownLoad) {
		this.mIsDownLoad = isDownLoad;
	}

	public String getContentType() {
		return mContentType;
	}

	public void setContentType(String contentType) {
		this.mContentType = contentType;
	}

	public void sendCbkMessage(Message msg) {

		if (this.mCbkHandler != null) {

			if (msg == null) {
				Message _msg = new Message();
				_msg.what = 9;

				mCbkHandler.sendMessage(_msg);
			} else {
				mCbkHandler.sendMessage(msg);
			}
		}
	}

	private OutputStream initOutPutIO() throws IOException {
		OutputStream res = null;

		if (this.mIsDownLoad) {
			// create the folder
			new File(mDownloadDstFilePath.substring(0, mDownloadDstFilePath.lastIndexOf("/"))).mkdirs();

			// use a temp file and rename after finish download
			if (mIsSupportBt) {
				res = new FileOutputStream(mDownloadDstFilePath + ".temp", true);
			} else {
				res = new FileOutputStream(mDownloadDstFilePath + ".temp");
			}
		} else {
			res = new ByteArrayOutputStream();
		}
		return res;
	}

	private void handleNetRequest() {
		// add time 2012-08-24
		HttpClient client = HttpClientHelper.getHttpClient();
//		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,mTimeOutSeconds);//2016.8.16连接超时
//		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,mTimeOutSeconds);//2016.8.16 读取超时
		HttpPost postRequest = null;
		HttpGet getRequest = null;
		HttpResponse response = null;
		// end
		OutputStream outputio = null;
		InputStream inio = null;
		try {
			do {
				byte[] bs = null;
				if (mDownloadDstFilePath != null) {
					setIsDownLoad(true);
					getRequest = HttpClientHelper.getHttpGetRequest(mUrl);
					if (this.mIsSupportBt) {
						long tempfileSize = FileHelper.getFileSize(mDownloadDstFilePath + ".temp");
						getRequest.setHeader("RANGE", "bytes=" + tempfileSize + "-");
					}
					try {

						response = client.execute(getRequest);
					} catch (Exception e) {
						client = HttpClientHelper.getHttpClient();
						try {
							response = client.execute(getRequest);
						} catch (Exception ex) {
							client = HttpClientHelper.getHttpClient();
							try {
								response = client.execute(getRequest);
							} catch (Exception ex1) {
								mLogger.d("ex1 = "+ex1);
								handleErrorEvent("网络连接有错误！", YFErrorCode.YF_NET_GENER_ERROR);
								mTask.cancel(true);
								return;
							}
						}
					}
				} else {

					postRequest = HttpClientHelper.getHttpPostRequest(mUrl);

					setIsDownLoad(false);
					StringEntity reqEntity = new StringEntity(mRequestData, "UTF-8");
					postRequest.setEntity(reqEntity);
					if (this.mStop) {
						mTask.cancel(true);
						handleCancelEvent("cancel before write data to pipe");
						return;
					}
					try {
						mLogger.d("发起请求");
						response = client.execute(postRequest);
						mLogger.d("得到响应");
					} catch (Exception e) {
						client = HttpClientHelper.getHttpClient();
						try {
							response = client.execute(postRequest);
						} catch (Exception ex) {
							client = HttpClientHelper.getHttpClient();
							try {
								response = client.execute(postRequest);
							} catch (Exception ex1) {
								handleErrorEvent("网络连接有错误！", YFErrorCode.YF_NET_GENER_ERROR);
								mTask.cancel(true);
								return;
							}
						}
					}
				}
				outputio = this.initOutPutIO();

				int responseCode = response.getStatusLine().getStatusCode();
				if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_PARTIAL_CONTENT) {
					int currentReadbyteCount = 0;
					long responseDataLen = response.getEntity().getContentLength();
					long havedownDataSize = 0;
					inio = response.getEntity().getContent();
					byte[] tempBytes = new byte[BUFFER_SIZE];

					if (this.mIsDownLoad && this.mIsSupportBt) {
						havedownDataSize = FileHelper.getFileSize(mDownloadDstFilePath + ".temp");
						responseDataLen += havedownDataSize;
					}
					while ((currentReadbyteCount = inio.read(tempBytes)) != -1) {
						if (this.mStop) {
							mTask.cancel(true);
							break;
						}
						havedownDataSize += currentReadbyteCount;
						// if is download request
						// then need create the progress event and
						// handle the event to UI layer
						if (this.mIsDownLoad) {
							handleDownLoadingEvent(responseDataLen, havedownDataSize);
						}
						outputio.write(tempBytes, 0, currentReadbyteCount);
					}
					if (this.mStop) {
						mTask.cancel(true);
						handleCancelEvent("cancel after read data from pipe");
						break;
					}

					if (responseDataLen != -1) {
						if (havedownDataSize != responseDataLen) {
							// error happen
							handleErrorEvent("网络连接有错误！", YFErrorCode.YF_NET_GENER_ERROR);
							break;
						} else if (havedownDataSize == responseDataLen) {

							if (!this.mIsDownLoad) {
								bs = ((ByteArrayOutputStream) outputio).toByteArray();
							} else {
								File file = new File(mDownloadDstFilePath + ".temp");
								file.renameTo(new File(mDownloadDstFilePath));
							}
							handleSuccessEvent(bs);
						}
					} else {
						MyLogger.getLogger(this.getClass().getName()).v("response data length is -1");
						{
							// When download file
							handleErrorEvent("content len is error", YFErrorCode.YF_NET_GENER_ERROR);
						}

					}
				} else if (responseCode == HttpStatus.SC_MOVED_PERMANENTLY || responseCode == HttpStatus.SC_MOVED_TEMPORARILY) {
					String redirectUrl = response.getFirstHeader("location").getValue();
					if (redirectUrl != null && redirectUrl.length() > 0) {
						setUrl(redirectUrl);
						handleNetRequest();
					}
				} else {
					if (responseCode == HttpStatus.SC_GATEWAY_TIMEOUT) {
						handleErrorEvent("connect time out", YFErrorCode.YF_NET_TIME_OUT);
					} else if (responseCode == -1 && mRepeatCount == 0) {
						mRepeatCount = 1;
						handleNetRequest();
					} else {
						String codestr = String.format("Net Error Code: %d", responseCode);
						String msgstr = String.format("Net Error Msg: %s", response.getStatusLine().getReasonPhrase());

						MyLogger.getLogger(this.getClass().getName()).v(codestr);
						MyLogger.getLogger(this.getClass().getName()).v(msgstr);

						handleErrorEvent("net error", YFErrorCode.YF_NET_GENER_ERROR);
					}
				}

			} while (false);

			// close the io pipe
			if (inio != null) {
				inio.close();
				inio = null;
			}

			if (outputio != null) {
				outputio.close();
				outputio = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			MyLogger.getLogger(this.getClass().getName()).v(e.toString());
			handleErrorEvent("exception happen", YFErrorCode.YF_NET_GENER_ERROR);
		} finally {

			if (inio != null) {
				inio = null;
			}

			if (outputio != null) {
				outputio = null;
			}
		}
	}

	private void handleDownLoadingEvent(long totalSize, long currentSize) {
		// create custom message instance and
		// set parameter
		NetMessage msg = new NetMessage();
		msg.setMessageType(NetMessage.NetMessageType.NetDownloadling);
		msg.setTotalSize(totalSize);
		msg.setCurentSize(currentSize);
		msg.setRequestId(this.hashCode());

		// create system message instance
		Message sysmsg = new Message();
		sysmsg.obj = msg;

		sendCbkMessage(sysmsg);
	}

	private void handleErrorEvent(String netError, int errorCode) {

		NetMessage msg = new NetMessage();
		if (this.mIsDownLoad) {
			msg.setMessageType(NetMessage.NetMessageType.NetDownloadFailure);
			File file = new File(mDownloadDstFilePath + ".temp");
			file.delete();
		} else {
			msg.setMessageType(NetMessageType.NetFailure);
			msg.setErrorCode(errorCode);
		}
		msg.setErrorString(netError);
		msg.setRequestId(this.hashCode());

		// create system message instance
		Message sysmsg = new Message();
		sysmsg.obj = msg;

		sendCbkMessage(sysmsg);
	}

	private void handleSuccessEvent(byte[] responseStr) {
		NetMessage msg = new NetMessage();
		if (this.mIsDownLoad) {
			msg.setMessageType(NetMessage.NetMessageType.NetDownloadSuccess);

		} else {
			msg.setMessageType(NetMessageType.NetSuccess);

			String resString = "";
			try {
				AES myaes = AES.getInstance();
				String str = new String(responseStr, "UTF-8");
				resString = myaes.aesDecrypt(new String(responseStr, "UTF-8"));
				mLogger.d(this.mRequestTag+"下行------" + resString);
				BaseResult res=null;
					if(jsonHelper == null){
					res = JSONHelper.parserWithTag(this.mRequestTag, resString);
				} else {
					res = jsonHelper.doParserWithTag(this.mRequestTag, resString);
				}
					
				msg.setResponseData(res);

			} catch (JSONException e) {
				// 捕获JSON异常
				e.printStackTrace();
				msg.setErrorCode(YFErrorCode.YF_JSON_PARSER_ERROR);
				msg.setErrorString("parse json error");
			} catch (Exception ex) {
				// net request failed
				msg.setErrorCode(YFErrorCode.YF_NET_DATA_ERROR);
				msg.setErrorString("receive data error");
			} finally {

			}
		}
		msg.setRequestId(this.hashCode());

		// create system message instance
		Message sysmsg = new Message();
		sysmsg.obj = msg;

		sendCbkMessage(sysmsg);
	}

//	public void saveTotext(String str, String name){
//		//File file =  new File( Environment.getExternalStorageDirectory(),name);
//		File path = Environment.getExternalStoragePublicDirectory(YFUtil.CACHE_DOWNLOAD_DIR);
//        File file = new File(path.getAbsolutePath() + "/" + name);
//		
//		FileOutputStream fos;
//		//FileWriter fw = null;
//		try {
//			file.createNewFile();
//			fos = new FileOutputStream(file);
//		//	fw = new FileWriter(file);
////			byte[] b = str.getBytes();
////			int length = str.length();
////			for(int i = 0;i<length;i++){
////				fos.write(str.charAt(i));
////			}
//			//fos.write(b,0,length);
//			
//			//fw.write(str);
//			PrintWriter pw = new PrintWriter(fos);
//			pw.write(str);
//			//}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	/*
	 * @Transfer request cancel event to UI layer
	 * 
	 * @Param cancelStr specify the cancel reason and not use yet
	 */
	private void handleCancelEvent(String cancelStr) {

		MyLogger.getLogger(this.getClass().getName()).v(cancelStr);

		NetMessage msg = new NetMessage();
		msg.setMessageType(NetMessageType.NetCancel);
		msg.setRequestId(this.hashCode());

		// create system message instance
		Message sysmsg = new Message();
		sysmsg.obj = msg;

		sendCbkMessage(sysmsg);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(YFPlatformInternal.getInstance().getmContext() == null)
			return;
		handleNetRequest();
	}
}

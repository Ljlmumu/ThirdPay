package com.yifu.platform.single.net;

import com.yifu.platform.single.net.response.BaseResult;

public class NetListenerAdapter implements INetListener {

	@Override
	public void onNetResponse(int requestTag, BaseResult responseData,
			int requestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDownLoadStatus(DownLoadStatus status, int requestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDownLoadProgressCurSize(long curSize, long totalSize,
			int requestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNetResponseErr(int requestTag, int requestId, int errorCode,
			String msg) {
		// TODO Auto-generated method stub

	}

}

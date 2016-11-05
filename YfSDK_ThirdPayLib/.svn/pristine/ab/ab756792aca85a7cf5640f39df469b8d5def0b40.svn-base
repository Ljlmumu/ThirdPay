package com.yifu.platform.single.control.factory;

import com.yifu.platform.single.control.IPayFactory;
import com.yifu.platform.single.control.PayChannel;
/**
 * 没有支付需求
 * @author zero
 *
 */
public class NotPayFactoryimpl implements IPayFactory{
	
	public static NotPayFactoryimpl getInstance() {
		return Inner.factoryimpl;
	}

	private static class Inner{
		static NotPayFactoryimpl factoryimpl=new NotPayFactoryimpl();
	}

	@Override
	public PayChannel getPayChannel() {
		return null;
	}

	@Override
	public PayChannel getSparePayChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayChannel getFourPayChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayChannel getThirdPayChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayChannel getFivePayChannel() {
		// TODO Auto-generated method stub
		return null;
	}

}

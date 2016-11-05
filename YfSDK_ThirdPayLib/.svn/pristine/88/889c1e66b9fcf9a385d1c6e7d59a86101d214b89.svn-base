package com.yifu.platform.single.util;

import java.util.Collection;


public interface ICheckInitFinish {
	public boolean isInitFinish(Collection<String> prameType);
}

class NOCheckFinish implements ICheckInitFinish {

	public static NOCheckFinish getInstance() {
		return Inner.factoryimpl;
	}

	private static class Inner{
		static NOCheckFinish factoryimpl=new NOCheckFinish();
	}

	
	@Override
	public boolean isInitFinish(Collection<String> prameType) {
		return true;
	}
}

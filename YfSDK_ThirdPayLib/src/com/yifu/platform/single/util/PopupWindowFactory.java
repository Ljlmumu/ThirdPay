package com.yifu.platform.single.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupWindowFactory {
	
	public static PopupWindow createDropDownPopupWindow(Context context,
			int imgSrc,String txtSrc,View dropDownView,final View.OnClickListener clickListener){
		final PopupWindow popup = new PopupWindow(context);
		final View contentView = LayoutInflater.from(context).inflate(ResourceUtil.getLayoutId(context,"yf_new_payment_dropdown"), null,false);
		contentView.findViewById(ResourceUtil.getId(context, "dropdown_arrow_iv")).setVisibility(View.GONE);
		if(imgSrc == 0){
			contentView.findViewById(ResourceUtil.getId(context,"dropdown_iv")).setVisibility(View.GONE);
		}else{
			ImageView iv = (ImageView)contentView.findViewById(ResourceUtil.getId(context,"dropdown_iv"));
			iv.setImageResource(imgSrc);
		}
		if(txtSrc == null){
			contentView.findViewById(ResourceUtil.getId(context,"dropdown_tv")).setVisibility(View.GONE);
		}else{
			TextView tv = (TextView)contentView.findViewById(ResourceUtil.getId(context,"dropdown_tv"));
			tv.setText(txtSrc);
		}
		popup.setOutsideTouchable(true);
		contentView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popup.dismiss();
				if(v == contentView){
					if(clickListener!=null){
						clickListener.onClick(v);
					}
				}
			}
		});
		popup.setWidth(dropDownView.getWidth());
		popup.setHeight(dropDownView.getHeight());
		
		popup.setContentView(contentView);
		popup.setBackgroundDrawable(null);
		popup.showAsDropDown(dropDownView);
		return popup;
	}
}

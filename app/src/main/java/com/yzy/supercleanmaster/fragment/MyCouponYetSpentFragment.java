package com.yzy.supercleanmaster.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 我的券已消費券Fragment类
 */
@SuppressLint("InflateParams")
public class MyCouponYetSpentFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		tv.setTextSize(24.f);
		tv.setPadding(15,10,15,10);
		tv.setText("空调");
		return tv;
	}
}

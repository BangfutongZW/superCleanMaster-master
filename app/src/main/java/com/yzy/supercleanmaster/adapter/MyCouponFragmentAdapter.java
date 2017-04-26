package com.yzy.supercleanmaster.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.yzy.supercleanmaster.fragment.MyCouponNhSpentFragment;
import com.yzy.supercleanmaster.fragment.MyCouponNotSpendingFragment;
import com.yzy.supercleanmaster.fragment.MyCouponYetSpentFragment;

/**
 * 我的券Fragment 页面适配器
 */
public class MyCouponFragmentAdapter extends FragmentPagerAdapter {

	public MyCouponFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		Log.e("p","position"+position);
		switch (position) {

		// 我的券未消费
		/*case 0:
			fragment = new MyCouponNotSpendingFragment();
			break;
		// 我的券已消费
		case 1:
			fragment = new MyCouponYetSpentFragment();
			break;
			case 2:
				fragment = new MyCouponNhSpentFragment();
				break;*/
			case 0:
				fragment =new MyCouponNotSpendingFragment();
				break;
			case 1:
				fragment =new MyCouponYetSpentFragment();
				break;
			case 2:
				fragment=new MyCouponNhSpentFragment();
				break;

		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// super.destroyItem(container, position, object);
	}
}

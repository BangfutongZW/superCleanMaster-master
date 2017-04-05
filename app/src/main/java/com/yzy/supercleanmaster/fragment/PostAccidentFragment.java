package com.yzy.supercleanmaster.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.FragmentContainerActivity;

import butterknife.OnClick;


public class PostAccidentFragment extends Fragment {


    public static void launch(Activity from) {
        FragmentContainerActivity.launch(from, PostAccidentFragment.class, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setDisplayShowHomeEnabled(false);
        getActivity().getActionBar().setTitle(R.string.title_settings);
        View view = inflater.inflate(R.layout.fragment_post_accident, container, false);

        //Find the +1 button
        return view;
    }
    @OnClick(R.id.photo_baoshi)
    void clickBaoshi(){
        startActivityForResult(new Intent(),0);
    };

    @Override
    public void onResume() {
        super.onResume();

    }

}

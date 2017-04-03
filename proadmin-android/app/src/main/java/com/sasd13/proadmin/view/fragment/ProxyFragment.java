package com.sasd13.proadmin.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.proadmin.R;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class ProxyFragment extends Fragment {

    public static ProxyFragment newInstance() {
        return new ProxyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_proxy, container, false);

        return view;
    }
}

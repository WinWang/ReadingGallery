package com.winwang.mvvm.loadsir;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kingja.loadsir.callback.Callback;
import com.winwang.mvvm.R;


/**
 * Description:TODO
 * Create Time:2017/9/2 16:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class TimeoutCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_timeout;
    }



}

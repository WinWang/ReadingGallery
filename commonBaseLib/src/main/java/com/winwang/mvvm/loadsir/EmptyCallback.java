package com.winwang.mvvm.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.winwang.mvvm.R;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

}

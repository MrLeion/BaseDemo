package org.tzl.baselibrary.net.callback;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.lzy.okgo.request.BaseRequest;

import org.tzl.baselibrary.utils.T;
import org.tzl.baselibrary.widget.CustomProgressDialog;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class ProgressDialogCallback extends JsonCallback<T> {

    private final Activity             mActivity;
    private       CustomProgressDialog dialog;


    public ProgressDialogCallback(Activity activity) {
        super();
        mActivity = activity;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
//            dialog.showLoading(mActivity,"正在加载中...");
            dialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}

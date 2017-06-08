package dialogutil.jessie.com.dialoglibrary.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import dialogutil.jessie.com.dialoglibrary.R;


/**
 * 创建时间: 2016/11/25
 * 编写人: JessieKate
 * 功能描述:弹窗基本类
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected int layout;//布局文件id
    protected static final int DEFAULT_COLOR = -1; //default
    protected static final int DEFAULT_BG= -1;

    protected int animation = R.style.BottomDialog;//动画类型
    public static final int BOTTOM_TO_TOP=0;
    public static final int TOP_TO_BOTTOM=1;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Step1 build Dialog
        if (layout == 0) {
            throw new RuntimeException("no correct layout found.");
        }

        View view = LayoutInflater.from(getActivity()).inflate(layout, null);
        Dialog dialog= setDialog(view);

        return dialog;
    }

    protected abstract Dialog setDialog(View view);
    public  BaseDialogFragment setDialogAnimation(int animation){
        if(animation==TOP_TO_BOTTOM){
            this.animation= R.style.TopDialog;
        }else{
            this.animation= R.style.BottomDialog;
        }

        return this;
    }
    public void showDialog(FragmentManager fragmentManager){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("dialogFragment");
        //避免重复弹窗
        if (fragment != null) {
            ft.remove(fragment);
        }

        show(ft, "dialogFragment");//显示一个Fragment并且给该Fragment添加一个Tag，可通过findFragme ntByTag找到该Fragment
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

package dialogutil.jessie.com.dialoglibrary.load;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import dialogutil.jessie.com.dialoglibrary.R;
import dialogutil.jessie.com.dialoglibrary.base.BaseDialogFragment;

/**
 * 创建时间: 03/12/2016
 * 编写人: JessieKate
 * 功能描述:载入对话框父类
 */

public abstract class BaseLoadDialogFragment extends BaseDialogFragment {

    @Override
    protected Dialog setDialog(View view) {
        Dialog dlg = new Dialog(getActivity(), R.style.MyDialogBottom);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setContentView(view);
        dlg.getWindow().setBackgroundDrawableResource(R.color.transparent);
        ProgressBar pb_progress = (ProgressBar) view.findViewById(R.id.pb_progress);
        TextView load_msg = (TextView) view.findViewById(R.id.load_msg);
        ImageView dot_loading1 = (ImageView) view.findViewById(R.id.dot_loading1);
        ImageView dot_loading2 = (ImageView) view.findViewById(R.id.dot_loading2);
        ImageView dot_loading3 = (ImageView) view.findViewById(R.id.dot_loading3);
        LinearLayout ly_background= (LinearLayout) view.findViewById(R.id.ly_background);
        ly_background.setBackgroundResource(getBackgroundShape());
        load_msg.setText(getLoadMsgText());
        if (getLoadMsgColor() != DEFAULT_COLOR) {
            load_msg.setTextColor(getResources().getColor(getLoadMsgColor()));
        }
        if (getLoadImg() != DEFAULT_COLOR) {
            pb_progress.setIndeterminateDrawable(getResources().getDrawable(getLoadImg()));
        }
        Drawable drawableBlue = getResources().getDrawable(getLoadShape1());
        Drawable drawableGray = getResources().getDrawable(getLoadShape2());

        AnimationDrawable anim1 = new AnimationDrawable();
        anim1.addFrame(drawableBlue, 300);
        anim1.addFrame(drawableGray, 300);
        anim1.addFrame(drawableGray, 300);
        anim1.setOneShot(false);
        dot_loading1.setBackgroundDrawable(anim1);
        anim1.start();

        AnimationDrawable anim2 = new AnimationDrawable();
        anim2.addFrame(drawableGray, 300);
        anim2.addFrame(drawableBlue, 300);
        anim2.addFrame(drawableGray, 300);
        anim2.setOneShot(false);
        dot_loading2.setBackgroundDrawable(anim2);
        anim2.start();

        AnimationDrawable anim3 = new AnimationDrawable();
        anim3.addFrame(drawableGray, 300);
        anim3.addFrame(drawableGray, 300);
        anim3.addFrame(drawableBlue, 300);
        anim3.setOneShot(false);
        dot_loading3.setBackgroundDrawable(anim3);
        anim3.start();
        return dlg;
    }

    //获得加载文字颜色
    protected abstract int getLoadMsgColor();

    //获得加载文字
    protected abstract String getLoadMsgText();

    //获得加载图片
    protected abstract int getLoadImg();


    //设置加载进度图1
    protected abstract int getLoadShape1();

    //设置加载进度图2
    protected abstract int getLoadShape2();

    //设置背景
    protected abstract int getBackgroundShape();

}

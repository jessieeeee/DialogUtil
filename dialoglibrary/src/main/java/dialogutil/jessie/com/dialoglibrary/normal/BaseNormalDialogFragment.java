package dialogutil.jessie.com.dialoglibrary.normal;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dialogutil.jessie.com.dialoglibrary.R;
import dialogutil.jessie.com.dialoglibrary.base.BaseDialogFragment;


/**
 * 创建时间: 2016/11/24
 * 编写人: JessieKate
 * 功能描述:提示对话框父类
 */

public abstract class BaseNormalDialogFragment extends BaseDialogFragment {
    protected static final int DEFAULT_ICON=-1;
    protected static final int HIDE_ICON=-2;//隐藏字段
    protected ClickListener clickListener;//按键监听

    //监听接口
    public interface ClickListener {
        void clickSure();

        void clickCancel();
    }

    //设置自定义窗口和监听
    public Dialog setDialog(View view) {
        Dialog dlg = new Dialog(getActivity(), R.style.MyDialogBottom);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setContentView(view);
        dlg.getWindow().setBackgroundDrawableResource(R.color.transparent);
        TextView textMsg = (TextView) view.findViewById(R.id.text_msg);
        Button sureButton = (Button) view.findViewById(R.id.btn_sure);
        Button cancelButton = (Button) view.findViewById(R.id.btn_cancel);
        ImageView dialog_icon= (ImageView) view.findViewById(R.id.dialog_icon);
        ImageView divider_vertical= (ImageView) view.findViewById(R.id.divider_vertical);
        ImageView divider_horizontal= (ImageView) view.findViewById(R.id.divider_horizontal);

        if (getSureTextColor() != DEFAULT_COLOR) {
            sureButton.setTextColor(getResources().getColor(getSureTextColor()));
        }
        if (getCancelTextColor() != DEFAULT_COLOR) {
            cancelButton.setTextColor(getResources().getColor(getCancelTextColor()));
        }
        if (getCancelBg() != DEFAULT_BG) {
            sureButton.setBackgroundResource(getSureBg());
        }
        if (getSureBg() != DEFAULT_BG) {
            sureButton.setBackgroundResource(getSureBg());
        }

        if (getDividerHorizontalColor() != DEFAULT_COLOR){
            divider_horizontal.setBackgroundColor(getResources().getColor(getDividerHorizontalColor()));
        }
        if (getDividerVerticalColor() != DEFAULT_COLOR){
            divider_vertical.setBackgroundColor(getResources().getColor(getDividerVerticalColor()));
        }

        if( getDialogIcon() != DEFAULT_ICON){
            if(getDialogIcon()==HIDE_ICON){
                dialog_icon.setVisibility(View.GONE);
            }else{
                dialog_icon.setVisibility(View.VISIBLE);
                dialog_icon.setImageResource(getDialogIcon());
            }
        }

        textMsg.setText(getContent());
        sureButton.setText(getSureText());
        cancelButton.setText(getCancelText());
        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.clickSure();
                }
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.clickCancel();
                }
                dismiss();
            }
        });
        return dlg;
    }


    public BaseNormalDialogFragment setOnBtnClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }


    protected abstract int getSureTextColor();

    protected abstract int getCancelTextColor();

    protected abstract String getSureText();

    protected abstract String getCancelText();

    protected abstract String getContent();

    protected abstract int getSureBg();

    protected abstract int getCancelBg();

    protected abstract int getDialogIcon();

    protected abstract int getDividerVerticalColor();

    protected abstract int getDividerHorizontalColor();

}

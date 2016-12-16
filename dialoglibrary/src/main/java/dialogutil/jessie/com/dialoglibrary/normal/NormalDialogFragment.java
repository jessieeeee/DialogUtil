package dialogutil.jessie.com.dialoglibrary.normal;

import android.os.Bundle;

import dialogutil.jessie.com.dialoglibrary.R;


/**
 * 创建时间: 2016/11/24
 * 编写人: JessieKate
 * 功能描述:具体实现的提示对话框
 */

public class NormalDialogFragment extends BaseNormalDialogFragment {

    private String content="";
    private String suretext="确定";
    private String canceltext="取消";
    private int sureTextColor=DEFAULT_COLOR;
    private int cancelColor=DEFAULT_COLOR;
    private int sureBg=DEFAULT_BG;
    private int cancelBg=DEFAULT_BG;
    protected static final String TAG_ARG = "layout";

    public static NormalDialogFragment newInstance() {
        //创建一个带有参数的Fragment实例
        NormalDialogFragment fragment = new NormalDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAG_ARG, R.layout.dialog_normal);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            layout = arguments.getInt(TAG_ARG, 0);
        }
    }


    public NormalDialogFragment setContent(String content) {
        this.content = content;
        return this;
    }


    public NormalDialogFragment setSuretext(String suretext) {
        this.suretext = suretext;
        return this;
    }


    public NormalDialogFragment setCanceltext(String canceltext) {
        this.canceltext = canceltext;
        return this;
    }


    public NormalDialogFragment setSureTextColor(int sureTextColor){
        this.sureTextColor=sureTextColor;
        return this;
    }

    public NormalDialogFragment setCancelTextColor(int cancelColor){
        this.cancelColor=cancelColor;
        return this;
    }

    public NormalDialogFragment setSureBg(int sureBg){
        this.sureBg=sureBg;
        return this;
    }

    public NormalDialogFragment setCancelBg(int cancelBg){
        this.cancelBg=cancelBg;
        return this;
    }
    @Override
    protected int getSureTextColor() {
        return sureTextColor;
    }


    @Override
    protected int getCancelTextColor() {
        return cancelColor;
    }


    @Override
    protected String getSureText() {
        return suretext;
    }

    @Override
    protected String getCancelText() {
        return canceltext;
    }

    @Override
    protected String getContent() {
        return content;
    }

    @Override
    protected int getSureBg() {
        return sureBg;
    }

    @Override
    protected int getCancelBg() {
        return cancelBg;
    }


}

package dialogutil.jessie.com.dialoglibrary.load;

import android.os.Bundle;

import dialogutil.jessie.com.dialoglibrary.R;


/**
 * 创建时间: 03/12/2016
 * 编写人: JessieKate
 * 功能描述:实现加载对话框
 */

public class LoadDialogFragment extends BaseLoadDialogFragment{

    protected static final String TAG_ARG = "layout";
    private String loadMsgText="";
    private int loadMsgColor=DEFAULT_COLOR;
    private int loadImg=DEFAULT_BG;
    private int loadShape1=R.drawable.load_cicle_blue;
    private int loadShape2=R.drawable.load_cicle_gray;
    private int backgroundShape=R.drawable.bg_load;

    public static LoadDialogFragment newInstance(){
        LoadDialogFragment fragment=new LoadDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAG_ARG, R.layout.dialog_load);
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

    public LoadDialogFragment setLoadMsgText(String loadMsgText){
        this.loadMsgText=loadMsgText;
        return this;
    }

    public LoadDialogFragment setLoadMsgColor(int loadMsgColor){
        this.loadMsgColor=loadMsgColor;
        return this;
    }

    public LoadDialogFragment setLoadImg(int loadImg){
        this.loadImg=loadImg;
        return this;
    }

    public LoadDialogFragment setBackgroundShape(int backgroundShape){
        this.backgroundShape=backgroundShape;
        return this;
    }

    public LoadDialogFragment setLoadShape1(int res){
        this.loadShape1=res;
        return this;
    }

    public LoadDialogFragment setLoadShape2(int res){
        this.loadShape2=res;
        return this;
    }


    @Override
    protected int getLoadMsgColor() {
        return loadMsgColor;
    }

    @Override
    protected String getLoadMsgText() {
        return loadMsgText;
    }

    @Override
    protected int getLoadImg() {
        return loadImg;
    }

    @Override
    protected int getLoadShape1() {
        return loadShape1;
    }

    @Override
    protected int getLoadShape2() {
        return loadShape2;
    }

    @Override
    protected int getBackgroundShape() {
        return backgroundShape;
    }

}

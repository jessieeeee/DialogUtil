package dialogutil.jessie.com.dialoglibrary.list;

import android.os.Bundle;

import java.util.List;

import dialogutil.jessie.com.dialoglibrary.R;


/**
 * 创建时间: 2016/11/25
 * 编写人: JessieKate
 * 功能描述:实现列表对话框
 */

public class ListDialogFragment extends BaseListDialogFragment {

    protected static final String TAG_ARG = "layout";
    private String listTitleText="分享到";
    private String backText="取消";
    private String sureText="确定";
    private int listTitleColor=DEFAULT_COLOR;
    private int backTextTitleColor=DEFAULT_COLOR;
    private int sureTextTitleColor=DEFAULT_COLOR;
    private boolean multipleChoice=false;
    private int gravity=BOTTOM;//对话框显示的位置
    private int titleBarColor=DEFAULT_COLOR;//标题栏颜色
    private int listColor=DEFAULT_COLOR;//列表颜色
    private int rowNum =4;
    private List<Item> items;
    public static ListDialogFragment newInstance(int orientation) {
        //创建一个带有参数的Fragment实例
        ListDialogFragment fragment = new ListDialogFragment();
        fragment.orientation=orientation;
        Bundle bundle = new Bundle();
        bundle.putInt(TAG_ARG, R.layout.dialog_list);
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

    //仅在网格布局有效
    public ListDialogFragment setRowNum(int rowNum){
        this.rowNum=rowNum;
        return this;
    }
    public ListDialogFragment setTitleBarColor(int titleBarColor){
        this.titleBarColor=titleBarColor;
        return this;
    }

    public ListDialogFragment setListColor(int listColor){
        this.listColor=listColor;
        return this;
    }

    public ListDialogFragment setItems(List<Item> items){
        this.items=items;
        return this;
    }

    public ListDialogFragment setBackTextTitleColor(int backTextTitleColor){
        this.backTextTitleColor=backTextTitleColor;
        return this;
    }

    public ListDialogFragment setSureTextTitleColor(int sureTextTitleColor){
        this.sureTextTitleColor=sureTextTitleColor;
        return this;
    }

    public ListDialogFragment setListTitleText(String listTitleText){
        this.listTitleText=listTitleText;
        return this;
    }

    public ListDialogFragment setListTitleColor(int listTitleColor){
        this.listTitleColor=listTitleColor;
        return this;
    }

    public ListDialogFragment setBackText(String backText){
        this.backText=backText;
        return this;
    }

    public ListDialogFragment setSureText(String sureText){
        this.sureText=sureText;
        return this;
    }

    public ListDialogFragment setMultipleChoice(boolean multipleChoice){
        this.multipleChoice=multipleChoice;
        return this;
    }

    public ListDialogFragment setGravity(int gravity){
        this.gravity=gravity;
        return this;
    }

    @Override
    public String getListTitleText() {
        return listTitleText;
    }

    @Override
    public String getBackText() {
        return backText;
    }

    @Override
    public String getSureText() {
        return sureText;
    }

    @Override
    public int getListTitleColor() {
        return listTitleColor;
    }

    @Override
    public int getBackTextTitleColor() {
        return backTextTitleColor;
    }

    @Override
    public int getSureTextTitleColor() {
        return sureTextTitleColor;
    }

    @Override
    public boolean getMultipleChoice() {
        return multipleChoice;
    }

    @Override
    public int getGravity() {
        return gravity;
    }

    @Override
    public int getTitleBarColor() {
        return titleBarColor;
    }


    @Override
    public int getListColor() {
        return listColor;
    }

    @Override
    public int getRowNum() {
        return rowNum;
    }

    @Override
    public List<Item> getDataList() {
        return items;
    }



}

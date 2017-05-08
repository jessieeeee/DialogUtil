package com.jessie.dialogutil;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import dialogutil.jessie.com.dialoglibrary.list.BaseListDialogFragment;
import dialogutil.jessie.com.dialoglibrary.list.DialogListAdapter;
import dialogutil.jessie.com.dialoglibrary.list.Item;
import dialogutil.jessie.com.dialoglibrary.list.ListDialogFragment;
import dialogutil.jessie.com.dialoglibrary.load.LoadDialogFragment;
import dialogutil.jessie.com.dialoglibrary.normal.BaseNormalDialogFragment;
import dialogutil.jessie.com.dialoglibrary.normal.NormalDialogFragment;

public class MainActivity extends FragmentActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        Button btn_normal = (Button) findViewById(R.id.btn_normal);
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog();
            }
        });


        Button btn_list_vertical = (Button) findViewById(R.id.btn_list_vertical);
        btn_list_vertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog(BaseListDialogFragment.VERTICAL, BaseListDialogFragment.BOTTOM, true,BaseListDialogFragment.BOTTOM_TO_TOP);
            }
        });

        Button btn_list_horizontal = (Button) findViewById(R.id.btn_list_horizontal);
        btn_list_horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog(BaseListDialogFragment.HORIZONTAL, BaseListDialogFragment.CENTER, false,BaseListDialogFragment.BOTTOM_TO_TOP);
            }
        });

        Button btn_list_grid = (Button) findViewById(R.id.btn_list_grid);
        btn_list_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog(BaseListDialogFragment.GRID, BaseListDialogFragment.TOP, false,BaseListDialogFragment.TOP_TO_BOTTOM);
            }
        });
        Button btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadDialog();
            }
        });
    }

    //展示普通提示框
    public void showNormalDialog() {
        NormalDialogFragment.newInstance()
                .setContent("这只是一个普通的提示弹窗")
                .setSureTextColor(R.color.white)
                .setCancelTextColor(R.color.default_blue)
                .setSuretext("确定")
                .setCanceltext("取消")
                .hideIcon()
                .setSureBg(R.drawable.dialog_btn_bg_round)
                .setOnBtnClickListener(new BaseNormalDialogFragment.ClickListener() {
                    @Override
                    public void clickSure() {
                        Toast.makeText(context, "点击了确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void clickCancel() {
                        Toast.makeText(context, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setDialogAnimation(BaseListDialogFragment.BOTTOM_TO_TOP)
                .showDialog(getFragmentManager());
    }

    //展示列表框
    public void showListDialog(int orientation, int gravity, final boolean multipleChoice,int animation) {
        ListDialogFragment.newInstance(orientation)
                .setListTitleText("分享到")
                .setBackText("返回")
                .setSureText("确定")
                .setMultipleChoice(multipleChoice)
                .setGravity(gravity)
                .setClickListener(new BaseListDialogFragment.ClickListener() {
                    @Override
                    public void clickSure(List<Item> selectItems) {
                        if(selectItems.size()>0){
                            String selectStr="";
                            Iterator<Item> it=selectItems.iterator();
                            while(it.hasNext()){
                                selectStr+=" "+it.next().getTitle();
                            }
                            Toast.makeText(context, "选择了"+selectStr, Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(context, "点击了确定", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void clickBack() {
                        Toast.makeText(context, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnItemClickListener(new DialogListAdapter.OnItemClickListener() {
                    @Override
                    public void click(Item item, BaseListDialogFragment baseListDialogFragment) {
                        Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if(!multipleChoice){
                            baseListDialogFragment.dismiss();
                        }

                    }
                })
                .setDialogAnimation(animation)
                .showDialog(getFragmentManager());
    }

    //展示加载对话框
    public void showLoadDialog() {
        LoadDialogFragment loadDialogFragment = LoadDialogFragment.newInstance()
                .setLoadMsgText("正在加载中")
                .setLoadImg(R.drawable.load_progress)
                .setLoadMsgColor(R.color.white);
        loadDialogFragment .setDialogAnimation(BaseListDialogFragment.BOTTOM_TO_TOP).showDialog(getFragmentManager());
//        loadDialogFragment.dismiss();
    }
}

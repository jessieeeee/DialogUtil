package com.jessie.dialogutil.list;

import android.app.Dialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jessie.dialogutil.R;
import com.jessie.dialogutil.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2016/11/25
 * 编写人: JessieKate
 * 功能描述:列表对话框父类
 */

public abstract class BaseListDialogFragment extends BaseDialogFragment {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int GRID=2;
    protected int orientation;
    private DialogListAdapter dialogListAdapter;
    private DialogListAdapter.OnItemClickListener onItemClickListener;
    private ClickListener clickListener;

    public static final int BOTTOM=Gravity.BOTTOM;//底部
    public static final int CENTER=Gravity.CENTER;//中间
    public static final int TOP=Gravity.TOP;//顶部

    public interface ClickListener {
        void clickSure(List<Item> selectItems);

        void clickBack();
    }

    @Override
    protected Dialog setDialog(View view) {
        Dialog dlg;
        dlg= new Dialog(getActivity(), animation);
        dlg.setContentView(view);
        setCancelable(true);
        dlg.setCanceledOnTouchOutside(true);
        dlg.getWindow().setGravity(getGravity());
        dlg.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView text_list_title = (TextView) view.findViewById(R.id.text_list_title);
        text_list_title.setText(getListTitleText());

        if (getListTitleColor() != DEFAULT_COLOR) {
            text_list_title.setTextColor(getResources().getColor(getListTitleColor()));
        }

        if(getMultipleChoice()){
            TextView text_list_sure = (TextView) view.findViewById(R.id.text_list_sure);
            TextView text_list_back = (TextView) view.findViewById(R.id.text_list_back);
            text_list_sure.setText(getSureText());
            text_list_back.setText(getBackText());
            text_list_back.setVisibility(View.VISIBLE);
            text_list_sure.setVisibility(View.VISIBLE);
            if (getBackTextTitleColor() != DEFAULT_COLOR) {
                text_list_sure.setTextColor(getResources().getColor(getSureTextTitleColor()));
            }

            if (getSureTextTitleColor() != DEFAULT_COLOR) {
                text_list_back.setTextColor(getResources().getColor(getBackTextTitleColor()));
            }

            text_list_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.clickSure(dialogListAdapter.getSelectItems());
                    dismiss();
                }
            });


            text_list_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.clickBack();
                    dismiss();
                }
            });
        }

        RecyclerView dialog_list = (RecyclerView) view.findViewById(R.id.dialog_list);
        List<Item> items = new ArrayList<>();

        items.add(new Item(1, "通讯录", getActivity().getResources().getDrawable(R.drawable.icon_content_phone)));
        items.add(new Item(2, "好友", getActivity().getResources().getDrawable(R.drawable.icon_friend)));
        items.add(new Item(3, "朋友圈", getActivity().getResources().getDrawable(R.drawable.icon_moments)));
        items.add(new Item(4, "微信", getActivity().getResources().getDrawable(R.drawable.icon_wechat)));
        addItems(items, dialog_list);

        return dlg;
    }


    public void addItems(List<Item> items, RecyclerView dialog_list) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RecyclerView.LayoutManager manager;
        dialogListAdapter = new DialogListAdapter(getActivity().getApplicationContext(), items, orientation,getMultipleChoice(),this);
        dialogListAdapter.setOrientation(orientation);
        dialogListAdapter.setItemClick(onItemClickListener);
        if (orientation == HORIZONTAL)
            manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        else if (orientation == GRID)
            manager = new GridLayoutManager(getActivity().getApplicationContext(), DialogListAdapter.ROW_NUM);
        else
            manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        dialog_list.setLayoutParams(params);
        dialog_list.setLayoutManager(manager);
        dialog_list.setAdapter(dialogListAdapter);
    }

    public abstract String getListTitleText();

    public abstract String getBackText();

    public abstract String getSureText();

    public abstract int getListTitleColor();

    public abstract int getBackTextTitleColor();

    public abstract int getSureTextTitleColor();

    public abstract boolean getMultipleChoice();

    public abstract int getGravity();

    public BaseListDialogFragment setOnItemClickListener(DialogListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public BaseListDialogFragment setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }


}

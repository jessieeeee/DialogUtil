package dialogutil.jessie.com.dialoglibrary.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dialogutil.jessie.com.dialoglibrary.R;

/**
 * 创建时间: 2016/11/25
 * 编写人: JessieKate
 * 功能描述:列表适配器
 */

public class DialogListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int leftPadding;
    private int padding;
    private int leftIcon;
    private int topPadding;
    private int topIcon;
    private Context context;

    private List<Item> mItems = Collections.emptyList();
    private OnItemClickListener itemClickListener;
    public  final int HORIZONTAL = 0;
    public  final int VERTICAL = 1;
    public  final int GRID=2;
    private BaseListDialogFragment baseListDialogFragment;
    private int orientation;
    public  int rowNum=4;
    private boolean multipleChoice;
    private List<Item> selectItems;
    public interface OnItemClickListener {
        void click(Item item,BaseListDialogFragment baseListDialogFragment);
    }


    public DialogListAdapter(Context context,List<Item> mItems, int orientation,boolean multipleChoice,BaseListDialogFragment baseListDialogFragment){
        this.context=context;
        setList(mItems);
        leftPadding = context.getResources().getDimensionPixelSize(R.dimen.dimen_10);
        padding = context.getResources().getDimensionPixelSize(R.dimen.dimen_4);
        leftIcon = context.getResources().getDimensionPixelSize(R.dimen.dimen_50);
        topIcon=leftIcon;
        topPadding=leftPadding;
        this.orientation=orientation;
        this.multipleChoice=multipleChoice;
        this.baseListDialogFragment=baseListDialogFragment;
        if(multipleChoice){
            selectItems=new ArrayList<>();
        }
    }

    public void setRowNum(int rowNum){
        this.rowNum=rowNum;
    }

    public void setItemClick(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        notifyDataSetChanged();
    }


    private void setList(List<Item> items) {
        mItems = items == null ? new ArrayList<Item>() : items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (orientation == GRID)
            return new TopHolder(new LinearLayout(parent.getContext()));
        else if (orientation == HORIZONTAL)
            return new TopHolder(new LinearLayout(parent.getContext()));
        else return new LeftHolder(new LinearLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = mItems.get(position);

        final TopHolder topHolder;
        final LeftHolder leftHolder;

        if (orientation == GRID) {
            topHolder = (TopHolder) holder;

            topHolder.item.setText(item.getTitle());
            topHolder.item.setCompoundDrawablesWithIntrinsicBounds(null, topHolder.icon(item.getIcon()), null, null);
            topHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        if(multipleChoice){
                            if(item.isCheck()){
                                topHolder.item.setTextColor(context.getResources().getColor(R.color.black));
                                item.setCheck(false);
                                selectItems.remove(item);
                            }else{
                                topHolder.item.setTextColor(context.getResources().getColor(R.color.colorAccent));
                                item.setCheck(true);
                                selectItems.add(item);
                            }

                        }
                        itemClickListener.click(item,baseListDialogFragment);
                    }
                }
            });
        } else if (orientation == HORIZONTAL) {
            topHolder = (TopHolder) holder;

            topHolder.item.setText(item.getTitle());
            topHolder.item.setCompoundDrawablesWithIntrinsicBounds(null, topHolder.icon(item.getIcon()), null, null);
            topHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        if (multipleChoice){
                            if(item.isCheck()){
                                topHolder.item.setTextColor(context.getResources().getColor(R.color.black));
                                item.setCheck(false);
                                selectItems.remove(item);
                            }else{
                                topHolder.item.setTextColor(context.getResources().getColor(R.color.colorAccent));

                                item.setCheck(true);
                                selectItems.add(item);
                            }
                        }
                        itemClickListener.click(item,baseListDialogFragment);

                    }
                }
            });
        } else {
            leftHolder = (LeftHolder) holder;

            leftHolder.item.setText(item.getTitle());
            leftHolder.item.setCompoundDrawablesWithIntrinsicBounds(leftHolder.icon(item.getIcon()), null, null, null);
            leftHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        if (multipleChoice){
                            if(item.isCheck()){
                                leftHolder.item.setTextColor(context.getResources().getColor(R.color.black));
                                item.setCheck(false);
                                selectItems.remove(item);
                            }else{
                                leftHolder.item.setTextColor(context.getResources().getColor(R.color.colorAccent));
                                item.setCheck(true);
                                selectItems.add(item);
                            }
                        }
                        itemClickListener.click(item,baseListDialogFragment);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class TopHolder extends RecyclerView.ViewHolder {
        private TextView item;

        public TopHolder(View view) {
            super(view);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if(orientation == HORIZONTAL){
                params.width = getScreenWidth(context) / mItems.size();
            }else{
                params.width = getScreenWidth(context) / rowNum;
            }
            params.setMargins(0, padding, 0, padding);
            item = new TextView(view.getContext());
            item.setLayoutParams(params);
            item.setMaxLines(1);
            item.setEllipsize(TextUtils.TruncateAt.END);
            item.setGravity(Gravity.CENTER);
            item.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
            item.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.font_14));
            item.setCompoundDrawablePadding(topPadding);

            TypedValue typedValue = new TypedValue();
            view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            item.setBackgroundResource(typedValue.resourceId);

            ((LinearLayout) view).addView(item);
        }

        private Drawable icon(Drawable drawable) {
            if (drawable != null) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                Drawable resizeIcon = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, topIcon, topIcon, true));
                Drawable.ConstantState state = resizeIcon.getConstantState();
                resizeIcon = DrawableCompat.wrap(state == null ? resizeIcon : state.newDrawable().mutate());
                return resizeIcon;
            }
            return null;
        }
    }


    public class LeftHolder extends RecyclerView.ViewHolder {
        private TextView item;

        public LeftHolder(View view) {
            super(view);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            params.setMargins(padding, padding, padding, padding);
            item = new TextView(view.getContext());
            item.setLayoutParams(params);
            item.setMaxLines(1);
            item.setEllipsize(TextUtils.TruncateAt.END);
            item.setGravity(Gravity.CENTER_VERTICAL);
            item.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
            item.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.font_14));
            item.setCompoundDrawablePadding(leftPadding);

            TypedValue typedValue = new TypedValue();
            view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            item.setBackgroundResource(typedValue.resourceId);

            ((LinearLayout) view).addView(item);
        }

        private Drawable icon(Drawable drawable) {
            if (drawable != null) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                Drawable resizeIcon = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, leftIcon, leftIcon, true));
                Drawable.ConstantState state = resizeIcon.getConstantState();
                resizeIcon = DrawableCompat.wrap(state == null ? resizeIcon : state.newDrawable().mutate());
                return resizeIcon;
            }
            return null;
        }
    }

    public List<Item> getSelectItems(){
        return selectItems;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}

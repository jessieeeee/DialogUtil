# DialogUtil
Multiple types dialog in android program use DialogFragment
## support
### normal dialog
### list dialog(single-choice,multiple-choice,list and grid)
### load dialog

- ![image](http://oqujmbgen.bkt.clouddn.com/%E5%88%A9%E7%94%A8dialogfragment%E5%B0%81%E8%A3%85%E5%BC%B9%E7%AA%97%E5%B7%A5%E5%85%B7%E7%B1%BB2.gif)


## Using library in your application
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
	<pre>
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	</pre>
### Step 2. Add the dependency
<pre>
dependencies {
compile 'com.github.jessieeeee:DialogUtil:v1.1'
	}
</pre>
###How to Use
1. normalDialog
```java
   NormalDialogFragment.newInstance()
                 .setContent("这只是一个普通的提示弹窗")
                 .setSureTextColor(R.color.white)
                 .setCancelTextColor(R.color.default_blue)
                 .setSuretext("确定")
                 .setCanceltext("取消")
                 .setDividerHorizontalColor(R.color.default_line)
                 .setDividerVerticalColor(R.color.default_line)
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
```
2. listDialog
```java
    List<Item> items = new ArrayList<>();
   
           items.add(new Item(1, "通讯录", getResources().getDrawable(R.mipmap.icon_content_phone)));
           items.add(new Item(2, "好友", getResources().getDrawable(R.mipmap.icon_friend)));
           items.add(new Item(3, "朋友圈", getResources().getDrawable(R.mipmap.icon_moments)));
           items.add(new Item(4, "微信", getResources().getDrawable(R.mipmap.icon_wechat)));
           items.add(new Item(5, "微博", getResources().getDrawable(R.mipmap.icon_weibo)));
           ListDialogFragment.newInstance(orientation)
                   .setListTitleText("分享到")
                   .setListTitleColor(R.color.black)
                   .setBackText("返回")
                   .setSureText("确定")
                   .setRowNum(3)   //仅在网格布局有效
                   .setBackTextTitleColor(R.color.colorAccent)
                   .setSureTextTitleColor(R.color.colorAccent)
                   .setItems(items)
                   .setTitleBarColor(R.color.white)
                   .setListColor(R.color.white)
                   .setItemTextColor(R.color.black)
                   .setMultipleChoice(multipleChoice)
                   .setSelectBackgroundColor(R.color.white)
                   .setSelectItemTextColor(R.color.colorAccent)
                   .setGravity(gravity)
                   .setClickListener(new BaseListDialogFragment.ClickListener() { //只在多选时有效
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
```

3. loadDialog
```java
   LoadDialogFragment loadDialogFragment = LoadDialogFragment.newInstance()
                .setLoadMsgText("正在加载中")
                .setLoadImg(R.drawable.load_progress)
                .setLoadShape1(R.drawable.load_cicle_blue)
                .setLoadShape2(R.drawable.load_cicle_gray)
                .setBackgroundShape(R.drawable.bg_load)
                .setLoadMsgColor(R.color.white);

        loadDialogFragment .setDialogAnimation(BaseListDialogFragment.BOTTOM_TO_TOP).showDialog(getFragmentManager());
//        loadDialogFragment.dismiss();
```





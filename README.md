# DialogUtil
Multiple types dialog in android program use DialogFragment
## support
### normal dialog
### list dialog(single-choice,multiple-choice,list and grid)
### load dialog

- ![image](https://github.com/jessieeeee/DialogUtil/blob/master/Kapture%202016-12-09%20at%2021.36.33.gif)


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
compile 'com.github.jessieeeee:DialogUtil:v1.0.1'
	}
</pre>
###How to Use
1. normalDialog
<pre>
 NormalDialogFragment.newInstance()
                .setContent("这只是一个普通的提示弹窗")
                .setSureTextColor(R.color.white)
                .setCancelTextColor(R.color.default_blue)
                .setSuretext("确定")
                .setCanceltext("取消")
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
</pre>
2. listDialog
<pre>
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
</pre>

3. loadDialog
<pre>
 LoadDialogFragment loadDialogFragment = LoadDialogFragment.newInstance()
                .setLoadMsgText("正在加载中")
                .setLoadImg(R.drawable.load_progress)
                .setLoadMsgColor(R.color.white);
        loadDialogFragment .setDialogAnimation(BaseListDialogFragment.BOTTOM_TO_TOP).showDialog(getFragmentManager());
//        loadDialogFragment.dismiss();
</pre>





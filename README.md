Android-TransitionAnimation
======================

###说明

前些日子在微信朋友圈看到一个朋友发了个效果图，被炫哭了，原来Android还有这样吊炸天的动画效果！然后知道了他是从这里下载的：[google store Depth Library Demo](https://play.google.com/store/apps/details?id=no.agens.depth)，Github上并未看到作者开源出来。只是怀着学习的心态，反编译了这个Demo，并学习它炫酷的过渡动画是怎么实现的。

####我实现的效果如下：

![](http://ww2.sinaimg.cn/mw690/7ef01fcajw1f3269ss5scg20bb0hhhdu.gif)

有没有一种款拽酷炫吊炸天的既视感，咳，我自己都被深深感动到了，不知道你看后有什么感受？

如果觉得动画太快没看清，没事，这里还有慢动作的效果：

![](http://ww4.sinaimg.cn/mw690/7ef01fcajw1f3269yhcb7g20bb0hhhdu.gif)

搞出来之后，觉得解决反编译出来的魔法数字、代码、理解原作者所写的实现方式的等诸多问题都不是问题了，至少知道了它原来是这样炫出来的。

###简单讲讲实现方式

####(1)点击Fab按钮触发当前fragment离开的动画事件
```java
TransitionHelper.startExitAnim(WaterFragment.this.root);
```
跟进去看看是这样的
```java
    public static void startExitAnim(View paramView) {
        exitAnimate((DepthLayout) paramView.findViewById(R.id.root_dl), 0.0F, 30.0F, 15L, 190, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.appbar), 15.0F, 20.0F, 30L, 170, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.fab_container), 30.0F, 20.0F, 45L, 210, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.dl2), 15.0F, 20.0F, 60L, 230, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.dl3), 30.0F, 20.0F, 75L, 250, true);
        hideStatusBar(paramView);
    }
```
通过传入的`paramView`找到内部的child view 并各自做离开时的动画，`exitAnimate`方法在`TransitionHelper`类中，这个是动画的帮助类，其中视图进入的动画，恢复动画等方法都在这里做了实现，具体可以看源码了解更多。
####(2)当前fragment离开后，另一个fragment进入
```java
localWindFragment.setIntroAnimate(true);
((RootActivity) WaterFragment.this.getActivity()).goToFragment(localWindFragment);
```
进入的动画效果在`TransitionHelper`类中的一个`introAnimate`方法实现,主要是一些view视图的缩小、移动动画，具体看源码。

####(3)层级关系
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.cjj.customview.DepthRendrer
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent">
    
    <com.cjj.customview.DepthLayout
        ...
        android:paddingTop="@dimen/appbar_height">
        <ImageView
            ...
            />
    </com.cjj.customview.DepthLayout>
    

    <com.cjj.customview.DepthLayout
        ...
        app:edge_color="@color/statusbar2">

        <ImageView
           .../>

        <TextView
          .../>
    </com.cjj.customview.DepthLayout>
  
    <com.cjj.customview.DepthLayout
        ......
        app:custom_elevation="6.0dip"
        app:edge_color="#ff9a6a70"
        app:is_circle="true">

        <android.support.design.widget.FloatingActionButton
            android:id="@+id/fab"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:clickable="true"
            android:src="@drawable/ic_forward"
          />
    </com.cjj.customview.DepthLayout>
</com.cjj.customview.DepthRendrer>
```
从xml文件中，可以看出定义的DepthRendrer、DepthLayout，它们都继承了RelativeLayout，并实现绘制阴影等效果。

* 注意：过多的图层嵌套，导致了掉帧的了情况，这是可以优化的。有时间我优化下，不过效果还是蛮流畅的，你可以真机试试。


###关于我
如果你喜欢这个东东的话，可以关注我[github](https://github.com/android-cjj) ,也可以关注我微博[Android_cJJ](http://weibo.com/chenjijun2011/).


License
=======

    The MIT License (MIT)

	Copyright (c) 2016 android-cjj

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.












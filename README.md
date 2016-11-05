>CustomView这个项目主要是实现一系列的动画，分为几个系列。有些是之前博客写过了，有些是新写的，在这里都做下汇总。都是自定义的view，在xml布局里面直接引用就行了。

#1、Bezier系列
##动画演示

![二阶贝塞尔曲线](https://github.com/AdleyLong/CustomView/blob/master/pic/bezier01.gif)
![三阶贝塞尔曲线](https://github.com/AdleyLong/CustomView/blob/master/pic/bezier02.gif)

![贝塞尔曲线－画板](https://github.com/AdleyLong/CustomView/blob/master/pic/bezier03.gif)
![贝塞尔曲线－360清除动画](https://github.com/AdleyLong/CustomView/blob/master/pic/bezier04.gif)

##知识点
二阶贝塞尔曲线实现
```java
//设置起点  
path.moveTo(200,200);  
//设置辅助点坐标 300，200       
//终点坐标400，200  
path.quadTo(300, 200, 400, 200); 
```

三阶贝塞尔曲线实现
```java
//设置起点 200，200
path.moveTo(200,200); 
//辅助点1 100，100
//辅助点2 200，200
//辅助点3 300，300
mPath.cubicTo(100, 100, 200, 200, 300, 300);
```

贝赛尔曲线公式请参考维基百科：https://en.wikipedia.org/wiki/B%C3%A9zier_curve

#2、渐变渲染系列
##动画演示
![闪动文字](https://github.com/AdleyLong/CustomView/blob/master/pic/gradient01.gif)
![动态音频条](https://github.com/AdleyLong/CustomView/blob/master/pic/gradient02.gif)
![倒影](https://github.com/AdleyLong/CustomView/blob/master/pic/gradient03.png)

##知识点
1、讲一下LinearGradient，关于他的参数：

第一个,第二个参数表示渐变起点 可以设置起点终点在对角等任意位置

第三个,第四个参数表示渐变终点

第五个参数表示渐变颜色

第六个参数可以为空,表示坐标,值为0-1 new float[] {0.25f, 0.5f, 0.75f, 1 } 如果这是空的，颜色均匀分布，沿梯度线。

第七个表示平铺方式：CLAMP重复最后一个颜色至最后 ，MIRROR重复着色的图像水平或垂直方向已镜像方式填充会有翻转效果 ， REPEAT重复着色的图像水平或垂直方向


2、闪动文字实现参考博客 http://blog.csdn.net/picasso_l/article/details/49735121

3、动态音频条实线参考博客 http://blog.csdn.net/picasso_l/article/details/49737471

4、倒影实现参考博客 http://blog.csdn.net/picasso_l/article/details/49814905



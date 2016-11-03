>CustomView这个项目主要是实现一系列的动画，分为几个系列。都是自定义的view，在xml布局里面直接引用就行了。

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

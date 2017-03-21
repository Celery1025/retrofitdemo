Android 架构选型分析
一、Android市场普遍开发模式现状
就目前Android市场而言，绝大多数app选择的开发模式是mvc，而这些mvc开发模式中又有不小的一部分开发模式甚至不能称得上是mvc模式，mvc顾名思义model-view-control，有一部分app因为缺乏基本的封装，包目录随意增删，java文件随意放置，最终结果是，control和view混合在一起，目录结构混乱，很不利于维护。
现在主流的app中，像美团，饿了么、网易音乐等都采用mvp开发模式。又因为mvp开发模式对Android工程师有一定的考验，很多中小型的公司在实际项目开发中并不采用mvp框架，对一般Android工程师而言，mvp甚至mvvm是难以理解的，大部分人情况是，写着写着mvp就变成了mvc。
二、Android 主流开发模式类型
目前Android市场中，主流开发模式无非三种mvc、mvp、mvvm，其中在实际项目中基本上只有mvc和mvp的开发模式。对于mvvm在目前的开发大环境中是到底是一是一个怎么样的结构还在不断演变中，并没有达成绝对官方的共识。
说白了开发模式的演变无非解决这些问题：
1、使代码简洁、清晰。
2、代码高度解耦。
3、拓展性高有利于维护升级等。
从发展历程来讲Android最早的开发模式是mvc后面演变出mvp最近几年演变出mvvm的开发模式。

三、MVC 开发模式介绍
MVC就是Model-View-Controller，它们的作用是：

(数据模型)Model：数据的封装和保存，业务逻辑和实体模型
(视图)View：视图界面，对应于布局文件
(控制器)Controller：业务逻辑，对应于Activity、Fragment等

它们之间的关系如下图所示：
　　　　View传送指令到Controller,Controller完成业务逻辑后，改变Model的状态，Model将新的数据发送到View，这就是MVC模式的处理逻辑。·


四、MVP开发模式介绍
MVP是Model-View-Presenter，它们的作用如下
Model：业务逻辑和实体模型，用来操作实际的数据，包含Bean和Model的抽象接口来降低耦合。
   View：就是Android中的视图，需要建立一个View的抽象接口View Interface。通过实现View的接口来实现View与Presenter的交互，从而降低耦合。对应于Activity，负责View的绘制与用户交互；
   Presenter：View和Model的中间枢纽，处理和用户交互的逻辑。
结构如下图所示：

五、MVVM现状
对于mvvm开发模式，目前Google官方正在开发中，各种模块的设计和选型并没有完全确认，而对于市场上的app使用这种开发模式的也是少之又少。可参考android-architecture Github地址如下：
https://github.com/googlesamples/android-architecture

Github 截图如下：

六、Mvc 和mvp对比
MVC和MVP区别：

其实最明显的区别就是，MVC中是允许Model和View进行交互的，而MVP中很明显，Model与View之间的交互由Presenter完成。还有一点就是Presenter与View之间的交互是通过接口的（代码中会体现)。
MVC 到MVP的转变
MVC到MVP的一个转变，减少了Activity的职责，简化了Activity中的代码，将复杂的逻辑代码提取到了Presenter中进行处理。与之对应的好处就是，耦合度更低，更方便的进行测试。

转变为：







两种模式优缺点对比：
开发模式	优点	缺点
MVC	1、结构简单、易于理解，上手容易学    习成本低。
2、实体模型和业务逻辑分离
3、代码复用性良好	1、业务逻辑和展示层view混合，耦合性高。
2、不利于维护，不利于测试业务逻辑。
3、代码结构不够清晰。
4、Activity承担较多功能
MVP	1、代码结构清晰
2、解耦性高
3、易拓展、维护
4、实体模型和业务模型分离
5、代码复用性高
6、Activity只做显示	1、学习成本较高，上手难度较大。
2、代码结构较多，产生较多的类。

下面是mvc和mvp代码展示：
Mvc代码截图：

Mvp代码截图：
















业务逻辑定义：

七、响应式编程的好处（rxAndroid、rxjava）
    响应式编程的好处可以总结成一两个字：简洁

异步操作很关键的一点是程序的简洁性，因为在调度过程比较复杂的情况下，异步代码经常会既难写也难被读懂。 Android 创造的AsyncTask 和Handler ，其实都是为了让异步代码更加简洁。RxJava 的优势也是简洁，但它的简洁的与众不同之处在于，随着程序逻辑变得越来越复杂，它依然能够保持简洁。
假设有这样一个需求：界面上有一个自定义的视图 imageCollectorView ，它的作用是显示多张图片，并能使用 addImage(Bitmap) 方法来任意增加显示的图片。现在需要程序将一个给出的目录数组 File[] folders 中每个目录下的 png 图片都加载出来并显示在imageCollectorView 中。需要注意的是，由于读取图片的这一过程较为耗时，需要放在后台执行，而图片的显示则必须在 UI 线程执行。常用的实现方式有多种，我这里贴出其中一种：


而如果使用 RxJava ，实现方式是这样的：


八、Dagger2好处
我们知道Dagger是一个依赖注入的框架，那么什么是依赖注入呢？
我们在activity中有可能会用到很多很多的类，这些类要在activity中进行实例化，这样就导致我们的activity非常依赖这么多的类，这样的程序耦合非常
严重，不便于维护和扩展，有什么办法可以不去依赖这些类呢，这时候就需要有一个容器（IoC），将这些类放到这个容器里并实例化，我们activity在用
到的时候去容器里面取就可以了，我们从依赖类到依赖这个容器，实现了解耦，这就是我所理解的依赖注入，即所谓控制反转；
简单的说 Dagger就是用来创造这个容器，所有需要被依赖的对象在Dagger的容器中实例化，并通过Dagger注入到合适的地方，实现解耦，MVP框架就是为解耦而生，因此MVP和Dagger是绝配；
详情看：
http://www.cnblogs.com/all88/p/5788556.html

九、总结
出于mvp、rxjava、dagger2的诸多好处和成熟性，框架选型中，我建议采用如下：


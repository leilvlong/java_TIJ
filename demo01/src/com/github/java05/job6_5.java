package com.github.java05;

/*
对构造器理解思路梳理:
        1.构造方法更多的作用是为该类的加载提供一个加载入口
    以及为该类的成员变量传递创建对象时指定的初始化值这两个
    作用
        2.构造器可以确保对象被正确构造（指构造，非初始化），
    并对对象的每个元素的权限做效验，从而得到哪些事可以被导
    出类访问，哪些是拒绝访问
        这就使得构造方法能访问该类所有成员（所以这种运作模
    式是否能更加佐证其本质是静态的）

 以上这两点并不冲突:
        就好像一台电脑是windows系统,有方便快捷的图形化界面,
    但亦有很多服务在运行,这些并不直接对用户开放。
        拿电脑的无线连接来说，用户通过图形化界面，只需点击
    操作，输入密码即可完成联网。但是其底层是由无线驱动服务
    与硬件设备所支持的，用户并不需要知道这些系统级服务亦可
    使用
        而java的构造器亦是如此,我们作为客户端程序员,构造器
    开放给我们的便是通过构造器来访问类并为该类成员赋值，其
    系统服务则是确保该类可以正确创建对象，效验权限等功能
        知道这些对于编程的帮助并不会太大，因为有能力对系统
    服务做出改变的是很少一部分开发者，但是如果无线网卡驱动
    坏了，你都不知道有这个东西，解决问题又何从谈起


 */

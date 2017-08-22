# LJFramework
自用的 Android 快速开发框架

## 依赖

* [Icepick](https://github.com/frankiesardo/icepick) 自动保存恢复实例状态
* [AndroidEventBus](https://github.com/hehonghui/AndroidEventBus) 订阅函数支持tag的事件总线框架
* [RxJava](https://github.com/ReactiveX/RxJava) RxJava2
* [RxBinding](https://github.com/JakeWharton/RxBinding) RxBinding2
* [ButterKnife](https://github.com/JakeWharton/butterknife) 使用注释绑定视图
* [CommonAdapter](https://github.com/tianzhijiexian/CommonAdapter) 通用的，简易的Adapter
* [LogUtils](https://github.com/pengwei1024/LogUtils) 更方便和易于使用的Android日志管理器
* [Ultra Pull To Refresh](https://github.com/imliujun/android-Ultra-Pull-To-Refresh) 可以包含任何View的下拉刷新

另外还依赖了一些 [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode) 中的工具类。

本框架使用的 MVP 模式为 [MVPArt](https://github.com/JessYanCoding/MVPArt) 具体的使用方式请看该项目文档





## Gradle
```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile "com.github.imliujun.LJFramework:framework:1.0.1"
}
```

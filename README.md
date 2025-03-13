## ReadingGallery（Android组件化项目）<br>

1、本项目基于Jetpack组件开发的MVVM组件化项目,通过gradle.properties中变量isModule控制整包编译还是分包编译；<br>
2、内容包含Kotlin+Koin（依赖注入）+coil（图片加载）+couroutine（协程）+ViewModel+LiveData+ViewBinding+DataBinding；<br>
3、组件化通信基于阿里Arouter开源框架；<br>
4、使用了腾讯API去中心化方案，降低代码耦合；<br>
5、内部封装了具有网络请求和生命周期感知能力的自定义组合View，针对单页面多接口场景比较实用，目前已在生产项目中运用2年多时间，具体可见项目中BaseVBViewComponent；
内部可以根据使用爱好选择Viewbinding还是DataBinding，都在基类做了对应集成，viewmodel同时也可以按自己喜好是否使用koin注入对应View层

## 其他一些学习练手的项目 <br>

**Android组件化项目初始化工具**ApplicationInit链接(gradle-plugin+注解APT+ASM)：https://github.com/WinWang/ApplicationInit <br>

**Flutter版本**的音乐播放App链接(getx+retrofit+dio)：https://github.com/WinWang/music_listener <br>

**React版本**的开眼App链接(React18+React-Vant+Mobx+axios)：https://github.com/WinWang/react-oepn-eye <br>

**Vue2版本**WanAndroid链接(Vue2+vuex+vant+axios)：https://github.com/WinWang/Vue-WanAndroid <br>

**Vue3版本**WanAndroid链接(vue3+typeScript+pinia+vant+vite)：https://github.com/WinWang/Vue3-wanAndroid <br>



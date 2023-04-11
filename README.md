# ReadingGallery（Android组件化项目）<br>

1、本项目基于Jetpack组件开发的MVVM组件化项目；<br>
2、内容包含Kotlin+Koin（依赖注入）+coil（图片加载）+couroutine（协程）+ViewModel+LiveData+ViewBinding+DataBinding；
3、组件化通信基于阿里Arouter开源框架；<br>
4、使用了腾讯API去中心化方案，降低代码耦合；<br>
5、内部封装了具有网络请求和生命周期感知能力的自定义组合View，针对单页面多接口场景比较实用，目前已在生产项目中运用2年多时间，具体可见项目中BaseVBViewComponent；
内部可以根据使用爱好选择Viewbinding还是DataBinding，都在基类做了对应集成，viewmodel同时也可以按自己喜好是否使用koin注入对应View层

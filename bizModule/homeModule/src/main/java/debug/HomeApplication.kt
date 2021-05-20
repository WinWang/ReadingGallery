package debug

import com.winwang.homemodule.di.homeModule
import com.winwang.mvvm.base.BaseApplication
import org.koin.core.context.startKoin

/**
 *Created by WinWang on 2021/5/15
 *Description->
 */
class HomeApplication : BaseApplication() {

    override fun initMethod() {
        super.initMethod()
        startKoin {
            modules(homeModule)
        }
    }

}
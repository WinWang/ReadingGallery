package debug

import com.winwang.catmodule.di.catModule
import com.winwang.mvvm.base.BaseApplication
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 *Created by WinWang on 2021/5/15
 *Description->
 */
class CatApplication : BaseApplication() {

    override fun initMethod() {
        super.initMethod()
        startKoin {
            modules(catModule)
        }
    }

}
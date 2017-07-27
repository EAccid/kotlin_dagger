package com.dive.inkotlin.presentation.home.impl

import android.os.Bundle
import com.dive.inkotlin.App
import com.dive.inkotlin.presentation.IInfoKeeper
import com.dive.inkotlin.presentation.base.impl.BaseActivity
import com.dive.inkotlin.provider.info.Info
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by olena on 28.06.2017.
 */

class HomeActivity : BaseActivity(), IInfoKeeper {

    init {
        App.getComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        HomeActivityUI().setContentView(this)


    }

    override fun updateInfo(type: Info, value: Int) {

    }

    override fun keepTypes(): Array<Info> {
        return arrayOf(Info.VALUE)
    }

}


class HomeActivityUI : AnkoComponent<HomeActivity> {
    override fun createView(ui: AnkoContext<HomeActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Hello world") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }

        }
    }
}
package com.dive.inkotlin.presentation.home.impl

import android.os.Bundle
import com.dive.inkotlin.App
import com.dive.inkotlin.R
import com.dive.inkotlin.presentation.IBalanceKeeper
import com.dive.inkotlin.presentation.base.impl.BaseActivity
import com.dive.inkotlin.provider.balance.Balance


/**
 * Created by olena on 28.06.2017.
 */

class HomeActivity : BaseActivity(), IBalanceKeeper {

    init {
        App.getComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getActivityContentView(): Int {
        return R.layout.activity_home
    }


    override fun updateBalance(type: Balance, value: Int) {

    }

    override fun keepTypes(): Array<Balance> {
        return arrayOf(Balance.VALUE)
    }

}

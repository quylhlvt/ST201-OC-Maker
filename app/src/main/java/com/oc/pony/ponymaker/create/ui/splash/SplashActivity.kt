package com.oc.pony.ponymaker.create.ui.splash

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.oc.pony.ponymaker.create.R
import com.oc.pony.ponymaker.create.base.AbsBaseActivity
import com.oc.pony.ponymaker.create.databinding.ActivitySplashBinding
import com.oc.pony.ponymaker.create.ui.language.LanguageActivity
import com.oc.pony.ponymaker.create.ui.tutorial.TutorialActivity
import com.oc.pony.ponymaker.create.utils.CONST
import com.oc.pony.ponymaker.create.utils.DataHelper.getData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AbsBaseActivity<ActivitySplashBinding>() {
    @Inject
    lateinit var apiRepository: com.oc.pony.ponymaker.create.data.repository.ApiRepository

    @Inject
    lateinit var sharedPreferenceUtils: com.oc.pony.ponymaker.create.utils.SharedPreferenceUtils
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initView() {
            lifecycleScope.launch {
                delay(3000)
                action()
            }

        }


    override fun initAction() {
        GlobalScope.launch(Dispatchers.IO) {
            getData(apiRepository)
        }
    }

    fun action() {
        if (!sharedPreferenceUtils.getBooleanValue(CONST.LANGUAGE)
        ) {
            startActivity(Intent(this@SplashActivity, LanguageActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, TutorialActivity::class.java))
        }
        finish()
    }
       override fun onBackPressed() {

    }
}
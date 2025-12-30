package com.oc.pony.ponymaker.create.ui.setting

import android.view.View
import com.oc.pony.ponymaker.create.R
import com.oc.pony.ponymaker.create.base.AbsBaseActivity
import com.oc.pony.ponymaker.create.databinding.ActivitySettingBinding
import com.oc.pony.ponymaker.create.ui.language.LanguageActivity
import com.oc.pony.ponymaker.create.utils.RATE
import com.oc.pony.ponymaker.create.utils.newIntent
import com.oc.pony.ponymaker.create.utils.onSingleClick
import com.oc.pony.ponymaker.create.utils.policy
import com.oc.pony.ponymaker.create.utils.rateUs
import com.oc.pony.ponymaker.create.utils.shareApp
import com.oc.pony.ponymaker.create.utils.unItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingActivity : AbsBaseActivity<ActivitySettingBinding>() {
    @Inject
    lateinit var sharedPreferences: com.oc.pony.ponymaker.create.utils.SharedPreferenceUtils
    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initView() {
        if (sharedPreferences.getBooleanValue(RATE)) {
            binding.llRateUs.visibility = View.GONE
        }
        unItem = {
            binding.llRateUs.visibility = View.GONE
        }
    }

    override fun onStop() {
        super.onStop()
    }
    override fun initAction() {
        binding.apply {
            llLanguage.onSingleClick {
                startActivity(
                    newIntent(
                        applicationContext,
                        LanguageActivity::class.java
                    )
                )
            }
            llRateUs.onSingleClick {
                rateUs(0)
            }
            llShareApp.onSingleClick {
                shareApp()
            }
            llPrivacy.onSingleClick {
                policy()
            }
            imvBack.onSingleClick {
                finish()
            }
        }
    }
}
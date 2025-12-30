package com.oc.pony.ponymaker.create.ui.category

import com.oc.pony.ponymaker.create.R
import com.oc.pony.ponymaker.create.base.AbsBaseActivity
import com.oc.pony.ponymaker.create.databinding.ActivityCategoryBinding
import com.oc.pony.ponymaker.create.ui.customview.CustomviewActivity
import com.oc.pony.ponymaker.create.utils.DataHelper
import com.oc.pony.ponymaker.create.utils.isInternetAvailable
import com.oc.pony.ponymaker.create.utils.newIntent
import com.oc.pony.ponymaker.create.utils.onSingleClick
import com.oc.pony.ponymaker.create.utils.showInter
import com.oc.pony.ponymaker.create.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryActivity : AbsBaseActivity<ActivityCategoryBinding>() {
    @Inject
    lateinit var apiRepository: com.oc.pony.ponymaker.create.data.repository.ApiRepository
    val adapter by lazy { CategoryAdapter() }

    override fun getLayoutId(): Int = R.layout.activity_category



    override fun onRestart() {
        super.onRestart()
    }

    override fun initView() {
        if (DataHelper.arrBg.size == 0) {
//            GlobalScope.launch(Dispatchers.IO) {
//                getData(apiRepository)
//            }
            finish()
        } else {
            binding.rcv.itemAnimator = null
            binding.rcv.adapter = adapter
            adapter.submitList(DataHelper.arrBlackCentered)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun initAction() {
        binding.apply {
            imvBack.onSingleClick {
                showInter {
                    finish()
                }
            }
            adapter.onCLick = {
                if (DataHelper.arrBlackCentered[it].checkDataOnline) {
                    if (isInternetAvailable(this@CategoryActivity)) {
                        showInter {
                            var a = DataHelper.arrBlackCentered[it].avt.split("/")
                            var b = a[a.size - 2]

                            startActivity(
                                newIntent(
                                    applicationContext,
                                    CustomviewActivity::class.java
                                ).putExtra("data", it)
                            )
                        }
                    } else {
                        showToast(
                            this@CategoryActivity,
                            R.string.please_check_your_network_connection
                        )
                    }
                } else {
                    showInter {
                        var a = DataHelper.arrBlackCentered[it].avt.split("/")
                        var b = a[a.size - 2]

                        startActivity(
                            newIntent(
                                applicationContext,
                                CustomviewActivity::class.java
                            ).putExtra("data", it)
                        )
                    }
                }
            }
        }
    }
}
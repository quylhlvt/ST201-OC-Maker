package com.oc.pony.ponymaker.create.ui.customview

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.oc.pony.ponymaker.create.R
import com.oc.pony.ponymaker.create.base.AbsBaseAdapter
import com.oc.pony.ponymaker.create.base.AbsBaseDiffCallBack
import com.oc.pony.ponymaker.create.databinding.ItemPartBinding
import com.oc.pony.ponymaker.create.utils.DataHelper.dp
import com.oc.pony.ponymaker.create.utils.DataHelper.setMargins
import com.oc.pony.ponymaker.create.utils.onClickCustom
import com.oc.pony.ponymaker.create.utils.onSingleClick

class PartAdapter : AbsBaseAdapter<String, ItemPartBinding>(R.layout.item_part, PathDiff()) {
    var onClick: ((Int,String) -> Unit)? = null
    var posPath = 0
    //    var checkOnline = false
    fun setPos(pos: Int) {
        posPath = pos
    }

    class PathDiff : AbsBaseDiffCallBack<String>() {
        override fun itemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun contentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem != newItem
        }

    }

    override fun bind(
        binding: ItemPartBinding,
        position: Int,
        data: String,
        holder: RecyclerView.ViewHolder
    ) {
        binding.apply {
            imageBgItem.setBackgroundResource( if (posPath == position) R.drawable.bg_frame_custom_item_select else R.drawable.bg_frame_custom_item_unselect)
        }
        Glide.with(binding.imv).clear(binding.imv)
        // 🔴 BẮT BUỘC: scaleType cố định
        binding.imv.scaleType = ImageView.ScaleType.CENTER_INSIDE
        // reset padding (KHÔNG dùng margin)
        binding.imv.setPadding(0, 0, 0, 0)
        when (data) {
            "none" -> {

                binding.imv.setPadding(16, 16, 16, 16)
                loadImage(binding, R.drawable.ic_none)
            }
            "dice" -> {
                binding.imv.setPadding(6, 6, 6, 6)
                loadImage(binding, R.drawable.ic_random_layer)
            }
            else -> {
                loadImage(binding, data)
            }
        }
        binding.root.onClickCustom {
            onClick?.invoke(position,data)
        }
    }
    private fun loadImage(binding: ItemPartBinding, data: Any) {
        Glide.with(binding.imv)
            .load(data)
            .encodeQuality(50)
            .override(512)
            .dontTransform()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.imv)
    }
}
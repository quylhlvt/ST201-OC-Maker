package com.oc.pony.ponymaker.create.ui.customview

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
        binding.imv.setMargins(0, 0, 0, 0)
        when (data) {
            "none" -> {
                binding.imv.setMargins(26 ,26,26,26)
                Glide.with(binding.root).load(R.drawable.ic_none).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(binding.imv)
            }

            "dice" -> {
                binding.imv.setMargins(16 ,16,16,16)
                Glide.with(binding.root).load(R.drawable.ic_random_layer).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(binding.imv)
            }
            else -> {
                Glide.with(binding.root).load(data).into(binding.imv)
            }
        }

        binding.root.onClickCustom {
            onClick?.invoke(position,data)
        }

    }
}
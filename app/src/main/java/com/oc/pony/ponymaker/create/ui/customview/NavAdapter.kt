package com.oc.pony.ponymaker.create.ui.customview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.oc.pony.ponymaker.create.R
import com.oc.pony.ponymaker.create.base.AbsBaseAdapter
import com.oc.pony.ponymaker.create.data.model.BodyPartModel
import com.oc.pony.ponymaker.create.databinding.ItemNavigationBinding
import com.oc.pony.ponymaker.create.utils.onClickCustom
import com.oc.pony.ponymaker.create.utils.onSingleClick

class NavAdapter : AbsBaseAdapter<BodyPartModel, ItemNavigationBinding>(R.layout.item_navigation, DiffNav()) {
    var posNav = 0
    var onClick: ((Int) -> Unit)? = null

    class DiffNav : com.oc.pony.ponymaker.create.base.AbsBaseDiffCallBack<BodyPartModel>() {
        override fun itemsTheSame(oldItem: BodyPartModel, newItem: BodyPartModel): Boolean {
            return oldItem.icon == newItem.icon
        }

        override fun contentsTheSame(oldItem: BodyPartModel, newItem: BodyPartModel): Boolean {
            return oldItem.icon != newItem.icon
        }

    }

    fun setPos(pos: Int) {
        posNav = pos
    }

    override fun bind(
        binding: ItemNavigationBinding,
        position: Int,
        data:BodyPartModel,
        holder: RecyclerView.ViewHolder
    ) {
        Glide.with(binding.root).load(data.icon).encodeQuality(50).override(512).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(binding.imv)
        if (posNav == position) {
            binding.bg.setImageResource(R.drawable.bg_frame_custom_item_select)
        } else {
            binding.bg.setImageResource(R.drawable.bg_white_12)
        }
        binding.root.onClickCustom {
            onClick?.invoke(position)
        }
    }

}
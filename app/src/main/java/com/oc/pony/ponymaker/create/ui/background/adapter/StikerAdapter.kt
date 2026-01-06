package com.oc.pony.ponymaker.create.ui.background.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.oc.pony.ponymaker.create.R
import com.oc.pony.ponymaker.create.databinding.ItemStikerBgBinding
import com.oc.pony.ponymaker.create.utils.onSingleClick

class StikerAdapter :
    com.oc.pony.ponymaker.create.base.AbsBaseAdapter<com.oc.pony.ponymaker.create.data.model.SelectedModel, ItemStikerBgBinding>(
        R.layout.item_stiker_bg,
        DiffCallBack()
    ) {
    var onClick: ((String) -> Unit)? = null
    override fun bind(
        binding: ItemStikerBgBinding,
        position: Int,
        data: com.oc.pony.ponymaker.create.data.model.SelectedModel,
        holder: RecyclerView.ViewHolder
    ) {
        binding.imv.onSingleClick {
            onClick?.invoke(data.path)
        }
        Glide.with(binding.root).load(data.path)
            .override(512, 512)
            .encodeQuality(50)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(binding.imv)
    }

    class DiffCallBack :
        com.oc.pony.ponymaker.create.base.AbsBaseDiffCallBack<com.oc.pony.ponymaker.create.data.model.SelectedModel>() {
        override fun itemsTheSame(
            oldItem: com.oc.pony.ponymaker.create.data.model.SelectedModel,
            newItem: com.oc.pony.ponymaker.create.data.model.SelectedModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun contentsTheSame(
            oldItem: com.oc.pony.ponymaker.create.data.model.SelectedModel,
            newItem: com.oc.pony.ponymaker.create.data.model.SelectedModel
        ): Boolean {
            return oldItem.path != newItem.path || oldItem.isSelected != newItem.isSelected
        }

    }
}
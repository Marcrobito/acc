package dev.eighteentech.acc.ui

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import dev.eighteentech.acc.databinding.ItemImageTitleBinding
import dev.eighteentech.acc.databinding.ItemSimpleTextBinding
import dev.eighteentech.acc.databinding.ItemTitleBinding
import dev.eighteentech.acc.entity.CardHolder
import dev.eighteentech.acc.entity.SimplifiedCard

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private var items = listOf<SimplifiedCard>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder.getItemFromGroupType(parent, viewType)

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        with(holder.binding) {
            when (this) {
                is ItemTitleBinding -> {
                    title.text = items[position].title
                    title.setTextColor(Color.parseColor(items[position].titleTextColor))
                    title.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        items[position].titleFontSize.toFloat()
                    )
                    items[position].description?.let {
                        description.text = it
                    }
                    items[position].descriptionTextColor?.let {
                        description.setTextColor((Color.parseColor(it)))
                    }
                    items[position].descriptionFontSize?.toFloat()?.let {
                        description.setTextSize(TypedValue.COMPLEX_UNIT_SP, it)
                    }
                }
                is ItemImageTitleBinding -> {
                    items[position].url?.let {
                        Glide.with(imageView).load(it).into(imageView)
                    }

                    title.text = items[position].title
                    title.setTextColor(Color.parseColor(items[position].titleTextColor))
                    title.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        items[position].titleFontSize.toFloat()
                    )

                    items[position].description?.let {
                        description.text = it
                    }
                    items[position].descriptionTextColor?.let {
                        description.setTextColor((Color.parseColor(it)))
                    }
                    items[position].descriptionFontSize?.toFloat()?.let {
                        description.setTextSize(TypedValue.COMPLEX_UNIT_SP, it)
                    }
                }
                is ItemSimpleTextBinding -> {
                    title.text = items[position].title
                    title.setTextColor(Color.parseColor(items[position].titleTextColor))
                    title.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        items[position].titleFontSize.toFloat()
                    )
                }
                else -> {
                }
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].card_type) {
            "title_description" -> 1
            "image_title_description" -> 2
            else -> 0
        }
    }

    fun updateItems(items: List<SimplifiedCard>) {
        this.items = items
        notifyDataSetChanged()
    }

}


sealed class HomeViewHolder(open val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    data class SimpleViewHolder(override val binding: ItemSimpleTextBinding) :
        HomeViewHolder(binding)

    data class ImageViewHolder(override val binding: ItemImageTitleBinding) :
        HomeViewHolder(binding)

    data class TitleViewHolder(override val binding: ItemTitleBinding) :
        HomeViewHolder(binding)

    companion object {
        fun getItemFromGroupType(parent: ViewGroup, viewType: Int) = when (viewType) {
            0 -> SimpleViewHolder(
                ItemSimpleTextBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            1 -> TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> ImageViewHolder(
                ItemImageTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}
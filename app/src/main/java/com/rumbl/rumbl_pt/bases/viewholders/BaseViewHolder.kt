package com.rumbl.rumbl_pt.bases.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mohamed Shalan on 4/19/20.
 */

abstract class BaseViewHolder<viewBinding : ViewDataBinding, T>(
	private val binding: viewBinding,
	private val listener: ItemListener? = null
) :
	RecyclerView.ViewHolder(binding.root) {


	fun baseBinding(item: T){
		binding.root.setOnClickListener {
			listener?.onItemClicked(bindingAdapterPosition)
		}
		bind(item)
	}

	abstract fun bind(item: T)
}

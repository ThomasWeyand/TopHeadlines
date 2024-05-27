package com.thomas.topheadlines.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thomas.topheadlines.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.switchVisibility(isVisible: Boolean) {
    if(isVisible)
        show()
    else
        hide()
}

fun AppCompatImageView.loadImageFromUrl(url: String) {
    this.apply {
        context?.let { safeContext ->
            Glide.with(safeContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_image_placeholder)
                .error(R.drawable.empty_image_placeholder)
                .into(this)
        }
    }
}
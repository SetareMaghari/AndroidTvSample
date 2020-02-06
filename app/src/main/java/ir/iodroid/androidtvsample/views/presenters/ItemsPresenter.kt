package ir.iodroid.androidtvsample.views.presenters

import android.view.ViewGroup
import android.view.Gravity
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import ir.iodroid.androidtvsample.R


class ItemsPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = TextView(parent?.context).apply {
            layoutParams = ViewGroup.LayoutParams(300, 100)
            //make the item focusable while using TV's remote control
            isFocusable = true
            setBackgroundColor(ContextCompat.getColor(parent?.context!!,
                R.color.primary
            ))
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        (viewHolder?.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(p0: ViewHolder?) {}
}
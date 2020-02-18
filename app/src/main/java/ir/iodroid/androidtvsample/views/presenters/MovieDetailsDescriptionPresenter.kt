package ir.iodroid.androidtvsample.views.presenters

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import ir.iodroid.androidtvsample.models.Movie

class MovieDetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(
        viewHolder: ViewHolder,
        item: Any
    ) {
        val movie =
            item as? Movie
        if (movie != null) {
            viewHolder.title.text = movie.title
            viewHolder.subtitle.text = movie.director
            viewHolder.body.text = movie.description
        }
    }
}
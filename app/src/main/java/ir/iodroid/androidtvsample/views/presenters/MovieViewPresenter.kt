package ir.iodroid.androidtvsample.views.presenters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.iodroid.androidtvsample.R
import ir.iodroid.androidtvsample.models.Movie

class MovieViewPresenter(private val context: Context): Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return CardViewHolder(ImageCardView(context))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        (item as? Movie)?.let {movie->
            (viewHolder as? CardViewHolder)?.imageCardView?.apply {
                titleText = movie.title
                contentText = movie.description
                Glide.with(context).load(movie.coverUrl).apply(RequestOptions().centerCrop()).into(this.mainImageView)
            }
        }
    }


    class CardViewHolder(itemView: View): ViewHolder(itemView){
        var imageCardView: ImageCardView = itemView as ImageCardView

        init {
            imageCardView.apply {
                isFocusable = true
                cardType = BaseCardView.CARD_TYPE_MAIN_ONLY
                infoVisibility = BaseCardView.CARD_REGION_VISIBLE_ALWAYS
                setMainImageDimensions(300, 150)
                badgeImage = ContextCompat.getDrawable(context, R.drawable.ic_play_circle_outline_black_24dp)
            }
        }
    }


    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}
package ir.iodroid.androidtvsample.views.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import androidx.core.content.ContextCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ir.iodroid.androidtvsample.R
import ir.iodroid.androidtvsample.models.Movie
import ir.iodroid.androidtvsample.views.presenters.MovieDetailsDescriptionPresenter
import ir.iodroid.androidtvsample.views.presenters.MovieViewPresenter

class MovieDetailsFragment : DetailsSupportFragment() {

    companion object {
        private const val DETAIL_THUMB_WIDTH = 300
        private const val DETAIL_THUMB_HEIGHT = 400
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private val detailFullOverviewPresenter =
        FullWidthDetailsOverviewRowPresenter(MovieDetailsDescriptionPresenter())

    lateinit var detailOverviewRow: DetailsOverviewRow

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val selectedMovie =
            activity?.intent?.getParcelableExtra<Parcelable>(EXTRA_MOVIE) as Movie

        detailOverviewRow = DetailsOverviewRow(selectedMovie)

        setMovieThumbnail(selectedMovie)
        setMovieDetailActions()
        setMovieDetailAdapter()

    }

    private fun setMovieDetailAdapter() {
        val classPresenterSelector = ClassPresenterSelector().apply {
            addClassPresenter(DetailsOverviewRow::class.java, detailFullOverviewPresenter)
            addClassPresenter(ListRow::class.java, ListRowPresenter())
        }

        val detailPageAdapter = ArrayObjectAdapter(classPresenterSelector).apply {
            add(detailOverviewRow)
            add(generateRelatedMoviesRow())
        }

        setAdapter(detailPageAdapter)
    }


    private fun generateRelatedMoviesRow(): ListRow {
        val movieViewPresenter = MovieViewPresenter(requireContext())
        val listRowAdapter = ArrayObjectAdapter(movieViewPresenter).apply {
            add(
                Movie(
                    "Sleeping Beauty",
                    "Directed by: Clyde Geronimi",
                    "https://i1.wp.com/www.tor.com/wp-content/uploads/2015/07/Sleeping2-740x360.jpg?fit=740%2C%209999&crop=0%2C0%2C100%2C360px",
                    "Sleeping Beauty is a 1959 American animated musical fantasy film produced by Walt Disney based on The Sleeping Beauty by Charles Perrault. The 16th Disney animated feature film, it was released to theaters on January 29, 1959, by Buena Vista Distribution. This was the last Disney adaptation of a fairy tale for some years because of its initial mixed critical reception and underperformance at the box office; the studio did not return to the genre until 30 years later, after Walt Disney died in 1966, with the release of The Little Mermaid (1989)."
                )
            )
            add(
                Movie(
                    "Arthur Christmas",
                    "Directed by: Sarah Smith, Barry Cook",
                    "https://www.gannett-cdn.com/-mm-/b2b05a4ab25f4fca0316459e1c7404c537a89702/c=0-0-1365-768/local/-/media/2018/06/11/USATODAY/usatsports/247WallSt.com-247WS-469696-arthur-christmas.jpg",
                    "Arthur Christmas is a 2011 British-American 3D computer-animated Christmas comedy film, produced by Aardman Animations and Sony Pictures Animation as their first collaborative project. The film was released on 11 November 2011, in the UK, and on 23 November 2011, in the US.\n" +
                     "Directed by Sarah Smith and co-directed by Barry Cook,[4] it stars the voices of James McAvoy, Hugh Laurie, Bill Nighy, Jim Broadbent, Imelda Staunton, and Ashley Jensen."
                )
            )
            add(
                (Movie(
                    "Frankenweenie",
                    "Directed by: Tim Burton",
                    "https://www.gannett-cdn.com/-mm-/1f8ac36e35875bcd7baab5ef2b330f818b7ad867/c=12-0-588-324/local/-/media/2018/05/14/USATODAY/usatsports/wp-USAT-allthemoms-front1-11182-frankenweenie.jpg",
                    "Frankenweenie is a 2012 American 3D stop motion-animated supernatural horror comedy film directed by Tim Burton and produced by Walt Disney Pictures.[3] It is a remake of Burton's 1984 short film of the same name and is also both a parody of and homage to the 1931 film Frankenstein, based on Mary Shelley's book of the same name. The voice cast includes four actors who worked with Burton on previous films: Winona Ryder (Beetlejuice and Edward Scissorhands); Martin Short (Mars Attacks!); Catherine O'Hara (Beetlejuice and The Nightmare Before Christmas); and Martin Landau (Ed Wood and Sleepy Hollow), along with some new voice actors, such as Charlie Tahan and Atticus Shaffer."
                ))
            )
            add(
                Movie(
                    "Winnie the Pooh",
                    "Directed by: Stephen J. Anderson, Don Hall",
                    "https://www.gannett-cdn.com/-mm-/b2b05a4ab25f4fca0316459e1c7404c537a89702/c=0-0-1365-768/local/-/media/2018/06/11/USATODAY/usatsports/winnie-the-pooh-2011.jpg",
                    "Winnie-the-Pooh, also called Pooh Bear and Pooh, is a fictional anthropomorphic teddy bear created by English author A. A. Milne.\n" +
                    "The first collection of stories about the character was the book Winnie-the-Pooh (1926), and this was followed by The House at Pooh Corner (1928). Milne also included a poem about the bear in the children's verse book When We Were Very Young (1924) and many more in Now We Are Six (1927). All four volumes were illustrated by E. H. Shepard."
                )
            )
        }
        val headerItem = HeaderItem(0, "Related Movies")

        return ListRow(headerItem, listRowAdapter)
    }

    private fun setMovieDetailActions() {
        val actionsAdapter = ArrayObjectAdapter().apply {
            add(Action(1, "Watch").apply {
                icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_play_arrow_white_24dp)
            })
            add(Action(2, "78 People", "Liked the Movie").apply {
                icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_thumb_up_white_24dp)
            })
            add(Action(3, "Watch Trailer"))
        }
        detailOverviewRow.actionsAdapter = actionsAdapter
    }

    private fun setMovieThumbnail(selectedMovie: Movie) {
        Glide.with(activity!!)
            .asBitmap()
            .load(selectedMovie.coverUrl)
            .apply(RequestOptions().override(DETAIL_THUMB_WIDTH, DETAIL_THUMB_HEIGHT).centerCrop())
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    detailOverviewRow.setImageBitmap(activity, resource)
                }
            })
    }
}
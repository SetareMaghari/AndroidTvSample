package ir.iodroid.androidtvsample


import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import ir.iodroid.androidtvsample.models.Movie
import ir.iodroid.androidtvsample.views.presenters.MovieViewPresenter

class MainFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        headersState = HEADERS_ENABLED
        showTitle(true)
        badgeDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.banner)
        brandColor = ContextCompat.getColor(requireContext(), R.color.headers_background)

        makeListItems()
    }

    private fun makeListItems() {

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        val headerItem = HeaderItem(0, "FirstRow")
        val headerItem1 = HeaderItem(1, "SecondRow")
        val headerItem2 = HeaderItem(2, "ThirdRow")
        val movieViewPresenter = MovieViewPresenter(requireContext())
        val itemsPresenter = ItemsPresenter()

        val itemRowAdapter = ArrayObjectAdapter(itemsPresenter).apply {
            add("ITEM 1")
            add("ITEM 2")
            add("ITEM 3")
        }


        val movieRowAdapter = ArrayObjectAdapter(movieViewPresenter).apply {
            add(Movie("Sleeping Beauty", "Directed by: Clyde Geronimi", "https://www.filimo.com/public/public/user_data/video_thumb_star/14/27509_27509-m.jpg"))
            add(Movie("Arthur Christmas", "Directed by: Sarah Smith, Barry Cook", "https://www.gannett-cdn.com/-mm-/b2b05a4ab25f4fca0316459e1c7404c537a89702/c=0-0-1365-768/local/-/media/2018/06/11/USATODAY/usatsports/247WallSt.com-247WS-469696-arthur-christmas.jpg"))
            add((Movie("Frankenweenie", "Directed by: Tim Burton", "https://www.gannett-cdn.com/-mm-/1f8ac36e35875bcd7baab5ef2b330f818b7ad867/c=12-0-588-324/local/-/media/2018/05/14/USATODAY/usatsports/wp-USAT-allthemoms-front1-11182-frankenweenie.jpg")))
            add(Movie("Winnie the Pooh", "Directed by: Stephen J. Anderson, Don Hall", "https://www.gannett-cdn.com/-mm-/b2b05a4ab25f4fca0316459e1c7404c537a89702/c=0-0-1365-768/local/-/media/2018/06/11/USATODAY/usatsports/winnie-the-pooh-2011.jpg"))
        }
        rowsAdapter.add(ListRow(headerItem, itemRowAdapter))
        rowsAdapter.add(ListRow(headerItem1, movieRowAdapter))
        rowsAdapter.add(ListRow(headerItem2, movieRowAdapter))

        adapter = rowsAdapter
    }

}

package ir.iodroid.androidtvsample


import android.os.Bundle
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v17.leanback.widget.HeaderItem
import android.support.v17.leanback.widget.ListRow
import android.support.v17.leanback.widget.ListRowPresenter
import android.support.v4.content.ContextCompat


class MainFragment : BrowseSupportFragment() {
    private var rowsAdapter: ArrayObjectAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        title = "IODROID"
        headersState = HEADERS_ENABLED

        badgeDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.banner)
        brandColor = ContextCompat.getColor(requireContext(), R.color.headers_background)

        loadListItems()
    }

    private fun loadListItems() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        val headerItem = HeaderItem(0, "FirstRow")
        val itemsPresenter = ItemsPresenter()

        val rowAdapter = ArrayObjectAdapter(itemsPresenter).apply {
            add("ITEM 1")
            add("ITEM 2")
            add("ITEM 3")
        }
        rowsAdapter?.add(ListRow(headerItem, rowAdapter))

        adapter = rowsAdapter
    }

}

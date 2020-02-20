package ir.iodroid.androidtvsample.views.activities

import android.content.ContentUris
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.tvprovider.media.tv.Channel
import androidx.tvprovider.media.tv.ChannelLogoUtils
import androidx.tvprovider.media.tv.PreviewProgram
import androidx.tvprovider.media.tv.TvContractCompat
import ir.iodroid.androidtvsample.R
import ir.iodroid.androidtvsample.models.Movie
import ir.iodroid.androidtvsample.views.fragments.MovieDetailsFragment
import java.util.concurrent.TimeUnit

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createRecomDefaultChannel()
    }

    private fun createRecomDefaultChannel() {
                val builder = Channel.Builder().setType(TvContractCompat.Channels.TYPE_PREVIEW)
                    .setDisplayName(resources.getString(R.string.app_name))
                val channelUri =
                    contentResolver.insert(TvContractCompat.Channels.CONTENT_URI, builder.build().toContentValues())

                ContentUris.parseId(channelUri).let { channelId ->
                    ChannelLogoUtils.storeChannelLogo(this, channelId,
                        BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                    //MAKE THE CHANNEL AS DEFAULT
                    TvContractCompat.requestChannelBrowsable(this, channelId)
                    updateRecommendationChannel( Movie(
                        "Sleeping Beauty",
                        "Directed by: Clyde Geronimi",
                        "https://i.pinimg.com/originals/ba/b0/a2/bab0a2f905243a35161df877b4da1467.jpg",
                        "Sleeping Beauty is a 1959 American animated musical fantasy film produced by Walt Disney based on The Sleeping Beauty by Charles Perrault. The 16th Disney animated feature film, it was released to theaters on January 29, 1959, by Buena Vista Distribution. This was the last Disney adaptation of a fairy tale for some years because of its initial mixed critical reception and underperformance at the box office; the studio did not return to the genre until 30 years later, after Walt Disney died in 1966, with the release of The Little Mermaid (1989)."
                    ), channelId
                    )

                }
    }
    private fun updateRecommendationChannel(recomChannelMovie: Movie, channelId: Long) {
        val builder = PreviewProgram.Builder()
        builder.setChannelId(channelId)
            .setType(TvContractCompat.PreviewPrograms.TYPE_MOVIE)
            .setTitle(recomChannelMovie.title)
            .setId(1)
            .setContentId(recomChannelMovie.title)
            .setDurationMillis(
                TimeUnit.MINUTES.toMillis(20).toInt())
            .setReviewRating("4")
            .setCanonicalGenres(arrayOf("Kids"))
            .setPosterArtAspectRatio(TvContractCompat.PreviewPrograms.ASPECT_RATIO_MOVIE_POSTER)
            .setDescription(recomChannelMovie.description)
            .setPosterArtUri(Uri.parse(recomChannelMovie.coverUrl))
            .setIntent(Intent(this, MovieDetailsActivity::class.java).putExtra(
                MovieDetailsFragment.EXTRA_MOVIE
                , recomChannelMovie))

        val updatedRecordCount = contentResolver.update(TvContractCompat.buildPreviewProgramUri(1),
            builder.build().toContentValues(), null, null)
        if (updatedRecordCount <= 0) {
            contentResolver.insert(TvContractCompat.PreviewPrograms.CONTENT_URI,
                builder.build().toContentValues())
        }
    }
}

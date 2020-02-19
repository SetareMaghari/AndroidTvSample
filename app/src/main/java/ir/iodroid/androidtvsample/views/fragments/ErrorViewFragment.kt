package ir.iodroid.androidtvsample.views.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.ErrorSupportFragment
import ir.iodroid.androidtvsample.R


class ErrorViewFragment : ErrorSupportFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Oooops :("
        imageDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.lb_ic_sad_cloud)
        message = "NO Internet connection!"
        setDefaultBackground(false)
        buttonText = "Back to home"
        buttonClickListener = View.OnClickListener {
            activity?.finish()
        }
    }
}
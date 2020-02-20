package ir.iodroid.androidtvsample.views.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import ir.iodroid.androidtvsample.R
import ir.iodroid.androidtvsample.views.fragments.ErrorViewFragment


class ErrorActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        supportFragmentManager.beginTransaction().add(R.id.fragment_error_root, ErrorViewFragment())
            .commit()
    }
}

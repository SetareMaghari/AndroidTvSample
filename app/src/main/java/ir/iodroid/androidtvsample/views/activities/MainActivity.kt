package ir.iodroid.androidtvsample.views.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import ir.iodroid.androidtvsample.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

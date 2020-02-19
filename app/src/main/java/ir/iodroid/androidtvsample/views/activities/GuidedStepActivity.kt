package ir.iodroid.androidtvsample.views.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.GuidedStepSupportFragment
import ir.iodroid.androidtvsample.views.fragments.ChooseStartPositionFragment

class GuidedStepActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == savedInstanceState) {
            GuidedStepSupportFragment.add(supportFragmentManager, ChooseStartPositionFragment())
        }
    }
}

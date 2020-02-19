package ir.iodroid.androidtvsample.views.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction
import ir.iodroid.androidtvsample.R

class ChooseStartPositionFragment: GuidedStepSupportFragment() {
    companion object{
        private const val GUIDANCE_CHECK_SET_ID = 20
    }
    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance("Don you wanna continue watching?", "You have watched the movie before, start from beginning or continue?",
            null, ContextCompat.getDrawable(requireContext(), R.drawable.ghost))
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        actions.add(0, GuidedAction.Builder(requireContext()).id(0).checkSetId(GUIDANCE_CHECK_SET_ID).checked(true).icon(R.drawable.ic_watch_before).title("Continue watching").build())
        actions.add(1, GuidedAction.Builder(requireContext()).id(1).checkSetId(GUIDANCE_CHECK_SET_ID).checked(false).icon(R.drawable.ic_play_arrow_black_24dp).title("Start from beginning").build())
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        when(action?.id){
             0L -> Toast.makeText(requireContext(), "You will continue watching", Toast.LENGTH_LONG).show()
             1L -> Toast.makeText(requireContext(), "You will watch from beginning", Toast.LENGTH_LONG).show()
        }
    }
}
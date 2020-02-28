package za.co.nofuss.graphicsinvestigations.ui.space_invaders

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import za.co.nofuss.graphicsinvestigations.R

class SpaceInvadersFragment : Fragment() {

    private lateinit var spaceInvadersViewModel: SpaceInvadersViewModel
    private var spaceInvadersView: SpaceInvadersView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        spaceInvadersViewModel =
            ViewModelProviders.of(this).get(SpaceInvadersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_space_invaders, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        spaceInvadersViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val size = Point()
        val display = parentFragment?.activity?.windowManager?.defaultDisplay?.getSize(size)

        spaceInvadersView = SpaceInvadersView(requireContext(), size)
        return spaceInvadersView
        // return root
    }

    override fun onResume() {
        super.onResume()
        spaceInvadersView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        spaceInvadersView?.onPause()
    }
}

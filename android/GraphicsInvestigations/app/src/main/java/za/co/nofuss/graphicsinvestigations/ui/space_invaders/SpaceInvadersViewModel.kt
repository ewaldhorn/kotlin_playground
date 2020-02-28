package za.co.nofuss.graphicsinvestigations.ui.space_invaders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpaceInvadersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Space Invaders!"
    }
    val text: LiveData<String> = _text
}
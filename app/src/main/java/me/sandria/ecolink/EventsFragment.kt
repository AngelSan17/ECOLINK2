package me.sandria.ecolink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class EventsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_events_fragment, container, false)
    }

    // Aquí puedes implementar la lógica específica para tu fragmento de eventos,
    // como configurar un RecyclerView, manejar clics, etc.
}

package me.sandria.ecolink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_events_fragment, container, false)

        recyclerView = view.findViewById(R.id.photosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Crea una lista de ejemplo para el adaptador
        val sampleEvents = listOf("Evento 1", "Evento 2", "Evento 3")

        // Inicializa el adaptador con la lista de ejemplo
        adapter = EventsAdapter(sampleEvents)
        recyclerView.adapter = adapter

        return view
    }
}

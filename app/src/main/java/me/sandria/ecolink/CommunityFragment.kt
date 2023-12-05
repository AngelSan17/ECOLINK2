package me.sandria.ecolink

import Evento
import EventosAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
// Otras importaciones...


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CommunityFragment : Fragment(), EventosAdapter.OnEventoClickListener {

    private lateinit var viewModel: EventosViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventosAdapter: EventosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EventosViewModel::class.java)
        // Continúa con la configuración de tu RecyclerView y otros elementos de la UI
        recyclerView = view.findViewById(R.id.events_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val eventos = obtenerEventosDePrueba()
        eventosAdapter = EventosAdapter(eventos, this)
        recyclerView.adapter = eventosAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_community_fragment, container, false)
        recyclerView = view.findViewById(R.id.events_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val eventos = obtenerEventosDePrueba()
        eventosAdapter = EventosAdapter(eventos, this)
        recyclerView.adapter = eventosAdapter

        return view
    }

    override fun onEventoClicked(evento: Evento) {
        viewModel.seleccionarEvento(evento)
        // Aquí, en lugar de reemplazar el fragmento, simplemente actualizas el ViewModel.
        // El fragmento de eventos observará estos cambios.
    }

    private fun obtenerEventosDePrueba(): List<Evento> {
        return listOf(
            Evento("Limpieza de Playa", "Limpieza", "10:00 AM", "Juan Pérez", "url_imagen_1"),
            Evento("Recogida en el Parque", "Reciclaje", "2:00 PM", "Ana Gómez", "url_imagen_2"),
            // Más eventos...
        )
    }
}

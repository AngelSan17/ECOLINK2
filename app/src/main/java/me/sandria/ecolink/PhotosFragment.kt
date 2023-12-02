package me.sandria.ecolink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
////Calendario de Eventos y Reuniones
class PhotosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_photos_fragment, container, false)

        recyclerView = view.findViewById(R.id.photosRecyclerView)
        val numberOfColumns = 3 // El número de columnas que deseas mostrar
        recyclerView.layoutManager = GridLayoutManager(context, numberOfColumns)

        // Crea una lista de ejemplo con IDs de drawable
        val samplePhotos = listOf(R.drawable.organizador,)

        // Inicializa el adaptador con la lista de ejemplo
        adapter = PhotosAdapter(samplePhotos)
        recyclerView.adapter = adapter

        return view
    }
}

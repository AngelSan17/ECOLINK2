package me.sandria.ecolink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiscussionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DiscussionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragment
        val view = inflater.inflate(R.layout.activity_discussion_fragment, container, false)

        // Configura el RecyclerView
        recyclerView = view.findViewById(R.id.discussionRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Crea una lista de ejemplo para el adaptador
        val sampleDiscussions = listOf("Discusión 1", "Discusión 2", "Discusión 3")

        // Inicializa el adaptador con la lista de ejemplo
        adapter = DiscussionAdapter(sampleDiscussions)
        recyclerView.adapter = adapter

        return view
    }
}

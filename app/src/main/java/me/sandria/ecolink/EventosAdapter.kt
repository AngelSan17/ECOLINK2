import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.sandria.ecolink.R

class EventosAdapter(
    private val eventosList: List<Evento>,
    private val listener: OnEventoClickListener
) : RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    interface OnEventoClickListener {
        fun onEventoClicked(evento: Evento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventosList[position]
        holder.bind(evento)
    }

    override fun getItemCount() = eventosList.size

    inner class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreEvento: TextView = itemView.findViewById(R.id.nombre_evento)
        private val tipoEvento: TextView = itemView.findViewById(R.id.tipo_evento)
        private val horaEvento: TextView = itemView.findViewById(R.id.hora_evento)
        private val encargadoEvento: TextView = itemView.findViewById(R.id.encargado_evento)
        private val imagenEvento: ImageView = itemView.findViewById(R.id.imagen_evento)

        fun bind(evento: Evento) {
            nombreEvento.text = evento.nombre
            tipoEvento.text = evento.tipo
            horaEvento.text = evento.hora
            encargadoEvento.text = evento.encargado

            if(evento.imagenUrl.isNotEmpty()) {
                Picasso.get().load(evento.imagenUrl).into(imagenEvento)
            }

            itemView.setOnClickListener { listener.onEventoClicked(evento) }
        }
    }
}

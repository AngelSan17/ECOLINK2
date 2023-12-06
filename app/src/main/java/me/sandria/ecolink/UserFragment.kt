import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import me.sandria.ecolink.R
import com.google.firebase.database.ValueEventListener

class UserFragment : Fragment() {

    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etUbicacion: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var ivPerfil: ImageView
    private lateinit var bActualizar: Button

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.activity_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()

        // Inicializa tus vistas aquí
        etNombre = view.findViewById(R.id.etNombre)
        etApellido = view.findViewById(R.id.etApellido)
        etUbicacion = view.findViewById(R.id.etUbicacion)
        etDescripcion = view.findViewById(R.id.etDescripcion)
        ivPerfil = view.findViewById(R.id.ivPerfil)
        bActualizar = view.findViewById(R.id.bActualizar)

        bActualizar.setOnClickListener {
            actualizarInformacionDelPerfil()
        }

        ivPerfil.setOnClickListener {
            cargarImagenDePerfil()
        }

        cargarInformacionDelPerfil()
    }

    private fun cargarInformacionDelPerfil() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(UserProfile::class.java)
                userProfile?.let {
                    etNombre.setText(it.nombre)
                    etApellido.setText(it.apellido)
                    etUbicacion.setText(it.ubicacion)
                    etDescripcion.setText(it.descripcion)
                    // Suponiendo que la URL de la imagen de perfil está almacenada en la base de datos.
                    // Utilizar Picasso o Glide para cargar la imagen.
                    // Picasso.get().load(it.imagenPerfilUrl).into(ivPerfil)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error al cargar la información.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarInformacionDelPerfil() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)

        val userProfile = mapOf(
            "nombre" to etNombre.text.toString(),
            "apellido" to etApellido.text.toString(),
            "ubicacion" to etUbicacion.text.toString(),
            "descripcion" to etDescripcion.text.toString()
        )

        databaseReference.updateChildren(userProfile).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Perfil actualizado con éxito.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al actualizar el perfil.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cargarImagenDePerfil() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data ?: return
            val userId = firebaseAuth.currentUser?.uid ?: return
            val storageRef = storage.reference.child("profileImages/$userId.jpg")

            storageRef.putFile(imageUri).addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)
                    databaseReference.child("imagenPerfilUrl").setValue(uri.toString()).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Imagen de perfil actualizada.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error al actualizar la imagen de perfil.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Error al cargar la imagen.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1234
    }
}

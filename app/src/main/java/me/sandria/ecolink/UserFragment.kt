package me.sandria.ecolink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_user_fragment, container, false)
    }

    // Aquí puedes implementar la lógica específica para tu fragmento de usuario,
    // como mostrar la información del perfil, permitir al usuario editar su información, etc.
}

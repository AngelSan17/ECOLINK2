package me.sandria.ecolink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3 // Cambiar al número de secciones

    override fun createFragment(position: Int): Fragment {
        // Retornar un nuevo fragmento dependiendo de la posición
        return when (position) {
            0 -> DiscussionFragment()
            1 -> EventsFragment()
            2 -> PhotosFragment()
            else -> Fragment()
        }
    }
}

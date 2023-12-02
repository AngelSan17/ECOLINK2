package me.sandria.ecolink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CommunityActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        viewPager = findViewById(R.id.view_pager)
        tabs = findViewById(R.id.tabs)

        // Configurar el ViewPager con un adaptador que maneja los fragmentos de las secciones
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager.adapter = sectionsPagerAdapter

        // Configurar TabLayout con ViewPager
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            // Aquí definirías los títulos de las pestañas según las secciones
            // Por ejemplo, si tienes tres secciones podrías hacer algo así:
            tab.text = when (position) {
                0 -> "Discusión"//comunidad
                1 -> "Eventos"
                2 -> "Fotos"
                else -> null
            }
        }.attach()
    }
}

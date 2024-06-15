package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import ru.mixail_akulov.a38_jetpacknavcom3.R
import ru.mixail_akulov.a38_jetpacknavcom3.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding

    // TODO("Connect Nav Component to the BottomNavigationView here")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        // т.к. находимся в фрагменте, то обращаемся к childFragmentManager
        // получаем фрагмент как NavHostFragment (прописано в макете)
        // после получаем контролер хоста
        // и с его помощью соединяем bottomNavigation со областью экрана, где будут показываться фрагменты вкладок
        // управляемые графом навигации контейнера
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}
package ru.mixail_akulov.a38_jetpacknavcom3.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import ru.mixail_akulov.a38_jetpacknavcom3.R

fun Fragment.findTopNavController(): NavController {
    // получаем фрагмент основного активити fragmentContainer, как навХост
    // далее берем его контролер, чтобы открывать фрагменты поверх всего окна без нижнего бара
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}
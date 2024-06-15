package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.mixail_akulov.a38_jetpacknavcom3.R
import ru.mixail_akulov.a38_jetpacknavcom3.Repositories
import ru.mixail_akulov.a38_jetpacknavcom3.databinding.FragmentBoxBinding
import ru.mixail_akulov.a38_jetpacknavcom3.utils.observeEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.viewModelCreator
import ru.mixail_akulov.a38_jetpacknavcom3.views.DashboardItemView

class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    private val viewModel by viewModelCreator { BoxViewModel(getBoxId(), Repositories.boxesRepository) }

    private val args by navArgs<BoxFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        binding.root.setBackgroundColor(DashboardItemView.getBackgroundColor(getColorValue()))
        binding.boxTextView.text = getString(R.string.this_is_box, getColorName())

        binding.goBackButton.setOnClickListener { onGoBackButtonPressed() }

        listenShouldExitEvent()
    }

    // TODO("Go back to the previous screen here")
    private fun onGoBackButtonPressed() {
        findNavController().popBackStack()
    }


    // TODO("Go back to the previous screen here")
    private fun listenShouldExitEvent() = viewModel.shouldExitEvent.observeEvent(viewLifecycleOwner) { shouldExit ->
        // close the screen if the box has been deactivated
        if (shouldExit) {
            findNavController().popBackStack()
        }
    }

    // TODO("Extract box id from arguments here")
    private fun getBoxId(): Int = args.boxId

    // TODO("Extract color value from arguments here")
    private fun getColorValue(): Int = args.colorValue

    // TODO("Extract color name from arguments here")
    private fun getColorName(): String = args.colorName
}
package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.mixail_akulov.a38_jetpacknavcom3.R
import ru.mixail_akulov.a38_jetpacknavcom3.Repositories
import ru.mixail_akulov.a38_jetpacknavcom3.databinding.FragmentDashboardBinding
import ru.mixail_akulov.a38_jetpacknavcom3.model.boxes.entities.Box
import ru.mixail_akulov.a38_jetpacknavcom3.utils.viewModelCreator
import ru.mixail_akulov.a38_jetpacknavcom3.views.DashboardItemView

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val viewModel by viewModelCreator { DashboardViewModel(Repositories.boxesRepository) }

    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        clearBoxViews()

        viewModel.boxes.observe(viewLifecycleOwner) { renderBoxes(it) }
    }

    private fun renderBoxes(boxes: List<Box>) {
        clearBoxViews()
        if (boxes.isEmpty()) {
            binding.noBoxesTextView.visibility = View.VISIBLE
            binding.boxesContainer.visibility = View.INVISIBLE
        } else {
            binding.noBoxesTextView.visibility = View.INVISIBLE
            binding.boxesContainer.visibility = View.VISIBLE
            createBoxes(boxes)
        }
    }

    private fun createBoxes(boxes: List<Box>) {

        // let's create boxes here by using dynamic view generation

        val width = resources.getDimensionPixelSize(R.dimen.dashboard_item_width)
        val height = resources.getDimensionPixelSize(R.dimen.dashboard_item_height)
        val generatedIdentifiers = boxes.map { box ->
            val id = View.generateViewId()
            val dashboardItemView = DashboardItemView(requireContext())
            dashboardItemView.setBox(box)
            dashboardItemView.id = id
            dashboardItemView.tag = box
            dashboardItemView.setOnClickListener(boxClickListener)
            val params = ConstraintLayout.LayoutParams(width, height)
            binding.boxesContainer.addView(dashboardItemView, params)
            return@map id
        }.toIntArray()
        binding.flowView.referencedIds = generatedIdentifiers
    }

    private fun clearBoxViews() {
        binding.boxesContainer.removeViews(1, binding.root.childCount - 1)
    }

    // TODO("Launch BoxFragment and send box.id, box.colorValue and color name as it's arguments. " +
    //  "BoxFragment should be placed inside the current tab (tabs should be available from BoxFragment)")
    private val boxClickListener = View.OnClickListener {
        val box = it.tag as Box

        val directions = DashboardFragmentDirections.actionDashboardFragmentToBoxFragment(
            box.id,
            getString(box.colorNameRes),
            box.colorValue
        )
        findNavController().navigate(directions)
    }

}
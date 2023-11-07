package com.example.hstest.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hstest.R
import com.example.hstest.databinding.FragmentMainPageBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainPageViewModel by viewModels()
    private val adapter: DishesAdapter by lazy { DishesAdapter() }
    private val adAdapter: AdsAdapter by lazy {
        AdsAdapter(
            listOf(
                Ad(id = 1, image = R.drawable.ad1),
                Ad(id = 2, image = R.drawable.placeholder),
                Ad(id = 3, image = R.drawable.ad1),
                Ad(id = 4, image = R.drawable.placeholder),
                Ad(id = 5, image = R.drawable.ad1),
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData() {
        binding.dishes.adapter = adapter
        binding.adList.adapter = adAdapter
        binding.adList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.data.collectLatest { list ->
                    adapter.submitList(list)
                    list.map { dish ->
                        dish.strCategory
                    }.toSet().forEach { binding.chips.addChip(requireContext(), it.toString()) }
                }
            }
        }
        binding.chips.setOnCheckedStateChangeListener { group, checkedIds -> }
    }

    private fun ChipGroup.addChip(context: Context, title: String) {
        val chip = Chip(context, null, R.attr.CustomChipChoiceStyle).apply {
            id = View.generateViewId()
            text = title
            isClickable = true
            isCheckable = true
            setChipSpacingHorizontalResource(R.dimen.common_margin)
            isCheckedIconVisible = false
            isFocusable = true
            addView(this)
        }
        chip.setOnCheckedChangeListener { _, b ->
            if (b) {
                viewModel.addTag(chip.text.toString())
            } else{
                viewModel.removeTag(chip.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
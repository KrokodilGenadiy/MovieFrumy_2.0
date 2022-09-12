package com.zaus_app.moviefrumy_20.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.moviefrumy_20.*
import com.zaus_app.moviefrumy_20.domain.Film
import com.zaus_app.moviefrumy_20.databinding.FragmentHomeBinding
import com.zaus_app.moviefrumy_20.utils.AnimationHelper
import com.zaus_app.moviefrumy_20.view.MainActivity
import com.zaus_app.moviefrumy_20.view.rv_adaptes.FilmAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration
import com.zaus_app.moviefrumy_20.view.rv_adaptes.FilmDiff
import com.zaus_app.moviefrumy_20.viewmodel.HomeFragmentViewModel
import java.util.*


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeFragmentViewModel>()
    private var filmsDataBase = listOf<Film>()
        set(value) {
            if (field == value) return
            field = value
            updateData(field)
        }
    private val filmsAdapter by lazy {
        FilmAdapter(object : FilmAdapter.OnItemClickListener {
            override fun click(position: Int) {
              (requireActivity() as MainActivity).launchDetailsFragment(getFilm(position))
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        initSearchView()
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)
        viewModel.filmsListLiveData.observe(viewLifecycleOwner) {
            filmsDataBase = it
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    updateData(filmsDataBase)
                    return true
                }
                val result = filmsDataBase.filter {
                    it.title.lowercase(Locale.getDefault()).contains(newText.lowercase(Locale.getDefault()))
                }
                updateData(result)
                return true
            }
        })
    }

    private fun getFilm(position: Int): Film {
        return filmsAdapter.getItems()[position]
    }

    private fun updateData(newList: List<Film>){
        val oldList = filmsAdapter.getItems()
        val productDiff = FilmDiff(oldList,newList)
        val diffResult = DiffUtil.calculateDiff(productDiff)
        filmsAdapter.setItems(newList)
        diffResult.dispatchUpdatesTo(filmsAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
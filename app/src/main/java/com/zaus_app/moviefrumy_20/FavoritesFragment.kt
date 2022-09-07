package com.zaus_app.moviefrumy_20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.moviefrumy_20.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        updateData(FavoritesDatabase.favoritesList)
    }

    private fun updateData(newList: List<Film>){
        val oldList = filmsAdapter.getItems()
        val productDiff = FilmDiff(oldList,newList)
        val diffResult = DiffUtil.calculateDiff(productDiff)
        filmsAdapter.setItems(newList)
        diffResult.dispatchUpdatesTo(filmsAdapter)
    }

    //TODO Remove boilerplate code
    private fun getFilm(position: Int): Film {
        return filmsAdapter.getItems()[position]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
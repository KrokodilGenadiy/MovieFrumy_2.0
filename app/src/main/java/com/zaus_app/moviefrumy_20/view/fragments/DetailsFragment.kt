package com.zaus_app.moviefrumy_20.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zaus_app.moviefrumy_20.data.FavoritesDatabase
import com.zaus_app.moviefrumy_20.R
import com.zaus_app.moviefrumy_20.domain.Film
import com.zaus_app.moviefrumy_20.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilmsDetails()
    }

    private fun setFilmsDetails() {
        val film = arguments?.get("film") as Film
        binding.detailsToolbar.title = film.title
        binding.detailsPoster.setImageResource(film.poster)
        binding.detailsDescription.text = film.description
        initAddToFavoritesBtnFunctionality(film)
        initShareFab(film)
    }

    private fun initShareFab(film: Film) {
        binding.detailsShareFab.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    private fun initAddToFavoritesBtnFunctionality(film: Film) {
        val addToFavoritesFab = binding.detailsFabFavorites
        addToFavoritesFab.setOnClickListener {
            if (!film.isInFavorites) {
                addToFavoritesFab.setImageResource(R.drawable.ic_round_in_favorite)
                film.isInFavorites = true
                FavoritesDatabase.favoritesList.add(film)
            } else {
                addToFavoritesFab.setImageResource(R.drawable.ic_round_not_in_favorite)
                film.isInFavorites = false
                FavoritesDatabase.favoritesList.remove(film)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
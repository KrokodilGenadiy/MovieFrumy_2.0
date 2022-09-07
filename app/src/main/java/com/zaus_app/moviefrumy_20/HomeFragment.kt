package com.zaus_app.moviefrumy_20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaus_app.moviefrumy_20.databinding.FragmentHomeBinding
import java.util.*


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val filmsAdapter by lazy {
        FilmAdapter(object : FilmAdapter.OnItemClickListener {
            override fun click(position: Int) {
              (requireActivity() as MainActivity).launchDetailsFragment(getFilm(position))
            }
        })
    }



    val filmsDataBase = listOf(
        Film("The Shawshank Redemption", R.drawable.shawshank, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."),
        Film("The Godfather", R.drawable.god_father, "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."),
        Film("The Dark Knight", R.drawable.dark_knight, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."),
        Film("Pulp Fiction", R.drawable.pulp, "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."),
        Film("Inception", R.drawable.inception, "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."),
        Film("Hamilton", R.drawable.hamilton, "The real life of one of America's foremost founding fathers and first Secretary of the Treasury, Alexander Hamilton. Captured live on Broadway from the Richard Rodgers Theater with the original Broadway cast."),
        Film("Gisaengchung", R.drawable.parasites, "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan."),
        Film("Interstellar", R.drawable.interstellar, "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."),
        Film("Joker", R.drawable.joker, "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker."),
        Film("1917", R.drawable.seventy, "April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.")
    )

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
        updateData(filmsDataBase)
        initSearchView()
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
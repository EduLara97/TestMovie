package pe.com.test.presentation.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pe.com.test.presentation.home.adapters.MovieUpcomingAdapter
import pe.com.test.presentation.ErrorMessage
import pe.com.test.presentation.home.adapters.MoviePopularAdapter
import pe.com.test.presentation.home.viewModel.HomeViewModel
import pe.com.test.R
import pe.com.test.databinding.FragmentFirstBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapterUpcoming : MovieUpcomingAdapter
    private lateinit var moviePopularAdapter: MoviePopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {

        viewModel.popularMovies.observe(viewLifecycleOwner) { user ->
            user.getContentIfNotHandled()?.let {
                moviePopularAdapter = MoviePopularAdapter(it) {bundle ->
                    findNavController().navigate(R.id.action_FirstFragment_to_DetailFragment, bundle)
                }
                binding?.moviePopularRecyclerView?.layoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                binding?.moviePopularRecyclerView!!.adapter = moviePopularAdapter
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error.getContentIfNotHandled()?.let {
                val idResError = when(it) {
                    ErrorMessage.ERROR_SEARCH -> R.string.errorSearch
                }
                Snackbar.make(binding!!.baseView, requireContext().getString(idResError), Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) { follow ->
            follow.getContentIfNotHandled()?.let {
                adapterUpcoming = MovieUpcomingAdapter(it){bundle ->
                    findNavController().navigate(R.id.action_FirstFragment_to_DetailFragment, bundle)
                }
                binding?.movieUpcomingRecyclerView?.layoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                binding?.movieUpcomingRecyclerView!!.adapter = adapterUpcoming
            }
        }

        viewModel.data()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
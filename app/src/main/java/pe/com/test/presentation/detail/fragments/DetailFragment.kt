package pe.com.test.presentation.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import pe.com.test.BuildConfig
import pe.com.test.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.textViewName.text = it.getString("title")
            binding.textViewDescription.text = it.getString("overview")

            Glide.with(requireContext())
                .load("${BuildConfig.URL_BASE_IMAGE}${it.getString("posterPath")}")
                .into(binding.imageViewPoster)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package pe.com.test.presentation.home.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.com.test.BuildConfig
import pe.com.test.data.model.Movie
import pe.com.test.databinding.ItemMoviePopularBinding

class MoviePopularAdapter(
    private val moviePopular: List<Movie?>,
    private val clickMovie: (bundle: Bundle) -> Unit
) : RecyclerView.Adapter<MoviePopularAdapter.MoviePopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePopularViewHolder {
        return MoviePopularViewHolder(ItemMoviePopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = moviePopular.size

    override fun onBindViewHolder(holder: MoviePopularViewHolder, position: Int) {
        val item = moviePopular[position]
        Glide.with(holder.itemView.context)
            .load("${BuildConfig.URL_BASE_IMAGE}${item?.posterPath}")
            .into(holder.imageView)

        val bundle = bundleOf("title" to item?.title, "posterPath" to item?.posterPath, "overview" to item?.overview)
        holder.itemView.setOnClickListener{
            clickMovie.invoke(bundle)
        }
    }

    class MoviePopularViewHolder(binding: ItemMoviePopularBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.posterImageView
    }
}
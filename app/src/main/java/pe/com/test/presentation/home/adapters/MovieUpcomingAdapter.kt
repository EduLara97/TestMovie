package pe.com.test.presentation.home.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.com.test.BuildConfig
import pe.com.test.data.model.Movie
import pe.com.test.databinding.MovieUpcomingItemBinding

class MovieUpcomingAdapter(
    private val data: List<Movie?>,
    private val clickMovie: (bundle: Bundle) -> Unit
) :
    RecyclerView.Adapter<MovieUpcomingAdapter.MovieUpcomingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUpcomingViewHolder {
        return MovieUpcomingViewHolder(MovieUpcomingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieUpcomingViewHolder, position: Int) {
        val item = data[position]
        Glide.with(holder.itemView.context)
            .load("${BuildConfig.URL_BASE_IMAGE}${item?.posterPath}")
            .into(holder.imageView)
        val bundle = bundleOf("title" to item?.title, "posterPath" to item?.posterPath, "overview" to item?.overview)
        holder.itemView.setOnClickListener{
            clickMovie.invoke(bundle)
        }
    }

    class MovieUpcomingViewHolder(binding: MovieUpcomingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.posterImageView
    }

}
package com.rizaki.challengech6Binar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rizaki.challengech6Binar.databinding.FragmentDetailBinding
import com.rizaki.challengech6Binar.helper.MovieAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDetailMovie()
    }

    private fun showDetailMovie() {
        binding.apply {
            Glide.with(requireView())
                .load(MovieAdapter.posterBaseUrl + args.movie.backdropPath)
                .into(ivBackdrop)
            Glide.with(requireView())
                .load(MovieAdapter.posterBaseUrl + args.movie.posterPath)
                .into(ivPoster)
            tvTitle.text = args.movie.title
            tvOverview.text = args.movie.overview
            tvReleaseDate.text = args.movie.releaseDate
            tvAverageRating.text = args.movie.voteAverage.toString()
            tvRateCount.text = args.movie.voteCount.toString()
            tvPopularity.text = args.movie.popularity.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
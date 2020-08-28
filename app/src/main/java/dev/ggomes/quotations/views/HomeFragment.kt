package dev.ggomes.quotations.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ggomes.quotations.MainActivity
import dev.ggomes.quotations.R
import dev.ggomes.quotations.adapters.QuotesRecyclerViewAdapter
import dev.ggomes.quotations.models.Quote
import dev.ggomes.quotations.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment() {

    private val isUserLoggedIn = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_avatar_imageview.setOnClickListener { onUserViewsClicked() }
        username_textview.setOnClickListener { onUserViewsClicked() }

        quotes_recyclerview.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.VERTICAL, false)
        }

        val viewModel: HomeViewModel by viewModels()

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { onError(it) })

        viewModel.getPublicQuotes().observe(viewLifecycleOwner, Observer {
            if (quotes_recyclerview.adapter == null)
                quotes_recyclerview.adapter = QuotesRecyclerViewAdapter(it.toMutableList(), ::onQuoteClicked)
            else
                (quotes_recyclerview.adapter as QuotesRecyclerViewAdapter).addItems(it)
        })

        username_textview.text = "Gustavo"
        Glide.with(this)
            .load("https://pbs.twimg.com/profile_images/848254682950754304/vgBBQzPY_400x400.jpg")
            .centerCrop()
            .into(user_avatar_imageview)
            //.placeholder()
    }

    private fun onQuoteClicked(quote: Quote) {
        // TODO -> Go to the DetailsFragment, possibly using a Dialog because there isn't a lot of information
    }

    private fun onUserViewsClicked() {
        val fragment = if (isUserLoggedIn) UserProfileFragment.newInstance()
                       else LoginFragment.newInstance()

        (activity as MainActivity).pushFragment(fragment)
    }

    private fun onError(error: Error) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
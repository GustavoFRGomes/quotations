package dev.ggomes.quotations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ggomes.quotations.R
import dev.ggomes.quotations.models.Quote
import kotlinx.android.synthetic.main.view_holder_quote_list.view.*

class QuotesRecyclerViewAdapter(private val quotes: List<Quote>, private val onClickListener: (Quote) -> Unit): RecyclerView.Adapter<QuotesRecyclerViewAdapter.QuoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_quote_list, parent, false)
        )
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) = holder.bind(quotes[position], onClickListener)

    class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(quote: Quote, onClickListener: (Quote) -> Unit) {
            itemView.quote_textview.text = quote.body
            itemView.author_textview.text = quote.author

            itemView.setOnClickListener {
                onClickListener(quote)
            }
        }
    }

}
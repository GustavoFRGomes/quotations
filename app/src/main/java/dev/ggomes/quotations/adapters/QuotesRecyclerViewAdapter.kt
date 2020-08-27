package dev.ggomes.quotations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ggomes.quotations.R
import dev.ggomes.quotations.models.Quote
import kotlinx.android.synthetic.main.view_holder_quote_list.view.*

class QuotesRecyclerViewAdapter(private val quotes: MutableList<Quote>, private val onClickListener: (Quote) -> Unit): RecyclerView.Adapter<QuotesRecyclerViewAdapter.QuoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_quote_list, parent, false)
        )
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) = holder.bind(quotes[position], onClickListener)

    fun addItems(newItems: List<Quote>) {
        val diffUtilCallback = object: DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = newItems[newItemPosition].id == quotes[oldItemPosition].id

            override fun getOldListSize(): Int = quotes.size

            override fun getNewListSize(): Int = newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = newItems[newItemPosition] == quotes[oldItemPosition]
        }

        quotes.addAll(newItems)

        DiffUtil.calculateDiff(diffUtilCallback)
            .dispatchUpdatesTo(this)
    }

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
package dev.ggomes.quotations.views

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import dev.ggomes.quotations.models.Quote

class QuoteDetailsFragment: Fragment() {

    companion object {
        private const val QUOTE_BODY_ARG = "QUOTE_BODY_ARG"
        private const val QUOTE_AUTHOR_ARG = "QUOTE_AUTHOR_ARG"
        private const val QUOTE_ID_ARG = "QUOTE_ID_ARG"

        @JvmStatic
        fun newInstance(quote: Quote): QuoteDetailsFragment {
            val bundle = bundleOf(
                QUOTE_ID_ARG to quote.id,
                QUOTE_AUTHOR_ARG to quote.author,
                QUOTE_BODY_ARG to quote.body
            )

            return QuoteDetailsFragment().apply {
                arguments = bundle
            }
        }
    }
}
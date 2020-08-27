package dev.ggomes.quotations.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.ggomes.quotations.R
import dev.ggomes.quotations.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: LoginViewModel by viewModels()

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { onError(it) })

        login_button.setOnClickListener {
            if (username_edittext.text.isNullOrBlank())
                username_edittext.error = getString(R.string.error_empty_field)

            if (password_edittext.text.isNullOrBlank())
                password_edittext.error = getString(R.string.error_empty_field)

            if (username_edittext.error.isNullOrBlank() || password_edittext.error.isNullOrBlank())
                viewModel.login(
                    username_edittext.text.toString(),
                    password_edittext.text.toString()).observe(viewLifecycleOwner, Observer {

                        if (it)
                            requireActivity().onBackPressed()
                        else
                            onError(Error(getString(R.string.login_failed_message)))
                    })

        }
    }

    private fun onError(error: Error) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
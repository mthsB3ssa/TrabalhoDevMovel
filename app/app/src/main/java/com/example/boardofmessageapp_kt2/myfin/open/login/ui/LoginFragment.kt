package com.example.boardofmessageapp_kt2.open.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.boardofmessageapp_kt2.Consts
import com.example.boardofmessageapp_kt2.base.BaseFragment
import com.example.boardofmessageapp_kt2.closed.PrivateActivity
import com.example.boardofmessageapp_kt2.data.network.Resource
import com.example.boardofmessageapp_kt2.databinding.FragmentLoginBinding
import com.example.boardofmessageapp_kt2.utils.BiometricsHelper
import com.example.boardofmessageapp_kt2.utils.enable
import com.example.boardofmessageapp_kt2.utils.startNewActivity
import com.example.boardofmessageapp_kt2.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@Composable
class LoginFragment : BaseFragment() {
    private val biometricsHelper by lazy { BiometricsHelper(this) }
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        bindListeners()
    }

    private fun bindObservers() {
        viewModel.shouldLoginButtonBeEnabled.observe(viewLifecycleOwner, Observer {
            binding.loginBtn.enable(it)
        })
        /*binding.loginBtn.enable(viewModel.shouldLoginButtonBeEnabled.value!!)*/

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            //binding.loadingPb.visibility = if (it is Resource.Loading) View.VISIBLE else View.GONE
            binding.loadingPb.root.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    // TODO -  This should be decided by the viewmodel!
                    goToPrivateActivity()
                }

                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        })

        viewModel.usernameInput.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank() && binding.usernameEt.text.isNullOrEmpty()) {
                // set default username
                binding.usernameEt.setText(it)
            }
        }

        viewModel.triggerBiometricAuth.observe(viewLifecycleOwner) {
            if (it) {
                checkBiometrics()
            }
        }
    }

    private fun goToPrivateActivity() {
        requireActivity().startNewActivity(PrivateActivity::class.java)
        activity?.finish()
    }

    private fun bindListeners() {
        binding.loginBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            val keepSession = binding.rememberMeCb.isChecked

            // TODO - add validations
            viewModel.attemptLogin(username, password, keepSession, requireContext())
        }

        binding.usernameEt.addTextChangedListener {
            val username = binding.usernameEt.text.toString().trim()
            viewModel.onUsernameInputChanged(username)
        }

        binding.passwordEt.addTextChangedListener {
            val password = binding.passwordEt.text.toString()
            viewModel.onPasswordInputChanged(password)
        }
    }


    // BIOMETRICS
    private fun checkBiometrics() {
        if (Consts.APP_ENVIRONMENT.equals(Consts.Environments.DEV))
            viewModel.attemptBiometricLogin(requireContext())
        else
            biometricsHelper.showBiometricPrompt {
                //  SUCCESS
                viewModel.attemptBiometricLogin(requireContext())
            }
    }

}
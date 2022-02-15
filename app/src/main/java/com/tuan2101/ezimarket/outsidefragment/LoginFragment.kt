package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tuan2101.ezimarket.databinding.FragmentLoginBinding
import com.tuan2101.ezimarket.viewmodel.LoginAndSignupViewModel
import com.tuan2101.ezimarket.viewmodel.LoginAndSigupViewModelFactory

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    val model: LoginAndSignupViewModel by activityViewModels {
        LoginAndSigupViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.model = model

        model.navToRegister.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToRegisterFragment())
                model.navToRegister.value = false
            }
        }
        return binding.root
    }

}
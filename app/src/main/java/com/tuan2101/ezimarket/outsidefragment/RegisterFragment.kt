package com.tuan2101.ezimarket.outsidefragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentRegisterBinding
import com.tuan2101.ezimarket.utils.toast
import com.tuan2101.ezimarket.viewmodel.LoginAndSignupViewModel
import com.tuan2101.ezimarket.viewmodel.LoginAndSigupViewModelFactory

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    val model: LoginAndSignupViewModel by activityViewModels {
        LoginAndSigupViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        binding.model = model

        onTextChange()

        clickNextButtonHandle()


        return binding.root
    }

    private fun clickNextButtonHandle() {
        val context = requireContext()
        model.navToContinueRegister.observe(viewLifecycleOwner) {
            if (it) {
                if (model.user.name.isNullOrEmpty()) {
                    toast(context, "Chưa nhập tên người dùng")
                    binding.userName.requestFocus()
                } else if (model.phoneNumber.isEmpty()) {
                    toast(context, "Chưa nhập số điện thoạt")
                    binding.email.requestFocus()
                } else if (model.password.isEmpty()) {
                    toast(context, "Chưa nhập mật khẩu")
                    binding.password.requestFocus()
                } else if (binding.rewritePassword.text.toString() != model.password) {
                    toast(context, "Nhập lại mật khẩu chưa chính xác")
                    binding.rewritePassword.requestFocus()
                } else {
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToConRegisterFragment())
                    model.navToContinueRegister.value = false
                }
            }
        }
    }


    private fun onTextChange() {
        binding.countryCodePicker.setDefaultCountryUsingNameCode("VN")
        var countryCode: String = binding.countryCodePicker.defaultCountryCodeWithPlus
        model.region = binding.countryCodePicker.defaultCountryNameCode

        binding.countryCodePicker.setOnCountryChangeListener {
            countryCode = binding.countryCodePicker.selectedCountryCodeWithPlus
            model.region = binding.countryCodePicker.selectedCountryNameCode
        }

        binding.userName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank() && p0.length < 30) {
                    model.user.name = p0.toString().trim()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank() && p0.length < 10) {
                    model.phoneNumber = countryCode + p0.toString().trim()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank()) {
                    model.password = p0.toString().trim()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.rewritePassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() == model.password) {
                    binding.rewritePassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_done_24, 0)
                } else {
                    binding.rewritePassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0) }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }


}
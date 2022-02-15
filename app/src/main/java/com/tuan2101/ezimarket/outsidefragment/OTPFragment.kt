package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentOTPBinding
import com.tuan2101.ezimarket.utils.toast
import com.tuan2101.ezimarket.viewmodel.LoginAndSignupViewModel
import com.tuan2101.ezimarket.viewmodel.LoginAndSigupViewModelFactory
import java.util.concurrent.TimeUnit

class OTPFragment : Fragment() {

    lateinit var binding: FragmentOTPBinding
    val model: LoginAndSignupViewModel by activityViewModels {
        LoginAndSigupViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOTPBinding.inflate(layoutInflater, container, false)
        binding.model = model
        model.shareVerificationCode(model.phoneNumber)
        autoInputCode()
        status()
        onEnterOtp()
        return binding.root
    }

    private fun autoInputCode() {
        model.code.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.pinView.setText(it)
            }
        }
    }

    private fun onEnterOtp() {
        binding.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length == 6) {
                    model.verifyCode(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun status() {
        model.isSuccess.observe(viewLifecycleOwner) {
            toast(requireContext(), it)
            Log.i("444", it)
            if (it.equals("Tạo tài khoản thành công")) {

            } else if (it.equals("Nhập sai mã OTP")) {
                toast(requireContext(), "Nhập sai mã OTP")
            }
        }
    }


}
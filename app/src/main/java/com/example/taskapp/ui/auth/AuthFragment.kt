package com.example.taskapp.ui.auth
import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentAuthBinding
import com.example.taskapp.extenssion.snowToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }


    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            if (binding.etPhone.text!!.isNotEmpty()) {
                sendPhone()
                Toast.makeText(requireContext(), "Отправка", Toast.LENGTH_SHORT).show()
            } else {
                binding.etPhone.error = "Введите номер телефона"
            }
            binding.btnConfirm.setOnClickListener {
                sendCode()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun sendPhone() {
        auth.setLanguageCode("RU")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())
            .setTimeout(120L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    snowToast("Невышло  " + p0.message.toString())
                    Log.d("ololo", "onVerificationFailed:" + p0.message.toString())
                }

                override fun onCodeSent(
                    verificationCode: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    correctCode = verificationCode
                    binding.etLayoutPhone.isVisible = false
                    binding.btnSend.isVisible = false

                    binding.linearLayout.isVisible = true
                    binding.btnConfirm.isVisible = true
                    Log.d("ololo", "onCodeSent:$verificationCode")
                    super.onCodeSent(verificationCode, p1)
                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun initViews() {

    }

    private fun sendCode() {
        val otp = "${binding.one.text}${
            binding.two.text}${
            binding.three.text}${
            binding.four.text}${
            binding.five.text}${
            binding.six.text}"
        val credential = correctCode?.let { it1 -> PhoneAuthProvider.getCredential(it1, otp) }

        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.navigation_home)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("opol", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.d("asd", "signInWithPhoneAuthCredential: " + task.exception.toString())
                    }
                    // Update UI
                }
            }
    }
}


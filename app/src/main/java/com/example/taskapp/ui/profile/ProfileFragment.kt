package com.example.taskapp.ui.profile
import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.extenssion.loadImage
import com.example.taskapp.ui.utils.Preferences


class ProfileFragment : Fragment() {
    private lateinit var preferences: Preferences

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri ->
        preferences.setProfile(uri.toString())
        binding.imageView.loadImage(uri.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preferences = Preferences(requireContext())
        _binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        imageChooser()
        binding.imageView.loadImage(preferences.isProfile())
        binding.editName.setText(preferences.isProfileUser())
        binding.email.setText(preferences.isProfileEmail())
        binding.phone.setText(preferences.isProfilePhone())
        binding.gender.setText(preferences.isProfileGender())
        binding.data.setText(preferences.isProfileDate())
        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.btnRed.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Вы точно хотите выйти с акаунта?")
            builder.setNeutralButton("Да"){ _, _ ->
                findNavController().navigate(R.id.authFragment)
                preferences.setProfileShowed(true)
            }
            builder.setPositiveButton("Нет"){ o2, _ ->
                o2.dismiss()
            }
            builder.show()
        }
    }

    override fun onResume() {
        super.onResume()
        preferences.setProfileUser(binding.editName.text.toString())
        preferences.setProfileEmail(binding.email.text.toString())
        preferences.setProfilePhone(binding.phone.text.toString())
        preferences.setProfileGender(binding.gender.text.toString())
        preferences.setProfileDate(binding.data.text.toString())
    }

    override fun onPause() {
        super.onPause()
        preferences.setProfileUser(binding.editName.text.toString())
        preferences.setProfileEmail(binding.email.text.toString())
        preferences.setProfilePhone(binding.phone.text.toString())
        preferences.setProfileGender(binding.gender.text.toString())
        preferences.setProfileDate(binding.data.text.toString())
    }

    private fun imageChooser() {
        binding.imageView.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }
}


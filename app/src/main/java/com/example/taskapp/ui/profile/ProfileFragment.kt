package com.example.taskapp.ui.profile
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taskapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri ->
        binding?.imageView?.setImageURI(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //  binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        //   imageChooser()
        return binding!!.root
    }

    private fun imageChooser() {
        binding!!.imageView.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }
}
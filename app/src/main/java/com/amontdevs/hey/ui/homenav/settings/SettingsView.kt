package com.amontdevs.hey.ui.homenav.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amontdevs.hey.R
import com.amontdevs.hey.databinding.FragmentSettingsViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsView : Fragment() {
    private var _binding: FragmentSettingsViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        binding.settingsResetBtn.setOnClickListener {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle(R.string.modal_title)
                    .setMessage(R.string.modal_description)
                    .setPositiveButton(R.string.modal_confirm){ _, _ ->
                        viewModel.resetApp()
                    }
                    .setNegativeButton(R.string.modal_cancel){ dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        binding.settingsMailBtn.setOnClickListener {
            sendMail()
        }
    }

    private fun sendMail(){
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:amontdevs@gmail.com"))
        intent.putExtra("subject","Justo App Challenge")
        this.context?.startActivity(Intent.createChooser(intent, "Choose Mail Client..."))
    }
}
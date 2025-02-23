package com.smilegate.Easel.presentation.view.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.smilegate.Easel.R
import com.smilegate.Easel.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStartBinding.inflate(inflater, container, false)

        val toolbar = requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_container)
        val backButton = toolbar.findViewById<ImageView>(R.id.back_btn)
        backButton.visibility = View.GONE

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        navController = findNavController()

        binding.startFragmentLoginBtn.setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_loginFragment)
            backButton.visibility = View.GONE
        }

        binding.startFragmentJoinBtn.setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_CreateAccountFragment)
            backButton.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

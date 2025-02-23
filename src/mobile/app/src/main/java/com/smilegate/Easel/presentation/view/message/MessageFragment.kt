package com.smilegate.Easel.presentation.view.message

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.GestureDetector
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smilegate.Easel.R
import com.smilegate.Easel.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private lateinit var binding: FragmentMessageBinding

    private lateinit var navController: NavController

    private var doubleBackPressed = false

    private var isFabOpen = false
    private lateinit var gestureDetector: GestureDetector

    private var isAnimationRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(inflater, container, false)

        val toolbar = requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_container)
        toolbar.visibility = View.VISIBLE

        val profileButton = toolbar.findViewById<ImageView>(R.id.back_btn)
        profileButton.setImageResource(R.drawable.ic_circle_person)
        profileButton.visibility = View.VISIBLE
        profileButton.setOnClickListener {
            findNavController().navigate(R.id.myPageFragment)
        }

        val settingButton = toolbar.findViewById<ImageView>(R.id.else_btn)
        settingButton.setImageResource(R.drawable.ic_setting)
        settingButton.visibility = View.VISIBLE

        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigation?.visibility = View.VISIBLE

        navController = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼을 처리하는 부분
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && isCurrentFragment()) {
                handleBackPressed()
                return@setOnKeyListener true
            }

            false
        }

        setFABClickEvent()
    }

    private fun isCurrentFragment(): Boolean {
        return true
    }

    private fun setFABClickEvent() {

        gestureDetector = GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {

            override fun onSingleTapUp(e: MotionEvent): Boolean {
                if(isFabOpen) {
                    toggleFab()

                    binding.fabMain.scaleX = 0.8f
                    binding.fabMain.scaleY = 0.8f

                    binding.fabMain.scaleX = 1.0f
                    binding.fabMain.scaleY = 1.0f

                    isAnimationRunning = false

                }
                return true
            }
        })

        binding.fabMain.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        binding.root.setOnTouchListener { _, event ->
            // 상호작용을 막는 부분
            return@setOnTouchListener isAnimationRunning
        }
    }

    private fun toggleFab() {
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 180f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 180f, 0f).apply { start() }
        }

        isFabOpen = !isFabOpen

        isAnimationRunning = false

    }

    private fun handleBackPressed() {
        if (doubleBackPressed) {
            requireActivity().finish()
            return
        }

        doubleBackPressed = true
        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackPressed = false
        }, 2000)
    }
}

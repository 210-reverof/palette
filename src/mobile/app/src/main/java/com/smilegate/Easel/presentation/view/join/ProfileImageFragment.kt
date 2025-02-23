package com.smilegate.Easel.presentation.view.join

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.smilegate.Easel.R
import com.smilegate.Easel.databinding.FragmentProfileImageBinding
import com.smilegate.Easel.presentation.viewmodel.JoinViewModel

class ProfileImageFragment : Fragment() {
    private lateinit var _binding: FragmentProfileImageBinding
    private val binding get() = _binding!!

    // 이미지 선택 요청 코드
    private val pickImageRequest = 1
    private var selectedImageUri: Uri? = null

    private lateinit var navController: NavController
    private lateinit var joinViewModel: JoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileImageBinding.inflate(inflater, container, false)

        // ViewModel 초기화
        joinViewModel = ViewModelProvider(requireActivity()).get(JoinViewModel::class.java)

        // 갤러리 아이콘 클릭 시 이미지 선택 요청
        binding.profileImageFragmentCameraBtn.setOnClickListener {
            openGallery()
        }

        navController = findNavController()

        binding.profileImageFragmentNextBtn.setOnClickListener {
            navController.navigate(R.id.action_profileImageFragment_to_askNameFragment)
        }

        binding.profileImageFragmentSkipBtn.setOnClickListener {
            navController.navigate(R.id.action_profileImageFragment_to_askNameFragment)
        }

        binding.profileImageFragmentDeleteBtn.setOnClickListener {
            selectedImageUri = null
            binding.profileImageFragmentCameraBtn.visibility = View.VISIBLE
        }

        return binding.root
    }

    private fun openGallery() {
        // 갤러리에서 이미지를 선택하는 Intent 생성
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, pickImageRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickImageRequest && resultCode == Activity.RESULT_OK) {
            // 이미지가 성공적으로 선택되었을 때
            var selectedImageUri: Uri? = data?.data
            val nextButton = binding?.root?.findViewById<Button>(R.id.profile_image_fragment_next_btn)
            joinViewModel.setProfileImageURL(selectedImageUri.toString())

            // 선택한 이미지를 ImageView에 설정
            selectedImageUri?.let {
                binding.profileImage.setImageURI(it)
                binding.profileImage.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.deleteCardView.visibility = View.VISIBLE
                binding.profileImageFragmentDeleteBtn.setOnClickListener {
                    // 이미지 삭제 처리
                    selectedImageUri = null
                    binding.profileImageFragmentCameraBtn.visibility = View.VISIBLE
                    binding.deleteCardView.visibility = View.GONE
                    val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.bg_profile_image_picker)
                    binding.profileImage.setImageDrawable(drawable)
                }
            }

            // ImageView에 Image가 선택되어있지 않으면 버튼 비활성화
            if (selectedImageUri == null) {
                nextButton?.isEnabled = false
                nextButton?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Grey_300))
            } else {
                nextButton?.isEnabled = true
                nextButton?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }

            //Camera Button 숨기기
            binding.profileImageFragmentCameraBtn.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_container)
        val backButton = toolbar.findViewById<ImageView>(R.id.back_btn)
        backButton.visibility = View.VISIBLE

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}

package com.rumbl.rumbl_pt.features.profile

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.chip.Chip
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.services.ImageLoadingService
import com.rumbl.rumbl_pt.databinding.FragmentProfileBinding
import com.rumbl.rumbl_pt.features.profile.list.TrainerGalleryAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(
    layoutId = R.layout.fragment_profile,
    clazz = ProfileViewModel::class
), KoinComponent {
    private val imageLoadingService: ImageLoadingService by inject()
    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.getTrainerData()?.let { trainer ->
            if (trainer.specifications.isEmpty() && trainer.certifications.isEmpty()) {
                binding.apply {
                    chipGroupCertificatesExpertise.visibility = View.GONE
                    tvCertificatesExpertise.visibility = View.GONE
                }
            } else {
                showTrainerSpecificationsAndCertifications(
                    trainer.certifications.map { it.name },
                    trainer.specifications.map { it.name })
            }
            trainer.profile_picture?.let { profilePhoto ->
                imageLoadingService.loadCircleImageWithPlaceholder(
                    requireContext(),
                    profilePhoto,
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_rumbl_logo)!!,
                    iv_trainer
                )
            }
            tv_trainer_bio.text = trainer.bio
            tv_trainer_name.text = trainer.name
            trainer.level?.id?.let { level ->
                tv_trainer_level.text = trainer.level.name
                when (level) {
                    1 -> {
                        tv_trainer_level.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green
                            )
                        )
                        iv_trainer_badge.setImageResource(R.drawable.ic_badge_level_one)
                    }
                    2 -> {
                        tv_trainer_level.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.light_blue
                            )
                        )
                        iv_trainer_badge.setImageResource(R.drawable.ic_badge_kevel_two)
                    }
                    3 -> {
                        tv_trainer_level.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.gc_red
                            )
                        )
                        iv_trainer_badge.setImageResource(R.drawable.ic_badge_level_three)
                    }
                }
            }
            trainer.gallery?.let { photos ->
                if (photos.isNotEmpty()) {
                    tv_gallery_item.visibility = View.VISIBLE
                    rv_gallery.visibility = View.VISIBLE
                    val trainerGalleryAdapter = TrainerGalleryAdapter(photos)
                    rv_gallery.adapter = trainerGalleryAdapter
                    val pagerSnapHelper = PagerSnapHelper()
                    pagerSnapHelper.attachToRecyclerView(rv_gallery)
                }
            }

        }
    }

    private fun showTrainerSpecificationsAndCertifications(
        specificationList: List<String>,
        certificationsList: List<String>
    ) {
        (specificationList + certificationsList).forEach { trainerSpecificationText ->
            val chip =
                Chip(ContextThemeWrapper(requireContext(), R.style.Theme_MaterialComponents_Light))
            chip.apply {
                isCheckable = false
                isClickable = false
                text = trainerSpecificationText
                textSize = 10f
                setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.gc_red))
                val face = ResourcesCompat.getFont(requireContext(), R.font.sfprodisplay_bold)
                typeface = face
                chipBackgroundColor =
                    ContextCompat.getColorStateList(requireContext(), R.color.gc_red_transparent)
                gravity = Gravity.CENTER
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                binding.chipGroupCertificatesExpertise.addView(this)
            }
        }

    }
}
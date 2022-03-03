package com.siddydevelops.flicker_kots

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.siddydevelops.flicker_kots.databinding.ActivityPhotoDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_details.*

class PhotoDetailsActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPhotoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activateToolbar(true)

        //val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photo
        val photo = intent.extras?.getParcelable<Photo>(PHOTO_TRANSFER) as Photo

        photo_title.text = photo.title
        photo_tags.text = photo.tag
        photo_author.text = photo.author
        Picasso.get().load(photo.link)
            .error(R.drawable.baseline_image_black_48dp)
            .placeholder(R.drawable.baseline_image_black_48dp)
            .into(photo_image)



    }
}
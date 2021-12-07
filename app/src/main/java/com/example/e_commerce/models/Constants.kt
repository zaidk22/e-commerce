package com.example.e_commerce.models

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi


object Constants {
    const val USERS : String = "users"
   const val E_COMMERCE_PREFERENCES : String = "My e_commerce preferences"
    const val Logged_In_UserName :String = "logged_in_username"
    const val EXTRA_USER_DETAILS:String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1
    const val MALE:String = "Male"
    const val FEMALE:String = "Female"
    const val MOBILE : String = "mobile"
    const val GENDER:String = "gender"
    const val IMAGE : String = "image"
    const val Complete_Profile : String = "profileCompleted"
    const val User_Profile_Image : String = "User_Profile_Image"
    @RequiresApi(Build.VERSION_CODES.Q)
    fun showImageChooser(activity: Activity){
        val galleryIntent = Intent(
            Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }
    fun getFileExtension(activity: Activity,uri: Uri?):String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}
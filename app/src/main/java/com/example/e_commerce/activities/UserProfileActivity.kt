package com.example.e_commerce.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.e_commerce.R
import com.example.e_commerce.firestore.FirestoreClass
import com.example.e_commerce.models.Constants
import com.example.e_commerce.models.User
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity(), View.OnClickListener {
    private  var mUserDetails: User? = null
    private var mSelectedImageFileUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val getaction = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri ->
                iv_image_view_up.setImageURI(uri)
            })
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }
        et_firstName_up.isEnabled = false
        et_firstName_up.setText(mUserDetails?.firstName)
       et_lastName_up.isEnabled = false
        et_lastName_up.setText(mUserDetails?.lastName)
       et_email_up.isEnabled = false
        et_email_up.setText(mUserDetails?.email)

        iv_image_view_up.setOnClickListener { getaction.launch("image/*") }
        btn_submit_up.setOnClickListener(this@UserProfileActivity)
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_image_view_up -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        Constants.showImageChooser(this)
                        //Log.e("msg","you already have permission")
                    } else {
                        ActivityCompat.requestPermissions(
                            this@UserProfileActivity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }
                R.id.btn_submit_up -> {

                    //FirestoreClass().uploadImageToCloudStorage(this,mSelectedImageFileUri)
                    val userHashMap = HashMap<String, Any>()
                    val mobileNumber = et_mobile_up.text.toString().trim { it <= ' ' }
                    val gender = if (rb_male.isChecked) {
                        Constants.MALE

                    } else {
                        Constants.FEMALE
                    }
                    if (mobileNumber.isNotEmpty()) {
                        userHashMap[Constants.MOBILE] = mobileNumber.toLong()
                    }
                    userHashMap[Constants.GENDER] = gender
                    userHashMap[Constants.Complete_Profile] = 1
                    FirestoreClass().updateUserProfileData(this, userHashMap)


                }
            }
        }
    }

    fun userProfileUpdatedSuccess() {
        Toast.makeText(
            this@UserProfileActivity, resources.getString(R.string.msg_profile_update_success),
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this@UserProfileActivity, MainActivity::class.java))
        finish()
    }

}

    /*fun imageUploadSuccess(imageURL: String) {
        Log.e("msg","image upload success")
    }
*/


// @RequiresApi(Build.VERSION_CODES.Q)
// override fun onRequestPermissionsResult(
// requestCode: Int,
// permissions: Array<out String>,
// grantResults: IntArray
// ) {
// super.onRequestPermissionsResult(requestCode, permissions, grantResults)
// if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
// //if permission is granted
// if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// Constants.showImageChooser(this)
// //Log.e("msg","permission is granted")
//
// } else {
// Toast.makeText(
// this,
// resources.getString(R.string.read_storage_permission_denied),
// Toast.LENGTH_LONG
// ).show()
// }
// }
//
//
// }
//
// override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
// super.onActivityResult(requestCode, resultCode, data)
// if (resultCode==Constants.PICK_IMAGE_REQUEST_CODE){
// if (data!=null){
// try{
// mSelectedImageFileUri= data.data!!
//
// }catch (e: IOException)
// {e.printStackTrace()
// Toast.makeText(this@UserProfileActivity,resources.getString(R.string.image_selection_failed),
// Toast.LENGTH_SHORT).show()
// }
// }
// }else if(resultCode==Activity.RESULT_CANCELED){}
// }
//
//
// }
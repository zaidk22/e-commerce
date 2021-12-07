package com.example.e_commerce.firestore
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.example.e_commerce.activities.LoginActivity
import com.example.e_commerce.activities.UserProfileActivity
import com.example.e_commerce.models.Constants
import com.example.e_commerce.models.User
import com.example.e_commerce.activities.userRegistrationSuccess
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()
    private val mSelectedImageProfileUri: Uri? = null

    fun registerUser(userInfo: User) {
        mFireStore.collection(Constants.USERS)
            .document(userInfo.id).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("message", "Error")
            }


    }

    private fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = " "
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

    fun getUserDetails(activity: Activity) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())
                try {
                    val user: User = document.toObject(User::class.java)!!
                    val sharedPreferences = activity.getSharedPreferences(
                        Constants.E_COMMERCE_PREFERENCES,
                        Context.MODE_PRIVATE
                    )
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString(
                        Constants.Logged_In_UserName,
                        "${user.firstName} ${user.lastName}"
                    )
                    editor.apply()
                    when (activity) {
                        is LoginActivity -> {
                            activity.userLoggedInSuccess(user)
                        }
                    }

                } catch (ignored: NullPointerException) {

                }


            }
            .addOnFailureListener { e ->
                Log.e("message", "login failure")
            }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is UserProfileActivity -> {
                        activity.userProfileUpdatedSuccess()
                    }
                }
            }
            .addOnFailureListener { e -> }
        when (activity) {
            is UserProfileActivity -> {

            }
        }
        Log.e(activity.javaClass.simpleName, "Error while updating the user details.")

    }
}
   /* fun uploadImageToCloudStorage(
        userProfileActivity: UserProfileActivity,
        mSelectedImageFileUri: Uri?
    ) {

    }
}*/

   /* fun uploadImageToCloudStorage(
        activity: Activity, mSelectedImageFileUri: Uri?
    ) {
        val sRef: StorageReference = FirebaseStorage.getInstance().reference
            .child(
                Constants.User_Profile_Image + System.currentTimeMillis() + "."
                        + Constants.getFileExtension(activity, mSelectedImageFileUri)
            )
        sRef.putFile(mSelectedImageFileUri!!).addOnSuccessListener { taskSnapshot ->
            Log.e("Firebase Image Uri ", taskSnapshot.metadata!!.reference!!.downloadUrl.toString())
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                Log.e("Downloadable Image URL ", uri.toString())
                when (activity) {
                    is UserProfileActivity -> { activity.imageUploadSuccess(uri.toString())

                    }
                }
            }
                .addOnFailureListener { exception ->
                    Log.e(
                        activity.javaClass.simpleName,
                        exception.message,
                        exception
                    )
                }

        }
        }
    }



/*fun uploadImageToCloudStorage(activity: Activity, imageFileUri: Uri?) {
val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
Constants.User_Profile_Image + System.currentTimeMillis() + "."
+ Constants.getFileExtension(activity, imageFileUri)

)

sRef.putFile(imageFileUri!!).addOnSuccessListener { taskSnapshot ->
Log.e(
"Firebase Image URL", taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
)
taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
Log.e("Downloadable Image URL ", uri.toString())
when (activity) {
is UserProfileActivity -> {
activity.imageUploadSuccess(uri.toString())
}
}
}
}
.addOnFailureListener { exception ->
Log.e(
activity.javaClass.simpleName,
exception.message,
exception
)
}

}


   */
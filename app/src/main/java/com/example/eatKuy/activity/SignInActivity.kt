package com.example.eatKuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.eatKuy.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG_FB = "FACELOG"
        private const val TAG_GOOGLE = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Facebook Login
        callbackManager = CallbackManager.Factory.create()
        val imgFacebook: ImageView = findViewById(R.id.facebook)
        imgFacebook.setOnClickListener(this)

        //G-mail login
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val imgGmail: ImageView = findViewById(R.id.gmail)
        imgGmail.setOnClickListener(this)
        googleSignInClient = GoogleSignIn.getClient(this, gso)


        auth = FirebaseAuth.getInstance()

        //Login normally with email and password
        val btnSubmit: Button = findViewById(R.id.btn_submit)
        btnSubmit.setOnClickListener(this)

        //If no account , register
        val noAccount: TextView = findViewById(R.id.no_account)
        noAccount.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when (v.id) {
            R.id.no_account -> {
                val signUpIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(signUpIntent)
            }
            R.id.facebook -> {
                LoginManager.getInstance()
                    .logInWithReadPermissions(this, listOf("public_profile", "email"))
                LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult?> {
                        override fun onSuccess(loginResult: LoginResult?) {
                            Log.d(TAG_FB, "facebook:onSuccess:$loginResult")
                            if (loginResult != null) {
                                handleFacebookAccessToken(loginResult.accessToken)
                            }
                        }
                        override fun onCancel() {
                            Log.d(TAG_FB, "facebook:onCancel")
                        }
                        override fun onError(error: FacebookException) {
                            Log.d(TAG_FB, "facebook:onError", error)
                        }
                    })
            }
            R.id.gmail ->{
                val gMailSignInIntent = googleSignInClient.signInIntent
                startActivityForResult(gMailSignInIntent, RC_SIGN_IN)
            }
            R.id.btn_submit ->{
                val signInIntent = Intent(this@SignInActivity, MainMenuActivity::class.java)
                startActivity(signInIntent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Pass the activity result back to the Facebook SDK

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG_GOOGLE, "Google sign in failed", e)
            }
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val successIntent = Intent(this@SignInActivity, MainMenuActivity::class.java)
            startActivity(successIntent)
        }
    }
    //Facebook
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG_FB, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG_FB, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG_FB, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Gmail
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG_GOOGLE, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG_GOOGLE, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG_GOOGLE, "signInWithCredential:failure", task.exception)
                }

            }
    }
}

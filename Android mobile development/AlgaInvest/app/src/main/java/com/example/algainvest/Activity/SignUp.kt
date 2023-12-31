package com.example.algainvest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.charmrides.ApiService.AuthService
import com.example.charmrides.FormData.UserSignUp
import com.example.charmrides.RetrofitService.RetrofitService
import com.example.charmrides.Validation.ValidationResult
import com.example.fitme.DialogAlerts.ProgressLoader
import com.example.algainvest.EntityReq.AuthSignUp
import com.example.algainvest.EntityRes.AuthSignUpRes
import com.example.innova.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    private lateinit var tvRedirectLogin: TextView
    private lateinit var cvSignUpBtn: CardView
    private lateinit var etPasswordSignUp: EditText
    private lateinit var etEmailSignUp: EditText
    private lateinit var etNameSignUp: EditText
    private lateinit var progressLoader: ProgressLoader
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)
        cvSignUpBtn = findViewById(R.id.cvSignUpBtn)
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp)
        etEmailSignUp = findViewById(R.id.etEmailSignUp)
        etNameSignUp = findViewById(R.id.etNameSignUp)

        tvRedirectLogin.setOnClickListener {
            startActivity(Intent(this@SignUp, SignIn::class.java))
        }

        cvSignUpBtn.setOnClickListener {
            authSignUp(
                etEmailSignUp.text.toString(),
                etPasswordSignUp.text.toString(),
                etNameSignUp.text.toString()
            )
        }
    }

    private fun authSignUp(email: String, password: String, name: String) {
        validateSignUp(email, password, name)
        if (count == 3) { // Adjusted count based on the number of validations
            progressLoader = ProgressLoader(
                this@SignUp, "Verifying Register", "Please Wait...."
            )
            progressLoader.startProgressLoader()
            val retrofitService = RetrofitService()
            val authService: AuthService = retrofitService.getRetrofit().create(AuthService::class.java)

            val call: Call<AuthSignUpRes> = authService.createUserAuth(
                AuthSignUp(email, password, password, name)
            )
            call.enqueue(object : Callback<AuthSignUpRes> {
                override fun onResponse(call: Call<AuthSignUpRes>, response: Response<AuthSignUpRes>) {
                    println(response.body())
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null) {
                            val intent = Intent(this@SignUp, SignIn::class.java)
                            startActivity(intent)
                            progressLoader.dismissProgressLoader()
                            finish()
                        }
                    } else {
                        Toast.makeText(this@SignUp, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        progressLoader.dismissProgressLoader()
                    }
                }

                override fun onFailure(call: Call<AuthSignUpRes>, t: Throwable) {
                    Toast.makeText(this@SignUp, "Server Error", Toast.LENGTH_SHORT).show()
                    progressLoader.dismissProgressLoader()
                }
            })
            count = 0
        }
        count = 0
    }

    private fun validateSignUp(email: String, password: String, name: String) {
        val userSignUp = UserSignUp(
            email,
            password,
            password,
            name
        )

        val emailValidation = userSignUp.validateEmail()
        val passwordValidation = userSignUp.validatePassword()
        val nameValidation = userSignUp.validateName()

        when (emailValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                etEmailSignUp.error = emailValidation.errorMsg
            }
            is ValidationResult.Empty -> {
                etEmailSignUp.error = emailValidation.errorMsg
            }
        }

        when (nameValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                etNameSignUp.error = nameValidation.errorMsg
            }
            is ValidationResult.Empty -> {
                etNameSignUp.error = nameValidation.errorMsg
            }
        }

        when (passwordValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                etPasswordSignUp.error = passwordValidation.errorMsg
            }
            is ValidationResult.Empty -> {
                etPasswordSignUp.error = passwordValidation.errorMsg
            }
        }
    }
}

package com.example.tutorchinese.ui.view.beginner.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.tutorchinese.R
import com.example.tutorchinese.ui.data.entities.User
import com.example.tutorchinese.ui.controler.CustomProgressDialog
import com.example.tutorchinese.ui.view.main.MainActivity
import com.example.tutorchinese.ui.view.beginner.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtPassword
import kotlinx.android.synthetic.main.activity_login.edtUsername

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LoginActivity : AppCompatActivity() {

    private lateinit var mLoginPresenter: LoginPresenter
    private var dialog: CustomProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mLoginPresenter = LoginPresenter()
        actionOnClick()
    }

    private fun actionOnClick() {
        btnRegister.setOnClickListener {
            val myIntent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(myIntent)
        }

        btnLogin.setOnClickListener {
            dialog = CustomProgressDialog(this, "กำลังโหลด..")
            val ad: AlertDialog.Builder = AlertDialog.Builder(this)
            ad.setTitle("พบข้อมผิดพลาด!")
            ad.setIcon(android.R.drawable.btn_star_big_on)
            ad.setPositiveButton("ปิด", null)
            dialog?.show()

            if (edtUsername.text.isEmpty()) {
                if (dialog?.isShowing!!) {
                    dialog?.dismiss()
                }
                ad.setMessage("กรุณากรอกชื่อผู้ใช้")
                ad.show()
                edtUsername.requestFocus()
                return@setOnClickListener
            }

            if (edtPassword.text.isEmpty()) {
                if (dialog?.isShowing!!) {
                    dialog?.dismiss()
                }
                ad.setMessage("กรุณากรอกรหัสผ่าน")
                ad.show()
                edtPassword.requestFocus()
                return@setOnClickListener
            }
            mLoginPresenter.callLogin(
                this,
                edtUsername.text.toString(),
                edtPassword.text.toString(), object : LoginPresenter.View {
                    override fun onSuccessService(user: List<User>?, type: String) {
                        when (type) {
                            "user" -> {
                                mLoginPresenter.addDataUserToPrefs(applicationContext, user, type)
                                val myIntent = Intent(applicationContext, MainActivity::class.java)
                                myIntent.putExtra("username", user!![0].U_username)
                                myIntent.putExtra("type", type)
                                startActivity(myIntent)
                                finish()
                                Log.d("As6dasd", user[0].U_username)
                            }
                            "tutor" -> {
                                mLoginPresenter.addDataTutorTioPrefs(applicationContext, user, type)
                                val myIntent = Intent(applicationContext, MainActivity::class.java)
                                myIntent.putExtra("username", user!![0].T_username)
                                myIntent.putExtra("type", type)
                                startActivity(myIntent)
                                finish()
                                Log.d("As6dasd", user[0].T_username)
                            }
                            else -> {
                                mLoginPresenter.addDataAdminToPrefs(applicationContext, user, type)
                                val myIntent = Intent(applicationContext, MainActivity::class.java)
                                myIntent.putExtra("username", user!![0].admin_username)
                                myIntent.putExtra("type", type)
                                startActivity(myIntent)
                                finish()
                                Log.d("As6dasd", user[0].admin_username)
                            }
                        }
                        if (dialog?.isShowing!!) {
                            dialog?.dismiss()
                        }
                    }

                    override fun onErrorService(error: String) {
                        if (dialog?.isShowing!!) {
                            dialog?.dismiss()
                        }
                        val ad: AlertDialog.Builder = AlertDialog.Builder(this@LoginActivity)
                        ad.setTitle("พบข้อมผิดพลาด!")
                        ad.setIcon(android.R.drawable.btn_star_big_on)
                        ad.setPositiveButton("ปิด", null)
                        ad.setMessage(error)
                        ad.show()
                        Log.d("As6dasd", error)

                    }
                }
            )
        }
    }

    private fun checkIsEmpty() {
        val ad: AlertDialog.Builder = AlertDialog.Builder(this)
        ad.setTitle("พบข้อมผิดพลาด!")
        ad.setIcon(android.R.drawable.btn_star_big_on)
        ad.setPositiveButton("ปิด", null)

        if (edtUsername.text.isEmpty()) {
            ad.setMessage("กรุณากรอกชื่อผู้ใช้")
            ad.show()
            edtUsername.requestFocus()
            return
        }

        if (edtPassword.text.isEmpty()) {
            ad.setMessage("กรุณากรอกรหัสผ่าน")
            ad.show()
            edtPassword.requestFocus()
            return
        }

    }
}
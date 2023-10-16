package com.example.mynhcare

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
        View.OnKeyListener {

        private lateinit var mBinding: ActivityRegisterBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
            setContentView(mBinding.root)

            mBinding.fullNameEt.onFocusChangeListener = this
            mBinding.EmailEt.onFocusChangeListener = this
            mBinding.PasswordEt.onFocusChangeListener = this
            mBinding.ConfirmPasswordEt.onFocusChangeListener = this
        }

        private fun showError(binding: TextInputLayout, errorMessage: String) {
            binding.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        private fun clearError(binding: TextInputLayout) {
            binding.isErrorEnabled = false
        }

        private fun validateFullName(): Boolean {
            val value = mBinding.fullNameEt.text.toString()
            if (value.isEmpty()) {
                showError(mBinding.fullNameTil, "Nama lengkap diperlukan")
                return false
            }
            clearError(mBinding.fullNameTil)
            return true
        }

        private fun validateEmail(): Boolean {
            val value = mBinding.EmailEt.text.toString()
            if (value.isEmpty()) {
                showError(mBinding.EmailTil, "Email diperlukan")
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                showError(mBinding.EmailTil, "Email tidak valid")
                return false
            }
            clearError(mBinding.EmailTil)
            return true
        }

        private fun validatePassword(): Boolean {
            val value = mBinding.PasswordEt.text.toString()
            if (value.isEmpty()) {
                showError(mBinding.PasswordTil, "Isi Password terlebih dahulu")
                return false
            } else if (value.length < 8) {
                showError(mBinding.PasswordTil, "Password Minimal 8 Karakter")
                return false
            }
            clearError(mBinding.PasswordTil)
            return true
        }

        private fun validateConfirmPassword(): Boolean {
            val value = mBinding.ConfirmPasswordEt.text.toString()
            if (value.isEmpty()) {
                showError(mBinding.ConfirmPasswordTil, "isi konfirmasi Password")
                return false
            }
//            else if (value.length < 8) {
//                showError(mBinding.ConfirmPasswordTil, "Password konfirmasi Minimal 8 Karakter")
//                return false
//            }
            clearError(mBinding.ConfirmPasswordTil)
            return true
        }

        private fun validatePasswordAndConfirmPassword(): Boolean {
            val password = mBinding.PasswordEt.text.toString()
            val confirmPassword = mBinding.ConfirmPasswordEt.text.toString()
            if (password != confirmPassword) {
                showError(mBinding.ConfirmPasswordTil, "Password tidak sesuai")
                return false
            }
            clearError(mBinding.ConfirmPasswordTil)
            return true
        }

        override fun onClick(view: View?) {
            // Handle button clicks or other UI interactions here
        }

        override fun onFocusChange(view: View?, hasFocus: Boolean) {
            if (view != null) {
                when (view.id) {
                    R.id.fullNameEt -> {
                        if (hasFocus && mBinding.fullNameTil.isErrorEnabled) {
                            clearError(mBinding.fullNameTil)
                        } else {
                            validateFullName()
                        }
                    }

                    R.id.EmailEt -> {
                        if (hasFocus && mBinding.EmailTil.isErrorEnabled) {
                            clearError(mBinding.EmailTil)
                        } else {
                            validateEmail()
                        }
                    }

                    R.id.PasswordEt -> {
                        if (hasFocus && mBinding.PasswordTil.isErrorEnabled) {
                            clearError(mBinding.PasswordTil)
                        } else {
                            if (validatePassword() && mBinding.ConfirmPasswordEt.text!!.isNotEmpty() &&
                                validateConfirmPassword() && validatePasswordAndConfirmPassword()
                            ) {
                                if (mBinding.ConfirmPasswordTil.isErrorEnabled) {
                                    clearError(mBinding.ConfirmPasswordTil)
                                }
                                mBinding.ConfirmPasswordTil.apply {
                                    setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                    setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }

                    R.id.ConfirmPasswordEt -> {
                        if (hasFocus && mBinding.ConfirmPasswordTil.isErrorEnabled) {
                            clearError(mBinding.ConfirmPasswordTil)
                        } else {
                            if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()) {
                                if (mBinding.ConfirmPasswordTil.isErrorEnabled) {
                                    clearError(mBinding.ConfirmPasswordTil)
                                }
                                mBinding.ConfirmPasswordTil.apply {
                                    setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                    setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }
                }
            }
        }

        override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
            // Handle key events, e.g., Enter key
            return false
        }
    }

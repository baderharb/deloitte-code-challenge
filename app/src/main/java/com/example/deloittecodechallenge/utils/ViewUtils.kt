@file:JvmName("ViewUtils")

package com.example.deloittecodechallenge.utils

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.deloittecodechallenge.R
import com.google.android.material.textfield.TextInputEditText

fun AppCompatButton.enableButton() {
    post {
        this.isClickable = true
        this.isFocusable = true
        this.isEnabled = true
        this.setTextColor(
            ContextCompat.getColor(context, R.color.white)
        )
        this.setBackgroundResource(R.drawable.shape_btn_corner_blue_4)
    }
}

fun AppCompatButton.disableButton() {
    post {
        this.isClickable = false
        this.isFocusable = false
        this.isEnabled = false
        this.setTextColor(
            ContextCompat.getColor(context, R.color.main)
        )
        this.setBackgroundResource(R.drawable.shape_btn_corner_light_grey_4)
    }
}

inline fun EditText.afterTextChanged(crossinline callback: (text: CharSequence) -> Unit) {
    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {
            callback(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // No impl
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // No impl
        }
    }
    addTextChangedListener(textWatcher)
}

fun TextInputEditText.checkPhoneNumber(): String {
    if (this.text?.toString()?.startsWith("0") == true) {
        this.setMaxLength(10)
        if (this.text.toString().length > 1)
            if (this.text?.toString()?.get(1) ?: "" == '0') {
                this.setText(
                    this.text.toString()
                        .substring(1, this.text?.toString()?.length ?: 0)
                )
                this.setSelection(
                    this.text?.toString()?.length ?: 0
                )
            }
    } else {
        this.setMaxLength(10)
    }
    return this.text.toString()
}

fun TextInputEditText.checkNationalID(): String {
    if (this.text?.toString()?.startsWith("1") == true) {
        this.setMaxLength(14)
        if (this.text.toString().length > 1)
            if (this.text?.toString()?.get(1) ?: "" == '1') {
                this.setText(
                    this.text.toString()
                        .substring(1, this.text?.toString()?.length ?: 0)
                )
                this.setSelection(
                    this.text?.toString()?.length ?: 0
                )
            }
    } else {
        this.setMaxLength(14)
    }
    return this.text.toString()
}

fun TextInputEditText.setMaxLength(maxLength: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(maxLength)
    filters = filterArray
}
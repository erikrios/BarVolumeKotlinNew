package com.erikriosetiawan.barvolumekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    private val outStateKey = "EM1519Love"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLength = findViewById(R.id.edt_length)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            tvResult.text = savedInstanceState.getString(outStateKey)
        }
    }

    override fun onClick(v: View?) {
        val length = edtLength.text.toString().trim()
        val width = edtWidth.text.toString().trim()
        val height = edtHeight.text.toString().trim()

        var isEmptyFields = false
        var isInvalidNumber = false

        if (TextUtils.isEmpty(length)) {
            edtLength.error = getString(R.string.empty_field_error_message)
            isEmptyFields = true
        }

        if (TextUtils.isEmpty(width)) {
            edtWidth.error = getString(R.string.empty_field_error_message)
            isEmptyFields = true
        }

        if (TextUtils.isEmpty(height)) {
            edtHeight.error = getString(R.string.empty_field_error_message)
            isEmptyFields = true
        }

        if (toDouble(length) == null) {
            edtLength.error = getString(R.string.invalid_number_error_message)
            isInvalidNumber = true
        }

        if (toDouble(width) == null) {
            edtWidth.error = getString(R.string.invalid_number_error_message)
            isInvalidNumber = true
        }

        if (toDouble(height) == null) {
            edtHeight.error = getString(R.string.invalid_number_error_message)
            isInvalidNumber = true
        }

        if (!isEmptyFields && !isInvalidNumber) {
            val barCalculate = BarCalculate()
            barCalculate.length = length.toDouble()
            barCalculate.width = width.toDouble()
            barCalculate.height = height.toDouble()

            tvResult.text = barCalculate.calculate().toString()
        }
    }

    private fun toDouble(str: String): Double {
        try {
            return str.toDouble()
        } catch (e: NumberFormatException) {
            return 0.0
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString(outStateKey, tvResult.text.toString().trim())
    }
}
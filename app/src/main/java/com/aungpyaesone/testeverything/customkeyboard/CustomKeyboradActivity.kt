package com.aungpyaesone.testeverything.customkeyboard

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.aungpyaesone.testeverything.R
import kotlinx.android.synthetic.main.activity_custom_keyborad.*

class CustomKeyboradActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_keyborad)

        etInputText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        etInputText.setTextIsSelectable(true)
     //   etInputText.requestFocus()

        val ic = etInputText.onCreateInputConnection(EditorInfo())
        ic?.let { myKeyboard.setInputConnection(it) }
        val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(etInputText.windowToken,0)
        etInputText.setOnClickListener {
        //    val view = LayoutInflater.from(this).inflate(R.layout.custom_keyboard_layout,this,true);
            myKeyboard.visibility = View.VISIBLE
        }
    }




}

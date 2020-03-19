package com.aungpyaesone.testeverything.customkeyboard

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import android.widget.Toast
import com.aungpyaesone.testeverything.R
import kotlinx.android.synthetic.main.activity_custom_keyborad.view.*
import kotlinx.android.synthetic.main.custom_keyboard_layout.view.*


class MyKeyboard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener
{
    val mContext = context
    val attrs = attrs
    private val keyValues = SparseArray<String>()
   // private val inputConnection = InputConnection
   private var inputConnection: InputConnection? = null

    init {
        LayoutInflater.from(mContext).inflate(R.layout.custom_keyboard_layout,this,true)
        btnOne.setOnClickListener(this)
        btnTwo.setOnClickListener(this)
        btnThree.setOnClickListener(this)
        btnFour.setOnClickListener(this)
        btnFive.setOnClickListener(this)
        btnSix.setOnClickListener(this)
        btnSeven.setOnClickListener(this)
        btnEight.setOnClickListener(this)
        btnNine.setOnClickListener(this)
        btnZero.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnClose.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        setUpButton()
    }

    private fun setUpButton(){
        keyValues.put(R.id.btnOne,resources.getString(R.string.one))
        keyValues.put(R.id.btnTwo,resources.getString(R.string.two))
        keyValues.put(R.id.btnThree,resources.getString(R.string.three))
        keyValues.put(R.id.btnFour,resources.getString(R.string.four))
        keyValues.put(R.id.btnFive,resources.getString(R.string.five))
        keyValues.put(R.id.btnSix,resources.getString(R.string.six))
        keyValues.put(R.id.btnSeven,resources.getString(R.string.seven))
        keyValues.put(R.id.btnEight,resources.getString(R.string.eight))
        keyValues.put(R.id.btnNine,resources.getString(R.string.nine))
        keyValues.put(R.id.btnZero,resources.getString(R.string.zero))
    }

    override fun onClick(v: View?) {
        if(inputConnection == null){
            return
        }
        when(v?.id){
            R.id.btnDelete ->{
                val selectedText = inputConnection?.getSelectedText(0)
                if(TextUtils.isEmpty(selectedText))
                {
                    inputConnection?.deleteSurroundingText(1,0)
                }else{
                    inputConnection?.commitText("",1)
                }
            }
            R.id.btnClose ->{
                myKeyboard.visibility = View.GONE
            }
            else ->{
            val value: String? = v?.id?.let { keyValues.get(it) }
            inputConnection?.commitText(value,1)
        }
        }
    }

    fun showData(){

    }
    fun setInputConnection(ic:InputConnection){
        inputConnection = ic
    }
}
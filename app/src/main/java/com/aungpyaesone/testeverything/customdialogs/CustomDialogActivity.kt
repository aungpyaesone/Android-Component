package com.aungpyaesone.testeverything.customdialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.aungpyaesone.testeverything.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_custom_dialog.*
import kotlinx.android.synthetic.main.custom_dialog_layout.view.*


class CustomDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_dialog)

        btncustomDialog.setOnClickListener {
            showCustomDialog()
        }

    }

    fun showCustomDialog(){
        val alertDialog = AlertDialog.Builder(this).create()
        val inflater:LayoutInflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_layout,null)
        dialogView.buttonSubmit.setOnClickListener {
            if(dialogView.etHomescore.text.isNullOrEmpty() && dialogView.etAwayScore.text.isNullOrEmpty()){
                showSnackbar("Pls fill result")
            }else{
                showSnackbar("Submit Success")
                alertDialog.dismiss()
            }
        }

        dialogView.imgResult.setOnClickListener{
            showSnackbar("It is image view")
        }

       dialogView.buttonCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.setView(dialogView)
        alertDialog.show()

    }

    fun showSnackbar(message:String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }
}

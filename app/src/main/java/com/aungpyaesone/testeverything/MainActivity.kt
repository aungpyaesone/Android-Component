package com.aungpyaesone.testeverything

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig
import uk.co.deanwild.materialshowcaseview.shape.Shape


class MainActivity : AppCompatActivity() {

    companion object{

        const val SHOWCASE_ID = "showcase"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        shwoCaseconfig()
    }

    fun callShowCase(){
        MaterialShowcaseView.Builder(this)
            .setTarget(mButton)
            .setDismissText("GOT IT")
            .setContentText("This is some amazing feature you should know about")
            .setDelay(200) // optional but starting animations immediately in onCreate can make them choppy
            .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
            .show()
    }

    fun shwoCaseconfig(){
        val config = ShowcaseConfig()
        config.delay = 500
        config.renderOverNavigationBar = true
        config.maskColor = R.color.colorAccent

        val sequence: MaterialShowcaseSequence = MaterialShowcaseSequence(this, SHOWCASE_ID)
        sequence.setConfig(config)
        sequence.addSequenceItem(mButton,"Hello Good Morning","GOT IT")
        sequence.addSequenceItem(btnTwo,
            "This is button two", "GOT IT")
        sequence.addSequenceItem(btnThree,
            "This is button three", "GOT IT")
        sequence.addSequenceItem(btnFour,
            "This is button three", "GOT IT")

        sequence.start()
    }
}

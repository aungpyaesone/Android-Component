package com.aungpyaesone.testeverything

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.jakewharton.threetenabp.AndroidThreeTen
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MyTest : Application(){

}
fun main(args: Array<String>){
    //changeStringToDate()

    jinKaung()
}

@RequiresApi(Build.VERSION_CODES.N)
fun calculateDate(){
   // val dateOne = SimpleDateFormat("dd/MM/yyyy").parse("11/03/2020")
  //  val dateTwo = SimpleDateFormat("dd/MM/yyyy").parse("11/03/2025")
}

@RequiresApi(Build.VERSION_CODES.O)
fun changeStringToDate(){
    var result = false
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val currentDate = LocalDate.now()
    val date = "11/03/2020";
    val localDate = LocalDate.parse(date, formatter)
    localDate?.let {
     //   println(it.toString())
        println(it.year.toString())

    }
    if(currentDate>localDate){
        println("Big")
    }
    if(currentDate<localDate){
        println("Small")
    }
    if(currentDate==localDate){
        println("Equal")
    }

}

fun jinKaung(){
    val localDatetime = org.threeten.bp.LocalDate.now()
    val formatter = org.threeten.bp.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = "11/03/2020"
    val mydate = org.threeten.bp.LocalDate.parse(date,formatter)
    mydate?.let {
        println(mydate.toString())
    }
}


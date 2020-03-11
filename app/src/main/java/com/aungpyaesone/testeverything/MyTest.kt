package com.aungpyaesone.testeverything

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>){
    changeStringToDate()
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

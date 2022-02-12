package com.example.simplecalculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecalculator.model.Number
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivityViewModel:ViewModel() {

    val numberLiveData: LiveData<String> get() = mutableNumber
    private val mutableNumber = MutableLiveData<String>().apply { postValue("") }

    val resultLiveData: LiveData<Number> get() = mutableResult
    private val mutableResult = MutableLiveData<Number>()

    fun evaluateExpression(string: String, clear: Boolean) {
        if(clear){
            mutableNumber.value = ""
        }else{
            mutableNumber.value += string
        }

//
//        if(clear) {
//            builder.append(string)
//            Number(builder.toString())
//            Log.d("cek number clear true", builder.toString())
//        } else {
//            builder.append(text)
//            builder.append(string)
//            Number(builder.toString())
//            Log.d("cek number clear false", builder.toString())
//        }
//
//        mutableNumber.value = if(clear) {
//            builder.append(string)
//            Number(builder.toString())
//        } else {
//            builder.append(text)
//            builder.append(string)
//            Number(builder.toString())
//        }
    }

    fun calculateEquals(number: Number){
        val expression = ExpressionBuilder(number.result).build()
        val result = expression.evaluate()
        val longResult = result.toLong()

        mutableResult.value = if (result == longResult.toDouble()) {
            Number(longResult.toString())
        } else {
            Number(result.toString())
        }
    }

}
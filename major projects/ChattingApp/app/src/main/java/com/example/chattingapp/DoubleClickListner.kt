package com.example.chattingapp

import android.view.View

abstract class DoubleClickListner:View.OnClickListener {
    private  var lastClickTime:Long =0
    companion object {
        private const val  DOUBLE_CLICK_TIME_DELTA = 300
    }

     override fun onClick(v:View?) {
         val clickTime  = System.currentTimeMillis()
         if(clickTime-lastClickTime < DOUBLE_CLICK_TIME_DELTA){
             onDoubleCLick(v)
         }
         lastClickTime = clickTime
    }

    abstract fun onDoubleCLick(v:View?)
}

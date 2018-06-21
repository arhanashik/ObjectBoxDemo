package com.blackspider

import android.app.Application
import com.blackspider.objectboxdemo.data.local.note.MyObjectBox
import io.objectbox.BoxStore

/*
*  ****************************************************************************
*  * Created by : Arhan Ashik on 6/20/2018 at 3:55 PM.
*  * Email : ashik.pstu.cse@gmail.com
*  * 
*  * Last edited by : Arhan Ashik on 6/20/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class AppController: Application() {
    lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(this).build()
    }
}
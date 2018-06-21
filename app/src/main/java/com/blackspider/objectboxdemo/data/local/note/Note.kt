package com.blackspider.objectboxdemo.data.local.note

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/*
*  ****************************************************************************
*  * Created by : Arhan Ashik on 6/19/2018 at 5:29 PM.
*  * Email : ashik.pstu.cse@gmail.com
*  * 
*  * Last edited by : Arhan Ashik on 6/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

@Entity
data class Note (@Id var id: Long = 0,
                 val title: String,
                 val note: String,
                 val date: Long)
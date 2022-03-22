package com.example.harman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.EditText
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var data:String
        data=intent.extras?.getString("input").toString()

        lateinit var t:TextView
        t=findViewById(R.id.tv)
        t.text=data



    }


    fun contactHandler(view: View) {
        when(view.id) {
            R.id.set->{   setContact() }
            R.id.stalram->{ createAlarm("studytime",10,0)}
        }


    }

    private fun setContact() {
        var etext: EditText
        etext = findViewById(R.id.contact)
        var str = etext.text.toString()
        var resIntent = Intent()
        resIntent.putExtra("result", str)
        setResult(RESULT_OK, resIntent)
        finish()
    }
    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
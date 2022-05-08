package com.example.hangouts.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hangouts.R
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.ui.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel : MainActivityViewModel by viewModels()

    private lateinit var smsBroadcastReceiver: SMSBroadcastReceiver



    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                && (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
                && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS), REQUEST_CODE_SMS_PERMISSION)
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PERMISSION)
            } else {
                requestSmsPermission()
                requestCallPermission()
            }
        } else {
            Toast.makeText(applicationContext, "Not permission", Toast.LENGTH_SHORT).show()
        }

//        requestCallPermission()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.min0 -> {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#017E86")))
//                this.setTheme(R.style.NewTheme)
                true
            }
            R.id.min1 -> {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00A991")))
//                this.setTheme(R.style.Theme_Hangouts)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRestart() {
        super.onRestart()
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        val time = sh.getLong("Time", 1L)
        if (time > 0) {
            val newTime = (System.currentTimeMillis() - time) / 1000
            Toast.makeText(this, printBackgroundText(newTime), Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("ResourceType")
    private fun printBackgroundText(time : Long) : String {
        val s1 = getString(R.string.upsent)
        val s2 = getString(R.string.second)
        return s1 + time + s2
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        myEdit.putLong("Time", System.currentTimeMillis())
        myEdit.apply()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_SMS_PERMISSION) {
            if (permissions[0] == Manifest.permission.SEND_SMS && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && permissions[1] == Manifest.permission.RECEIVE_SMS && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                requestSmsPermission()
            } else {
                Toast.makeText(this, "App need grant permission!", Toast.LENGTH_LONG).show()
            }
        }
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (permissions[0] == Manifest.permission.CALL_PHONE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestCallPermission()
            } else {
                Toast.makeText(this, "App need grant permission!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun readContact() {

        val cols = listOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()

        val rs = this.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        while (rs?.moveToNext()!!) {
            val contact = Contact(
                id = rs.getString(2).toLong(),
                phoneNumber = rs.getString(1),
                firstName = rs.getString(0),
                lastName = "",
                email = null,
                address = null,
                avatar = null
            )
            viewModel.addContactList(contact)
        }
    }

    private fun requestSmsPermission() {
        val permission = Manifest.permission.RECEIVE_SMS
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CODE_SMS_PERMISSION)
        }
    }

    private fun requestCallPermission() {
        val permission = Manifest.permission.CALL_PHONE
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CALL_PERMISSION)
        }
    }

    companion object {
        private const val REQUEST_CODE_SMS_PERMISSION = 1
        private const val REQUEST_CALL_PERMISSION = 2
    }

}
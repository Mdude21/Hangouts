package com.example.hangouts.ui.activity

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
import com.example.hangouts.R
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.ui.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CONTACTS
                    ), REQUEST_CODE_PERMISSION
                )
            }
        } else {
            Toast.makeText(applicationContext, "Not permission", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.min0 -> {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#017E86")))
                true
            }
            R.id.min1 -> {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00A991")))
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

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (permissions[0] == Manifest.permission.SEND_SMS && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && permissions[1] == Manifest.permission.RECEIVE_SMS && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && permissions[2] == Manifest.permission.CALL_PHONE && grantResults[2] == PackageManager.PERMISSION_GRANTED
                && permissions[3] == Manifest.permission.READ_CONTACTS && grantResults[3] == PackageManager.PERMISSION_GRANTED
            ) {
                readContact()
            } else {
                Toast.makeText(this, getString(R.string.perrmisions), Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun printBackgroundText(time: Long): String {
        val s1 = getString(R.string.upsent)
        val s2 = getString(R.string.second)
        return s1 + time + s2
    }

    //read contacts from contactList
    private fun readContact() {

        val cols = listOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()

        val rs = this.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

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

    companion object {
        private const val REQUEST_CODE_PERMISSION = 1
    }

}
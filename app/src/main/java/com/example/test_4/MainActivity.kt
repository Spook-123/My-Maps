package com.example.test_4

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_4.adapter.MapsAdapter
import com.example.test_4.data.ContainerViewModel
import com.example.test_4.data.Place
import com.example.test_4.maps.CreateMapActivity
import com.example.test_4.maps.DisplayMapActivity
import kotlinx.android.synthetic.main.activity_main.*


const val EXTRA_MAP_TITLE = "EXTRA_MAP_TITLE"
const val EXTRA_MAP_CREATOR = "EXTRA_MAP_CREATOR"
const val PLACE = "PLACE"
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), MapsAdapter.OnClicked {

    private lateinit var mapsViewModel:ContainerViewModel
    private lateinit var place:MutableList<Place>
    private lateinit var adapter:MapsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "My Maps"

        adapter = MapsAdapter(this,this)
        rvMap.layoutManager = LinearLayoutManager(this)
        rvMap.adapter = adapter

        mapsViewModel = ViewModelProvider(this).get(ContainerViewModel::class.java)

        mapsViewModel.readAllData.observe(this, Observer {
            place = it.toMutableList()
            adapter.updateData(it)
        })

        fabAdd.setOnClickListener {
            showAlertDialog()
        }

    }

    private fun showAlertDialog() {
        val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_map,null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Map Title")
            .setView(mapFormView)
            .setNegativeButton("Cancel",null)
            .setPositiveButton("OK",null)
            .show()

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val title = mapFormView.findViewById<EditText>(R.id.etMainTitle).text.toString()
            val creatorName = mapFormView.findViewById<EditText>(R.id.etCreator).text.toString()
            if(title.trim().isEmpty() && creatorName.trim().isEmpty()) {
                Toast.makeText(this,"Map must have non-empty title and Creator name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(this@MainActivity,CreateMapActivity::class.java)
            intent.putExtra(EXTRA_MAP_TITLE,title)
            intent.putExtra(EXTRA_MAP_CREATOR,creatorName)
            startActivity(intent)
            dialog.dismiss()


        }
    }

    override fun onItemDelete(maps: Place) {
        mapsViewModel.delete(maps)
        Toast.makeText(this,"Deleted!",Toast.LENGTH_LONG).show()
    }

    override fun onItemClicked(position: Int) {
        val intent = Intent(this@MainActivity,DisplayMapActivity::class.java)
        intent.putExtra(PLACE,place[position])
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
    }



}
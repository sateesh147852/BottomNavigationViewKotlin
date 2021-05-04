package com.testkotlin

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var backTime: Long = 0
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeToolBar()
        initialize()
    }

    private fun initializeToolBar() {
        setSupportActionBar(binding.commonToolbar.toolBar)
        val toolbar = binding.commonToolbar.toolBar
        val tvTitle: AppCompatTextView = binding.commonToolbar.tvTitle
        tvTitle.text = "Home"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initialize() {
        bottomNavigationView = binding.btNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        loadFragment(FragmentA.fragmentA,0)
    }

    override fun onBackPressed() {
        if (backTime + 2000 > System.currentTimeMillis())
            finish()
        else
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
        backTime = System.currentTimeMillis()
    }


    private fun loadFragment(fragment: Fragment, position : Int){
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when(position){

            0 -> {
                if (FragmentB.fragmentB.isAdded)
                    fragmentTransaction.hide(FragmentB.fragmentB)
                if (FragmentC.fragmentC.isAdded)
                    fragmentTransaction.hide(FragmentC.fragmentC)
                if (FragmentD.fragmentD.isAdded)
                    fragmentTransaction.hide(FragmentD.fragmentD)
                if (FragmentA.fragmentA.isAdded)
                    fragmentTransaction.show(FragmentA.fragmentA)
                else
                    fragmentTransaction.add(R.id.flContainer, FragmentA.fragmentA)
            }

            1 -> {
                if (FragmentA.fragmentA.isAdded)
                    fragmentTransaction.hide(FragmentA.fragmentA)
                if (FragmentC.fragmentC.isAdded)
                    fragmentTransaction.hide(FragmentC.fragmentC)
                if (FragmentD.fragmentD.isAdded)
                    fragmentTransaction.hide(FragmentD.fragmentD)
                if (FragmentB.fragmentB.isAdded)
                    fragmentTransaction.show(FragmentB.fragmentB)
                else
                    fragmentTransaction.add(R.id.flContainer, FragmentB.fragmentB)

            }

            2 -> {
                if (FragmentA.fragmentA.isAdded)
                    fragmentTransaction.hide(FragmentA.fragmentA)
                if (FragmentB.fragmentB.isAdded)
                    fragmentTransaction.hide(FragmentB.fragmentB)
                if (FragmentD.fragmentD.isAdded)
                    fragmentTransaction.hide(FragmentD.fragmentD)
                if (FragmentC.fragmentC.isAdded)
                    fragmentTransaction.show(FragmentC.fragmentC)
                else
                    fragmentTransaction.add(R.id.flContainer, FragmentC.fragmentC)
            }

            3 -> {
                if (FragmentA.fragmentA.isAdded)
                    fragmentTransaction.hide(FragmentA.fragmentA)
                if (FragmentB.fragmentB.isAdded)
                    fragmentTransaction.hide(FragmentB.fragmentB)
                if (FragmentC.fragmentC.isAdded)
                    fragmentTransaction.hide(FragmentC.fragmentC)
                if (FragmentD.fragmentD.isAdded)
                    fragmentTransaction.show(FragmentD.fragmentD)
                else
                    fragmentTransaction.add(R.id.flContainer, FragmentD.fragmentD)
            }
        }
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.home ->{
                loadFragment(FragmentA.fragmentA, 0)
            }

            R.id.history ->{
                loadFragment(FragmentB.fragmentB, 1)
            }

            R.id.details ->{
                loadFragment(FragmentC.fragmentC, 2)
            }

            R.id.profile ->{
                loadFragment(FragmentD.fragmentD, 3)
            }
        }
        return true
    }
}
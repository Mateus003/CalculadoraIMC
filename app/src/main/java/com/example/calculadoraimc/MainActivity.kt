package com.example.calculadoraimc

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonCalculate.setOnClickListener(this)

        binding.buttonReset.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_calculate){
            val wight = binding.editKg.text.toString()
            val height = binding.editCm.text.toString()

            if (wight != "" && height != ""){
                if (wight!="" && height!="0"){
                    val result = (wight.toFloat()/(height.toFloat()*height.toFloat()))*10000
                    binding.resultCalculate.text = "Seu IMC é %.2f".format(result)
                    binding.typeImc.text = "${categoryIMC(result)}"
                }else{
                    Toast.makeText(this, R.string.height_0,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }else{
                Toast.makeText(this, R.string.fill_correct,
                    Toast.LENGTH_LONG).show()
            }

        }else if(view.id == R.id.button_reset){
            binding.editKg.text.clear()
            binding.editCm.text.clear()
            binding.resultCalculate.text = getString(R.string.data_information)
            binding.typeImc.text= ""

        }

    }

    private fun categoryIMC(result:Float):String{
        return if (result < 18.5) {
           getString(R.string.low_weight)
        } else if (result in 18.6..24.9) {
            getString(R.string.normal_weight)
        } else if (result in 25.0..29.9) {
            getString(R.string.overweight)
        } else if (result in 30.0..34.9) {
           getString(R.string.obesity_i)
        } else if (result in 35.0..39.9) {
            getString(R.string.obesity_ii)
        } else if (result > 45.0) {
            getString(R.string.obesity_iii)
        } else {
            ""
        }


    }
}
package com.felipe.fsnotepad

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.felipe.fsnotepad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = AnnotationPreference(applicationContext)
        retrieveAnnotation(preference)
        addSaveBtn(preference)
    }

    private fun retrieveAnnotation(preference: AnnotationPreference) {
        val annotationCurrent = preference.getAnnotation()

        if (!annotationCurrent.isNullOrBlank()) {
            binding.annotationContentIcl.annotationEtxt.setText(annotationCurrent)
        }
    }

    private fun addSaveBtn(preference: AnnotationPreference) {
        val saveBtn = binding.saveBtn

        saveBtn.setOnClickListener {
            val annotationEditText = binding.annotationContentIcl.annotationEtxt.text.toString()
            val isValid = validateAnnotation(annotationEditText)
            if (isValid) {
                preference.saveAnnotation(annotationEditText)
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateAnnotation(annotation: String?): Boolean {
        if (annotation.isNullOrBlank()) {
            Toast.makeText(this, "Add some annotation!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
package com.example.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cadastro.databinding.ActivityMainBinding
import com.example.cadastro.domain.enums.Estado
import com.example.cadastro.domain.enums.Sexo
import com.example.cadastro.domain.models.Formulario

class MainActivity : AppCompatActivity() {
    private lateinit var amb: ActivityMainBinding
    private lateinit var nomeCompletoEt: EditText
    private lateinit var telefoneEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var listaEmailCb: CheckBox
    private lateinit var sexoRg: RadioGroup
    private lateinit var masculinoRb: RadioButton
    private lateinit var femininoRb: RadioButton
    private lateinit var cidadeEt: EditText
    private lateinit var estadoSp: Spinner
    private lateinit var cleanFormBtn: Button
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(amb.root)

        nomeCompletoEt = amb.nomeCompletoEt
        telefoneEt = amb.telefoneEt
        emailEt = amb.emailEt
        listaEmailCb = amb.listaEmailCb
        sexoRg = amb.sexoRg
        masculinoRb = amb.masculinoRb
        femininoRb = amb.femininoRb
        cidadeEt = amb.cidadeEt
        estadoSp = amb.estadoSp
        cleanFormBtn = amb.cleanFormBtn
        saveBtn = amb.saveBtn

        masculinoRb.isChecked = true
        setupCleanFormBtnOnClickListener()
        setupSaveBtnOnClickListener()
    }

    private fun setupCleanFormBtnOnClickListener() {
        cleanFormBtn.setOnClickListener {
            cleanForms()
        }
    }

    private fun cleanForms() {
        nomeCompletoEt.text.clear()
        telefoneEt.text.clear()
        emailEt.text.clear()
        listaEmailCb.isChecked = false
        val radioButtonCount = sexoRg.childCount
        for (i in 0 until radioButtonCount) {
            val radioButton = sexoRg.getChildAt(i) as RadioButton
            radioButton.isChecked = false
        }
        masculinoRb.isChecked = true
        cidadeEt.text.clear()
        estadoSp.setSelection(0)
    }

    private fun setupSaveBtnOnClickListener() {
        saveBtn.setOnClickListener {
            val formulario = Formulario()
            formulario.nome = nomeCompletoEt.text.toString()
            formulario.telefone = telefoneEt.text.toString()
            formulario.email = emailEt.text.toString()
            formulario.listaEmails = listaEmailCb.isChecked
            formulario.sexo = getSexoEnum()
            formulario.cidade = cidadeEt.text.toString()
            println(estadoSp.selectedItem.toString())
            formulario.estado =
                Estado.values().find { it.nomeCompleto == estadoSp.selectedItem.toString() }!!

            showFormAnswers(formulario)
            cleanForms()
        }
    }

    private fun getSexoEnum(): Sexo {
        val selectedRadioButtonId = sexoRg.checkedRadioButtonId
        val selectedRadioButton = amb.root.findViewById<RadioButton>(selectedRadioButtonId)
        return if (selectedRadioButton == masculinoRb) Sexo.MASCULINO
        else Sexo.FEMININO
    }

    private fun showFormAnswers(formulario: Formulario) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val scrollView = ScrollView(this)
        val messageView = TextView(this)

        messageView.text = formulario.toString()
        messageView.setPadding(20, 20, 20, 20)
        messageView.textSize = 16f

        scrollView.addView(messageView)
        alertDialogBuilder.setTitle("Respostas")
        alertDialogBuilder.setView(scrollView)
        alertDialogBuilder.setPositiveButton("OK", null)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}
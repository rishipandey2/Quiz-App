package com.example.quizstuio

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizstuio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val question = arrayOf("What is the built-in database in Android Studio?",
        "What is the full form of APK in Android Development?",
        "In which year, first android was released bt Google?")

    private val options = arrayOf(arrayOf("MySql", "SQLite", "Firebase"), arrayOf("Application Programming Interface", "Android Programming InterFace", "Android Package information"), arrayOf("2010", "2006", "2008"))

    private val correctAns = arrayOf(1,0,2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.optionButton1.setOnClickListener {
            checkAnswer(0)
        }

        binding.optionButton2.setOnClickListener {
            checkAnswer(1)
        }

        binding.optionButton3.setOnClickListener {
            checkAnswer(2)
        }

        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
    }


    private fun correctButtonColor(buttonIndex:Int){
        when(buttonIndex){
            0 -> binding.optionButton1.setBackgroundColor(Color.GREEN)
            1 -> binding.optionButton2.setBackgroundColor(Color.GREEN)
            2 -> binding.optionButton3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColor(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.optionButton1.setBackgroundColor(Color.RED)
            0 -> binding.optionButton2.setBackgroundColor(Color.RED)
            0 -> binding.optionButton3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColor(){
        binding.optionButton1.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.optionButton2.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.optionButton3.setBackgroundColor(Color.rgb(50, 59, 96))

    }

    private fun showResult(){
        Toast.makeText(this, "Your score: $score out of ${question.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = question[currentQuestionIndex]
        binding.optionButton1.text = options[currentQuestionIndex][0]
        binding.optionButton2.text = options[currentQuestionIndex][1]
        binding.optionButton3.text = options[currentQuestionIndex][2]
        resetButtonColor()
    }

    private fun checkAnswer(selectedAnswerIndex:Int){
        val correctAnswerIndex = correctAns[currentQuestionIndex]

        if(selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColor(selectedAnswerIndex)
        }else{
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }
        if(currentQuestionIndex < question.size-1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        }else {
            showResult()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}
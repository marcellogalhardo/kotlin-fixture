package com.mgalhardo.fixture

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

@SuppressLint("NewApi")
class MainActivity : AppCompatActivity() {

    private val fixture = Fixture().apply {
        register {
            LocalDate.of(1990, 6, 28)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView)
        testFixture(textView)
    }

    private fun testFixture(textView: TextView) {
        textView.text = "Fixture Test"
        textView.setOnClickListener {
            val person = fixture.create<Person>().copy(age = 65)
            textView.text = "${person.name.firstName}.${person.birthday.month}.${person.birthday.year}"
        }
    }

}

data class Person(
    val id: PersonId,
    val name: PersonName,
    val age: Int,
    val birthday: LocalDate
)

data class PersonName(
    val firstName: String,
    val lastName: String
)

inline class PersonId(
    val id: Long
)



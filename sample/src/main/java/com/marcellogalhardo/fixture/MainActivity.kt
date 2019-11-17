package com.marcellogalhardo.fixture

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import kotlin.system.measureTimeMillis

@SuppressLint("NewApi")
class MainActivity : AppCompatActivity() {

    private val fixture = Fixture {
        register {
            LocalDate.of(1800, 2, 1)
        }
        register<AbstractClass> {
            object : AbstractClass() {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView)
        testFixture(textView)
    }

    private fun testFixture(textView: TextView) {
//        val person = Person(
//            id = PersonId(1),
//            name = PersonName(
//                firstName = "Marcello",
//                lastName = "Galhardo"
//            ),
//            age = 45,
//            birthday = LocalDate.now(),
//            objectAny = ObjectTest,
//            type = Type.One(),
//            myInterface = object : Interface {
//
//            },
//            abstractClass = object: AbstractClass() {
//
//            }
//        )

        textView.text = "Fixture Test"
        textView.setOnClickListener {
            var person: Person? = null
            val time = measureTimeMillis {
                person = fixture.create()
            }
            Toast.makeText(this, time.toString(), Toast.LENGTH_LONG).show()
            textView.text = "${person?.name?.firstName}.${person?.birthday?.month}.${person?.birthday?.year}"
        }
    }

}

data class Person(
    val id: Int,
    val name: PersonName,
    val age: Int,
    val birthday: LocalDate,
    val type: Type,
    val objectAny: ObjectTest,
    val myInterface: Interface,
    val abstractClass: AbstractClass
)

data class PersonName(
    val firstName: String,
    val lastName: String
)

sealed class Type {
    class One : Type()
}

interface Interface

abstract class AbstractClass

object ObjectTest

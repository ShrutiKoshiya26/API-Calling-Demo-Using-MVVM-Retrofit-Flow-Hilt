package com.practicaltask.shruutest.util

fun main() {

    var person1=Person("gg")
    var person2=Person("gg")
    println(person2 == person1)

}

class Person(var string: String) {
    var name="Shruti"
    var gender="Female"


    override fun toString(): String {
        return "$name $gender"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
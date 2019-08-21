package com.erikriosetiawan.barvolumekotlin

data class BarCalculate(var length: Double = 0.0, var width: Double = 0.0, var height: Double = 0.0) {

    fun calculate(): Double {
        return length * width * height
    }
}
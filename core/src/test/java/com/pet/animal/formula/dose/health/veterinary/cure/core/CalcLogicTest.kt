package com.pet.animal.formula.dose.health.veterinary.cure.core

import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.CalcInteractorImpl
import org.junit.Assert.*
import org.junit.Test


class CalcLogicTest {
    @Test
    fun createCalc(){
        val calcIntercator = CalcInteractorImpl()
        assertNotNull(calcIntercator)
    }

    @Test
    fun testSum(){
        val calcIntercator = CalcInteractorImpl()
        calcIntercator.setCommand(2)
        calcIntercator.setCommand(13)
        calcIntercator.setCommand(9)
        val result = calcIntercator.getCommandResultValue()
        assertEquals(result,11.0)
    }

    @Test
    fun testDecrease(){
        val calcIntercator = CalcInteractorImpl()
        calcIntercator.setCommand(7)
        calcIntercator.setCommand(12)
        calcIntercator.setCommand(2)
        val result = calcIntercator.getCommandResultValue()
        assertEquals(result,5.0)
    }

    @Test
    fun testMulti(){
        val calcIntercator = CalcInteractorImpl()
        calcIntercator.setCommand(3)
        calcIntercator.setCommand(15)
        calcIntercator.setCommand(4)
        calcIntercator.setCommand(15)
        calcIntercator.setCommand(1)
        calcIntercator.setCommand(1)
        val result = calcIntercator.getCommandResultValue()
        assertEquals(result,132.0)
    }

    @Test
    fun test2p2m2(){
        val calcIntercator = CalcInteractorImpl()
        calcIntercator.setCommand(2)
        calcIntercator.setCommand(13)
        calcIntercator.setCommand(2)
        calcIntercator.setCommand(15)
        calcIntercator.setCommand(2)

        val result = calcIntercator.getCommandResultValue()
        assertEquals(result,6.0)
    }

    @Test
    fun divideByZero()
    {
        val calcIntercator = CalcInteractorImpl()
        calcIntercator.setCommand(2)
        calcIntercator.setCommand(14)
        calcIntercator.setCommand(0)
        val result = calcIntercator.getCommandResultValue()
        assertNull(result)
    }
}
package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void soma(){
        Calculadora calc = new Calculadora();
        assertEquals(120, calc.soma(50,70));
    }

    @Test
    void subt(){
        Calculadora calc = new Calculadora();
        assertEquals(20, calc.subt(50,70));
    }

    @Test
    void div(){
        Calculadora calc = new Calculadora();
        assertEquals(1, calc.div(50,50));
    }

    @Test
    void mult(){
        Calculadora calc = new Calculadora();
        assertEquals(140, calc.mult(2,70));
    }

}
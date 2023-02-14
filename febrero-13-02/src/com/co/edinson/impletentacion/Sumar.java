package com.co.edinson.impletentacion;

import com.co.edinson.interfas.OperacionesInterfas;

public class Sumar implements OperacionesInterfas {
    @Override
    public double sum(double numOne, double numTwo) {
        return numOne + numTwo;
    }

    @Override
    public double subs(double numOne, double numTwo) {
        return numOne - numTwo;
    }

    @Override
    public double mul(double numOne, double numTwo) {
        return numOne * numTwo;
    }

    @Override
    public double div(double numOne, double numTwo) {
        return numOne / numTwo;
    }
}

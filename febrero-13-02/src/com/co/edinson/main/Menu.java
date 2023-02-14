package com.co.edinson.main;

import com.co.edinson.impletentacion.Sumar;

import java.util.Scanner;

public class Menu {

    public void MostrarMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("1. sumar");
        System.out.println("2. restar");
        System.out.println("3. multiplicar");
        System.out.println("4. dividir");
        Integer optional = sc.nextInt();

        System.out.println("ingrese numero 1: \n");
        double numOne = sc.nextDouble();

        System.out.println("ingrese numero 2: \n");
        double numTwo = sc.nextDouble();

        this.Operaciones(optional, numOne, numTwo);
    }

    public void Operaciones(int op, double a, double b) {
        Sumar sm = new Sumar();

        switch (op){
            case 1:
                System.out.println("suma es: " + sm.sum(a, b));

                break;
            case 2:
                System.out.println("resta es: " + sm.subs(a, b));

                break;
                case 3:
                System.out.println("multiplicacion es: " + sm.mul(a, b));

                break;
            case 4:
                System.out.println("division es: " + sm.div(a, b));
            default:

        }

    }
}

package org.iesalandalus.programacion.damas;

import org.iesalandalus.programacion.damas.modelo.Color;
import org.iesalandalus.programacion.damas.modelo.Direccion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {


        private Consola() {}

        public static void mostrarMenu() {
            System.out.println("=========MENU========");
            System.out.println("1. Crear dama por defecto");
            System.out.println("2. Crear dama eligiendo color");
            System.out.println("3. Mover dama");
            System.out.println("4. Salir");
        }


        public static int elegirOpcionMenu() {

            int opcion;

            do {

                System.out.print("Elija una opcion(1-4): ");
                opcion = Entrada.entero();

            } while (opcion < 1 || opcion > 4);

            return opcion;
        }

        public static Color elegirColor() {

            Color color = null;

            do {

                System.out.print("Elija el color (1. Blanco, 2. Negro): ");
                int opcionColor = Entrada.entero();

                if (opcionColor == 1) {

                    color = Color.BLANCO;

                } else if (opcionColor == 2) {

                    color = Color.NEGRO;
                }
            } while (color == null);

            return color;
        }

        public static void mostrarMenuDirecciones() {

            System.out.println("======Menu direcciones=====");
            System.out.println("1. NORESTE");
            System.out.println("2. SURESTE");
            System.out.println("3. SUROESTE");
            System.out.println("4. NOROESTE");
        }

    public static int elegirPasos() {


        int pasos;

        do {

            System.out.print("Introduzca el numero de pasos a dar (1 o mas): ");
            pasos = Entrada.entero();

        } while (pasos < 1);

        return pasos;
    }

        public static Direccion elegirDireccion() {

            Direccion direccion = null;
            boolean isOpcionCorrecta = false;
            do{
                System.out.print("Elija una direccion(1-4): ");

                int opcionDireccion = Entrada.entero();

                switch (opcionDireccion) {
                    case 1:
                        direccion = Direccion.NORESTE;
                        isOpcionCorrecta = true;
                    break;

                    case 2:
                        direccion = Direccion.SURESTE;
                        isOpcionCorrecta = true;
                        break;

                    case 3:
                        direccion = Direccion.SUROESTE;
                        isOpcionCorrecta = true;
                        break;

                    case 4:
                        direccion = Direccion.NOROESTE;
                        isOpcionCorrecta = true;
                        break;

                    default:
                        throw new IllegalArgumentException("Opción no válida");


                }
                }while(!isOpcionCorrecta);

            return direccion;
        }

        public static void despedirse() {
            System.out.println("Hasta la proxima");
        }
    }

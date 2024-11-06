package org.iesalandalus.programacion.damas;

import org.iesalandalus.programacion.damas.modelo.Dama;
import org.iesalandalus.programacion.damas.modelo.Color;
import org.iesalandalus.programacion.damas.modelo.Direccion;

import javax.naming.OperationNotSupportedException;

public class MainApp {

    private static Dama dama;

    public static void ejecutarOpcion(int opcion) throws OperationNotSupportedException {

        switch (opcion) {
            case 1:
                crearDamaDefecto();
                break;
            case 2:
                crearDamaColor();
                break;
            case 3:
                mover();
                break;
            case 4:
                Consola.despedirse();
                break;
        }
    }

    public static void crearDamaDefecto() {

        dama = new Dama();
        System.out.println("Se ha creado una dama por defecto: " + dama);
    }

    public static void crearDamaColor() {

        Color color = Consola.elegirColor();
        dama = new Dama(color);
        System.out.println("Se ha creado una dama con color: " + dama);
    }

    public static void mover() throws OperationNotSupportedException {

        if (dama == null) {
            System.out.println("Aun no se ha creado ninguna dama.");
            return;
        }

        Consola.mostrarMenuDirecciones();
        Direccion direccion = Consola.elegirDireccion();
        int pasos;
        if(dama.isEsDamaEspecial()){

            pasos =Consola.elegirPasos();
        }
        pasos = 1;
        dama.mover(direccion, pasos);  // Mover la dama
        System.out.println("La dama se ha movido  a: " + dama.getPosicion());
    }

    public static void mostrarDama() {
        if (dama != null) {
            System.out.println("La dama actual es: " + dama);
        } else {
            System.out.println("No se ha creado ninguna dama.");
        }
    }

    public static void main(String[] args) throws OperationNotSupportedException {

        int opcion;

        do {
            Consola.mostrarMenu();

            opcion = Consola.elegirOpcionMenu();

            ejecutarOpcion(opcion);

        } while (opcion != 4);  // Repetimos hasta salir
    }
}

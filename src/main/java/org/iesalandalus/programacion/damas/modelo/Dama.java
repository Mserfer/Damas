package org.iesalandalus.programacion.damas.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Random;

public class Dama {


    private Color color;
    private Posicion posicion;
    private boolean esDamaEspecial;

    // Constructor defecto : crea dama blanca en posicion aleatoria.
    public Dama() {

        this.color = Color.BLANCO;
        this.posicion = crearPosicionInicial(Color.BLANCO);
        this.esDamaEspecial = false;
    }

    // Constructor en posicion y color personalizados mientras que sean validos.
    public Dama(Color color) {

        setColor(color);
        this.posicion = crearPosicionInicial(color);
        this.esDamaEspecial = false;
    }

    public Color getColor() {

        return color;
    }

    public void setColor(Color color) {
        if (color == null) {

            throw new NullPointerException("ERROR: El color no puede ser nulo.");
        }

        this.color = color;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {

        if (posicion == null) {

            throw new NullPointerException("ERROR: La posición no puede ser nula.");
        }

        this.posicion = posicion;
    }

    public boolean isEsDamaEspecial() {

        return esDamaEspecial;
    }

    public void setEsDamaEspecial(boolean esDamaEspecial) {

        this.esDamaEspecial = esDamaEspecial;
    }

    // metodo para crear una dama en posicion aleatoria
    private Posicion crearPosicionInicial(Color color) {

        int fila;
        char columna;
        Random random = new Random();

        if (color == Color.BLANCO) {

            fila = random.nextInt(3) + 1; // random para filas 1, 2 o 3

        } else {

            fila = random.nextInt(3) + 6; // random para filas  6, 7 u 8
        }

        do {

            columna = (char) ('a' + random.nextInt(8));

        } while ((fila + columna) % 2 == 0); // la casilla es negra si la suma es impar

        return new Posicion(fila, columna);
    }

    // metodo para mover la dama.
    public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {

        if (direccion == null) {

            throw new NullPointerException("ERROR: La dirección no puede ser nula.");
        }
        if (pasos < 1) {

            throw new IllegalArgumentException("ERROR: El numero de pasos debe ser positivo.");
        }

        // Si la dama no es especial, solo puede moverse una casilla.
        if (!esDamaEspecial) {

            pasos = 1;

            // Movimiento no permitido para una dama no especial
            if ((color == Color.BLANCO && (direccion == Direccion.SURESTE || direccion == Direccion.SUROESTE)) ||
                    (color == Color.NEGRO && (direccion == Direccion.NORESTE || direccion == Direccion.NOROESTE))) {
                throw new OperationNotSupportedException("ERROR: Movimiento no permitido para una dama no especial.");
            }
        }

        // cambiamos posicion de la dama segun la direccion.

        int filaActualizada = posicion.getFila();
        char columnaActualizada = posicion.getColumna();

        switch (direccion) {
            case NORESTE:
                filaActualizada += pasos;
                columnaActualizada += pasos;
                break;

            case SURESTE:
                filaActualizada -= pasos;
                columnaActualizada += pasos;
                break;

            case SUROESTE:
                filaActualizada -= pasos;
                columnaActualizada -= pasos;
                break;

            case NOROESTE:
                filaActualizada += pasos;
                columnaActualizada -= pasos;
                break;

            default:
                throw new OperationNotSupportedException("ERROR: Dirección no válida.");
        }

        // Ver si el movimiento esta dentro de los limites del tablero.
        if (filaActualizada < 1 || filaActualizada > 8 || columnaActualizada < 'a' || columnaActualizada > 'h') {
            throw new OperationNotSupportedException("ERROR: Movimiento fuera del tablero.");
        }

        // Cambiar la posicion de la dama.
        this.posicion = new Posicion(filaActualizada, columnaActualizada);

        // Si la dama llega a la ultima fila, se convierte en dama especial.
        if ((color == Color.BLANCO && filaActualizada == 8) || (color == Color.NEGRO && filaActualizada == 1)) {

            this.esDamaEspecial = true;
        }
    }

    // toString
    @Override
    public String toString() {

        return "color=" + color + ", posicion=(" + posicion + (esDamaEspecial ? " (especial)" : ")");
    }
}

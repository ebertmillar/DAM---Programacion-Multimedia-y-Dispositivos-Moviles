/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividad1;

import com.jme3.math.Vector3f;

/**
 *
 * @author Ebert
 */
public class Bolos {

    //Separación entre los bolos 
    private final static float SEPARACION = 0.305f;
    private final static float FILA_2 = -(7 + Bolos.SEPARACION);
    private final static float FILA_3 = -(7 + (Bolos.SEPARACION * 2));
    private final static float FILA_4 = -(7 + (Bolos.SEPARACION * 3));

    public final static Vector3f[] POSICION = {
        //Fila 1
        new Vector3f(0, 0, -7),
        //Fila 2
        new Vector3f((-Bolos.SEPARACION / 2), 0, Bolos.FILA_2),
        new Vector3f((Bolos.SEPARACION / 2), 0, Bolos.FILA_2),
        //Fila 3
        new Vector3f(-Bolos.SEPARACION, 0, Bolos.FILA_3),
        new Vector3f(0, 0, Bolos.FILA_3),
        new Vector3f(Bolos.SEPARACION, 0, Bolos.FILA_3),
        //Fila 4
        new Vector3f(-Bolos.SEPARACION * 1.5f, 0, Bolos.FILA_4),
        new Vector3f((-Bolos.SEPARACION / 2), 0, Bolos.FILA_4),
        new Vector3f((Bolos.SEPARACION / 2), 0, Bolos.FILA_4),
        new Vector3f(Bolos.SEPARACION * 1.5f, 0, Bolos.FILA_4)
    };

    // Constante que representa la masa de algún objeto en kilogramos.
    public final static float MASA = 1.6f;

// Constante que representa la altura de algún objeto en metros.
    public final static float ALTURA = 0.38f;

// Constante que representa el diámetro de algún objeto en metros.
    public final static float DIAMETRO = 0.12f;

}

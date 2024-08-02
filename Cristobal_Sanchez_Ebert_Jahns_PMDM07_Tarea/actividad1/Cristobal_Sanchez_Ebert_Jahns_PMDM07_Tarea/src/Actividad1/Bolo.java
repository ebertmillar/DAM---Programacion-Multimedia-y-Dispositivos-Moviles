/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividad1;


import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Cylinder;

/**
 *
 * @author Ebert
 */
public class Bolo extends Geometry {

    /**
     * Constructor de la clase Bolo.
     * 
     * @param assetManager El AssetManager para cargar recursos.
     */
    public Bolo(AssetManager assetManager) {
        
        // Llama al constructor de la clase Geometry para crear un cilindro con los parámetros dados
        super("Cylinder", new Cylinder(32, 32, Bolos.DIAMETRO, Bolos.ALTURA, true));

        // Rota el cilindro creado para que esté orientado correctamente
        this.rotate((float) Math.PI / 2.0f, 0, 0);
        
        // Crea un nuevo material para el bolo
        Material material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        // Habilita el uso de colores del material
        material.setBoolean("UseMaterialColors", true);
        // Establece el color ambiental del material como verde
        material.setColor("Ambient", ColorRGBA.Green);
        // Establece el color difuso del material como verde
        material.setColor("Diffuse", ColorRGBA.Green);
        // Establece el color especular del material como amarillo
        material.setColor("Specular", ColorRGBA.Yellow);
        // Establece la cantidad de brillo del material
        material.setFloat("Shininess", 1);
        
        // Asigna el material creado al objeto Bolo
        this.setMaterial(material);
    }
}
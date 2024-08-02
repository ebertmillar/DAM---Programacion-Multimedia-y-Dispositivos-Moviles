using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI; // Necesario para manipular objetos UI

public class InteraccionObjeto : MonoBehaviour
{
    public contadorObjetos contadorObjetos;
    
    // Start se llama antes del primer fotograma
    void Start()
    {
        contadorObjetos = GameObject.FindGameObjectWithTag("contadorObjetos").GetComponent<contadorObjetos>();  
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Player"))
        {
            contadorObjetos.cantidadObjetos--; // Disminuye la cantidad de objetos en 1 cuando el jugador interactúa con el objeto
            Destroy(gameObject); // Destruye el objeto con el que el jugador interactuó

            
        }
    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class InicializarScript : MonoBehaviour
{
    public contadorObjetos contadorObjetos;
    private GameObject jugador;
    
    [SerializeField] int minutos, segundos;
    [SerializeField] Text tiempo;
    [SerializeField] Text mensaje;
    [SerializeField] Text textoCantidadObjetos;

    private float restante;
    private bool timerOn;

    private void Awake()
    {
        restante = (minutos * 60) + segundos;
        timerOn = true;

        contadorObjetos = GameObject.FindGameObjectWithTag("Player").GetComponent<contadorObjetos>();
	
    }
	
    private void Start()
    {
        jugador = GameObject.FindGameObjectWithTag("usuario");
        jugador.GetComponent<Text>().text = PlayerPrefs.GetString("Jugador");
    }


   


     void Update()
    {
	textoCantidadObjetos.text = "Restan: " + contadorObjetos.cantidadObjetos;
	
	if(contadorObjetos.cantidadObjetos == 0)
        {
            timerOn = false;
	    mensaje.gameObject.SetActive(true);
	    mensaje.text = "LO CONSEGUISTE!!!";
            mensaje.color = Color.red;

	}

        if(timerOn && restante >= 0)
        {
            restante -= Time.deltaTime; 
            int tempMin = Mathf.FloorToInt(restante / 60);
            int tempSeg = Mathf.FloorToInt(restante % 60);

            tiempo.text = string.Format("{00:00}:{01:00}", tempMin, tempSeg);
        } 
else{ 
	
	if(contadorObjetos.cantidadObjetos > 0){
	       timerOn = false;
               mensaje.text = "GAME OVER";
               mensaje.color = Color.yellow;
	       tiempo.text = "00:00";
               
           }
}


    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class IniciarJuego : MonoBehaviour
{

    public InputField inputNombre;

    public void iniciar()
    {
        PlayerPrefs.SetString("Jugador", inputNombre.text);
        SceneManager.LoadScene("EscenaJuego");
    }

}



using UnityEngine;
using UnityEngine.SceneManagement;
using UnityStandardAssets.CrossPlatformInput;
using UnityEngine.UI; // Necesario para usar UI.Image

[RequireComponent(typeof (Image))] // Cambiado a Image
public class ForcedReset : MonoBehaviour
{
    private void Update()
    {
        // Si hemos forzado un reinicio ...
        if (CrossPlatformInputManager.GetButtonDown("ResetObject"))
        {
            // ... recargar la escena
            SceneManager.LoadScene(SceneManager.GetSceneAt(0).name);
        }
    }
}
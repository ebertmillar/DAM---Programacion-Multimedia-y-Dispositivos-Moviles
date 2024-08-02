/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Actividad1;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 *
 * @author Ebert
 */
public class Main extends SimpleApplication
        implements AnalogListener, ActionListener {

    // Estado de física de Bullet
    private BulletAppState bulletAppState;

    // Materiales para la bola y los bolos
    private Material bola_mat, piedras_mat;

    // Último tiempo de lanzamiento de la bola
    private long ultimoTiempoDeLanzamiento = 0;

    // Retraso entre lanzamientos de la bola en milisegundos
    private static final long RETRASO_DE_LANZAMIENTO = 3000;

    // Control de física para la bola
    private RigidBodyControl bola_fis;

    // Geometrías para la bola y los bolos
    private Geometry bola_geo;
    private Bolo[] bolo; // Colección de 10 bolos

    // Velocidad inicial de la bola
    private float velocidadDeLaBola;

    // Nodo de audio para reproducir cuando se lance la bola
    private AudioNode audioBola;

    // Método principal de ejecución del programa
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    // Método de inicialización de la aplicación
    @Override
    public void simpleInitApp() {

        // Inicialización de BulletAppState
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);

        // Velocidad de la bola obtenida de la clase Bola
        velocidadDeLaBola = Bola.VELOCIDAD;

        // Crear materiales
        crearMateriales();

        // Creamos el suelo
        crearSuelo();

        // Creamos la pared
        crearPared();

        // Crear la luz
        crearLuz();

        // Configuración de la cámara
        cam.setLocation(new Vector3f(0, 4f, 6f));
        cam.lookAt(new Vector3f(0, 2, 0), Vector3f.UNIT_Y);

        // Color de fondo
        viewPort.setBackgroundColor(new ColorRGBA(0f, 0f, 0.2f, 0));

        // Lanzamiento inicial de la bola
        hazBola();

        //Crear los bolos
        crearBolos();

        //Implementamos las acciones
        initInput();

        //Establecemos los sonidos del juego
        setSonidos();

    }

    // Método para crear el suelo en la escena
    private void crearSuelo() {

        // Crear una caja que represente el suelo con dimensiones 5x0.1x10
        Box suelo = new Box(Vector3f.ZERO, 5f, 0.1f, 10f);

        // Ajustar las coordenadas de textura para evitar deformaciones
        suelo.scaleTextureCoordinates(new Vector2f(6, 3));

        // Crear una geometría para representar el suelo
        Geometry suelo_geo = new Geometry("Floor", suelo);

        // Asignar el material del suelo
        suelo_geo.setMaterial(piedras_mat);

        // Establecer la posición local del suelo
        suelo_geo.setLocalTranslation(0, -0.1f, 0);

        // Adjuntar la geometría del suelo al grafo de la escena
        rootNode.attachChild(suelo_geo);

        // Crear un control físico para el suelo con masa 0 (estático)
        RigidBodyControl suelo_fis = new RigidBodyControl(0.0f);

        // Asociar el control físico a la geometría del suelo
        suelo_geo.addControl(suelo_fis);

        // Agregar el control físico del suelo al espacio de físicas gestionado por Bullet
        bulletAppState.getPhysicsSpace().add(suelo_fis);

        // Establecer el modo de sombra del suelo para recibir sombras
        suelo_geo.setShadowMode(ShadowMode.Receive);
    }

    // Método para crear la pared en la escena
    private void crearPared() {
        // Crear una caja que represente la pared con dimensiones 5x0.1x10
        Box pared = new Box(Vector3f.ZERO, 5f, 0.1f, 10f);

        // Ajustar las coordenadas de textura para evitar deformaciones
        pared.scaleTextureCoordinates(new Vector2f(6, 3));

        // Crear una geometría para representar la pared
        Geometry pared_geo = new Geometry("Wall", pared);

        // Asignar el material de la pared
        pared_geo.setMaterial(piedras_mat);

        // Establecer la posición local de la pared
        pared_geo.setLocalTranslation(0, -0.1f, -10);

        // Rotar la pared 90 grados en el eje X para ponerla en posición vertical
        pared_geo.rotate((float) Math.PI / 2.0f, 0f, 0);

        // Adjuntar la geometría de la pared al grafo de la escena
        rootNode.attachChild(pared_geo);

        // Crear un control físico para la pared con masa 0 (estático)
        RigidBodyControl pared_fis = new RigidBodyControl(0.0f);

        // Asociar el control físico a la geometría de la pared
        pared_geo.addControl(pared_fis);

        // Agregar el control físico de la pared al espacio de físicas gestionado por Bullet
        bulletAppState.getPhysicsSpace().add(pared_fis);

        // Establecer el modo de sombra de la pared para recibir sombras
        pared_geo.setShadowMode(ShadowMode.Receive);
    }

    // Método para crear la luz en la escena
    private void crearLuz() {
        // Crear una luz direccional
        DirectionalLight luz = new DirectionalLight();

        // Establecer el color y la intensidad de la luz
        luz.setColor(ColorRGBA.White.mult(0.8f));

        // Establecer la dirección de la luz (proveniente de la parte superior derecha detrás del jugador)
        luz.setDirection(new Vector3f(-1, -1, -1).normalizeLocal());

        // Agregar la luz al grafo de la escena
        rootNode.addLight(luz);

        // Configurar sombras para la luz direccional
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, 1024, 3);
        dlsr.setLight(luz);
        viewPort.addProcessor(dlsr);
    }

    // Método para crear los materiales utilizados en la escena
    private void crearMateriales() {
        // Material para el suelo
        piedras_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        // Cargar una textura predefinida para el suelo y hacerla repetir por su superficie
        TextureKey key = new TextureKey("Textures/Terrain/Pond/Pond.jpg");
        key.setGenerateMips(true);
        Texture textura = assetManager.loadTexture(key);
        textura.setWrap(WrapMode.Repeat);
        piedras_mat.setTexture("ColorMap", textura);

        // Material para la bola
        bola_mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        // Configurar el material de la bola
        bola_mat.setBoolean("UseMaterialColors", true);
        bola_mat.setColor("Ambient", ColorRGBA.Cyan);
        bola_mat.setColor("Diffuse", ColorRGBA.Cyan);
        bola_mat.setColor("Specular", ColorRGBA.White);
        bola_mat.setFloat("Shininess", 1);
    }

    public void hazBola() {
        // Verificar si ya existe una bola y eliminarla antes de crear una nueva
        if (bola_geo != null && rootNode.hasChild(bola_geo)) {
            rootNode.detachChild(bola_geo);
            bulletAppState.getPhysicsSpace().remove(bola_fis);
        }

        // Crear una esfera de 40 centímetros de diámetro
        Sphere esfera = new Sphere(32, 32, Bola.DIAMETRO);

        // Asociar la forma a una geometría nueva
        bola_geo = new Geometry("bola", esfera);
        // asignarle el material
        bola_geo.setMaterial(bola_mat);

        // añadirla al grafo de escena
        rootNode.attachChild(bola_geo);

        // la colocamos en la posición de la cámara
        bola_geo.setLocalTranslation(cam.getLocation());

        // Creamos el objeto de control físico asociado a la bola con un peso
        // de 1Kg.
        bola_fis = new RigidBodyControl(Bola.MASA);
        // Asociar la geometría de la bola al control físico
        bola_geo.addControl(bola_fis);
        // Añadirla al motor de física
        bulletAppState.getPhysicsSpace().add(bola_fis);

        //La bola emite sombra
        bola_geo.setShadowMode(RenderQueue.ShadowMode.Cast);
    }

    /**
     * añadir las acciones al inputManager
     */
    private void initInput() {

        //Crear la acción "Lanzar"
        inputManager.addMapping("Lanzar",
                new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

        //Creamos la acción "DesactivarGravedad" para desactivar la gravedad
        inputManager.addMapping("DesactivarGravedad", new KeyTrigger(KeyInput.KEY_G));

        //Añadimos el mapping a la escucha
        //Se puede procesar en los métodos onAnalog y onAction
        //todo depende de qué método implemente la acción
        inputManager.addListener(this, "Lanzar");
        inputManager.addListener(this, "DesactivarGravedad");
    }

    /**
     * Método que implementa los sonidos del juego.
     */
    private void setSonidos() {
        //Añadimos un sonido de ambiente al juego
        AudioNode audio = new AudioNode(assetManager,
                "Sound/Environment/River.ogg", AudioData.DataType.Buffer);
        audio.setVolume(0.3f);
        audio.setPositional(false);
        audio.setLooping(true);
        audio.play();
        rootNode.attachChild(audio);

        //Sonido al lanzar la bola
        audioBola = new AudioNode(assetManager,
                "Sound/Effects/Gun.wav", AudioData.DataType.Buffer);
        audioBola.setVolume(15);
        rootNode.attachChild(audioBola);

        bola_geo.setShadowMode(ShadowMode.CastAndReceive);
    }

    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    // Método para crear los bolos en la escena
    private void crearBolos() {
        // Inicializar el arreglo de bolos y de controles físicos de los bolos
        bolo = new Bolo[10];
        RigidBodyControl[] bolo_physc = new RigidBodyControl[10];

        // Iterar para crear y configurar cada bolo
        for (int i = 0; i < 10; i++) {
            // Crear un nuevo objeto Bolo
            bolo[i] = new Bolo(assetManager);

            // Agregar el bolo al grafo de la escena
            rootNode.attachChild(bolo[i]);

            // Colocar el bolo en su posición
            bolo[i].setLocalTranslation(Bolos.POSICION[i]);

            // Crear un control físico para el bolo con la masa especificada en la clase Bolos
            bolo_physc[i] = new RigidBodyControl(Bolos.MASA);
            // Agregar el control físico al bolo
            bolo[i].addControl(bolo_physc[i]);
            // Agregar el control físico al espacio de físicas gestionado por Bullet
            bulletAppState.getPhysicsSpace().add(bolo_physc[i]);

            // Configurar el modo de sombra de los bolos para que emitan y reciban sombras
            bolo[i].setShadowMode(ShadowMode.CastAndReceive);
        }
    }

    /**
     * Método para procesar las acciones.
     *
     * @param accion nombre de la acción
     * @param value valor dado por la presión de la tecla
     * @param tpf tiempo en segundos desde al última actualización
     */
    public void onAnalog(String accion, float value, float tpf) {

        if (accion.equals("Lanzar")) {
            long tiempoActual = System.currentTimeMillis();
            if (tiempoActual - ultimoTiempoDeLanzamiento >= RETRASO_DE_LANZAMIENTO) {
                ultimoTiempoDeLanzamiento = tiempoActual;
                audioBola.play();

                float velocidadLanzamiento = 18f; // Velocidad de lanzamiento deseada
                bola_fis.setLinearVelocity(cam.getDirection().mult(velocidadLanzamiento));
            }
        }

    }

    
    public void onAction(String accion, boolean pulsado, float tpf) {
        if (accion.equals("Lanzar") && pulsado) {
            // Crear una nueva bola cuando se active la acción "Lanzar"
            hazBola();
        } else if (accion.equals("DesactivarGravedad") && pulsado) {
            if (bulletAppState.isEnabled()) {
                bulletAppState.setEnabled(false);
                System.out.println("Gravedad desactivada");
            } else {
                bulletAppState.setEnabled(true);
                System.out.println("Gravedad activada");
            }
        }
    }

}

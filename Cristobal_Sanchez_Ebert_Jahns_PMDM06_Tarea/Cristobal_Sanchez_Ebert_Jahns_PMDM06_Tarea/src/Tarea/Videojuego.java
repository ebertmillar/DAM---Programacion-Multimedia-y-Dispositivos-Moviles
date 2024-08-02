/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.*;

/**
 *
 * @author Ebert
 */
public class Videojuego extends BasicGame {

    // Tilemap
    private TiledMap mapa;

// Estado del jugador
    private float jugadorX, jugadorY;
    private SpriteSheet cuadros;
    private SpriteSheet cuadrosRobot;
    private SpriteSheet explosion;
    private Animation jugador;
    private Animation jugadorArriba;
    private Animation jugadorDerecha;
    private Animation jugadorAbajo;
    private Animation jugadorIzquierda;
    private Animation animacionColision;
    private boolean jugadorVivo;

// Estado de los robots
    private Animation[] robot;
    private Animation robotArriba;
    private Animation robotDerecha;
    private Animation robotAbajo;
    private Animation robotIzquierda;
    private Animation robotArriba2;
    private Animation robotDerecha2;
    private Animation robotIzquierda2;
    private Animation robotAbajo2;
    private float[] robotX, robotY;
    private boolean[] robotVivo;
    private int numeroRobotsVivos;

// Escritura de cadenas
    private UnicodeFont fuente;

// Contador de tiempo
    private long tiempo;
    private long tiempoFinal;

// Sonidos
    private Sound winSound;
    private Sound lostSound;
    private Sound boomSound;
    private boolean sonidoGanarReproduciendose = false;
    private boolean sonidoPerderReproduciendose = false;
    //Musica 
    private Music musicaFondo;

// Variables de estado del juego
    private boolean juegoGanado = false;
    private boolean juegoPerdido = false;

    public Videojuego(String name) {
        super(name);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        //System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
        try {
            AppGameContainer container = new AppGameContainer(new Videojuego("PMDM06 - Tarea"));
            container.setDisplayMode(640, 480, false);
            container.setTargetFrameRate(60);
            container.setVSync(true);
            container.setShowFPS(false);
            container.setUpdateOnlyWhenVisible(false);
            container.start();
        } catch (SlickException e) {
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {

        // Cargar el mapa
        mapa = new TiledMap("data/mapa.tmx", "data");

        // Inicializar la música de fondo
        musicaFondo = new Music("data/musica_de_fondo.wav");
        // Reproducir la música de fondo en bucle
        musicaFondo.loop();

        // Cargar los sonidos
        winSound = new Sound("data/ganaste.wav");
        lostSound = new Sound("data/perdiste.wav");
        boomSound = new Sound("data/disparo.wav");

        // Cargar las spritesheets
        cuadros = new SpriteSheet("data/heroe.png", 24, 32);
        cuadrosRobot = new SpriteSheet("data/robot.png", 24, 32);
        explosion = new SpriteSheet("data/heroe12.png", 24, 32);

        // Cargar animaciones del jugador
        jugadorArriba = new Animation(cuadros, 0, 0, 2, 0, true, 150, false);
        jugadorDerecha = new Animation(cuadros, 0, 1, 2, 1, true, 150, false);
        jugadorAbajo = new Animation(cuadros, 0, 2, 2, 2, true, 150, false);
        jugadorIzquierda = new Animation(cuadros, 0, 3, 2, 3, true, 150, false);
        jugador = jugadorAbajo;

        // Cargar animaciones del robot
        robotArriba = new Animation(cuadrosRobot, 0, 0, 2, 0, true, 150, true);
        robotDerecha = new Animation(cuadrosRobot, 0, 1, 2, 1, true, 150, true);
        robotAbajo = new Animation(cuadrosRobot, 0, 2, 2, 2, true, 150, true);
        robotIzquierda = new Animation(cuadrosRobot, 0, 3, 2, 3, true, 150, true);

        // Cargar  Nuevas animaciones
        robotArriba2 = new Animation(cuadrosRobot, 0, 0, 2, 0, true, 150, true);
        robotDerecha2 = new Animation(cuadrosRobot, 0, 1, 2, 1, true, 150, true);
        robotAbajo2 = new Animation(cuadrosRobot, 0, 2, 2, 2, true, 150, true);
        robotIzquierda2 = new Animation(cuadrosRobot, 0, 3, 2, 3, true, 150, true);

        // Cargar la animación de colisión
        animacionColision = new Animation(explosion, 0, 0, 2, 0, true, 150, true);

        // Estado inicial del jugador
        jugadorX = 320;
        jugadorY = 240;
        jugadorVivo = true;

        // Estado inicial de los robots
        robot = new Animation[8];
        robotX = new float[]{20, 596, 20, 596, 596, 20, 288, 288};
        robotY = new float[]{84, 84, 428, 428, 200, 200, 84, 428};
        robotVivo = new boolean[]{true, true, true, true, true, true, true, true};
        numeroRobotsVivos = 8;

        // Colocar los robots mirando al jugador en las 4 esquinas
        robot = new Animation[]{robotDerecha, robotIzquierda, robotDerecha,
            robotIzquierda, robotDerecha, robotIzquierda, robotDerecha, robotIzquierda};

        // Cargar el tipo de letra y los símbolos necesarios
        fuente = new UnicodeFont("data/tuffy.ttf", 28, false, false);
        fuente.addAsciiGlyphs();
        fuente.addGlyphs("áéíóúÁÉÍÓÚñÑ¡¿");
        fuente.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        fuente.loadGlyphs();

        // Comenzar la cuenta de tiempo
        tiempo = System.currentTimeMillis();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input entrada = gc.getInput();

        // Si el jugador está muerto o no quedan robots, no actualizar nada
        if (!jugadorVivo || numeroRobotsVivos == 0) {
            return;
        }

        // Movimiento del jugador
        if (entrada.isKeyDown(Input.KEY_DOWN)) {   // Tecla abajo
            jugadorY += delta * 0.1f;
            // Limitar el movimiento dentro del contenedor
            if (jugadorY > (gc.getHeight() - jugador.getHeight())) {
                jugadorY = (gc.getHeight() - jugador.getHeight());
            }
            jugador = jugadorAbajo;
            jugador.update(delta);
        }
        if (entrada.isKeyDown(Input.KEY_UP)) { // Tecla arriba
            jugadorY -= delta * 0.1f;
            // Limitar el movimiento dentro del contenedor
            if (jugadorY < 32) {
                jugadorY = 32;
            }
            jugador = jugadorArriba;
            jugador.update(delta);
        }
        if (entrada.isKeyDown(Input.KEY_RIGHT)) {  // Tecla derecha
            jugadorX += delta * 0.1f;
            // Limitar el movimiento dentro del contenedor
            if (jugadorX > (gc.getWidth() - jugador.getWidth())) {
                jugadorX = (gc.getWidth() - jugador.getWidth());
            }
            jugador = jugadorDerecha;
            jugador.update(delta);
        }
        if (entrada.isKeyDown(Input.KEY_LEFT)) {   // Tecla izquierda
            jugadorX -= delta * 0.1f;
            // Limitar el movimiento dentro del contenedor
            if (jugadorX < 0) {
                jugadorX = 0;
            }
            jugador = jugadorIzquierda;
            jugador.update(delta);
        }

        // Detectar colisión con los robots
        Rectangle jugadorRect = new Rectangle((int) (jugadorX + jugador.getWidth() * 0.1), (int) (jugadorY + jugador.getHeight() * 0.1), (int) (jugador.getWidth() * 0.8), (int) (jugador.getHeight() * 0.8));
        for (int i = 0; i < 8; i++) {
            if (robotVivo[i]) {
                // Calcular la distancia entre el jugador y el robot
                float dx = jugadorX - robotX[i];
                float dy = jugadorY - robotY[i];
                float distancia = (float) Math.sqrt(dx * dx + dy * dy);

                // Definir la distancia mínima para la colisión (10 píxeles)
                float distanciaMinima = 10;

                // Si la distancia entre el jugador y el robot es menor que la distancia mínima,
                // consideramos que están lo suficientemente cerca como para estar en colisión
                if (distancia < distanciaMinima) {
                    // Si hay colisión entre el jugador y un robot, el jugador muere
                    jugadorVivo = false;
                    lostSound.play();
                    sonidoPerderReproduciendose = true;
                    juegoPerdido = true;
                    tiempoFinal = System.currentTimeMillis();
                    return; // Salir del método update() ya que el jugador está muerto
                }
            }
        }

        // Actualizar posición de los robots para que persigan al jugador
        for (int i = 0; i < 8; i++) {
            if (robotVivo[i]) {
                // Calcular la dirección hacia la que debe moverse el robot para alcanzar al jugador
                float dx = jugadorX - robotX[i];
                float dy = jugadorY - robotY[i];
                float distancia = (float) Math.sqrt(dx * dx + dy * dy);
                // Normalizar el vector de dirección para que el robot se mueva a una velocidad constante
                float velocidad = 0.1f;
                dx /= distancia;
                dy /= distancia;
                // Mover el robot en la dirección calculada
                robotX[i] += dx * velocidad * delta;
                robotY[i] += dy * velocidad * delta;
            }

            // Verificar colisiones entre robots
            for (int j = i + 1; j < 8; j++) {
                if (robotVivo[i] && robotVivo[j]) {
                    // Calcular la distancia entre los centros de los robots
                    float distanciaX = Math.abs(robotX[i] - robotX[j]);
                    float distanciaY = Math.abs(robotY[i] - robotY[j]);
                    double distanciaCentros = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

                    // Definir la distancia mínima para la colisión entre robots (ajustar según sea necesario)
                    double distanciaMinima = 2; // Por ejemplo, 2 píxeles

                    // Si los robots están lo suficientemente cerca, colisión detectada
                    if (distanciaCentros < distanciaMinima) {
                        // Reproducir sonido de colisión
                        boomSound.play();
                        //Subir Volumen de la musica de fondo despues de la explosion
                        musicaFondo.setVolume(0.8f);

                        // Desactivar los robots que colisionaron
                        robotVivo[i] = false;
                        robotVivo[j] = false;

                        // Guardar el tiempo en que ocurrió la colisión
                        tiempoFinal = System.currentTimeMillis();

                        // Reducir el número de robots vivos
                        numeroRobotsVivos -= 2;

                    }
                }
            }
        }

        // Comprobar colisiones adicionales entre robots
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (robotVivo[i] && robotVivo[j]) {
                    // Calcular la distancia entre los centros de los robots
                    float distanciaX = Math.abs(robotX[i] - robotX[j]);
                    float distanciaY = Math.abs(robotY[i] - robotY[j]);
                    double distanciaCentros = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

                    // Definir la distancia mínima para la colisión entre robots (ajustar según sea necesario)
                    double distanciaMinima = 2; // Por ejemplo, 2 píxeles

                    // Si los robots están lo suficientemente cerca, colisión detectada
                    if (distanciaCentros < distanciaMinima) {
                        //Reducir volumen de la musica de fondo mientras el robot explota
                        musicaFondo.setVolume(0.05f);
                        // Cambiar la animación de los robots a la animación de colisión
                        robot[i] = animacionColision;
                        robot[j] = animacionColision;
                    }
                }
            }
        }

        // Reproducir sonido de ganar si se han eliminado todos los robots
        if (numeroRobotsVivos == 0 && !sonidoGanarReproduciendose) {
            winSound.play();
            sonidoGanarReproduciendose = true;
            juegoGanado = true; // Indicar que el juego ha sido ganado
            tiempoFinal = System.currentTimeMillis();
        }

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        // Dibujar el mapa
        mapa.render(0, 0);

        // Dibujar al jugador
        jugador.draw(jugadorX, jugadorY);

        // Dibujar a los robots si están vivos
        for (int i = 0; i < 8; i++) {
            if (robotVivo[i]) {
                robot[i].draw(robotX[i], robotY[i]);
            }
        }

        // Dibujar el número de robots restantes
        fuente.drawString(400, 10, "Quedan " + numeroRobotsVivos + " robots");

        // Mostrar mensajes de fin de juego si corresponde
        if (juegoGanado) {
            String mensajeGanador = "¡Has ganado!";
            fuente.drawString((gc.getWidth() - fuente.getWidth(mensajeGanador)) / 2, (gc.getHeight() - fuente.getHeight(mensajeGanador)) / 2, mensajeGanador, Color.yellow);
        } else if (juegoPerdido) {
            String mensajePerdedor = "Fin de juego";
            fuente.drawString((gc.getWidth() - fuente.getWidth(mensajePerdedor)) / 2, (gc.getHeight() - fuente.getHeight(mensajePerdedor)) / 2, mensajePerdedor, Color.red);
        }

        // Mostrar el tiempo transcurrido si el juego ha terminado
        if (juegoGanado || juegoPerdido) {
            // Detener la música de fondo cuando el juego se cierre
            musicaFondo.stop();
            // Calcular la duración del juego
            long duracionJuego = tiempoFinal - tiempo;
            // Dibujar el tiempo transcurrido
            fuente.drawString(40, 10, "Tiempo: " + duracionJuego / 1000);
        } else {
            // Mostrar el tiempo transcurrido si el juego sigue en progreso
            fuente.drawString(40, 10, "Tiempo: " + (System.currentTimeMillis() - tiempo) / 1000);
        }
    }

}

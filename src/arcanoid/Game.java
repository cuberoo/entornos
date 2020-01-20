package arcanoid;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Game extends Canvas {
	JFrame Ventana = new JFrame ("Arcanoid");
	
	private  int JFRAME_WIDTH=500;
	private  int JFRAME_HEIGHT=700;
	
	private int FPS = 60;
	private long usedTime;


	private static Game instance= null;
	
	List<Ladrillos> ladrillos=new ArrayList<Ladrillos>();
	//List<Nave> naves = new ArrayList<Nave>();
	Pelota bola = new Pelota(0,100,25,25);
	Nave naves = new Nave(160,500,65,15);
	//Ladrillos ladrillo = new Ladrillos(10,20,18,15);
	public Game () {
		
		// Obtengo referencia al panel principal de la ventana
				JPanel panel = (JPanel) Ventana.getContentPane();
				// Establezco una plantilla en el panel, para poder incorporar el Canvas
				panel.setLayout(new BorderLayout());
				// Agrego el Canvas al panel
				panel.add(this, BorderLayout.CENTER);
				// Dimensiono la ventana
				Ventana.setBounds(0,0, JFRAME_WIDTH, JFRAME_HEIGHT);
				// Muestro la ventana en pantalla
				Ventana.setVisible(true);
				Ventana.addWindowListener( new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						cerrarAplicacion();
					}
				});
				// Con ignoreRepaint le decimos al JFrame que no debe repintarse cuando el Sistema Operativo se lo indique,
				// nosotros nos ocupamos totalmente del refresco de la pantalla
				Ventana.setIgnoreRepaint(true);
				// El foco de Windows ir� al Canvas, para que directamente podamos controlar este juego a trav�s del teclado
				this.requestFocus();
			
	}
	
	private void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(Ventana,"¿Desea cerrar la aplicacion?","Salir de la aplicacion",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
					
	public void World() {
		bola.x += bola.velDeLaX;
		bola.y += bola.velDeLaY; 
		if(bola.x < 0 || bola.x > this.getWidth() - this.bola.ancho) {
			bola.velDeLaX =  -bola.velDeLaX;
		}
		if(bola.y< 0 || bola.y > this.getHeight()-this.bola.alto) {
			bola.velDeLaY =  -bola.velDeLaY;
		}
	}
				
	public void bloques() {
		int cX =25;
		int cY=10;
		for (int i = 0; i < 4; i++) {
			for(int j=0; j< 4;j++) {
				ladrillos.add(new Ladrillos(cX,cY ,18,15));
				cX+=20;
			}
			cX=25;
			cY+=25;
		}
	}
	
	public void fps() {
	// El bucle se ejecutar� mientras el objeto Canvas sea visible
		bloques();
		while (this.isVisible()) {
			long startTime = System.currentTimeMillis(); // Tomo el tiempo, en millis, antes de crear el siguiente Frame del juego
			// actualizo y pinto la escena
			World();
			repaint();
			// Calculo el tiempo que se ha tardado en la ejecuci�n
			usedTime = System.currentTimeMillis()-startTime;
			// Hago que el bucle pare una serie de millis, antes de generar el siguiente FPS
			// El c�lculo hecho "duerme" el proceso para no generar m�s de los SPEED_FPS que se haya espec�ficado
			try { 
				int millisToSleep = (int) (1000/FPS - usedTime);
				if (millisToSleep < 0) {
					millisToSleep = 0;
				}
				Thread.sleep(millisToSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			  }
		}
	}
					
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, this.JFRAME_WIDTH, this.JFRAME_HEIGHT);
			bola.paint(g);	
			g.drawImage(loadImage("../resources/Nave.png"),naves.x,naves.y,this);
			for(Ladrillos ladrillo:ladrillos) {
				ladrillo.paint(g);
			}
		}
		
		private BufferedImage loadImage(String resourceName) {
			// Para localizar el archivo se utiliza un objeto de tipo URL
			URL url=null;
			
			// Se intenta cargar el recurso del disco duro, si no se pudiera se capturar� la excepci�n y se
			// mostrar� un mensaje en pantalla
			try {
				url = getClass().getResource(resourceName);
				return ImageIO.read(url);
			} catch (Exception e) {
				// Aqu� dentro capturamos y tratamos el error que pueda haberse ocasionado
				System.out.println("No se pudo cargar la imagen " + resourceName +" de "+url);
				System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
				System.exit(0); // Fin del programa
			}
			return null; // S�lo se llegar� a esta l�nea si no se ha podido cargar el recurso correctamente
		}
					
		public static Game getInstance() {
			if (instance == null) {
				instance = new Game();
			}
			return instance;
		}
					
}

package arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.imageio.ImageIO;

public class Nave extends Objeto  {

	public Nave(int x, int y, int alto, int ancho) {
		super(x, y, alto, ancho);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
	//	g.drawImage(loadImage("../resources/nave_down.png"),x,y,this);
		
	}
	private BufferedImage loadImage(String resourceName) {
		// Para localizar el archivo se utiliza un objeto de tipo URL
		URL url=null;
		
		// Se intenta cargar el recurso del disco duro, si no se pudiera se capturará la excepción y se
		// mostrará un mensaje en pantalla
		try {
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
		} catch (Exception e) {
			// Aquí dentro capturamos y tratamos el error que pueda haberse ocasionado
			System.out.println("No se pudo cargar la imagen " + resourceName +" de "+url);
			System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0); // Fin del programa
		}
		return null; // Sólo se llegará a esta línea si no se ha podido cargar el recurso correctamente
	}
	

}

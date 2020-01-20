package arcanoid;

import java.awt.Graphics;

public abstract class Objeto {
	public int x;
	public int y;
	public int alto;
	public int ancho;
	
	public Objeto(int x, int y, int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
	}
	
	public abstract void paint (Graphics g);
}

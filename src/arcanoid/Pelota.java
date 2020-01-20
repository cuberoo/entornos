package arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Pelota extends Objeto{
	public int velDeLaX= 10;
	public int velDeLaY= 10;
	
	public Pelota(int x, int y, int alto, int ancho) {
		super(x, y, alto, ancho);
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(this.x, this.y, this.alto, this.ancho);
	}



}

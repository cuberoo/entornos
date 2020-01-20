package arcanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillos extends Objeto{

	public Ladrillos(int x, int y, int alto, int ancho) {
		super(x, y, alto, ancho);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, alto,ancho);
		g.setColor(Color.black);
		g.drawRect(x, y, alto, ancho);
		
	}

}

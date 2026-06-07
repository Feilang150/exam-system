package mylib;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JLabel;

public class PINLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random r = new Random();
	private String randStr;

	public String getRandStr() {
		return randStr;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		int width = this.getWidth();
		int height = this.getHeight();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		int randnum = r.nextInt(8999) + 1000;
		randStr = String.valueOf(randnum);
		// System.out.println(randStr);
		g.setColor(Color.blue);
		g.setFont(new Font("", Font.PLAIN, height));
		g.drawString(randStr, 0, height);
		for (int i = 0; i < 100; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
	}
}

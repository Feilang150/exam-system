package mylib;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {// 定义图片面板类
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public void setImage(Image image) {
		this.image = image;
	}

	public ImagePanel(Image image) {
		this.setImage(image);
		this.setOpaque(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}
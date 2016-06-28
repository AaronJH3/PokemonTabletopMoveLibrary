package movelibrary;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * pokemonGif reads from file and draws a .gif image file on screen via a jFrame
 * <p> has issue with gif backgrounds
 * @author Ruben
 *
 */
@SuppressWarnings("serial")
class PokemonGif extends JPanel
{
	Image imgs;
	int w, h;
	//Random rand;

	 public PokemonGif(Image ims, int wid, int hei){
		 imgs = ims;
		 w = wid;
		 h = hei;
		 setPreferredSize(new Dimension(w, h));
		// setBackground(Color.WHITE);
		// setForeground(Color.WHITE);

		// rand = new Random();
		 }
	 
	 public void paintComponent(Graphics g)
	 {
		 
		  try
		    {
		      if((g != null) && (imgs != null))
				 g.drawImage(imgs,0,0,getWidth(),getHeight(),this);

		      g.dispose();
		      g.setPaintMode();
		    }
		    catch (Exception e)
		    {
		      System.out.println("Graphics context error : " + e);
		    }
		  		  
	 }
	 
	 /*
	 public static void main(String[] args)
	 {
		 String filename = "494.gif"; // an animated gif
		 JFrame frame = new JFrame();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container con = frame.getContentPane();
		 Image[] ia = new Image[5];
		 
		 for (int i = 0; i < ia.length; ++i)
		 {
			 ImageIcon ic = new ImageIcon(filename);
			 ia[i] = ic.getImage();
		 }
	 
		 PokemonGif agp = new PokemonGif(ia, 500, 400);
		 con.add(agp, BorderLayout.CENTER);
		 frame.pack();
		 frame.setVisible(true);
	}*/
		 
}

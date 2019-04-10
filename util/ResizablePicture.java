package util;

import java.awt.*;
import javax.swing.*;

public class ResizablePicture extends JPanel
{
   // properties
   public static final int SIZE_X = 300;
   public static final int SIZE_Y = 200;
   String filename;
   
   // constructors
   public ResizablePicture( String filename )
   {
      setPreferredSize( new Dimension( SIZE_X, SIZE_Y ) );
      this.filename = filename;
   }
   
   // methods
   @Override
   protected void paintComponent(Graphics g) 
   {
      super.paintComponent( g );
      
      Image bg = new ImageIcon(getClass().getResource( filename ) ).getImage();
      
      Graphics2D g2 = (Graphics2D) g.create();
      g2.drawImage( bg, 0,0,getWidth(),getHeight(), this);
      g2.dispose();
   }
   
   public void setPicture( String filename )
   {
      this.filename = filename;
      repaint();
   }
}
     
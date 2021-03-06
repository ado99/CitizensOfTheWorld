package util;

import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;     // Using Swing components and containers

// This class has been created to add buttons some functionality 
// For example: Player info button can close the player info if you click it when player info is open.
public class OpenCloseJButton extends JButton
{
   // properties
   boolean opened;
   
   // constructors
   public OpenCloseJButton( String text, int size )
   {
      super( text );
      setBackground( Color.cyan );
      setPreferredSize( new Dimension( size, size ) );
      
      opened = false;
   }
   
   public OpenCloseJButton( Icon icon, int size )
   {
      setLayout( new FlowLayout( FlowLayout.CENTER ) );
      setBackground( Color.cyan );
      setPreferredSize( new Dimension( size, size ) );
      
      add( new JLabel( icon ) );
      
      opened = false;
   }
   
   // methods
   public boolean isOpened()
   {
      return opened;
   }
   
   public void changeOpened()
   {
      opened = !opened;
   }
}
package gui;

import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;     // Using Swing components and containers
import mainCode.*;
import java.util.ArrayList;
import util.*;

// A Swing GUI application inherits from top-level container javax.swing.JFrame
public class GameGUI extends JPanel {
 
   // Private instance variables
   Game game;
   GameGUINorthPanel northPanel; // includes all variables until east panel
   PlayerInfo currentPlayersInfo; // to change player infos easily.
   GameGUIEastPanel eastPanel;
   JPanel southPanel; // includes two hardest button. 
   JButton rollDice; // rolls dice, moves current player, gets travel money, arranges north panel and player info accordingly, open the country info page
   JButton nextTurn; // changes the current player, uploads its informations into the related panels.
   JPanel centerPanel;
   CountryInfo countryInfo; 
   QuestionPage questionPage;
   YouWinPage youWinPage;
   YouLosePage youLosePage;
   AccomodationFeePage accomodationFeePage;
   Application app;
   int n; // used in for loops
   
   // Constructor to setup the GUI components and event handlers
   public GameGUI( Application application, Game game ) 
   {        
      this.game = game;
      this.app = application;
      
      createComponents();
      handleActionListeners();
      
      setVisible( true );
   }
   
   // Methods
   
   public void createComponents()
   {
      setLayout( new BorderLayout() );
      setSize( app.getSize() );
      
      // Center Panel
      centerPanel = new JPanel();
      add( centerPanel );
      
      countryInfo = new CountryInfo( game ); 
      centerPanel.add( countryInfo );
      
      youWinPage = new YouWinPage( this );
      centerPanel.add( youWinPage );
      
      youLosePage = new YouLosePage( this );
      centerPanel.add( youLosePage );
      
      accomodationFeePage = new AccomodationFeePage( this );
      centerPanel.add( accomodationFeePage );
      
      questionPage = new QuestionPage( this );
      centerPanel.add( questionPage );
      
      // North panel
      northPanel = new GameGUINorthPanel( this );      
      add( northPanel, BorderLayout.NORTH );
      
      // East Panel 
      eastPanel = new GameGUIEastPanel( this );
      add( eastPanel, BorderLayout.EAST );
      
      // South Panel
      southPanel = new JPanel();
      southPanel.setLayout( new FlowLayout() );
      
      // Roll Dice Button 
      rollDice = new JButton( "Roll Dice" );
      southPanel.add( rollDice );
      
      // Next Turn Button
      nextTurn = new JButton( "Next Turn" );
      nextTurn.setVisible( false );
      southPanel.add( nextTurn );
      
      add( southPanel, BorderLayout.SOUTH );
   }
   
   public void handleActionListeners()
   {
      rollDice.addActionListener( new RollDiceBtnListener() );
      nextTurn.addActionListener( new NextTurnBtnListener() );
   }
   
   // Roll Dice Button Listener 
   public class RollDiceBtnListener implements ActionListener
   {      
      @Override
      public void actionPerformed( ActionEvent evt )
      {                  
         game.rollDice();
         
         rollDice.setVisible( false );
      }
   }
   
   // Next Turn Button Listener 
   public class NextTurnBtnListener implements ActionListener
   {      
      @Override
      public void actionPerformed( ActionEvent evt )
      {        
         game.nextTurn();
                 
         nextTurn.setVisible( false );
         rollDice.setVisible( true );
      }
   }
}
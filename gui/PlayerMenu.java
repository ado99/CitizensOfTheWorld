package gui;

import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;     // Using Swing components and containers
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import mainCode.*;
import mainCode.pictures.Avatar;

// A Swing GUI application inherits from top-level container javax.swing.JFrame
public class PlayerMenu extends JPanel {
 
   // Private instance variables
   JButton back;
   JButton start;
   JButton numberOfPlayersButton; // the "OK" button.
   JPanel backAndStartPanel; // the panel that includes back and start buttons
   JPanel numberOfPlayersPanel; // the panel for the part we choose number of players
   CheckboxGroup numberOfPlayersGroup; 
   JPanel playerChoosePanel; // includes all 4 player selection panels
   JPanel playerOnePanel; // first player can select his name and country inside this
   JPanel playerTwoPanel;
   JPanel playerThreePanel;
   JPanel playerFourPanel;
   ArrayList<JList<String>> allCountryLists;
   ArrayList<JList<Avatar>> allAvatarLists;
   ArrayList<String> listedCountries; // countries that are listed as in the game ( the usage explained above the method )
   ArrayList<Avatar> listedAvatars;
   String[] alphabeticalCountries; // we need alphabetical list of countries in scroll panes
   Avatar[] avatars;
   Application app; 
   int numberOfPlayers;
   
   // Constructor to setup the GUI components and event handlers
   public PlayerMenu( Application app ) 
   {
      this.app = app;
      
      createComponents();
      handleActionListeners();
      
      setSize( app.getSize() );   // "super" JFrame sets initial size
      setVisible( false );    // "super" JFrame shows
   }
   
   // Methods
   
   public void createComponents()
   {
      setSize( app.getSize() );
      setLayout( new BorderLayout() );
      
      // Number Of Players
      numberOfPlayersPanel = new JPanel( new FlowLayout() );
      numberOfPlayersGroup = new CheckboxGroup();
      
      numberOfPlayersPanel.add( new Label( "Choose Player Number" ) );
      numberOfPlayersPanel.add( new Checkbox( "1", numberOfPlayersGroup, false ) );
      numberOfPlayersPanel.add( new Checkbox( "2", numberOfPlayersGroup, false ) );
      numberOfPlayersPanel.add( new Checkbox( "3", numberOfPlayersGroup, false ) );
      numberOfPlayersPanel.add( new Checkbox( "4", numberOfPlayersGroup, false ) );
      
      numberOfPlayersButton = new JButton( "OK" );
      numberOfPlayersPanel.add( numberOfPlayersButton );
                               
      add( numberOfPlayersPanel, BorderLayout.NORTH );     
      
      // Players Choices
      playerChoosePanel = new JPanel();
      playerChoosePanel.setLayout( new GridLayout( 2, 2 ) );
      
      alphabeticalCountries = getAlphabeticalCountriesArray(); // will be needed in country lists      
      allCountryLists = new ArrayList<JList<String>>();  
      
      avatars = getAvatarsArray(); // will be needed in avatar lists
      allAvatarLists = new ArrayList<JList<Avatar>>();
         
      JList<String> countryList;
      JList<Avatar> avatarList;
      for ( int n = 0; n < 4; n++ )
      {
         countryList = new JList<String>( alphabeticalCountries );
         allCountryLists.add( countryList );
         
         avatarList = new JList<Avatar>( avatars );
         avatarList.setLayoutOrientation( JList.VERTICAL_WRAP );
         avatarList.setVisibleRowCount( 13 );
         allAvatarLists.add( avatarList );
         
         playerChoicePanelCreator( playerOnePanel, countryList, avatarList, n ); 
      }
      
      add( playerChoosePanel );
      
      // Back and Start Buttons
      backAndStartPanel = new JPanel( new FlowLayout() );
   
      back = new JButton( "Back" );
      backAndStartPanel.add( back ); 
      
      start = new JButton( "Start" );
      backAndStartPanel.add( start ); 
 
      add( backAndStartPanel, BorderLayout.SOUTH );
   }
   
   // Player Choice Panel Creator ( each player panel's code are similar, so we do all of them with one method
   public void playerChoicePanelCreator( JPanel playerPanel, JList<String> countryList, JList<Avatar> avatarList, int playerNo )
   {
      playerPanel = new JPanel( new FlowLayout() );
      
      playerPanel.add( new JLabel( ( playerNo + 1 ) + "" ) );      
      playerPanel.add( new JTextField( 10 ) );
      playerPanel.add( new JScrollPane( countryList ) );
      playerPanel.add( new JScrollPane( avatarList ) );
      
      playerPanel.setVisible( false );
      playerChoosePanel.add( playerPanel ); 
   }
   
   public void handleActionListeners()
   {
      numberOfPlayersButton.addActionListener( new OkBtnListener() );
      back.addActionListener( new BackBtnListener() );
      listedCountries = getListedCountries(); // will be needed when the game is starting
      start.addActionListener( new StartBtnListener() );
   }
   
   // OK Button listener ( BURAYI OPT�M�ZE EDEB�L�R�Z )
   private class OkBtnListener implements ActionListener 
   {
      @Override
      public void actionPerformed( ActionEvent evt ) 
      {
         int n;
         
         numberOfPlayers = Integer.parseInt( numberOfPlayersGroup.getSelectedCheckbox().getLabel() );
         
         for( n = 0; n < numberOfPlayers; n++ )
         {
            playerChoosePanel.getComponent( n ).setVisible( true );
         }
         for( n = numberOfPlayers; n < 4; n++ )
         {
            playerChoosePanel.getComponent( n ).setVisible( false );
         }
      }
   }
   
   // Back Button Listener
   public class BackBtnListener implements ActionListener
   {
      @Override
      public void actionPerformed( ActionEvent evt )
      {
         setVisible( false );
         app.mainMenu.setVisible( true );
      }
   }
   
   // Start Button Listener
   private class StartBtnListener implements ActionListener 
   {
      @Override
      public void actionPerformed( ActionEvent evt ) 
      {
         String[] namesOfPlayers;
         int[] locationsOfPlayers;
         Avatar[] avatarsOfPlayers;
         String country;
         
         namesOfPlayers = new String[ numberOfPlayers ];
         locationsOfPlayers = new int[ numberOfPlayers ];
         avatarsOfPlayers = new Avatar[ numberOfPlayers ];
         for ( int n = 0; n < numberOfPlayers; n++ )
         {
            namesOfPlayers[ n ] = getPlayerName( n );
            locationsOfPlayers[ n ] = getSelectedCountry( n );
            avatarsOfPlayers[ n ] = getSelectedAvatar( n );
         }
         
         app.gameGui = new GameGUI( app, new Game( numberOfPlayers, locationsOfPlayers, namesOfPlayers, avatarsOfPlayers ) ); 
         app.add( app.gameGui );
         setVisible( false );
      }
   }
   
   // These two methods are created in order to reach the player name textfields and country selection scrolls easily and in for loop
   public String getPlayerName( int playerNo )
   {
      return ( (JTextField)( (JPanel) playerChoosePanel.getComponent( playerNo ) ).getComponent( 1 ) ).getText();
   }
   
   public int getSelectedCountry( int playerNo )
   {
      return listedCountries.indexOf( allCountryLists.get( playerNo ).getSelectedValue().toString() );  
   }
   
   public Avatar getSelectedAvatar( int playerNo )
   {
      return allAvatarLists.get( playerNo ).getSelectedValue();
   }
   
   // Creates the country arraylist with the order in the game ( to determine the locations of players from the country they chose )
   public ArrayList<String> getAlphabeticalCountries() 
   {
      final int COUNTRY_NUMBER = 60;
      
      try
      {
         ArrayList<String> countries;
         FileReader fr;
         BufferedReader br;
         
         countries = new ArrayList<String>();
         fr = new FileReader( "Country Info\\countries alphabetical.txt" );
         br = new BufferedReader( fr );
         for( int n = 0; n < COUNTRY_NUMBER; n++ )
         {
            countries.add( br.readLine() );
         }
         
         return countries;
      }
      catch (IOException e)
      {
         return null;
      }
   }
   
   // Creates the country array in alphabetical order ( to make it compatible with JList )
   public String[] getAlphabeticalCountriesArray()
   {
      String[] arrayCountries;
      ArrayList<String> listCountries;
      
      listCountries = getAlphabeticalCountries();
      arrayCountries = new String[ listCountries.size() ];
      
      for ( int n = 0; n < arrayCountries.length; n++ )
      {
         arrayCountries[ n ] = listCountries.get( n );
      }
      
      return arrayCountries;
   }
   
   public ArrayList<String> getListedCountries() 
   {
      final int COUNTRY_NUMBER = 60;
      
      try
      {
         ArrayList<String> countries;
         FileReader fr;
         BufferedReader br;
         
         countries = new ArrayList<String>();
         fr = new FileReader( "Country Info\\countries listed.txt" );
         br = new BufferedReader( fr );
         for( int n = 0; n < COUNTRY_NUMBER; n++ )
         {
            countries.add( br.readLine() );
         }
         
         return countries;
      }
      catch (IOException e)
      {
         return null;
      }
   }
   
   // Puts all accessible avatars in an array 
   public Avatar[] getAvatarsArray()
   {
      final int AVATAR_NUMBER = 26;
      
      try
      {
         Avatar[] avatars;
         FileReader fr;
         BufferedReader br;
         Avatar avatar;
         
         avatars = new Avatar[ AVATAR_NUMBER ];
         fr = new FileReader( "Avatar Pictures\\avatar names.txt" );
         br = new BufferedReader( fr );
         for( int n = 0; n < AVATAR_NUMBER; n++ )
         {
            avatar = new Avatar( br.readLine() );
            avatars[ n ] = avatar;
         }
         
         return avatars;
      }
      catch (IOException e)
      {
         return null;
      }
   }
   
   
}
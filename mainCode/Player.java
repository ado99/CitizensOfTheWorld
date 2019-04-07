package mainCode;

import java.util.Scanner;

// buraya bi comment at�ver
public class Player
{
   Scanner scan = new Scanner( System.in);
   
   // properties
   public static final int STARTING_MONEY = 1000;
   public static final int TRAVEL_CHARGE = 5;
   int money;
   Countries countries; // vatanda�� oldu�umuz �lkeler
   int numberOfCountries; // bu da onlar�n say�s�
   String name;
   int location;
   int playerNo;
   int revenue;
   
   // constructors
   
   // bu guide biyerde laz�m onun i��n yaz�yorum.
   public Player()
   {
      countries = new Countries();
      numberOfCountries = 0;
      money = STARTING_MONEY;
      name = "";
      location = 0;
      playerNo = 0;
      revenue = 0;
   }
   
   public Player( String name, int location, int playerNo )
   {
      countries = new Countries();
      numberOfCountries = 0;
      money = STARTING_MONEY;
      this.name = name;
      this.location = location;
      this.playerNo = playerNo;
      revenue = 0;
   }
   
   // methods ( javadoclar� yaz�n�z l�tfen )
   public int getPlayerNo()
   {
      return playerNo;
   }
   
   public String getName()
   {
      return name;
   }
   
   public int getNumberOfCountries()
   {
      return numberOfCountries;
   }
   
   public int getLocation()
   {
      return location;
   }
   
   public int getMoney()
   {
      return money;
   }
   
   public int getRevenue()
   {
      return revenue;
   }
   
   public boolean equals( Player p )
   {
      return this.getPlayerNo() == p.getPlayerNo();
   }
   
   // zar sallama olay�, locationunu de�i�tiriyosun ( burda ge�en yazd���m�z dice �eysini kullanabilirsin )
   public void rollDice()
   {
      int dice1;
      int dice2;
      
      dice1 = (int)( Math.random() * 6 ) + 1;
      dice2 = (int)( Math.random() * 6 ) + 1;     
      money = money - ( dice1 + dice2 ) * TRAVEL_CHARGE;
      location = ( location + dice1 + dice2 ) % 60;
   }
   
   public void payAccomodationFee( Country c )
   {
      money = money - c.getAccomodationFee();
   }
   
   // burda player e�er vatanda�sa t�m sorular� cevaplar�yla birlikte g�rebiliyor. Onu string olarak at�caks�n. Countrieste metodu var zaten onun kolay i�.
   public String getAllQuestions( Country country )
   {
      if ( country.isACitizen( this ) )
      {
         return country.getQuestions().toString();
      }
      else
      {
         return "You are not a citizen of this country.";
      }
   }
   
   // bu da e�er �lkenin ilk vatanda��ysa ba�ka bir oyuncu i�in 3 tane soru se�iyor. Tabi se�mesi i�in �nce t�m sorular� cevaplar�yla g�rmesi laz�m. 
   public Questions chooseQuestionsForOtherPlayer( Country country )
   {
      Questions questionsChosen;
      int n;
      System.out.println( getAllQuestions( country ) );
      int questionNo;
      
      System.out.println( country.getQuestions() );
      System.out.println( this.getName() + ", Please choose 3 questions for another player." );
      
      questionsChosen = new Questions( 3 );
      for ( n = 0; n < 3; n++ )
      {
         System.out.println( "Please enter the question number to choose" );
         questionNo = scan.nextInt();
         
         questionsChosen.add( country.getQuestions().getQuestion( questionNo - 1 ) );
      }
      
      return questionsChosen;
   }
   
   public Questions getQuiz( Country c )
   {
      payQuestionFee();
      
      if ( c.hasACitizen() )
      {
         return c.getCitizen( 0 ).chooseQuestionsForOtherPlayer( c );
      }
      else 
      {
         return c.determineThreeRandomQuestions();
      }
   }
   
   // citizenship kazan�rsa citizen arrayine ekle
   public void addCitizenship( Country c )
   {
      countries.add( c );
      numberOfCountries++;
      
      revenue = revenue + c.getTax();
   }
   
   public void addRevenue()
   {
      money = money + revenue;
   }
   
   public void payQuestionFee()
   {
      money = money - Question.QUESTION_FEE;
   }
   
   public int compareTo( Player p )
   {
      if ( this.numberOfCountries > p.numberOfCountries )
      {
         return 1;
      }
      else if ( this.numberOfCountries < p.numberOfCountries )
      {
         return -1;
      }
      else
      {
         if ( this.money > p.money )
         {
            return 1;
         }
         else if ( this.money < p.money )
         {
            return -1;
         }
         else 
         {
            return 0;
         }
      }
   }
}
   
   
   
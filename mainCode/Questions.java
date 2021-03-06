package mainCode;

// Represents package of questions.
public class Questions
{
   // properties
   Question[] questions;
   int numberOfQuestions;
   
   // constructors   
   public Questions( int size ) 
   {
      questions = new Question[ size ];
      numberOfQuestions = 0;
   }
   
   public Questions( Questions x )
   {
      int n;
      
      questions = new Question[ x.questions.length ];
      for ( n = 0; n < questions.length; n++ )
      {
         questions[ n ] = new Question( x.get( n ) );
      }
   }
   
   // methods  
   
   public Question get( int questionNo )
   {
      return questions[ questionNo ];
   }
   
   public void add( Question question )
   {
      questions[ numberOfQuestions ] = question;
      numberOfQuestions++;
   }
   
   public void putToIndex( Question question, int index )
   {
      questions[ index ] = question;
   }
}
   
   
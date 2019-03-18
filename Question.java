// Represents a single question, with choices and the answer, that will be asked to players
public class Question
{
   // properties
   public static final int QUESTION_FEE = 10;
   String question;
   String[] choices;
   int answer;
   
   // constructors
   public Question( String question, String[] choices, int answer )
   {
      this.question = question;
      this.choices = choices;
      this.answer = answer;
      
   }
   
   // clone constructoru ��endim yaz�ver
   public Question( Question x )
   {
      this.question = x.getQuestion();
      this.choices = x.getChoices();
      this.answer = x.getAnswer();
   }
   
   // methods
   
   // javadoclar� hallediver sana zahmet
   public String getQuestion()
   {
      return question;
   }
   
   public String[] getChoices()
   {
      return choices;
   }
   
   public int getAnswer()
   {
      return answer;
   }
   
   // toStringi biliyosun zaten soru c�mlesi ve cevaplar�n� d�zg�n bir �ekilde print etsin. Cevaplar� hizal� yapsan iyi olur. 
   public String toString()
   {
      String[] choices;
      choices = getChoices();
      
      return getQuestion() + "\n" + "A) " + choices[ 0 ] + "\n"  + "B) " + choices[ 1 ] 
             + "\n" + "C) " + choices[ 2 ] + "\n";
   }
   
   public String toStringWithAnswer()
   {
      String[] choices;
      choices = getChoices();
      
      return getQuestion() + "\n" + "A) " + choices[ 0 ] + "\n"  + "B) " + choices[ 1 ] 
             + "\n" + "C) " + choices[ 2 ] + "\n" + "Answer: " + answer + "\n";
   }
   
   public boolean isAnswerCorrect( int answer )
   {
      return this.answer == answer;
   }
   
   public String getStringAnswer()
   {
      return choices[ answer ];
   }
   
}

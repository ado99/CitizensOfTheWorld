package mainCode;

// Represents a single question, with choices and the answer, that will be asked to players
public class Question
{
   // properties
   public static final int QUESTION_FEE = 10;
   String questionSentence;
   String[] choices;
   int answer;
   
   // constructors
   public Question( String questionSentence, String[] choices, int answer )
   {
      this.questionSentence = questionSentence;
      this.choices = choices;
      this.answer = answer;
      
   }
   
   // clone constructoru ��endim yaz�ver
   public Question( Question x )
   {
      this.questionSentence = x.questionSentence;
      this.choices = x.choices;
      this.answer = x.answer;
   }
   
   // methods
   
   // javadoclar� hallediver sana zahmet
   public String getQuestionSentence()
   {
      return questionSentence;
   }
   
   public String[] getChoices()
   {
      return choices;
   }
   
   public String getChoice( int choiceNo )
   {
      return choices[ choiceNo ];
   }
   
   public final boolean isAnswerCorrect( int answer )
   {
      return this.answer == answer;
   }
}

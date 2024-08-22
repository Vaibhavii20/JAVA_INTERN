import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question 
{
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) 
    {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() 
    {
        return questionText;
    }

    public String[] getOptions() 
    {
        return options;
    }

    public int getCorrectAnswer() 
    {
        return correctAnswer;
    }

    public boolean isCorrect(int userAnswer) 
    {
        return userAnswer == correctAnswer;
    }
}

class Quiz 
{
    private List<Question> questions;
    private int score;

    public Quiz() 
    {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) 
    {
        questions.add(question);
    }

    public void start() 
    {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < questions.size(); i++) 
        {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getQuestionText());

            String[] options = q.getOptions();
            for (int j = 0; j < options.length; j++) 
            {
                System.out.println((j + 1) + ". " + options[j]);
            }

            int userAnswer = 0;
            while (true) 
            {
                System.out.print("Your answer (1-" + options.length + "): ");
                if (scanner.hasNextInt()) 
                {
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 1 && userAnswer <= options.length) 
                    {
                        break;
                    } else 
                    {
                        System.out.println("Please enter a number between 1 and " + options.length + ".");
                    }
                } else 
                {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            if (q.isCorrect(userAnswer)) 
            {
                System.out.println("Correct!");
                score++;
            } else 
            {
                System.out.println("Wrong. The correct answer was " + q.getOptions()[q.getCorrectAnswer() - 1]);
            }
            System.out.println();
        }

        System.out.println("Quiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());
        scanner.close();
    }
}

public class OnlineQuizApplication 
{
    public static void main(String[] args) 
    {
        Quiz quiz = new Quiz();

        quiz.addQuestion(new Question("Who invvented Java Programming?", 
                new String[]{"Guido van Rossum", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup"}, 2));
        quiz.addQuestion(new Question("Which component is used to compile,debug and execute the Java Programs?", 
                new String[]{"JRE", "JIT", "JDK", "JVM"}, 3));
        quiz.addQuestion(new Question("What is the extension of Java code files?", 
                new String[]{".js", ".txt", ".class", ".java"}, 4));

        quiz.start();
    }
}

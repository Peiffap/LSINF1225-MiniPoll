package be.lsinf1225gr12.minipoll.minipoll.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MCQ extends PollAbstract implements Serializable {

    private int numberQuestion;
    private List<Question> question;

    //private List<AssociationMCQ> listAssociationMCQ;

    /**
     * Fonction qui ajoute une instance de MCQ et la mets dans la db
     */
    public MCQ(String format, String name, User author, long date, int numberQuestion){
        super(format,name,author,date);

        this.question = new ArrayList<>();


        this.numberQuestion = numberQuestion;

    }

    public static MCQ createNewMCQ(String format, String name, User author, long date, int numberQuestion){

        MCQ mcq = new MCQ(format,name,author,date,numberQuestion);

        //écrit dans la BD
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyMcqAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyMcqDate(),date);
        cv.put(MySQLiteHelper.getKeyMcqFormat(),format);
        cv.put(MySQLiteHelper.getKeyMcqIsclosed(),false);
        cv.put(MySQLiteHelper.getKeyMcqNumberquestion(),numberQuestion);
        cv.put(MySQLiteHelper.getKeyMcqTitle(),name);
        int result = (int) db.insert(MySQLiteHelper.getTableMcq(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();

        return mcq;
    }

    /**
     * Fonction qui ajoute une question dans la db et crée une instance
     * @param title titre de la question
     * @param position donne la position de la question
     */
    public Question addQuestion(String title,int position)
    {

        Question question = new Question(title,1,position);
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyQuestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyQuestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyQuestionDescription(),title);
        cv.put(MySQLiteHelper.getKeyQuestionPosition(),position);
        cv.put(MySQLiteHelper.getKeyQuestionRightanswer(), 1);
        int result = (int) db.insert(MySQLiteHelper.getTableQuestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();
        this.question.add(question);
        return question;
    }
    /**
     * Fonction qui modifie la RightAnswer in db
     * @param positionRight Donne la position de réponse qu'on veut mettre en rightanswer
     * @param question Dit à quelle question on veut mettre la bonne réponse :RightAnswe
     */
    public void setRightanswer(int positionRight,Question question){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyQuestionRightanswer(),positionRight);
        String selection = MySQLiteHelper.getKeyQuestionAuthor() + " = ? AND " + MySQLiteHelper.getKeyQuestionDate() + " = ?"+MySQLiteHelper.getKeyQuestionDescription(); //rajouter autant qu'il faut
        String[] selectionArgs = new String[]{String.valueOf(author.getId()), String.valueOf(date),question.getTitle()};
        db.update(MySQLiteHelper.getTableQuestion(), cv, selection, selectionArgs);
        int result = (int) db.insert(MySQLiteHelper.getTableQuestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();

    }

    public static MCQ get(int author, long date){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String selection = MySQLiteHelper.getKeyMcqAuthor() + " = ? AND " + MySQLiteHelper.getKeyMcqDate() + " = ?";
        String[] colonnes = {MySQLiteHelper.getKeyMcqFormat(), MySQLiteHelper.getKeyMcqIsclosed(), MySQLiteHelper.getKeyMcqNumberquestion(),MySQLiteHelper.getKeyMcqTitle()};
        String[] selectionArgs = {String.valueOf(author),String.valueOf(date)};
        Cursor cursor = db.query(MySQLiteHelper.getTableMcq(), colonnes, selection, selectionArgs, null, null, null);

        // Placement du curseur sur la première ligne.
        if(!cursor.moveToFirst())
            return null;

        int numberQuestion = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.getKeyMcqNumberquestion()));
        String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyMcqTitle()));
        String format = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.getKeyMcqFormat()));

        MCQ mcq = new MCQ(format,name,User.getUserWithId(author),date,numberQuestion);


        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return mcq;

    }


    /**
     * Fonction qui ajoute un choix de question possible
     * @param question On veut rajouter le choix à quelle question
     * @param description description du choix
     * @param positionchoice position du choix
     */
    public void addQuestionChoice(Question question, String description,int positionchoice)
    {
        Questionchoice questionchoice = new Questionchoice(question.getPosition(),description,question.getPosition());
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyChoicequestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyChoicequestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyChoicequestionPosition(),positionchoice);
        cv.put(MySQLiteHelper.getKeyChoicequestionQuestionposition(),question.getPosition());
        cv.put(MySQLiteHelper.getKeyChoicequestionText(),description);


        int result = (int) db.insert(MySQLiteHelper.getTableChoicequestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();

    }



    /**
     * Fonction qui ajoute une réponse d'un utilisateur dans la db ...
     * @param questionchoice on vote pour le choix questionchoice
     * @param user Quel user donne le choix
     */
    public void addMCQAnswer(Questionchoice questionchoice, User user, int position)
    {

        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.getKeyAnswerquestionAuthor(),author.getId());
        cv.put(MySQLiteHelper.getKeyAnswerquestionDate(),this.getDate());
        cv.put(MySQLiteHelper.getKeyAnswerquestionPosition(),position);
        cv.put(MySQLiteHelper.getKeyAnswerquestionQuestionposition(),questionchoice.getPosition());
        cv.put(MySQLiteHelper.getKeyAnswerquestionUser(),user.getId());


        int result = (int) db.insert(MySQLiteHelper.getTableAnswerquestion(), null, cv);
        if (result==-1)
        {
            //erreur dans l'ajout, suppression
        }
        db.close();



    }

    /* Getters and setters */
    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

}
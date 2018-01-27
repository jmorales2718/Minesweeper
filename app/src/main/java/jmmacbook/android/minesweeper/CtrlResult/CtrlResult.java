package jmmacbook.android.minesweeper.CtrlResult;

/**
 * Created by jmmacbook on 3/6/16.
 *
 * Singleton class, helps control the page displayed after the user wins or loses
 */
public class CtrlResult
{
    private static CtrlResult instance = null;
    private String result;
    private String playAgain;

    private CtrlResult() {}

    public static CtrlResult getInstance()
    {
        if(instance == null)
        {
            instance = new CtrlResult();
        }
        return instance;
    }

    public static final short WIN  = 0;  //used to indicate the user winnning
    public static final short LOSSF = 1; //used to indicate a user loss, by placing an incorrect flag
    public static final short LOSSM = 2; //used to indicate a user loss, by clicking on a mine

    //Controls the text of the TextView on the ResultActivity
    public void setResult(short resultInt)
    {
        if(resultInt == WIN)
        {
            result = "Sweet! You have successfully flagged all mines and won!!!";
        }
        else if(resultInt == LOSSF)
        {
            result = "Whoops! You have incorrectly placed a flag and lost.";
        }
        else
        {
            result = "BOOM!! You have clicked on a mine and lost.";
        }
    }

    public String getResult()
    {
        return result;
    }


    //Sets the text of the replay button according to previous win or loss
    public void setPlayAgain(short resultInt)
    {
        if(resultInt == WIN)
        {
            playAgain = "Play Again";
        }
        else
        {
            playAgain = "Retry";
        }
    }

    public String getPlayAgain()
    {
        return playAgain;
    }
}

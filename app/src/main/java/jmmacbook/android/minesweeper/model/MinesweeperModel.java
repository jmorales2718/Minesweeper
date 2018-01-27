package jmmacbook.android.minesweeper.model;

import java.util.Random;

/**
 * Created by jmmacbook on 2/22/16.
 */
public class MinesweeperModel
{
    private static MinesweeperModel instance = null;

    private MinesweeperModel() {}

    public static MinesweeperModel getInstance()
    {
        if (instance == null)
        {
            instance = new MinesweeperModel();
            instance.plantMines();
        }
        return instance;
    }

    public static final short NULL = 0;
    public static final short ONE = 1;
    public static final short TWO = 2;
    public static final short THREE = 3;
    public static final short MINE = 4;
    public static final short EMPTY = 5;
    public static final short FLAG = 6;

    public final short NUMBER_MINES = 3;


    //Represents all the labels even if the user cannot see them.
    private short[][] model =
            {
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL}
            };
    //Represents the content that will be displayed on the screen when
    //the game field is drawn.
    private short[][] screenModel =
            {
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL},
                    {NULL, NULL, NULL, NULL, NULL}
            };

    //Randomly assigns a location to the mines and determines the correct
    //labels for the squares adjacent to the planted mine.
    private void plantMines()
    {
        Random rand = new Random();

        for (int i = 0; i < NUMBER_MINES; i++)
        {
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);

            if (model[x][y] == NULL || model[x][y] == ONE || model[x][y] == TWO)
            {
                model[x][y] = MINE;
                //Row above mine from left to right
                calculateAdjacent(x - 1, y - 1);
                calculateAdjacent(x, y - 1);
                calculateAdjacent(x + 1, y - 1);
                //Row containing mine from left to right
                calculateAdjacent(x - 1, y);
                calculateAdjacent(x + 1, y);
                //Row under mine from left to right
                calculateAdjacent(x - 1, y + 1);
                calculateAdjacent(x, y + 1);
                calculateAdjacent(x + 1, y + 1);
            }
            else
            {
                i--;
            }
        }
        plantEmpty();
    }

    //Increments the current number for the square indicated by
    //the two integer coordinates
    private void calculateAdjacent(int x, int y)
    {
        if (isValidCoordinate(x, y))
        {
            if (model[x][y] == NULL)
            {
                model[x][y] = ONE;
            }
            else if (model[x][y] == ONE)
            {
                model[x][y] = TWO;
            }
            else if (model[x][y] == TWO)
            {
                model[x][y] = THREE;
            }
        }
    }

    //Places an empty designation in the model
    private void plantEmpty()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (model[i][j] == NULL)
                {
                    model[i][j] = EMPTY;
                }
            }
        }
    }

    //Makes sure that the coordinate input is on the 5x5 field
    private boolean isValidCoordinate(int x, int y)
    {
        if (x >= 0 && x <= 4 && y >= 0 && y <= 4)
        {
            return true;
        }
        return false;
    }

    //Resets both the screenModel and model to a different field with new labels
    public void resetModel()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                screenModel[i][j] = NULL;
                model[i][j] = NULL;
            }
        }
        plantMines();
    }

    //Gets a specific square's content to find out what belongs on the users screen
    public short getFieldContent(int x, int y)
    {
        return screenModel[x][y];
    }

    //Sets a specific square's content to determine what will be displayed to the user
    public void setFieldContent(int x, int y, short content)
    {
        screenModel[x][y] = content;
    }

    //Gets the hidden data about a square (flag, mine, blank, etc.)
    public short getModelLocation(int x, int y)
    {
        return model[x][y];
    }

    //Sets the hidden data about a square (flag, mine, blank, etc.)
    public void setModel(int x, int y, short content)
    {
        model[x][y] = content;
    }
}

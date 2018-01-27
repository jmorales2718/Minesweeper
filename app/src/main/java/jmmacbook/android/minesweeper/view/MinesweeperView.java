package jmmacbook.android.minesweeper.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import jmmacbook.android.minesweeper.CtrlResult.CtrlResult;
import jmmacbook.android.minesweeper.GameActivity;
import jmmacbook.android.minesweeper.R;
import jmmacbook.android.minesweeper.model.MinesweeperModel;

/**
 * Created by jmmacbook on 2/22/16.
 */
public class MinesweeperView extends View
{
    private Paint paintLine;
    private Paint paintText;
    private Paint paintEmpty;
    private Bitmap background;
    private Bitmap flag;
    private boolean flagMode;

    public MinesweeperView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        //Indicates if the user is in flag or click mode
        flagMode = false;

        //Paint settings the 8 vertical and horizontal
        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(3);
        //Paint settings the numbers
        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setTextSize(48);
        //Paint settings for the overlay
        paintEmpty = new Paint();
        paintEmpty.setColor(Color.parseColor("#ffffff"));
        paintEmpty.setStyle(Paint.Style.FILL);
        paintEmpty.setAlpha(100);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.actmainback);
        flag = BitmapFactory.decodeResource(getResources(), R.drawable.murica);
    }

    public boolean getFlagMode()
    {
        return flagMode;
    }

    public void setFlagMode(boolean flagMode)
    {
        this.flagMode = flagMode;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(background, 0, 0, null);
        drawGameArea(canvas);
        drawCanvas(canvas);
    }

    public void drawGameArea(Canvas canvas)
    {
        //Draw Border
        canvas.drawLine(0, 0, 0, getHeight(), paintLine);
        canvas.drawLine(0, 0, getWidth(), 0, paintLine);
        canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), paintLine);
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paintLine);
        //Draw Vertical Lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine((getWidth() * 2) / 5, 0, (getWidth() * 2) / 5, getHeight(), paintLine);
        canvas.drawLine((getWidth() * 3) / 5, 0, (getWidth() * 3) / 5, getHeight(), paintLine);
        canvas.drawLine((getWidth() * 4) / 5, 0, (getWidth() * 4) / 5, getHeight(), paintLine);
        //Draw Horizontal Lines
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5, paintLine);
        canvas.drawLine(0, (getHeight() * 2) / 5, getWidth(), (getHeight() * 2) / 5, paintLine);
        canvas.drawLine(0, (getHeight() * 3) / 5, getWidth(), (getHeight() * 3) / 5, paintLine);
        canvas.drawLine(0, (getHeight() * 4) / 5, getWidth(), (getHeight() * 4) / 5, paintLine);
    }

    private void drawCanvas(Canvas canvas)
    {
        drawFlag(canvas);
        drawLabels(canvas);
    }

    //Draws the numbers, empty spaces and mines on the field
    private void drawLabels(Canvas canvas)
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.MINE)
                {
                    //Only draw mine if in clickmode
                    if (!flagMode)
                    {
                        float centerX = i * getWidth() / 5 + getWidth() / 10;
                        float centerY = j * getHeight() / 5 + getHeight() / 10;
                        int radius = getHeight() / 10 - 2;
                        canvas.drawCircle(centerX, centerY, radius, paintLine);
                    }
                }
                else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.ONE)
                {
                    float centerX = i * getWidth() / 5 + getWidth() / 12;
                    float centerY = j * getHeight() / 5 + getHeight() / 8;
                    canvas.drawText("1", centerX, centerY, paintText);

                }
                else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.TWO)
                {
                    float centerX = i * getWidth() / 5 + getWidth() / 12;
                    float centerY = j * getHeight() / 5 + getHeight() / 8;
                    canvas.drawText("2", centerX, centerY, paintText);
                }
                else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.THREE)
                {
                    float centerX = i * getWidth() / 5 + getWidth() / 12;
                    float centerY = j * getHeight() / 5 + getHeight() / 8;
                    canvas.drawText("3", centerX, centerY, paintText);
                }
                else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.EMPTY)
                {
                    float left = i * getWidth() / 5;
                    float top = j * getHeight() / 5;
                    float right = (i + 1) * getWidth() / 5;
                    float bottom = (j + 1) * getHeight() / 5;
                    canvas.drawRect(left, top, right, bottom, paintEmpty);
                }
            }
        }
    }

    //Draws the Bitmap flag in the field
    private void drawFlag(Canvas canvas)
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.MINE ||
                        MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.FLAG)
                {
                    //calculate the edges of the rectangle the flag will be placed in
                    float left = i * getWidth() / 5;
                    float top = j * getHeight() / 5;
                    float right = (i + 1) * getWidth() / 5;
                    float bottom = (j + 1) * getHeight() / 5;

                    RectF dest = new RectF(left, top, right, bottom);
                    canvas.drawBitmap(flag, null, dest, null);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (!flagMode)
        {
            onTouchClick(event);
        }
        else
        {
            onTouchFlag(event);
        }
        return super.onTouchEvent(event);
    }

    //Handles the case where the user touches while in click mode
    public void onTouchClick(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);

            if (tX < 5 && tY < 5 &&
                    MinesweeperModel.getInstance().getModelLocation(tX, tY) ==
                            MinesweeperModel.MINE)
            {
                MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.MINE);
                //Sets the text of the result screen
                CtrlResult.getInstance().setResult(CtrlResult.LOSSM);
                CtrlResult.getInstance().setPlayAgain(CtrlResult.LOSSM);
                ((GameActivity) getContext()).showResult();
                restartGamefield();
            }
            else if (tX < 5 && tY < 5 &&
                    MinesweeperModel.getInstance().getModelLocation(tX, tY) ==
                            MinesweeperModel.ONE)
            {
                MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.ONE);
                invalidate();
            }
            else if (tX < 5 && tY < 5 &&
                    MinesweeperModel.getInstance().getModelLocation(tX, tY) ==
                            MinesweeperModel.TWO)
            {
                MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.TWO);
                invalidate();
            }
            else if (tX < 5 && tY < 5 &&
                    MinesweeperModel.getInstance().getModelLocation(tX, tY) ==
                            MinesweeperModel.THREE)
            {
                MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.THREE);
                invalidate();
            }
            else if (tX < 5 && tY < 5 &&
                    MinesweeperModel.getInstance().getModelLocation(tX, tY) ==
                            MinesweeperModel.EMPTY)
            {
                MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.EMPTY);
                invalidate();
            }
        }
    }

    //Handles the case where the user touches in flag mode
    public void onTouchFlag(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);

            //If flagged a mine
            if (tX < 5 && tY < 5 &&
                    MinesweeperModel.getInstance().getModelLocation(tX, tY)
                            == MinesweeperModel.MINE)
            {
                MinesweeperModel.getInstance().setFieldContent(tX, tY, MinesweeperModel.FLAG);
                MinesweeperModel.getInstance().setModel(tX, tY, MinesweeperModel.FLAG);
                invalidate();
                boolean win = checkForWin();
                if (win)
                {
                    CtrlResult.getInstance().setResult(CtrlResult.WIN);
                    CtrlResult.getInstance().setPlayAgain(CtrlResult.WIN);
                    ((GameActivity) getContext()).showResult();
                    restartGamefield();
                }
            }
            //If flagged a non-mine square
            else
            {
                CtrlResult.getInstance().setResult(CtrlResult.LOSSF);
                CtrlResult.getInstance().setPlayAgain(CtrlResult.LOSSF);
                ((GameActivity) getContext()).showResult();
                restartGamefield();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
        background = Bitmap.createScaledBitmap(background, d, d, false);
    }

    //Creates a new game with different mine locations and redraws the field
    public void restartGamefield()
    {
        MinesweeperModel.getInstance().resetModel();
        invalidate();
    }

    //Checks for three flags on the field, if the user ahs flagged all three mines
    //then true is returned to indicate a win
    public boolean checkForWin()
    {
        int cnt = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (MinesweeperModel.getInstance().getModelLocation(i, j) == MinesweeperModel.FLAG)
                {
                    cnt++;
                }
            }
        }
        return cnt == 3;
    }
}

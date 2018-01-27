package jmmacbook.android.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import jmmacbook.android.minesweeper.view.MinesweeperView;

public class GameActivity extends AppCompatActivity
{
    private MinesweeperView gameField;
    private ToggleButton flagToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        flagToggle = (ToggleButton) findViewById(R.id.toggleFlag);
        flagToggle.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (!gameField.getFlagMode())
                        {
                            gameField.setFlagMode(true);
                        } else
                        {
                            gameField.setFlagMode(false);
                        }
                    }
                }
        );

        gameField = (MinesweeperView) findViewById(R.id.mineGameField);

        Button restartButton = (Button) findViewById(R.id.resartButton);
        restartButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        gameField.restartGamefield();
                    }
                });
    }
    public void showResult()
    {
        startActivity(new Intent(GameActivity.this, ResultActivity.class));
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(GameActivity.this, StartMenuActivity.class));
    }
}

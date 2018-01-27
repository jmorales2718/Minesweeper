package jmmacbook.android.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jmmacbook.android.minesweeper.CtrlResult.CtrlResult;

public class ResultActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvResult = (TextView) findViewById(R.id.winLoss);
        tvResult.setText(CtrlResult.getInstance().getResult());

        Button btnRetry = (Button) findViewById(R.id.backToGame);
        btnRetry.setText(CtrlResult.getInstance().getPlayAgain());
        btnRetry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ResultActivity.this, GameActivity.class));
            }
        });

        Button btnMenu = (Button) findViewById(R.id.backToMenu);
        btnMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ResultActivity.this, StartMenuActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ResultActivity.this, StartMenuActivity.class));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

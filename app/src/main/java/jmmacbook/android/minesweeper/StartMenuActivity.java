package jmmacbook.android.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_screen);

        Button startBtn = (Button) findViewById(R.id.Start);

        startBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent start = new Intent(StartMenuActivity.this, GameActivity.class);
                start.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(start);
            }
        });

        Button instrBtn = (Button) findViewById(R.id.Instructions);
        instrBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent inst = new Intent(StartMenuActivity.this, InstructionsActivity.class);
                inst.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inst);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed()
    {
        //crickets
        //Shouldn't be able to go back from the home screen
    }
}

package jmmacbook.android.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class InstructionsActivity extends AppCompatActivity
{
    ImageView iVInst;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //Used an imageView instead of drawable Background to conserve memory,
        //I would like to know if this is actually a good choice though.
        iVInst = (ImageView) findViewById(R.id.instIV);
        iVInst.setImageResource(R.drawable.instructions);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        iVInst.setImageDrawable(null);
    }
}

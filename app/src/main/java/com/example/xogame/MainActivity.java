package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;


public class MainActivity extends AppCompatActivity {

    boolean f=false;
    int [][]arr=new int[3][3];
    public void fill (View view)
    {
        ImageView imageView=(ImageView)view;
        if( imageView.getDrawable() == null) {
            imageView.setTranslationY(-1000f);

            if (f) {
                imageView.setImageResource(R.drawable.yellow);
                f = false;
                String s = imageView.getTag().toString();
                int i = (int) s.charAt(0) - (int) '0';
                int j = (int) s.charAt(2) - (int) '0';
                arr[i][j] = 1;

                if (WinTest()) {

                    TextView tvWinName = (TextView) findViewById(R.id.WinNameTV);
                    tvWinName.setText("Yellow Team Win!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.WinDecs);
                    layout.setVisibility(View.VISIBLE);

                }




            } else {
                imageView.setImageResource(R.drawable.red);
                f = true;
                String s = imageView.getTag().toString();
                int i = (int) s.charAt(0) - (int) '0';
                int j = (int) s.charAt(2) - (int) '0';
                arr[i][j] = 2;
                if (WinTest()) {
                    TextView tvWinName = (TextView) findViewById(R.id.WinNameTV);
                    tvWinName.setText("Red Team Win!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.WinDecs);
                    layout.setVisibility(View.VISIBLE);

                }
            }

            if(isTie())
            {
                TextView tvWinName = (TextView) findViewById(R.id.WinNameTV);
                tvWinName.setText("Two teams Is Tie!.");
                LinearLayout layout = (LinearLayout) findViewById(R.id.WinDecs);
                layout.setVisibility(View.VISIBLE);

            }

            imageView.animate().translationYBy(1000f).setDuration(400);
        }
    }

    public boolean isTie()
    {
        boolean f=true;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(arr[i][j]==0)
                    f=false;
            }
        }
        return f;
    }
    public boolean WinTest()
    {
        for(int i=0;i<3;i++)
        {
            if(arr[i][0]!=0)
            {
                 if(arr[i][0]==arr[i][1]&&arr[i][0]==arr[i][2])
                  return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(arr[0][i]!=0) {
                if (arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i])
                    return true;
            }
        }

        if(arr[1][1]!=0)
        {
            if(arr[0][0]==arr[1][1]&&arr[0][0]==arr[2][2])return true;
            if(arr[0][2]==arr[1][1]&&arr[1][1]==arr[2][0])return true;
        }
        return false;
    }
    public void playAgain(View v) {

        for(int x=0;x<3;x++)
        {
            for(int y=0;y<3;y++)
            {
                arr[x][y]=0;
            }
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.GridID);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.WinDecs);
        layout.setVisibility(View.INVISIBLE);
    }


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


}
package maverick.crazyfliecommandapp;

import android.app.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import android.view.View.OnClickListener;
import android.view.Window;


import java.io.BufferedWriter;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends Activity implements OnClickListener {


    private Socket socket;

    SeekBar mSeek1;
    SeekBar mSeek2;
    SeekBar mSeek3;
    int red = Color.parseColor("#00ffc5");
    int gray = Color.parseColor("#CCCCCC");


    Button mHover;

    boolean mHoverState = false;

    int mHoverData = 0;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        mHover = (Button) findViewById(R.id.button);
        mHover.setOnClickListener(this);
        mSeek1 = (SeekBar) findViewById(R.id.seekBar3);
        mSeek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progVal1 = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progVal1 = progress;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            //  client = new Socket(etIP.getText().toString(), port);
                            socket = new Socket("192.168.2.14", 1989);

                            PrintWriter printwriter = new
                                    PrintWriter(socket.getOutputStream(), true);
                            printwriter.write(String.valueOf(progVal1));
                            printwriter.flush();
                            printwriter.close();
                            //socket.setKeepAlive(true);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mSeek1.setProgress(0);
            }
        });
        mSeek2 = (SeekBar) findViewById(R.id.seekBar2);
        mSeek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progVal2 = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progVal2 = progress;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            //  client = new Socket(etIP.getText().toString(), port);
                            socket = new Socket("192.168.2.14", 1989);

                            PrintWriter printwriter = new
                                    PrintWriter(socket.getOutputStream(), true);
                            printwriter.write(String.valueOf(progVal2 + 100));
                            printwriter.flush();
                            printwriter.close();
                            //socket.setKeepAlive(true);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mSeek2.setProgress(50);
            }
        });

        mSeek3 = (SeekBar) findViewById(R.id.seekBar);
        mSeek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progVal3 = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progVal3 = progress;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            //  client = new Socket(etIP.getText().toString(), port);
                            socket = new Socket("192.168.2.14", 1989);

                            PrintWriter printwriter = new
                                    PrintWriter(socket.getOutputStream(), true);
                            printwriter.write(String.valueOf(progVal3 + 200));
                            printwriter.flush();
                            printwriter.close();
                            //socket.setKeepAlive(true);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mSeek3.setProgress(50);
            }
        });


    }

    // Called when one of the LED buttons is clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (mHoverState == true) {
                    mHoverState = false;
                    mHoverData = 0;
                    mHover.setText(" Hover");
                    mHover.setBackgroundColor(gray);



                } else {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            try {
                                //  client = new Socket(etIP.getText().toString(), port);

                                socket = new Socket("192.168.2.14", 1989);
                                PrintWriter printwriter = new
                                        PrintWriter(socket.getOutputStream(), true);
                                printwriter.write("Hover");
                                printwriter.flush();
                                printwriter.close();
                                socket.setKeepAlive(true);
                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }).start();


                    mHoverState = true;
                    mHoverData = 1;
                    mHover.setText(" Hover");
                    mHover.setBackgroundColor(red);
                }
                break;


        }


    }

}


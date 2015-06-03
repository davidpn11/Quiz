package quiz.com.david.quiz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends ActionBarActivity {

    private Button button, buttonCancel;
    private EditText player;
    private TextView area_resposta;
    ConnectionService mConn;
    boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("QUIZ");

        button = (Button) findViewById(R.id.conecta);
        player = (EditText) findViewById(R.id.player);
        area_resposta = (TextView) findViewById(R.id.retorno);
        buttonCancel = (Button) findViewById(R.id.btnCancela);
        final Intent it = new Intent(this, ConnectionService.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enviaPlayer = player.getText().toString();

                if (enviaPlayer.equals("")) {
                    Toast.makeText(MenuActivity.this, "Insira algum nome", Toast.LENGTH_SHORT).show();
                    return;
                }
                button.setEnabled(false);
                buttonCancel.setEnabled(true);
                it.putExtra("playerName", enviaPlayer);
                startService(it);
                bindService(it, mConnection, Context.BIND_AUTO_CREATE);
                mBound = true;

             /*   try{
                    Thread.sleep(5000);

                }catch (Exception e){
                    e.printStackTrace();
                }

                connCheck();*/

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    unbindService(mConnection);
                    stopService(it);
                    mBound = false;
                    button.setEnabled(true);
                }
            }
        });

        Button btnResult = (Button) findViewById(R.id.button5);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connCheck();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mBound) {
            unbindService(mConnection);
        }
    }

    public void connCheck() {
        while (mBound) {

            String result = mConn.getResult();

            if (result.equals("error")) {
                area_resposta.setText("Problema na conexão");

            } else if (result.equals("ready")) {
                area_resposta.setText("Inicializando jogo...");
                startActivity(new Intent(MenuActivity.this, QuestionsActivity.class));
            } else
                area_resposta.setText(result + " jogadores restantes para começar o jogo...");

            try{
                Thread.sleep(2000);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.e("onServiceConnected", "OK");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ConnectionService.LocalBinder binder = (ConnectionService.LocalBinder) service;
            mConn = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.e("onServiceDisconnected", "OK");
            mBound = false;
        }
    };


}



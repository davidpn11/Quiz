package quiz.com.david.quiz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Handler;
import android.os.Message;
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
    protected static final int RESULT = 1;
    boolean mBound = false;
    boolean boolHandler = false;
    String perguntas_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("QUIZ");

        button = (Button) findViewById(R.id.conecta);
        player = (EditText) findViewById(R.id.player);
        area_resposta = (TextView) findViewById(R.id.retorno);
        buttonCancel = (Button) findViewById(R.id.btnCancela);
        final Button btnResult = (Button) findViewById(R.id.button5);
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
                btnResult.setEnabled(true);
                it.putExtra("playerName", enviaPlayer);
                startService(it);
                bindService(it, mConnection, Context.BIND_AUTO_CREATE);
                mBound = true;
                boolHandler = true;

                connCheck();

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    unbindService(mConnection);
                    stopService(it);
                    mBound = false;
                    boolHandler = false;
                    button.setEnabled(true);
                    buttonCancel.setEnabled(false);
                    btnResult.setEnabled(false);
                }
            }
        });


        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnResult.setEnabled(false);
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("HANDLER", "called");
            super.handleMessage(msg);
            switch(msg.what){
                case RESULT:
                    String post = msg.getData().getString("resultado");
                    area_resposta.setText(post);
                    break;
                default:
                    break;
            }
        }
    };

    public void connCheck() {
        Log.e("CONN_CHECK", "called");
        new Thread() {
            @Override
            public void run() {

                String result,resposta;

                try {
                    while (boolHandler) {

                        Message mensagem = new Message();
                        mensagem.what = RESULT;
                        Bundle b = new Bundle();
                        result = mConn.getResult();
                        perguntas_string = mConn.getPerguntas();

                        if(result!="") {
                            if (result.equals("error")) {
                                resposta = "Problema na conexão";

                            } else if (result.equals("ready")) {
                                resposta = "Inicializando jogo...";
                                boolHandler = false;
                                Thread.sleep(2000);
                                Intent it = new Intent(MenuActivity.this, QuestionsActivity.class);
                                it.putExtra("perguntas_string", perguntas_string);
                                startActivity(it);
                            } else if(result.equals("wait")){
                                resposta = "Aguarde";

                            } else
                                resposta = result + " jogadores restantes para começar o jogo...";

                            b.putString("resultado", resposta);
                            mensagem.setData(b);
                            handler.sendMessage(mensagem);
                            Thread.sleep(2000);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

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



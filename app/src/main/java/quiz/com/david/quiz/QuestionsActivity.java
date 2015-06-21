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
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QuestionsActivity extends ActionBarActivity {

    private ImageButton btntemp, button1, button2, button3, button4, button5, button6, button7, button8;
    private GridLayout gridLayout;
    private int RESPOSTA;
    ConnectionService mConn;
    protected static final int RESULT = 1;
    boolean mBound = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setTitle("Escolha uma pergunta");

        //button = (ImageButton) findViewById(R.id.getPergunta);
        gridLayout = (GridLayout) findViewById(R.id.grid);
        int x = 4;
        gridLayout.setRowCount(x);

        try {
            final JSONArray lista_perguntas = StringToJson(getIntent().getStringExtra("perguntas_string"));


            Log.e("Pergunta2",lista_perguntas.get(1).toString());

            final Intent it = new Intent(this, ConnectionService.class);

            bindService(it, mConnection, Context.BIND_AUTO_CREATE);
            mBound = true;



         //   lista_perguntas = geraArray();//mConn.getPerguntas();

            listeners(lista_perguntas);
        } catch (JSONException e) {

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mBound) {
            unbindService(mConnection);
        }
    }


    public void listeners(final JSONArray array) throws JSONException {
        button1 = (ImageButton) findViewById(R.id.b1);
        button2 = (ImageButton) findViewById(R.id.b2);
        button3 = (ImageButton) findViewById(R.id.b3);
        button4 = (ImageButton) findViewById(R.id.b4);
        button5 = (ImageButton) findViewById(R.id.b5);
        button6 = (ImageButton) findViewById(R.id.b6);
        button7 = (ImageButton) findViewById(R.id.b7);
        button8 = (ImageButton) findViewById(R.id.b8);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                btntemp = button1;

                try {
                    JSONObject json = array.getJSONObject(0);
                    String livre;
                    Log.e("Pergunta1",array.get(0).toString());
                    mConn.setParametro("getmutex.0");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button2;

                try {
                    JSONObject json = array.getJSONObject(1);
                    String livre;
                    Log.e("Pergunta2",array.get(1).toString());
                    mConn.setParametro("getmutex.1");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button3;

                try {
                    JSONObject json = array.getJSONObject(2);
                    String livre;
                    Log.e("Pergunta3",array.get(2).toString());
                    mConn.setParametro("getmutex.2");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button4;

                try {
                    JSONObject json = array.getJSONObject(3);
                    String livre;
                    Log.e("Pergunta4",array.get(3).toString());
                    mConn.setParametro("getmutex.3");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button5;

                try {
                    JSONObject json = array.getJSONObject(4);
                    String livre;
                    Log.e("Pergunta5",array.get(4).toString());
                    mConn.setParametro("getmutex.4");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button6;
                try {
                    JSONObject json = array.getJSONObject(5);
                    String livre;
                    Log.e("Pergunta6",array.get(5).toString());
                    mConn.setParametro("getmutex.5");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button7;

                try {
                    JSONObject json = array.getJSONObject(6);
                    String livre;
                    Log.e("Pergunta7",array.get(6).toString());
                    mConn.setParametro("getmutex.6");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button8;

                try {
                    JSONObject json = array.getJSONObject(7);
                    String livre;
                    Log.e("Pergunta8",array.get(7).toString());
                    mConn.setParametro("getmutex.7");
                    livre = mConn.getResult();
                    Log.v("",livre);
                    if(livre.equals("1")) {

                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("0")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });


    }

    public JSONArray StringToJson(String s){
        try {
            JSONObject jsnobject = new JSONObject(s);
            JSONArray jsonArray = jsnobject.getJSONArray("Perguntas");

            JSONObject x = jsonArray.getJSONObject(0);
            Log.e("JSON: ", x.toString());

            return jsonArray;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    public JSONArray geraArray() {
        JSONArray array = new JSONArray();

        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONObject obj3 = new JSONObject();
        JSONObject obj4 = new JSONObject();


        try {
            obj1.put("pergunta", "Pergunta FEITA1");
            obj1.put("op1", "OPCAO 1");
            obj1.put("op2", "OPCAO 2");
            obj1.put("op3", "OPCAO 3");
            obj1.put("op4", "OPCAO 4");
            obj1.put("resposta", 2);

            obj2.put("pergunta", "Pergunta FEITA2");
            obj2.put("op1", "OPCAO 1");
            obj2.put("op2", "OPCAO 2");
            obj2.put("op3", "OPCAO 3");
            obj2.put("op4", "OPCAO 4");
            obj2.put("resposta", 2);

            obj3.put("pergunta", "Pergunta FEITA3");
            obj3.put("op1", "OPCAO 1");
            obj3.put("op2", "OPCAO 2");
            obj3.put("op3", "OPCAO 3");
            obj3.put("op4", "OPCAO 4");
            obj3.put("resposta", 2);

            obj4.put("pergunta", "Pergunta FEITA4");
            obj4.put("op1", "OPCAO 1");
            obj4.put("op2", "OPCAO 2");
            obj4.put("op3", "OPCAO 3");
            obj4.put("op4", "OPCAO 4");
            obj4.put("resposta", 2);

            array.put(obj1);
            array.put(obj2);
            array.put(obj3);
            array.put(obj4);

            return array;

        } catch (JSONException e) {
            return null;
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


    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == RESPOSTA) {
            if (resultCode == 1) {
                btntemp.setBackgroundResource(R.drawable.round_certo);
                btntemp.setImageResource(R.drawable.correta);
                btntemp.setClickable(false);
            } else if (resultCode == 0) {
                btntemp.setBackgroundResource(R.drawable.round_errado);
                btntemp.setImageResource(R.drawable.errada);
                btntemp.setClickable(false);
            }
        }
    }


}

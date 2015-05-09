package quiz.com.david.quiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class QuizActivity extends ActionBarActivity {

    private final String LOG_TAG = QuizActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        final TextView pergunta = (TextView) findViewById(R.id.pergunta);
        final Button button = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);

        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("objeto"));

            final int ans = obj.getInt("resposta");


            pergunta.setText(obj.getString("pergunta"));
            button.setText(obj.getString("op1"));
            button2.setText(obj.getString("op2"));
            button3.setText(obj.getString("op3"));
            button4.setText(obj.getString("op4"));



            button.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    int respondida = 1;

                    if (respondida == ans) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();


                   /* button.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    button2.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    button3.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    button4.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    button.setClickable(false);
                    button2.setClickable(false);
                    button3.setClickable(false);
                    button4.setClickable(false);
                    Toast.makeText(QuizActivity.this, "ERRADO! FECHE PARA TENTAR DE NOVO!", Toast.LENGTH_SHORT).show();*/

                }
            });

            button2.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    int respondida = 2;

                    if (respondida == ans) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();

                }
            });

            button3.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    int respondida = 3;

                    if (respondida == ans) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();

                  /*  button.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    button2.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    button3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                    button4.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    button.setClickable(false);
                    button2.setClickable(false);
                    button3.setClickable(false);
                    button4.setClickable(false);
                    Toast.makeText(QuizActivity.this, "RESPOSTA CORRETA!", Toast.LENGTH_SHORT).show();*/
                }
            });

            button4.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    int respondida = 4;

                    if (respondida == ans) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();

                }
            });



        } catch (JSONException e){

        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

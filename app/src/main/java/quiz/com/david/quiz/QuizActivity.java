package quiz.com.david.quiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class QuizActivity extends ActionBarActivity {


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

            Log.e("Pergunta_OBJ", obj.toString());

            final String ans = obj.getString("answer");
            pergunta.setText(obj.getString("pergunta"));
            button.setText(obj.getString("op1"));
            button2.setText(obj.getString("op2"));
            button3.setText(obj.getString("op3"));
            button4.setText(obj.getString("op4"));



            button.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    String respondida = "1";

                    if (respondida.equals(ans)) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();

                }
            });

            button2.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    String respondida = "2";

                    if (respondida.equals(ans)) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();

                }
            });

            button3.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    String respondida = "3";

                    if (respondida.equals(ans)) {
                        setResult(1);
                    }
                    else
                        setResult(0);

                    finish();

                }
            });

            button4.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    String respondida = "4";

                    if (respondida.equals(ans)) {
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
}

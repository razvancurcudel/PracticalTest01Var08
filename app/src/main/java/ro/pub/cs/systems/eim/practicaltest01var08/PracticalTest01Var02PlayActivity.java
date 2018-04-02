package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    EditText riddleEditText;
    EditText answerEditText;
    EditText attemptEditText;
    Button checkBtn;
    Button backBtn;

    Receiver receiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);


        riddleEditText = findViewById(R.id.secondRiddle);
        answerEditText = findViewById(R.id.secondAnswer);
        attemptEditText = findViewById(R.id.secondAttempt);

        Intent intent = getIntent();
        riddleEditText.setText(intent.getStringExtra("Riddle"));
        answerEditText.setText(intent.getStringExtra("Answer"));

        checkBtn = findViewById(R.id.check);
        backBtn = findViewById(R.id.back);

        Intent startServIntent = new Intent(this, PracticalTest01Var08Service.class);
        startServIntent.putExtra("ServiceAnswer", answerEditText.getText().toString());
        startService(startServIntent);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String attemptText = attemptEditText.getText().toString();
                String answerText = answerEditText.getText().toString();

                if (answerText.equals(attemptText)) {
                    Toast.makeText(getApplication(), "Attempt is correct. Hooray!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(), "Attempt is not correct. Boooo!", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Intent servIntent = new Intent(this, PracticalTest01Var08Service.class);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                stopService(servIntent);
                finish();
            }
        });


        receiver = new Receiver();

        filter = new IntentFilter();
        filter.addAction(Constants.ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

}

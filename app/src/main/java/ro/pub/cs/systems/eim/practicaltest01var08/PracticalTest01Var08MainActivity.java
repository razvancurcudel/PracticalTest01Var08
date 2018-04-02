package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    EditText riddleEditText;
    EditText answerEditText;
    Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddleEditText = findViewById(R.id.riddle);
        answerEditText = findViewById(R.id.answer);
        playBtn = findViewById(R.id.play);

        final Intent intent = new Intent(this, PracticalTest01Var02PlayActivity.class);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (riddleEditText.getText().toString().equals("")
                        || answerEditText.getText().toString().equals("")
                        ) {
                    return;
                }

                String riddleText = riddleEditText.getText().toString();
                String answerText = answerEditText.getText().toString();

                intent.setAction("START_SECOND");
                intent.putExtra("Riddle", riddleText);
                intent.putExtra("Answer", answerText);

                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Riddle", riddleEditText.getText().toString());
        outState.putString("Answer", answerEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("Riddle")) {
            riddleEditText.setText(savedInstanceState.getString("Riddle"));
        }
        if (savedInstanceState.containsKey("Answer")) {
            riddleEditText.setText(savedInstanceState.getString("Answer"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.REQUEST_CODE:
                Toast.makeText(this, "Activity returned with result " + (resultCode == -1 ? "OK" : "Canceled"), Toast.LENGTH_LONG).show();
                break;
        }
    }

}

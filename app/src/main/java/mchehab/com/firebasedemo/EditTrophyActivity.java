package mchehab.com.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

public class EditTrophyActivity extends AppCompatActivity {

    private EditText editTextSport;
    private EditText editTextYear;
    private EditText editTextDescription;
    private Button button;

    private DatabaseReference databaseReference;

    private Trophy trophy = new Trophy();

    private boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trophy);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        initUI();
        setButtonOnClickListener();
        handleBundle();
        initUIFromTrophy();
    }

    private void initUI(){
        editTextSport = findViewById(R.id.editTextSport);
        editTextYear = findViewById(R.id.editTextYear);
        editTextDescription = findViewById(R.id.editTextDescription);
        button = findViewById(R.id.button);
    }

    private void initUIFromTrophy(){
        editTextSport.setText(trophy.getSport());
        editTextYear.setText(trophy.getYear());
        editTextDescription.setText(trophy.getDescription());
    }

    private void setButtonOnClickListener(){
        button.setOnClickListener(e -> {
            String sport = editTextSport.getText().toString();
            String year = editTextYear.getText().toString();
            String description = editTextDescription.getText().toString();

            trophy.setSport(sport);
            trophy.setYear(year);
            trophy.setDescription(description);

            if(edit){
                databaseReference.child(trophy.getKey()).setValue(trophy);
            }else{
                String key = databaseReference.push().getKey();
                trophy.setKey(key);
                databaseReference.child(key).setValue(trophy);
            }
            finish();
        });
    }

    private void handleBundle(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            edit = bundle.getBoolean("edit");
            if(edit){
                trophy = Parcels.unwrap(bundle.getParcelable("trophy"));
            }
        }
    }
}
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

    private Trophy person = new Trophy();

    private boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trophy);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        initUI();
        setButtonOnClickListener();
        handleBundle();
        initUIFromPerson();
    }

    private void initUI(){
        editTextSport = findViewById(R.id.editTextSport);
        editTextYear = findViewById(R.id.editTextYear);
        editTextDescription = findViewById(R.id.editTextDescription);
        button = findViewById(R.id.button);
    }

    private void initUIFromPerson(){
        editTextSport.setText(person.getSport());
        editTextYear.setText(person.getYear());
        editTextDescription.setText(person.getDescription() + "");
    }

    private void setButtonOnClickListener(){
        button.setOnClickListener(e -> {
            String firstName = editTextSport.getText().toString();
            String lastName = editTextYear.getText().toString();
            String age = editTextDescription.getText().toString();

            person.setSport(firstName);
            person.setYear(lastName);
            person.setDescription(age);

            if(edit){
                databaseReference.child(person.getKey()).setValue(person);
            }else{
                String key = databaseReference.push().getKey();
                person.setKey(key);
                databaseReference.child(key).setValue(person);
            }
            finish();
        });
    }

    private void handleBundle(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            edit = bundle.getBoolean("edit");
            if(edit){
                person = Parcels.unwrap(bundle.getParcelable("person"));
            }
        }
    }
}
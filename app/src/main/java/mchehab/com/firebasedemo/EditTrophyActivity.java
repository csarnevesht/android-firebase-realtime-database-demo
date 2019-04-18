package mchehab.com.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

public class EditTrophyActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAge;
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
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        button = findViewById(R.id.button);
    }

    private void initUIFromPerson(){
        editTextFirstName.setText(person.getSport());
        editTextLastName.setText(person.getYear());
        editTextAge.setText(person.getDescription() + "");
    }

    private void setButtonOnClickListener(){
        button.setOnClickListener(e -> {
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String age = editTextAge.getText().toString();

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
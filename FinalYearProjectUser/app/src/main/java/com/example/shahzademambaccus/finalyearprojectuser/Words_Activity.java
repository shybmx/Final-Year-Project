package com.example.shahzademambaccus.finalyearprojectuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


public class Words_Activity extends AppCompatActivity {
    private TableLayout table;
    private ImageDatabase imageDatabase;
    private static int NUMBER_OF_ROWS = 8;
    private static int NUMBER_OF_COLUMNS = 1;
    private String[] words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);
        TableLayout table = (TableLayout) findViewById(R.id.tableForImages);
        this.imageDatabase = new ImageDatabase(this);
        this.table = table;
    }

    public void search(View v) {
        clearScreen();
        String[] typedWords = getSearchTerm().split(" ");
        //words = typedWords;
        for (int row = 0; row < typedWords.length; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            table.addView(tableRow);
            for (int col = 0; col < NUMBER_OF_COLUMNS /*typedWords.length*/; col++) {
                ImageView image = new ImageView(this);
                image.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                tableRow.addView(image);
                imageDatabase.imageFromDatabase(typedWords[row], image);

            }
        }
    }

    public void clearScreen(){
        table.removeAllViews();
    }

    public String[] getTypedWords(){
        return words;
    }

    public String getSearchTerm() {
        EditText searchTermET = (EditText) findViewById(R.id.SearchTerm);
        String searchTermTxt = searchTermET.getText().toString();
        if(searchTermTxt.isEmpty()){
            Toast.makeText(this, "Insert Text to be translated", Toast.LENGTH_LONG).show();
        }
        return searchTermTxt;
    }


}

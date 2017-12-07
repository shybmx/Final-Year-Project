package com.example.shahzademambaccus.finalyearprojectuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


public class Words_Activity extends AppCompatActivity {
    private TableLayout table;
    private ImageDatabase imageDatabase;
    private ImageView[] imageArray;
    private static int NUMBER_OF_ROWS = 1;
    private static int NUMBER_OF_COLUMNS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);
        // image = (ImageView) findViewById(R.id.Image);
        this.imageDatabase = new ImageDatabase(this);
        TableLayout table = (TableLayout) findViewById(R.id.tableForImages);
        this.table = table;
    }

    public void search(View v) {
        table.removeAllViews();
        String[] words = getSearchTerm().split(" ");
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            table.addView(tableRow);
            for (int col = 0; col < /*NUMBER_OF_COLUMNS*/ words.length; col++) {
                ImageView image = new ImageView(this);
                image.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                //Button button = new Button(this);
                tableRow.addView(image);
                // tableRow.addView(button);
                imageDatabase.imageFromDatabase(/*getSearchTerm()*/words[col], image);
            }
        }

        //image.setImageResource(0);
        //imageDatabase.imageFromDatabase(getSearchTerm());
    }

    public String getSearchTerm() {
        EditText searchTermET = (EditText) findViewById(R.id.SearchTerm);
        String searchTermTxt = searchTermET.getText().toString();
        return searchTermTxt;
    }

    public ImageView[] getImageArray() {
        return imageArray;
    }

}

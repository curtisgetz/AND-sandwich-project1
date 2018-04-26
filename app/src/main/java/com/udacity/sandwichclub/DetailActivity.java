package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    //TextView variables
    private TextView mAkaTv, mOriginTv, mDescriptionTv, mIngredientsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        //get TextViews by ID
        mAkaTv = findViewById(R.id.also_known_tv);
        mOriginTv = findViewById(R.id.origin_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        mIngredientsTv = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        //pass Sandwich object to populateUI
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

                        setTitle(sandwich.getMainName());
                        }

private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
        }

private void populateUI(Sandwich sandwich) {

        //set text to 'unknown' if there's no value
        if(sandwich.getAlsoKnownAs().size() == 0){
            mAkaTv.setText(getString(R.string.unknown_detail));
        }else {
            //iterate through List of AKA and append to TextView
            for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
                mAkaTv.append(sandwich.getAlsoKnownAs().get(i));
                //Add comma if not the last item in List
                if ((sandwich.getAlsoKnownAs().size() - i) > 1) {
                    mAkaTv.append(", ");
                }
            }
        }

        //set text to 'unknown' if there's no value, or populate with value
        if(sandwich.getPlaceOfOrigin().isEmpty()){
            mOriginTv.setText(getString(R.string.unknown_detail));
        } else {
            mOriginTv.setText(sandwich.getPlaceOfOrigin());
        }

        //set text to 'unknown' if there's no value, or populate with value
        if(sandwich.getDescription().isEmpty()) {
            mDescriptionTv.setText(sandwich.getDescription());
        }else {
            mDescriptionTv.setText(sandwich.getDescription());
        }

        //set text to 'unknown' if there's no value
        if(sandwich.getIngredients().size() == 0){
            mIngredientsTv.setText(getString(R.string.unknown_detail));
        }else {
            //iterate through List of AKA and append to TextView
            for (int i = 0; i < sandwich.getIngredients().size(); i++) {
                mIngredientsTv.append(sandwich.getIngredients().get(i));
                //Add comma if not the last item in List
                if ((sandwich.getIngredients().size() - i) > 1) {
                    mIngredientsTv.append(", ");
                }
            }
        }



    }

}

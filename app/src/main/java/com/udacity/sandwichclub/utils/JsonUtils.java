package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //Variables for JSON keys
    private static final String NAME_OBJECT_KEY = "name";
    private static final String MAIN_NAME_KEY = "mainName";
    private static final String AKA_ARRAY_KEY = "alsoKnownAs";
    private static final String ORIGIN_KEY = "placeOfOrigin";
    private static final String DESCRIPTION_KEY = "description";
    private static final String IMAGE_SRC_KEY  = "image";
    private static final String INGREDIENTS_ARRAY_KEY = "ingredients";



    public static Sandwich parseSandwichJson(String json) {

        try{

            //Create JSON object from String argument
            JSONObject sandwichObject = new JSONObject(json);
            //Variables for JSON values
            String mainName, origin, description, imageSrc ;
            List<String> akaList = new ArrayList<>();
            List<String> ingredientsList = new ArrayList<>();

            //get JSON values
            origin = sandwichObject.getString(ORIGIN_KEY);
            description = sandwichObject.getString(DESCRIPTION_KEY);
            imageSrc = sandwichObject.getString(IMAGE_SRC_KEY);

            JSONObject nameObject = sandwichObject.getJSONObject(NAME_OBJECT_KEY);
            mainName = nameObject.getString(MAIN_NAME_KEY);

            JSONArray akaArray = nameObject.getJSONArray(AKA_ARRAY_KEY);
            //iterate through AKA array and add to aka String

            for(int i = 0; i < akaArray.length(); i++){
                akaList.add(akaArray.getString(i));
            }
            //iterate through ingredients and add to ingredients List
            JSONArray ingredientsArray = sandwichObject.getJSONArray(INGREDIENTS_ARRAY_KEY);
            for(int i = 0; i < ingredientsArray.length(); i++){
                ingredientsList.add(ingredientsArray.getString(i));
            }

            //return new Sandwich object
            return new Sandwich(mainName, akaList, origin, description, imageSrc, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

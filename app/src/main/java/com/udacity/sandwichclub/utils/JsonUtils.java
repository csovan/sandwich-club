package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try
        {
            /*Get the JSONObject representing sandwich details data*/
            JSONObject sandwichDetailsData = new JSONObject(json);

            /*Extract sandwich details json data from strings.xml*/
            /*Get JSONObject called name*/
            JSONObject nameObject = sandwichDetailsData.getJSONObject("name");
            /*Get Key mainName*/
            String mainName = nameObject.getString("mainName");
            /*Get JSONArray called alsoKnownAs*/
            JSONArray akaArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> akaList = new ArrayList<>();
            /*Use a loop to loop through akaArray items*/
            for (int i = 0; i<akaArray.length(); i++){
                akaList.add(akaArray.getString(i));
            }
            String placeOfOrigin = sandwichDetailsData.getString("placeOfOrigin");
            String description = sandwichDetailsData.getString("description");
            String imageURL = sandwichDetailsData.getString("image");
            JSONArray ingredientsArray = sandwichDetailsData.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i<ingredientsArray.length(); i++){
                ingredientsList.add(ingredientsArray.getString(i));
            }
            Sandwich sandwichObject = new Sandwich(mainName,akaList,placeOfOrigin,description,imageURL,ingredientsList);
            return sandwichObject;
        }
        catch (JSONException e)
        {
            e.printStackTrace();

        }
        return null;
    }
}

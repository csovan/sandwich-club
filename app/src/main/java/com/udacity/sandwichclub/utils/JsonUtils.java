package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        /*JSON data is provided as a string-array in strings.xml file in res/values dir*/

        try
        {
            /*Create a JSONObject from the json string parameter*/
            JSONObject sandwichDetailsJson = new JSONObject(json);

            /*Extract sandwich details json data from strings.xml*/

            /*Get JSONObject called name*/
            JSONObject nameObject = sandwichDetailsJson.getJSONObject("name");
            /*Get Key String called mainName*/
            String mainName = nameObject.getString("mainName");
            /*Get JSONArray called alsoKnownAs*/
            JSONArray akaArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> akaList = new ArrayList<>();
            /*Use a loop to loop through akaArray items*/
            for (int i = 0; i<akaArray.length(); i++){
                akaList.add(akaArray.getString(i));
            }
            /*Get Key String called placeOfOrigin*/
            String placeOfOrigin = sandwichDetailsJson.getString("placeOfOrigin");
            /*Get Key String called description*/
            String description = sandwichDetailsJson.getString("description");
            /*Get Key String called image*/
            String imageURL = sandwichDetailsJson.getString("image");
            /*Get JSONArray called ingredients*/
            JSONArray ingredientArray = sandwichDetailsJson.getJSONArray("ingredients");
            List<String> ingredientList = new ArrayList<>();
            /*Use a loop to loop through ingredientArray items*/
            for (int i = 0; i<ingredientArray.length(); i++){
                ingredientList.add(ingredientArray.getString(i));
            }
            /*Return new Sandwich object*/
            return new Sandwich(mainName,akaList,placeOfOrigin,description,imageURL,ingredientList);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

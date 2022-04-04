import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public static JSONArray getArrayFromFile(String fileName){
        JSONArray movies = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
            movies = (JSONArray) jsonObject.get("Movies");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static  void editFieldFromMovie(String fieldName, JSONArray jsonArr,JSONObject movies2 ) throws IOException {
        System.out.println("Current " +fieldName+" is: ");
        System.out.println(movies2.get(fieldName));
        System.out.println("Enter a new " + fieldName+ ":");
        Scanner l = new Scanner(System.in);
        String enterName = l.nextLine();
        movies2.put(fieldName, enterName);
        System.out.println("New " + fieldName + " is: ");
        System.out.println(movies2.get(fieldName));

        System.out.println(movies2);
        saveObjToFile(jsonArr);
    }

    public static void saveObjToFile(JSONArray jsonArr) throws IOException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Movies",jsonArr);
        FileWriter fileToWrite = new FileWriter("mov.json", false);
        try {
            fileToWrite.write(jsonObj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileToWrite.flush();
        fileToWrite.close();
    }
}

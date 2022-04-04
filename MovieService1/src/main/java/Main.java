import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {

        int id;
        String name;
        String genre;
        float rating;
        int duration;
        int year;
        String fileName = "mov.json";

        Movie tempMovie = new Movie();

        Scanner scan = new Scanner(System.in);
        int vvod = 0;
        while (vvod != 8) {

            if (vvod == 0) {
                System.out.println("\n\n_________MENU_________\n" +
                        "| Hello,please select the menu number: |\n" +
                        "| 1.         Add data |\n" +
                        "| 2.        Edit data |\n" +
                        "| 3.      Delete data |\n" +
                        "| 4.        View data |\n" +
                        "| 5.      Data search |\n" +
                        "| 6. View sorted data |\n" +
                        "| 7.      Import data |\n" +
                        "| 8.             Exit |\n" +
                        "|---------------------|\n");
            }
            vvod = scan.nextInt();

            if (vvod > 8) {
                System.out.println("Your choice is not correct.Please, try again.");
                continue;
            }

            if (vvod == 1) {

                System.out.println("You selected ADDING.");

                System.out.println("Please,add some information.");
                Scanner in = new Scanner(System.in);

                System.out.println("Enter name: ");
                tempMovie.name = in.nextLine();
                System.out.println("Enter genre: ");
                tempMovie.genre = in.nextLine();
                System.out.println("Enter id: ");
                tempMovie.id = in.nextInt();
                System.out.println("Enter rating: ");
                tempMovie.rating = in.nextFloat();
                System.out.println("Enter duration: ");
                tempMovie.duration = in.nextInt();
                System.out.println("Enter year: ");
                tempMovie.year = in.nextInt();

                System.out.println("Do you want to save the data you entered?(y/n).");
                String save = in.next();

                if (save.equals("y")) {
                    {
                        File file = new File("mov.json");
                        if (!file.exists()) {
                            System.out.println("No file.");
                        } else {
                            JSONArray movies = FileManager.getArrayFromFile(fileName);

                            movies.add(movieToJson(tempMovie));

                            FileManager.saveObjToFile(movies);

                            showDataFromFile(fileName);
                            System.out.println("To return to the menu,press the number 0.");

                        }
                    }
                }
            }

            if (vvod == 2) {
                System.out.println("You selected EDITING.");
                JSONArray array2  = FileManager.getArrayFromFile(fileName);

                showDataFromFile(fileName);

                System.out.println("Enter the ID of the object in which you want to make the change: ");
                Scanner in = new Scanner(System.in);
                long numOfId2 = in.nextLong();

                for (int i = 0; i < array2.size(); i++) {
                    JSONObject movies2 = (JSONObject) array2.get(i);
                    long iid2 = (Long) movies2.get("id");
                    if (numOfId2 == iid2) {
                        System.out.println(array2.get(i));
                        System.out.println("Please select the number of what you want to change: ");
                        System.out.println("| 1.     Name |\n" +
                                           "| 2.    Genre |\n" +
                                           "| 3.       ID |\n" +
                                           "| 4.   Rating |\n" +
                                           "| 5. Duration |\n" +
                                           "| 6.     Year |\n");

                        int numOfSelect = in.nextInt();
                        String[] fieldNames = new String[]{"name","genre","id","rating","duration","year"};
                        FileManager.editFieldFromMovie(fieldNames[numOfSelect-1],array2,movies2);
                        break;
                    }
                    if (i == array2.size() - 1) {
                        movies2 = null;
                        System.out.println("Not founded.");
                    }
                }
                System.out.println("To return to the menu,press the number 0.");
            }

            if (vvod == 3) {
                System.out.println("You selected REMOVING.");
                JSONArray array3 = FileManager.getArrayFromFile(fileName);

                showDataFromFile(fileName);

                System.out.println("Please enter the ID number to delete the item: ");
                Scanner n = new Scanner(System.in);
                long numOfId = n.nextLong();

                for (int i = 0; i < array3.size(); i++) {
                    JSONObject movies3 = (JSONObject) array3.get(i);
                    long iid3 = (Long) movies3.get("id");
                    if (numOfId == iid3) {
                        array3.remove(movies3);
                    }
                }
                FileManager.saveObjToFile(array3);

                showDataFromFile(fileName);
                System.out.println("To return to the menu,press the number 0.");
            }

            if (vvod == 4) {
                System.out.println("You selected VIEWING.");
                showDataFromFile(fileName);
                System.out.println("To return to the menu,press the number 0.");
            }

            if (vvod == 5) {
                System.out.println("You selected SEARCHING.");
                JSONArray array5 = FileManager.getArrayFromFile(fileName);
                System.out.println("Please, enter the ID number to search information about movie: ");
                Scanner in = new Scanner(System.in);
                long numOfSearch = in.nextLong();

                for (int i = 0; i < array5.size(); i++) {
                    JSONObject movies5 = (JSONObject) array5.get(i);
                    long iid5 = (Long) movies5.get("id");
                    if (numOfSearch == iid5) {
                        System.out.println(array5.get(i));
                        break;
                    }

                    if (i == array5.size() - 1) {
                        System.out.println("Not founded.");
                    }
                }
                System.out.println("To return to the menu,press the number 0.");
            }

            if (vvod == 6) {
                System.out.println("You selected VIEWING SORTED DATA.");
                JSONArray array3 = FileManager.getArrayFromFile(fileName);

                System.out.println("Please enter the number of the criteria by which you want to sort the movies: "
                        + "\n" +
                        "| 1.   ID | \n" +
                        "| 2. Year | \n");
                Scanner in = new Scanner(System.in);
                int numOfSort = in.nextInt();
                long[] sortArr = new long[array3.size()];


                if (numOfSort == 1) {
                    for (int i = 0; i < array3.size(); i++) {
                        JSONObject movies3 = (JSONObject) array3.get(i);
                        long id3 = (Long) movies3.get("id");
                        sortArr[i] = (Long) movies3.get("id");
                    }

                    for (int i = 0; i < sortArr.length; i++) {
                        for (int j = 0; j < sortArr.length - 1; j++) {
                            if (sortArr[j] > sortArr[j + 1]) {
                                long temp = sortArr[j];
                                sortArr[j] = sortArr[j + 1];
                                sortArr[j + 1] = temp;
                            }
                        }
                    }

                    for (int j = 0; j < sortArr.length; j++) {
                        for (int i = 0; i < array3.size(); i++) {
                            JSONObject movies5 = (JSONObject) array3.get(i);
                            long iid5 = (Long) movies5.get("id");
                            if (sortArr[j] == iid5) {
                                System.out.println(array3.get(i));
                            }
                        }
                    }
                }

                if (numOfSort == 2) {
                    System.out.println("\n");
                    for (int i = 0; i < array3.size(); i++) {
                        JSONObject movies3 = (JSONObject) array3.get(i);
                        long year3 = (Long) movies3.get("year");
                        sortArr[i] = (Long) movies3.get("year");
                    }

                    for (int i = 0; i < sortArr.length; i++) {
                        for (int j = 0; j < sortArr.length - 1; j++) {
                            if (sortArr[j] > sortArr[j + 1]) {
                                long temp = sortArr[j];
                                sortArr[j] = sortArr[j + 1];
                                sortArr[j + 1] = temp;
                            }
                        }
                    }

                    for (int j = 0; j < sortArr.length; j++) {
                        for (int i = 0; i < array3.size(); i++) {
                            JSONObject movies5 = (JSONObject) array3.get(i);
                            long year3 = (Long) movies5.get("year");
                            if (sortArr[j] == year3) {
                                System.out.println(array3.get(i));
                            }
                        }
                    }
                }
                System.out.println("\n");
                System.out.println("To return to the menu,press the number 0.");
            }

            if(vvod == 7){
                System.out.println("Please,enter the name of file: ");
                Scanner in = new Scanner(System.in);
                String nameOfFile = in.nextLine();
                showDataFromFile(nameOfFile);
                System.out.println("To return to the menu,press the number 0.");
            }

            if (vvod == 8) {
                System.out.println("Are you sure you want to exit?(y/n).");
                String exit = scan.next();
                if (exit.equals("y")) {
                }
                if (exit.equals("n")) {
                    System.out.println("To return to the menu,press the number 0.");
                    vvod = scan.nextInt();
                }
            }
        }
    }

     public static  void showDataFromFile(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(new FileReader(fileName));

            JSONArray array = (JSONArray) jsonObj.get("Movies");

            System.out.println(fixLen ("ID",5) +
                    fixLen ("NAME",20) +
                    fixLen( "GENRE",10) +
                    fixLen( "RATING",8) +
                    fixLen("DURATION",10) +
                    fixLen("YEAR",5));

            for (int i = 0; i < array.size(); i++) {
                JSONObject movie = (JSONObject) array.get(i);

                System.out.println(fixLen( movie.get("id").toString(),5) +
                        fixLen( movie.get("name").toString(),20) +
                        fixLen( movie.get("genre").toString(),10) +
                        fixLen( movie.get("rating").toString(),8) +
                        fixLen( movie.get("duration").toString(),10) +
                        fixLen( movie.get("year").toString(),5));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fixLen(String string, int length) {
        return String.format("%1$-"+length+ "s", string);
    }

    public static JSONObject movieToJson(Movie model){
        JSONObject newObject = new JSONObject();
        newObject.put("name", model.name);
        newObject.put("genre", model.genre);
        newObject.put("id", model.id);
        newObject.put("rating", model.rating);
        newObject.put("duration", model.duration);
        newObject.put("year", model.year);

        return newObject;
    }
}








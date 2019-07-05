package util;

public class Utilities {

    public static String appendNumberPosition(int key){
        String appended = "";
        if(key == 3 || (key > 10 && (key % 10) == 3)){
            appended = "rd";
        }
        else if(key == 2 || (key > 10 && (key % 10) == 2)){
            appended = "nd";
        }
        else if(key == 1 || (key > 10 && (key % 10) == 1)){
            appended = "st";
        }
        else{
            appended = "th";
        }
        return String.valueOf(key)+appended;
    }
}

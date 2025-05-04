package Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorValidations {
   
    public static boolean isTextInserted(String text){
        String regex= "[\\D]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(text.isEmpty() || text.equals(" ") || !matcher.matches()){
            return false;
        }
        return true;
    }
}

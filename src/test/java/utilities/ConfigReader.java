package utilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigReader {

    private static Properties properties;
    static {
//        path of the config file
        String path = "configuration.properties";
        try {
//            Opening the file
            FileInputStream file = new FileInputStream(path);
//            loading the file
            properties= new Properties();
            properties.load(file);
//            closing the file
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    This method accepts the key and returns the value
    public static String getProperty(String key){
        return substituteProperties(properties.getProperty(key));
    }

    private static String substituteProperties(String value) {
        if (value == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}");
        Matcher matcher = pattern.matcher(value);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String propertyKey = matcher.group(1);
            String replacement = properties.getProperty(propertyKey);
            if (replacement != null) {
                matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
            }
        }
        matcher.appendTail(result);

        return result.toString();
    }
}

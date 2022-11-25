package utilities;

import pojo.SignUpPojo;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TxtWriter {

    public static void saveData(SignUpPojo signUp){

        try {

            FileWriter fileWriter = new FileWriter(ConfigReader.getProperty("path"),true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(signUp.toString()+"\n");
            bufferedWriter.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

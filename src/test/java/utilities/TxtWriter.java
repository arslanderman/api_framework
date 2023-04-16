package utilities;

import pojo.FindAllListPojo;
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
    public static void saveDataTagGroups(FindAllListPojo actual){

        try {

            FileWriter fileWriter = new FileWriter(ConfigReader.getProperty("path_tag_group"),true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           // bufferedWriter.append(actual.toString()+"\n");
            for (int i = 0; i< actual.getData().size(); i++){
                bufferedWriter.append(actual.getData().get(i).toString()+"\n");
            }
            bufferedWriter.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

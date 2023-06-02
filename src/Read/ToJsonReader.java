package Read;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.EdgeAux;
import model.Vertex;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToJsonReader {

    static boolean flag = false;

    static JFileChooser opcion = null;

    static boolean flag2 = false;

    static JFileChooser opcion2 = null;

    public static <T> ArrayList<Vertex<String>> readV() {

        String json = "";

        if(flag == false) {

            opcion = new JFileChooser();

            opcion.showOpenDialog(opcion);

            flag = true;

        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(opcion.getSelectedFile().getAbsolutePath()));

            String linea;

            while ((linea = br.readLine()) != null) {

                json += linea;

            }

            br.close();

        } catch (FileNotFoundException ex) {

            Logger.getLogger(ToJsonReader.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ToJsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }


        Gson gson = new Gson();

        java.lang.reflect.Type userArrayListType = new TypeToken<ArrayList<Vertex<T>>>() {}.getType();
        ArrayList<Vertex<String>> userArrayList = null;
        if(json != null) {

            userArrayList = gson.fromJson(json, userArrayListType);

        }
        return userArrayList;
    }
    public static <T> ArrayList<EdgeAux> readA() {

        String json = "";

        if(flag2 == false) {

            opcion2 = new JFileChooser();

            opcion2.showOpenDialog(opcion2);

            flag2 = true;

        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(opcion2.getSelectedFile().getAbsolutePath()));

            String linea;

            while ((linea = br.readLine()) != null) {

                json += linea;

            }

            br.close();

        } catch (FileNotFoundException ex) {

            Logger.getLogger(ToJsonReader.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ToJsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gson gson = new Gson();

        java.lang.reflect.Type userArrayListType = new TypeToken<ArrayList<EdgeAux>>() {}.getType();
        ArrayList<EdgeAux> userArrayList = null;
        if(json != null) {

            userArrayList = gson.fromJson(json, userArrayListType);
        }
        return userArrayList;
    }
}
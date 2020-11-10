package javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.EntryData.EntrySortingComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author Muzaffar
 */

public class FXMLDocumentController {

    @FXML
    private ListView listView;
    @FXML
    private TextArea inputFileTextField;
    @FXML
    private TextArea outputFileTextField;
    @FXML
    private TextArea textAreaIndex;
    
    private static List<EntryData> entryArrayList;

    public static String username;
    public static String score;
    public static String takenTime;

    public void getBtnSingleFileAction(ActionEvent event) throws FileNotFoundException, IOException {
        
        entryArrayList = new ArrayList<>();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fc.showOpenDialog(null);
        String filePath = selectedFile.getAbsolutePath();
        
        
        FileReader fr = new FileReader(filePath);
        LineNumberReader lnr = new LineNumberReader(fr);
        int linenumber;
        linenumber = 0;
        while (lnr.readLine() != null){
            linenumber++;
    	}
        
        if(selectedFile.length()==0){
            listView.getItems().add(selectedFile.getName());
            System.out.println("The Selected file is Empty");
            System.out.println("Please select a file which contains Specified Data");
        }
        else{
            if (selectedFile != null) {
                listView.getItems().add(selectedFile.getName());
            } else {
                System.out.println("File is not Valid!");
            }

            File file = new File(filePath);
            Scanner sc;

            try {
                sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String st = sc.nextLine();
                    parseLine(st);
                }
            } catch (IOException exp) {
                System.out.println(exp);
              }

            ArrayList<String> listInputDisplay = new ArrayList<>();
            sc = new Scanner(file);
                // Check if there is another line of input
                while (sc.hasNextLine()) {
                    String st = sc.nextLine();
                    listInputDisplay.add(st);
                }
                for(int k=0;k<listInputDisplay.size();k++){
                    System.out.println(listInputDisplay.get(k));
                    inputFileTextField.appendText(listInputDisplay.get(k) + "\n");
                }

            //Iterator<EntryData> custIterator; 
            //custIterator = entryListAL.iterator();
            //System.out.println("Before Sorting:\n"); 
            //while (custIterator.hasNext()) { 
            //EntryData data = custIterator.next();
            //System.out.println(data.getUsername() + " : " + data.getScore() + " : " + data.getTakenTime()); 
            //} 

            // sorting using Collections.sort(al, comparator); 
            Collections.sort(entryArrayList, new EntrySortingComparator()); 
            textAreaIndex.appendText("RANK    USERNAME    SCORE    TIME-TAKEN");
            int i = 1;
            for (EntryData participant : entryArrayList) { 
                outputFileTextField.appendText(i + " :          " + participant.getUsername() + "     :     " + participant.getScore() + "    :     " + participant.getTakenTime() + "\n");
                System.out.println(i + " : " + participant.getUsername() + " : " + participant.getScore() + " : " + participant.getTakenTime()); 
                i++;
            } 
        }
    }

    private static void parseLine(String str) {

        try (Scanner sc = new Scanner(str)) {
            sc.useDelimiter(",");
            username = sc.next();
            score = sc.next();
            takenTime = sc.next();
            entryArrayList.add(new EntryData(username, Integer.parseInt(score), takenTime)); 
        }
    }
}

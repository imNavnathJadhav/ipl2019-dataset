package ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatchDataReaderService {
    public static List<Match> matchesData = new ArrayList<Match>();
    public static void readData(){
        
        String csvFile = "C:/Users/Sachin/Desktop/navnath/git/ipl2019-dataset/FunctionalProgramming/src/ipl/resources/matches.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int skip = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                if(skip == 0){
                    skip++;
                    continue;
                }
                String[] match = line.split(cvsSplitBy);
                storeData(match);
            }
        }
            

         catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }    
    }
    
    
    public static void storeData(String[] data) {
        Match match = new Match();
        
        int data0 = Integer.parseInt(data[0]);
        int data1 = Integer.parseInt(data[1]);
        
        match.setMatchId(data0);
        match.setSeason(data1);
        match.setCity(data[2]);
        match.setDate(data[3]);
        match.setTeam1(data[4]);
        match.setTeam2(data[5]);
        match.setTossWinner(data[6]);
        match.setTossDecision(data[7]);
        match.setResult(data[8]);
        match.setWinner(data[9]);
        
        matchesData.add(match);
    }
}

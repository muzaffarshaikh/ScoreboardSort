package javafx;

import java.util.Comparator;

/**
 *
 * @author Muzaffar
 */

public class EntryData {

    private String username;
    private int score;
    private int timeinSec;
    private String takenTime;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getTimeinSec() {
        return timeinSec;
    }
    public void setTimeinSec(int timeinSec) {
        this.timeinSec = timeinSec;
    }

    public String getTakenTime() {
        return takenTime;
    }
    public void setTakenTime(String takenTime) {
        this.takenTime = takenTime;
    }
    
    public EntryData(String username, int score, String takenTime) {
        this.username = username;
        this.score = score;
        this.takenTime = takenTime;

        String[] hhmmss = takenTime.split(":");
        timeinSec = Integer.parseInt(hhmmss[0]) * 3600 + Integer.parseInt(hhmmss[1]) * 60 + Integer.parseInt(hhmmss[2]);
    }

    static class EntrySortingComparator implements Comparator<EntryData> {

        @Override
        public int compare(EntryData temp, EntryData temp1) {
            int scoreCompare = temp1.getScore() - temp.getScore();
            int timeCompare = temp.getTimeinSec() - temp1.getTimeinSec();
            int nameCompare = temp.getUsername().compareTo(temp1.getUsername());

            if(scoreCompare != 0){
                return scoreCompare;
            } else {
                if (timeCompare == 0) {
                    return ((nameCompare == 0) ? timeCompare : nameCompare);
                } else {
                    return timeCompare;
                }  
            }            
        }
    }
}

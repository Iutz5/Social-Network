/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import SocialNetwork.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ianut
 */
public class MidTerm {
    public static ArrayList<String> read(String filePath) {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine()) != null) {
                // only add the line if it has non-whitespace content
                // strip() removes leading and trailing whitespace
                if(line.strip().length() > 0) { 
                    lines.add(line);
                }
            }
        } catch(IOException e) { // if an error occurs, do this!
            System.err.println("Error reading file!");

            // System.err is like System.out but is used for errors
            // This allows us to separate program output from 
            // error output.
            e.printStackTrace(System.err);
        }

        return lines;
    }

    /* Given a comma separated String, return an 
     *  array of its tokens.  For example:
     *  
     *  String[] letters = commaSeparate("A,B,C,D");
     *  
     *  will return letters = { "A", "B", "C", "D" }
     */
    public static String[] commaSeparate(String line) {
        // Splitting a String is also known as tokenizing it!
        String[] tokens = line.split(",");

        return tokens;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SocialNetwork s = new SocialNetwork();
        
        String[] paths = new String[5];
        paths[0] = "data/CS174 - S21 - TestFile.txt"; // Test file from document
        paths[1] = "data/network.txt"; // Test file from midterm instructions document
        paths[2] = "data/network - Copy.txt"; // Test file that tests for trying to friend someone who is not in the network
        paths[3] = "data/network - Copy (2).txt"; // Test file that tests to message someone that is not someones friend
        paths[4] = "data/network - Copy (3).txt"; // Test file that shows whether blocking people changes the boolean for areFriends before and after the block
        ArrayList<String> textDoc = new ArrayList<String>();
        for(int j = 0; j < paths.length; j++){
            
        
        textDoc = read(paths[j]);
        
            for (int i = 0 ; i < textDoc.size() ; i++) {
                String line = textDoc.get(i);
                String[] tokens = commaSeparate(line);
                switch (tokens[0]) {
                    case "P":
                        System.out.println(s.createPerson(tokens[1], tokens[2]));
                        break;
                    case "M":
                        {
                            Person sender = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                            Person recipient = s.getPerson(s.getPersonPosition(tokens[3], tokens[4]));
                            Message m = new Message(sender, recipient, tokens[5]);
                            System.out.println(s.sendMessage(m));
                            break;
                        }
                    case "F":
                        Person friender = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                        Person friendee = s.getPerson(s.getPersonPosition(tokens[3], tokens[4]));
                        System.out.println(s.requestFriend(friender, friendee));
                        break;
                    case "B":
                        {
                            Person initiator = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                            Person recipient = s.getPerson(s.getPersonPosition(tokens[3], tokens[4]));
                            System.out.println(s.block(initiator, recipient));
                            break;
                        }
                    case "L":
                        {
                            Person someone = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                            System.out.println(s.listFriends(someone));
                            break;
                        }
                    case "E":
                        {
                            Person someone = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                            System.out.println(s.listMessages(someone));
                            break;
                        }
                    case "R":
                        {
                            Person someone = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                            System.out.println(s.listBlocked(someone));
                            break;
                        }
                    case "U":
                        {
                            Person initiator = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                            Person recipient = s.getPerson(s.getPersonPosition(tokens[3], tokens[4]));
                            System.out.println(s.unfriend(initiator, recipient));
                            break;
                        }
                    case "Q":
                        Person person1 = s.getPerson(s.getPersonPosition(tokens[1], tokens[2]));
                        Person person2 = s.getPerson(s.getPersonPosition(tokens[3], tokens[4]));
                        if(s.areFriends(person1, person2)){
                            System.out.println("Yes");
                        } else {
                            System.out.println("No");
                        }   break;
                    default:
                        break;
                }
            }
        }
    }
    
}

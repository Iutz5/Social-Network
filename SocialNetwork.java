/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocialNetwork;

import java.util.ArrayList;

/**
 *
 * @author ianut
 */
public class SocialNetwork {
    // member data
    private ArrayList<Person> _peopleList;
    
    // Constructor
    public SocialNetwork(){
        _peopleList = new ArrayList<Person>();
    }
    public Person createPerson(String first, String last){
        Person p = new Person(first,last);
        if(!_peopleList.contains(p)){
           _peopleList.add(p); 
        }
        return p;
    }
    public Person getPerson(int i){
        return _peopleList.get(i);
    }
    public int getPersonPosition(String first, String last){
        int toReturn = 0;
        int i = 0;
        while(i<_peopleList.size()){
            Person a = _peopleList.get(i);
            if(first.equals(a.getFirstName())&&last.equals(a.getLastName())){
                toReturn = i;
            }
            i++;
        }
        return toReturn;
    }
    @Override
    public String toString(){
        String toReturn = "";
        for(int i = 0 ; i<_peopleList.size();i++){
            Person a = _peopleList.get(i);
            toReturn = toReturn + a.toString();
        }
        return toReturn;
    }
    public boolean requestFriend(Person friender, Person friendee){
        boolean toReturn;
        if(!_peopleList.contains(friender)||!_peopleList.contains(friendee)||friendee._blockList.contains(friender)){
            toReturn = false;
        } else {
            friender.addFriend(friendee);
            friendee.addFriend(friender);
            toReturn = true;
        }
        return toReturn;
    }
    public String listFriends(Person someone){
        return "Friends: " + "\n" + someone.getFriends();
    }
    public boolean sendMessage(Message m){
        boolean toReturn;
        if(m._recipient._blockList.contains(m._sender)||m._sender._blockList.contains(m._recipient)){
            toReturn = false;
        } else {
            m._sender.receiveMessage(m);
            toReturn = true;
        }
        return toReturn;
    }
    public boolean unfriend(Person initaitor, Person recipient){
        boolean toReturn;
        if(!_peopleList.contains(initaitor)||!_peopleList.contains(recipient)||!initaitor._friendList.contains(recipient)){
            toReturn = false;
        } else {
            initaitor.unFriend(recipient);
            recipient.unFriend(initaitor);
            toReturn = true;
        }
        return toReturn;
    }
    public boolean block(Person initaitor, Person recipient){
        boolean toReturn;
        if(!_peopleList.contains(initaitor)||!_peopleList.contains(recipient)){
            toReturn = false;
        } else {
            initaitor.block(recipient);
            toReturn = true;
        }
        return toReturn;
    }
    public String listBlocked(Person someone){
        return "Blocked: " + "\n" + someone.getBlocked();
    }
    public String listMessages(Person someone){
        return "Messages: " + "\n" + someone.getMessages();
    }
    public boolean areFriends(Person person1, Person person2){
        return person1._friendList.contains(person2);
    }
    public int networkSize(){
        return _peopleList.size();
    }
}

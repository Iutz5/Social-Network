/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocialNetwork;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ianut
 */
public class Person implements Friendable, Messagable{
    // member data
    private final String _firstName;
    private final String _lastName;
    private static int _count;
    private int _id;
    protected ArrayList<Message> _receivedMessages;
    protected ArrayList<Person> _friendList;
    protected ArrayList<Person> _blockList;
    
    // Constructor
    public Person(String firstName, String lastName){
        _firstName = firstName;
        _lastName = lastName;
        _id = _count++;
        _receivedMessages = new ArrayList<Message>();
        _friendList = new ArrayList<Person>();
        _blockList = new ArrayList<Person>();
    }
    public String getFirstName(){
        return _firstName;
    }
    public String getLastName(){
        return _lastName;
    }
    public int getID(){
        return _id;
    }
    public String getFriends(){
        String toReturn = "";
        for(int i = 0 ; i < _friendList.size();i++){
            Person a = _friendList.get(i);
            toReturn = toReturn + a._firstName + " " + a._lastName + "\n";
        }
        return toReturn;
    }
    public String getMessages(){
        String toReturn = "";
        for(int i = 0 ; i < _receivedMessages.size();i++){
            Message a = _receivedMessages.get(i);
            toReturn = toReturn + a.toString() + "\n";
        }
        return toReturn;
    }
    public String getBlocked(){
        String toReturn = "";
        for(int i = 0 ; i < _blockList.size();i++){
            Person a = _blockList.get(i);
            toReturn = toReturn + a._firstName + " " + a._lastName + "\n";
        }
        return toReturn;
    }
    @Override
    public boolean equals(Object a){ // Overriding boolean to check deep equality between different objects
        // self check
        if (this == a){
            return true;
        }
        // null check
        if (a == null){
            return false;
        }
        // type check and cast
        if (getClass() != a.getClass()){
            return false;
        }
        Person p1 = (Person) a;
        // field comparison
        boolean toReturn = false;
        if (Objects.equals(_firstName, p1._firstName) && Objects.equals(_lastName, p1._lastName)){
            toReturn = true;
        }
        return toReturn;
    }
    
    public boolean addFriend(Person p){
        boolean toReturn;
        if(p._blockList.contains(this) || this._friendList.contains(p) || p._friendList.contains(this)){ // if the friendee has the other person blocked or they are already friends, do nothing
            toReturn = false;
        } else {
            this._friendList.add(p);
            p._friendList.add(this);
            this._receivedMessages.add(new Message(p,this,("You are now friends with "+p._firstName + " " + p._lastName + "!")));
            p._receivedMessages.add(new Message(this,p,"You are now friends with " + this._firstName + " " +this._lastName + "!"));
            toReturn = true;
        }
        return toReturn;
    }
    
    public boolean unFriend(Person p){
        boolean toReturn;
        if(!this._friendList.contains(p)||!p._friendList.contains(this)||p._blockList.contains(this)){
            toReturn = false;
        } else {
            this._friendList.remove(p);
            p._friendList.remove(this);
            this._receivedMessages.add(new Message(p,this,("You are now no longer friends with "+p._firstName + " " + p._lastName + "!")));
            p._receivedMessages.add(new Message(this,p,"You are now no longer friends with " + this._firstName + " " +this._lastName + "!"));
            toReturn = true;
        }
        return toReturn;
    }
    public boolean receiveMessage(Message m){
        boolean toReturn;
        if(m._recipient._blockList.contains(m._sender)){
            toReturn = false;
        } else {
            m = new Message(m._sender,m._recipient,m._message);
            m._recipient._receivedMessages.add(m);
            toReturn = true;
        }
        return toReturn;
    }
    public boolean block(Person recipient){
        boolean toReturn;
        if(this._blockList.contains(recipient)){
            toReturn = false;
        } else {
            this._blockList.add(recipient);
            this.unFriend(recipient);
            recipient.unFriend(this);
            toReturn = true;
        }
        return toReturn;
    }
    @Override
    public String toString(){
        return ""+ _firstName + " " + _lastName + "\n" + "ID: " + _id + "\n" + "Friends: " + getFriends() + "\n" + "Blocked: " + getBlocked() + "\n" + "Messages: " + getMessages() + "\n";
    }
}

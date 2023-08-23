/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocialNetwork;

/**
 *
 * @author ianut
 */
public class Message {
    // member data
    protected final Person _sender;
    protected final Person _recipient;
    protected final String _message;
    
    // Constructor
    public Message(Person sender, Person recipient, String message){
        _sender = sender;
        _recipient = recipient;
        _message = message;
    }
    public Person getSender(){
        return _sender;
    }
    public Person getRecipient(){
        return _recipient;
    }
    public String getMessage(){
        return _message;
    }
    @Override
    public String toString(){
        return "From " + _sender.getFirstName() + " " +_sender.getLastName() + ": " +_message;
    }
}

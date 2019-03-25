
package oop;


public abstract class Token {
    
    private String token;
    
    Token(String token){
        this.token = token;
    }
    
    
    public String toString(String type, String id, String value){
        return token + "-" + type + "{" + id + "{" + value + "}}";
    }
    
}

package model;

import java.io.Serializable;

public class User implements Serializable
{
    private String email;
    
    private String name;
    
   
  
    public User(String email, String name)
    {
        this.email = email;
        this.name = name;
    }

  
    public String getEmail()
    {
        return email;
    }
    
    public String getName()
    {
        return name;
    }
    
}

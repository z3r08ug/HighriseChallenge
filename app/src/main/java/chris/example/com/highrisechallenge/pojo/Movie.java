package chris.example.com.highrisechallenge.pojo;

import java.io.Serializable;

public class Movie implements Serializable
{
    private String name;
    private int lastUpdated;
    
    public Movie()
    {
    }
    
    public Movie(String name)
    {
        this.name = name;
    }
    
    public Movie(int lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }
    
    public Movie(String name, int lastUpdated)
    {
        this.name = name;
        this.lastUpdated = lastUpdated;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getLastUpdated()
    {
        return lastUpdated;
    }
    
    public void setLastUpdated(int lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }
    
}

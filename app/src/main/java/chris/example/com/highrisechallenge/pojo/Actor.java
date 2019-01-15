package chris.example.com.highrisechallenge.pojo;

import java.io.Serializable;

public class Actor implements Serializable
{
    private String name;
    private int age;
    private String pic;
    
    public Actor()
    {
    }
    
    public Actor(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    public Actor(String name, int age, String pic)
    {
        this.name = name;
        this.age = age;
        this.pic = pic;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getPic()
    {
        return pic;
    }
    
    public void setPic(String pic)
    {
        this.pic = pic;
    }
}

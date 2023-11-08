package com.company;

public class Node implements Comparable
{
    Node(String name,String code)
    {
        this.name = name;
        this.code = code;
    }

    public int compareTo(Object obj)
    {
        Node tmp = (Node)obj;

        if(this.code.compareTo(((Node)obj).code) < 0)
        {
            return -1;
        }
        else if(this.code.compareTo(((Node)obj).code) > 0)
        {
            return 1;
        }

        return 0;
    }

    public String getname()
    {
        return name;
    }
    public String getCode()
    {
        return code;
    }

    private String name,code;

}

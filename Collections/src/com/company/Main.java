package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Node[] node = new Node[4];

        List<Node> list = new LinkedList<>();

        Scanner in = new Scanner(System.in);

        for(Node ob : node)
        {
            System.out.println("Enter the name and code for object");
            String name = in.nextLine();
            String code = in.nextLine();

            ob = new Node(name,code);
            list.add(ob);
        }

        Collections.sort(list);

        for (Node ob : list)
        {
            System.out.println("Node object is name = " + ob.getname() + " code = " + ob.getCode());
        }

        System.out.println("After removal" + "\n");

        for(int i = 0; i < list.size();i++)
        {
            for (int j = i + 1; j < list.size(); j++)
            {
                if (list.get(i).getname().equals(list.get(j).getname()) || list.get(i).getCode().equals(list.get(j).getCode()))
                    list.remove(j);
            }
        }

        for (Node ob : list)
        {
            System.out.println("Node object is name = " + ob.getname() + " code = " + ob.getCode());
        }


    }
}

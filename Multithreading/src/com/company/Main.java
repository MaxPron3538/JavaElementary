package com.company;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.*;

class ExceptionDirectory
{
    public static void Exception_path(String path)
    {
        Pattern pattern = Pattern.compile("\\\\+\\w+.");
        Matcher matcher = pattern.matcher(path);

        try
        {
            if(!matcher.matches()) throw new Exception("Невірно введений шлях,повторіть ще раз");

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            Exception_path(path);
        }

    }
}


class DirectoryTraversal
{
    private static void InputNamefile()
    {
        Pattern pattern = Pattern.compile("\\\\+\\w+.+.txt");
        System.out.println("Введіть назву файлу,куди хочете записати результат!");
        Scanner in = new Scanner(System.in);
        spath = in.nextLine();
        Matcher matcher = pattern.matcher(spath);

        try
        {
            if (!matcher.matches()) throw new Exception("Неправельно введений шлях до файлу чи назва файлу");
        }
        catch (Exception ex)
        {
              System.out.println(ex.getMessage());
        }
    }

    private static void WriteOutput(FileOutputStream writer)
    {
        try
        {
            writer.write("Кількість видалених чисел - ".getBytes());
            writer.write(count);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void DirectoryWalk(String fpath)
    {
        File root = new File(fpath);
        File[] list = root.listFiles();

        Pattern pattern = Pattern.compile(".*.txt");
        Matcher matcher = pattern.matcher(fpath);

        if(matcher.matches())
        {
            InputNamefile();

            try (FileInputStream reader = new FileInputStream(fpath);FileOutputStream writer = new FileOutputStream(spath))
            {
                byte[] bytestr = new byte[reader.available()];

                reader.read(bytestr, 0, reader.available());

                String buffer = new String(bytestr);

                Find_Del(buffer, fpath);

                String str = new String(fpath);

                str.replaceFirst(str.substring(str.lastIndexOf("\\")), "");

                synchronized (this) { writer.write(str.getBytes());}

                WriteOutput(writer);
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }

        if (list == null) return;

        for(File f : list)
        {
            if(f.isDirectory())
            {
                JThread thread = new JThread(f.getAbsolutePath());
                thread.start();
            }
            else if(f.isFile())
            {
                JThread thread = new JThread(f.getAbsolutePath());
                thread.start();
            }
        }
    }

    public void Find_Del (String str,String fpath)
    {
        String regex1 = "\\b\\W";
        String regex2 = "\\d{3,5}";

        Pattern ptrn1 = Pattern.compile(regex1);
        Pattern ptrn2 = Pattern.compile(regex2);

        String[] fields = ptrn1.split(str);

        try(FileOutputStream fwriter = new FileOutputStream(fpath))
        {
            for(int i = 0; i < fields.length; i++)
            {
                Matcher matcher = ptrn2.matcher(fields[i]);

                while(matcher.find())
                {
                    fields[i] = fields[i].replaceFirst(regex2, "");
                    synchronized (this) {count++;}
                }
                fwriter.write(fields[i].getBytes());
            }
            str = Arrays.toString(fields);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static int count = 0;
    static String spath;
}

class JThread extends Thread
{
    JThread(String path)
    {
        this.path = new String(path);
    }

    public void run()
    {
        DirectoryTraversal traversal = new DirectoryTraversal();
        traversal.DirectoryWalk(path);
    }
    String path;
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Назва поточної директорії - C:\\Users\\M\\IdeaProjects\\laba2_3");

        System.out.println("Введіть назву внутрішньої директорії каталогу laba2_3 для пошуку внутрішнього файлу:");

        String path = in.nextLine();

        ExceptionDirectory ex = new ExceptionDirectory();

        ex.Exception_path(path);

        JThread thread = new JThread("C:\\Users\\M\\IdeaProjects\\laba2_3" + path);

        thread.start();
    }

}

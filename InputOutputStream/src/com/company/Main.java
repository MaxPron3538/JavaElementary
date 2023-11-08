package com.company;
import java.io.FileInputStream;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
       try(FileInputStream reader = new FileInputStream("Temp.txt"); FileOutputStream writer = new FileOutputStream("NewTemp.txt"))
       {
          byte[] bytestr = new byte[reader.available()];
          int lindex = 0;

          reader.read(bytestr,0,reader.available());

          String buffer = new String(bytestr);
          String[] bufferSubstr = new String[index_Str(buffer)];

           while (Punctuation_Selection(buffer) > 0)
             {
                bufferSubstr[lindex] = buffer.substring(0,Punctuation_Selection(buffer) + 1);
                buffer = buffer.replaceFirst(bufferSubstr[lindex], "");

                System.out.println(bufferSubstr[lindex]);

                if (lindex == bufferSubstr.length - 1)
                {
                   Writing_to_File_Instances_of_Words(writer,bufferSubstr);
                }
                lindex++;
             }
       }
    }

    public static int index_Str(String temp)
    {
        int index = 0;

         while (Punctuation_Selection(temp) > 0)
         {
            temp = temp.replaceFirst(temp.substring(0,Punctuation_Selection(temp) + 1), "");

            index++;
         }
        return index;
    }

    public static int Punctuation_Selection(String temp)
    {
        int index = 0;
        char[] symbol = new char[]{' ',',','.','?','!','-',':'};
        int i = 0;

            for (int j = i+1;j < symbol.length;j++)
            {
                if(temp.indexOf(symbol[i]) == - 1 && temp.indexOf(symbol[j]) == -1) index = temp.indexOf(symbol[j]);

                if (temp.indexOf(symbol[i]) < temp.indexOf(symbol[j]))
                {
                    if(temp.indexOf(symbol[i]) == -1) return temp.indexOf(symbol[j]);

                    index = temp.indexOf(symbol[i]);
                }
                if(temp.indexOf(symbol[i]) > temp.indexOf(symbol[j]))
                {
                    if(temp.indexOf(symbol[j]) == -1){ index = temp.indexOf(symbol[i]); continue;}

                        char tmp = symbol[i];
                        symbol[i] = symbol[j];
                        symbol[j] = tmp;
                        index = temp.indexOf(symbol[i]);
                }
                else index = temp.indexOf(symbol[i]);
            }
            return index;
    }

    public static void Writing_to_File_Instances_of_Words(FileOutputStream writer,String[] buffer) throws  Exception
    {
        int tmpfindex = 0,tmplindex = 1;

        while(tmpfindex < buffer.length)
        {
            int numval = 1;

            writer.write(buffer[tmpfindex].getBytes());

            while (tmplindex < buffer.length)
            {
                if (buffer[tmpfindex].equals(buffer[tmplindex]))
                {
                    buffer[tmplindex] = "";
                    numval++;
                }
                tmplindex++;
            }
            WriteFile_Num(writer,numval);
            tmpfindex++;
            tmplindex = tmpfindex + 1;
        }

    }

    public static void WriteFile_Num(FileOutputStream writer,int num) throws Exception
    {
        writer.write("частота зустрічі в тексті - ".getBytes());
        writer.write(Integer.toString(num).getBytes());
        writer.write(";".getBytes());
    }

}

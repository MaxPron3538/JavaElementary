import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindStr
{
    int Sum = 0;

    public int Find_Sum(String str)
    {
        String regex1 = "\\b\\W";
        String regex2 = "\\D";
        int Sum = 0;

        Pattern ptrn1 = Pattern.compile(regex1);
        Pattern ptrn2 = Pattern.compile(regex2);

        String[] fields = ptrn1.split(str);

        for(int i = 0;i < fields.length;i++)
        {
           Matcher matcher = ptrn2.matcher(fields[i]);

           while (matcher.find() == true)
           {
              fields[i] = fields[i].replaceFirst(regex2,"");
           }

           if(fields[i].length() != 0)

           Sum += Integer.parseInt(fields[i]);
        }
    return Sum;
    }
}

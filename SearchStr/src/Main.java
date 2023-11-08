import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int Sum = 0;
        System.out.print("Input a number string: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        String[] arr = new String[num];

        for(int i = 0;i < num;i++)
        {
            System.out.print("Input a String: ");
            Scanner temp = new Scanner(System.in);
            arr[i] = temp.nextLine();
        }

        FindStr Str = new FindStr();

        for(int i = 0;i < num;i++)
        {
            Sum += Str.Find_Sum(arr[i]);
        }

        System.out.print("Sum is: ");
        System.out.print(Sum);

    }
}

import java.util.Scanner;

public class Main
{
    private static int minIncome = 200000;    //минимальная прибыль
    private static int maxIncome = 900000;   //максимальная прибыль

    private static int officeRentCharge = 140000; //аренда офиса
    private static int telephonyCharge = 12000;  //плата за телефонию
    private static int internetAccessCharge = 7200; //плата за интернет

    private static int assistantSalary = 45000;  //зарплата помощника
    private static int financeManagerSalary = 90000;  //зарплата финансового менеджера

    private static double mainTaxPercent = 0.24;   //налоговый процент
    private static double managerPercent = 0.15;   //процент менеджера от прибыли
    private static double minInvestmentsAmount = 100000;  //минимальная сумма инвестиции

    private static double maxManagerSalaryInvestments = 135000;
    private static double minManagerSalaryInvestments = 30000;

    private static double salaryFromTheMinimum = maxManagerSalaryInvestments - minManagerSalaryInvestments;
    private static double  salaryCount = salaryFromTheMinimum;

    private static int count = 0;


    public static void calculateIncomeSecond(double maxSalaryManager)
    {
        double minIncomeInvestments = maxSalaryManager / managerPercent;
        double pureIncomeInvestments = minIncomeInvestments - maxSalaryManager - calculateFixedCharges();
        double taxAmountInvestments = mainTaxPercent * pureIncomeInvestments;
        double pureIncomeAfterTaxInvestments = pureIncomeInvestments - taxAmountInvestments;
        boolean minCanMakeInvestments = pureIncomeAfterTaxInvestments >= minInvestmentsAmount;

        salaryCount-= 1500;
        count++;

        if(pureIncomeAfterTaxInvestments >= minInvestmentsAmount & pureIncomeAfterTaxInvestments < minInvestmentsAmount + 1)
        {
            minIncomeInvestments = Math.round(maxSalaryManager / managerPercent);
            System.out.println((int) minIncomeInvestments);
        }

        if(salaryCount > 0 & maxSalaryManager < maxManagerSalaryInvestments)
        {
            if (minCanMakeInvestments) calculateIncomeSecond(maxSalaryManager - salaryCount/count);

            if (!minCanMakeInvestments) calculateIncomeSecond(maxSalaryManager + salaryCount/count);
        }
    }

    public static double calculateIncome(double maxSalaryManager)
    {
        double halfSalary = maxSalaryManager/2;
        double count = 0;

        if(halfSalary < minManagerSalaryInvestments) return 1;

        calculateIncome(halfSalary);

        if(maxSalaryManager == maxManagerSalaryInvestments) return 1;


        while(count <= maxSalaryManager)
        {
            minManagerSalaryInvestments += count;   //вычисляем зарплату менеджера
            double minIncomeInvestments  = minManagerSalaryInvestments / managerPercent;    //вычисляем общий доход компании
            double pureIncomeInvestments = minIncomeInvestments - minManagerSalaryInvestments - calculateFixedCharges();  //вычисляем чистый доход с затратами компании
            double taxAmountInvestments = mainTaxPercent * pureIncomeInvestments;             //вычисляем налог от дохода компании
            double pureIncomeAfterTaxInvestments = pureIncomeInvestments - taxAmountInvestments;  //вычисляем доход с налогом
            boolean minCanMakeInvestments = pureIncomeAfterTaxInvestments >= minInvestmentsAmount;  //вычисляем больше ли доход за минимальную инвестицию

            if(minCanMakeInvestments){ System.out.println("Minimal income - " + ((int) minIncomeInvestments)); break;}  //проверяем можно ли инвестировать

            count+=0.00002;
        }


        return 0;
    }

    public static void main(String[] args)
    {

        long startTime = System.nanoTime();

        double minManagerSalaryInvestments = 30000; //При условии, что минимальный доход составляет 200000 то зарплата менеджера составит 30000
        double count = 0;   //счетчик дя перебора зарплат менеджера

        while(count <= 135000) //135000 - зарплата менеджера при доходе компании - 900000 /пока счетчик меньше равно 135000
        {
            minManagerSalaryInvestments += count;   //вычисляем зарплату менеджера
            double minIncomeInvestments  = minManagerSalaryInvestments / managerPercent;    //вычисляем общий доход компании
            double pureIncomeInvestments = minIncomeInvestments - minManagerSalaryInvestments - calculateFixedCharges();  //вычисляем чистый доход с затратами компании
            double taxAmountInvestments = mainTaxPercent * pureIncomeInvestments;             //вычисляем налог от дохода компании
            double pureIncomeAfterTaxInvestments = pureIncomeInvestments - taxAmountInvestments;  //вычисляем доход с налогом
            boolean minCanMakeInvestments = pureIncomeAfterTaxInvestments >= minInvestmentsAmount;  //вычисляем больше ли доход за минимальную инвестицию

            if(minCanMakeInvestments){System.out.println("Minimal income - " + ((int) minIncomeInvestments)); break;}  //проверяем можно ли инвестировать

            count +=0.000001;
        }

        double minIncomeInvestments = (minInvestmentsAmount + calculateFixedCharges() * (1 - mainTaxPercent))/(1 - managerPercent - mainTaxPercent * (1 - managerPercent));

        System.out.println(minIncomeInvestments);


        while (true) {                                                 //бесконечный цикл
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");          //вывод диапазона доходов компании в месяц
            int income = (new Scanner(System.in)).nextInt();  //считываем введонное значение дохода

            if (!checkIncomeRange(income)) {   //если оно не входид в диапазон допустимых значений,пропускаем итерацию
                continue;
            }

            double managerSalary = income * managerPercent;      //вычисляем зарплату менеджера
            double pureIncome = income - managerSalary - calculateFixedCharges();   //вычисляем чистый доход компании
            double taxAmount = mainTaxPercent * pureIncome;  // вычисляем налог компании
            double pureIncomeAfterTax = pureIncome - taxAmount; // находим общий доход с налогом
            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;      //находим больше ли доход минимальной инвестиции

            System.out.println("Зарплата менеджера: " + managerSalary);   // выводим зарплату менеджера
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));       //общая сума налогов
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет")); //проверка можно ли инвестировать,если да то выводим да,если нет то выведем нет
            if (pureIncome < 0) {                  //если чистый доход меньше нуля то компания несет убытки
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }

    }

    private static boolean checkIncomeRange(int income) {   //статический  метод типа  bolean(возвращает true или false)проверка введенного значения дохода
        if (income < minIncome) {                //если доход меньше минимального
            System.out.println("Доход меньше нижней границы");
            return false; //возвращаем false
        }
        if (income > maxIncome) {   //если доход больше максимального
            System.out.println("Доход выше верхней границы");
            return false; //возвращаем false
        }
        return true; //возвращаем true
    }

    private static int calculateFixedCharges() {  //статический метод типа int(расчитывает суму затрат)
        return officeRentCharge +
            telephonyCharge +
            internetAccessCharge +
            assistantSalary +
            financeManagerSalary;  //возвращает суму затрат компании
    }
}

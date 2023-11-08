import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController {
    private static double passengerCarMaxWeight = 3500.0; // kg Статическая перемення(существует в единственом екземпляре) типа double(8 байт)
    private static int passengerCarMaxHeight = 2000; // mm  Статическая перемення(существует в единственом екземпляре) типа int(4 байт)
    private static int controllerMaxHeight = 3500; // mm Статическая перемення(существует в единственом екземпляре) типа int(4 байт)

    private static int passengerCarPrice = 100; // RUB  Статическая перемення(существует в единственом екземпляре) типа int(4 байт)
    private static int cargoCarPrice = 250; // RUB  Статическая перемення(существует в единственом екземпляре) типа int(4 байт)
    private static int vehicleAdditionalPrice = 200; // RUB  Статическая перемення(существует в единственом екземпляре) типа int(4 байт)

    public static void main(String[] args) {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in); //Создается обьект типа Scanner
        int carsCount = scanner.nextInt();        //Локальная переменная типа int(4 байт)

        for (int i = 0; i < carsCount; i++) {

            Car car = Camera.getNextCar();         //Создается екземпляр типа Car
            System.out.println(car);

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car);  //Локальная переменная типа int(4 байт)
            if (price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car) {  //Статический метод типа int(возвращает тип int)
        int carHeight = car.height;            //Локальная переменная типа int(4 байт)
        int price = 0;                         //Локальная переменная типа int(4 байт)
        if (carHeight > controllerMaxHeight) {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        } else if (carHeight > passengerCarMaxHeight) {
            double weight = car.weight;       //Локальная переменная типа double(8 байт)
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight) {
                price = cargoCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Легковой автомобиль
            else {
                price = passengerCarPrice;
            }
        } else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay() {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }       //Статический метод типа void(Не возвращает значение)

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason) {
        System.out.println("Проезд невозможен: " + reason);
        //Статический метод типа void(Не возвращает значение)
    }
}
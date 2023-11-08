package core;

public class Car {
    public String number;        //переменая екземпляра класа типа String
    public int height;           //переменая екземпляра класа типа int(4 байта)
    public double weight;        //переменая екземпляра класа типа double(8 байт)
    public boolean hasVehicle;   //переменая екземпляра класа типа bolean(1 байт)
    public boolean isSpecial;    //переменая екземпляра класа типа bolean(1 байт)

    public String toString() {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";     //Локальная переменная типа string
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}
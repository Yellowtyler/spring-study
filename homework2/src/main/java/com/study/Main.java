package com.study;

import com.study.model.Consumer;
import com.study.model.Product;
import com.study.repository.ConsumerRepository;
import com.study.repository.ProductRepository;
import com.study.service.ConsumerProductService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        System.out.println("Консольное приложение по управлению клиентами и покупками.");
        System.out.println("Чтобы выйти введите exit");
        Scanner scanner = new Scanner(System.in);
        boolean isExit=false;
        while(!isExit) {
            System.out.println("Введите команду: ");
            String cmd = scanner.nextLine();
            String[] strings = cmd.split("\\s");
            ConsumerProductService service = new ConsumerProductService(new ConsumerRepository(), new ProductRepository());
            switch (strings[0]){
                case "/showProductsByConsumer":
                    List<Product> products = service.showProductsByConsumer(strings[1]);
                    if(products!=null) {
                        for (Product product : products) {
                            System.out.println(product);
                        }
                    } else {
                        System.out.println("Покупатель с таким именем не существует.");
                    }

                    break;

                case "/showConsumersByProductTitle":
                    List<Consumer> consumers = service.showConsumersByProductTitle(strings[1]);
                    if(consumers!=null) {
                        for (Consumer consumer : consumers) {
                            System.out.println(consumer);
                        }
                    } else {
                        System.out.println("Продукт с таким именем не существует.");
                    }
                    break;

                case "/deleteConsumer":
                    if(service.deleteConsumer(strings[1])) {
                        System.out.println(strings[1] + " успешно удален!");
                    } else {
                        System.out.println("Ошибка! Удаление не удалось. " + strings[1] +" не найден");
                    }
                    break;

                case "/deleteProduct":
                    if(service.deleteProduct(strings[1])) {
                        System.out.println(strings[1] + " успешно удален!");
                    } else {
                        System.out.println("Ошибка! Удаление не удалось. " + strings[1] +" не найден");
                    }
                    break;

                case "/buy":
                    if(strings.length==3) {
                        int status = service.buy(Long.valueOf(strings[1]), Long.valueOf(strings[2]));
                        if(status==1) {
                            System.out.println("Покупка была успешно оформлена!");
                        } else if (status==2){
                            System.out.println("Ошибка! Такой продукт не существует");
                        } else if (status==3){
                            System.out.println("Ошибка! Такой покупатель не существует");
                        } else if (status==4){
                            System.out.println("Ошибка! Покупателя и продукта с такими id не существуют!");
                        }
                    } else {
                        System.out.println("Неправильно введена команда! Введите /buy id_покупателя id_товара");
                    }
                    break;

                case "exit":
                    isExit=true;
                    break;
                default:
                    System.out.println("Ошибка! Неизвестная команда.");
            }
        }

    }
}

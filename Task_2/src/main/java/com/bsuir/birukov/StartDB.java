package main.java.com.bsuir.birukov;

import main.java.com.bsuir.birukov.entity.*;

import java.sql.*;
import java.util.Scanner;

public class StartDB {
    // Блок объявления констант
    //public static final String DB_URL = "jdbc:h2:/c:/JavaPrj/SQLDemo/db/stockExchange";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root/TASK_01";
    //jdbc:mysql://127.0.0.1:3306/?user=root
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";

    public static final String DB_User = "root";
    public static final String DB_password = "3638";

    //private static final String SELECT_QUERY = "SELECT * FROM ;";


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            Class.forName(DB_Driver); // Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_password); // соединение с БД
            System.out.println("Соединение с СУБД выполнено.");

            Statement statement = connection.createStatement();
            CustomerOrders cust_orders = new CustomerOrders(1, 2, 1, 5, "Gruzilka", "delivered");
            CustomerOrders cust_orders2 = new CustomerOrders(1, 3, 1, 7, "Toskalka", "not delivered");
            CustomerOrders cust_orders_result = new CustomerOrders();

            while (true){
                System.out.println("\nChoose activity with database:\n" +
                                        "1 - Insert\n" +
                                        "2 - Delete\n" +
                                        "3 - Update\n" +
                                        "4 - SelectAll\n" +
                                        "5 - Exit\n");
                int choose = input.nextInt();
                if(choose == 5){
                    break;
                }
                switch(choose){
                    case 1:
                        InsertFullData(cust_orders);
                        break;
                    case 2:
                        System.out.println("Write id of account: ");
                        int id = input.nextInt();
                        DeleteDataById(id);
                        break;
                    case 3:
                        UpdateData(cust_orders2);
                        break;
                    case 4:
                        SelectAllData(cust_orders_result);
                        break;
                    default:
                        break;
                }
            }

            connection.close();  // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public static void InsertFullData(CustomerOrders object) {
        try (Connection connection =
                     DriverManager.getConnection(DB_URL, DB_User, DB_password);
             Statement statement = connection.createStatement()) {

            int nums = statement.executeUpdate("INSERT INTO database.customer_orders VALUES('"+ object.customer_order_id
                                                                                                +"','"+object.account_id
                                                                                                +"','"+object.delivery_id
                                                                                                +"','"+object.num_of_orders
                                                                                                +"','"+object.shipping
                                                                                                +"','"+object.status+"');");

            System.out.printf("%-20s%s%n", "id", "username");
            System.out.println("-------------------------------");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static <T> void DeleteDataById(int id) {
        try (Connection connection =
                     DriverManager.getConnection(DB_URL, DB_User, DB_password);
             Statement statement = connection.createStatement()) {

            int num = statement.executeUpdate("DELETE FROM database.customer_orders WHERE custom_order_id='"+id+"';");
            System.out.printf("%-20s%s%n", "id", "username");
            System.out.println("-------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void UpdateData(CustomerOrders object) {
        try (Connection connection =
                     DriverManager.getConnection(DB_URL, DB_User, DB_password);
             Statement statement = connection.createStatement()) {

            int num = statement.executeUpdate("UPDATE database.customer_orders SET custom_order_id = '"+object.customer_order_id
                                                                                        +"',account_id = '"+object.account_id
                                                                                        +"',delivery_id = '"+object.delivery_id
                                                                                        +"',num_of_orders = '"+object.num_of_orders
                                                                                        +"',shipping = '"+object.shipping
                                                                                        +"',status = '"+object.status+"';");
            System.out.printf("%-20s%s%n", "id", "username");
            System.out.println("-------------------------------");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static CustomerOrders SelectAllData(CustomerOrders object) {
        try (Connection connection =
                     DriverManager.getConnection(DB_URL, DB_User, DB_password);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM database.customer_orders;");
            System.out.printf("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s", "custom_order_id", "account_id", "delivery_id", "num_of_orders", "shipping", "status");
            System.out.println("\n-----------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                int custom_order_id = resultSet.getInt("custom_order_id");
                int account_id = resultSet.getInt("account_id");
                int delivery_id = resultSet.getInt("delivery_id");
                int num_of_orders = resultSet.getInt("num_of_orders");
                String shipping = resultSet.getString("shipping");
                String status = resultSet.getString("status");
                object = new CustomerOrders(custom_order_id, account_id, delivery_id, num_of_orders, shipping, status);
                System.out.printf("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s", custom_order_id,account_id, delivery_id, num_of_orders, shipping, status);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return object;
    }
}

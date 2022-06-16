package main.java.com.bsuir.birukov.entity;

public class CustomerOrders {
    public int customer_order_id;
    public int account_id;
    public int delivery_id;
    public int num_of_orders;
    public String shipping;
    public String status;

    public CustomerOrders(int custom_order_id, int account_id, int delivery_id, int num_of_orders, String shipping, String status){
        this.customer_order_id = custom_order_id;
        this.account_id = account_id;
        this.delivery_id = delivery_id;
        this.num_of_orders = num_of_orders;
        this.shipping = shipping;
        this.status = status;
    }

    public CustomerOrders(){
        this.customer_order_id = 0;
        this.account_id = 0;
        this.delivery_id = 0;
        this.num_of_orders = 0;
        this.shipping = "";
        this.status = "";
    }
}

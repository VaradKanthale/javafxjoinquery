package Family.model;

import java.util.Date;

public class Orders {
int Order_ID;
int Customer_id;
java.sql.Date order_date ;
int menu_id;



public int getOrder_ID() {
	return Order_ID;
}
public void setOrder_ID(int order_ID) {
	Order_ID = order_ID;
}
public int getCustomer_id() {
	return Customer_id;
}
public void setCustomer_id(int customer_id) {
	Customer_id = customer_id;
}
public java.sql.Date getOrder_date() {
	return order_date;
}
public void setOrder_date(java.sql.Date order_date) {
	this.order_date = order_date;
}
public int getMenu_id() {
	return menu_id;
}
public void setMenu_id(int menu_id) {
	this.menu_id = menu_id;
}
public Orders(int customer_id, java.sql.Date order_date, int menu_id) {
	super();
	Customer_id = customer_id;
	this.order_date = order_date;
	this.menu_id = menu_id;
}
public Orders(int order_ID, int customer_id, java.sql.Date order_date, int menu_id) {
	super();
	Order_ID = order_ID;
	Customer_id = customer_id;
	this.order_date = order_date;
	this.menu_id = menu_id;
}
public Orders() {
	super();
}

}

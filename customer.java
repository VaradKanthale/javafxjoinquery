package Family.model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

public class customer {
	
	int Customer_id;
	String name;
	String emailid;
	int age;
	String City;
	int menu_id ;
	String menu ;
	java.sql.Date order_date ;
	int Order_ID;

	
	public int getCustomer_id() {
		return Customer_id;
	}


	public void setCustomer_id(int customer_id) {
		Customer_id = customer_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getCity() {
		return City;
	}


	public void setCity(String city) {
		City = city;
	}


	public int getMenu_id() {
		return menu_id;
	}


	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}


	public String getMenu() {
		return menu;
	}


	public void setMenu(String menu) {
		this.menu = menu;
	}


	public java.sql.Date getOrder_date() {
		return order_date;
	}


	public void setOrder_date(java.sql.Date order_date) {
		this.order_date = order_date;
	}


	public int getOrder_ID() {
		return Order_ID;
	}


	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}	

	public customer(int customer_id, String name, String emailid, int age, String city, String menu ,
			java.sql.Date order_date) {
		super();
		Customer_id = customer_id;
		this.name = name;
		this.emailid = emailid;
		this.age = age;
		City = city;
		this.menu = menu;
		this.order_date = order_date;
	}


	public customer(int customer_id, String name, String emailid, int age, String city, int menu_id, String menu,
			java.sql.Date order_date) {
		super();
		Customer_id = customer_id;
		this.name = name;
		this.emailid = emailid;
		this.age = age;
		City = city;
		this.menu_id = menu_id;
		this.menu = menu;
		this.order_date = order_date;
	}

	
	public customer(int customer_id, String name, String emailid, int age, String city, int menu_id, String menu,
			java.sql.Date order_date, int order_ID) {
		super();
		Customer_id = customer_id;
		this.name = name;
		this.emailid = emailid;
		this.age = age;
		City = city;
		this.menu_id = menu_id;
		this.menu = menu;
		this.order_date = order_date;
		Order_ID = order_ID;
	}


	public customer(String name, String emailid, int age, String city, int menu_id, String menu, java.sql.Date order_date) {
		super();
		this.name = name;
		this.emailid = emailid;
		this.age = age;
		City = city;
		this.menu_id = menu_id;
		this.menu = menu;
		this.order_date = order_date;
	}


	public customer() {
		super();
	}


	
	
	

}

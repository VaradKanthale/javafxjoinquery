package Family.model;

public class Menues {
	int menu_id ;
	String menu ;
	int amount;
	String status;
	
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Menues(int menu_id, String menu) {
		super();
		this.menu_id = menu_id;
		this.menu = menu;
	}
	public Menues(int menu_id, String menu, int amount, String status) {
		super();
		this.menu_id = menu_id;
		this.menu = menu;
		this.amount = amount;
		this.status = status;
	}
	public Menues(String menu, int amount) {
		super();
		this.menu = menu;
		this.amount = amount;
	}
	public Menues(String menu, int amount, String status) {
		super();
		this.menu = menu;
		this.amount = amount;
		this.status = status;
	}
	public Menues(int menu_id, String menu, int amount) {
		super();
		this.menu_id = menu_id;
		this.menu = menu;
		this.amount = amount;
	}
	public Menues() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Menues [menu_id=" + menu_id + ", menu=" + menu + ", amount=" + amount + ", status=" + status + "]";
	}
	
		

}

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class StoreManagement {
	//array of the different stores created
	ArrayList<Store> stores = new ArrayList<Store>(); 
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<String> admins = new ArrayList<String>();//contains all the IDs of the those who are admins
	ArrayList<String> managers = new ArrayList<String>();//contains all the IDs of the those who are managers
	ArrayList<String> staff = new ArrayList<String>();//contains all the IDs of the those who are staff

	
	
		//can create a store
		public void storeCreation(String ID) {
			Scanner input = new Scanner(System.in);
			String role = validateID(ID);
			if(role.equals("Admin")){
				Store store = new Store();
				System.out.println("what will be the store name?");
				String name = input.nextLine();
				store.storeName = name;
				System.out.println("what will be the store location?");
				String location = input.nextLine();
				store.location = location;
				System.out.println("what is the contact information?");
				String info = input.nextLine();
				store.contactInfo = info;
				System.out.println("what is the store type?");
				String type = input.nextLine();
				store.storeType = type;
				System.out.println("what is the opening date? Example: 09/13/2023");
				String date = input.nextLine();
				store.opening = date;
				//adds the store that was created to the array of stores
				stores.add(store);	
			}
			if(!role.equals("Admin")){
				System.out.println("You don't have permission to complete this action");
			}
			
			
		}
	//delete a store
	public void deleteStore(String ID) {
		
		Scanner input = new Scanner(System.in);
		String status = validateID(ID);
		//if ID is in our system proceed to delete store
		if(status != null) {
			System.out.println("What store would you like to delete?");
			String store = input.nextLine();
		
			//if the store name they provided was in the store array, it will get deleted
			int index =searchStoreName(store);
			if(index != 100 ) {
				stores.remove(index);
				System.out.println("size of store array"+ stores.size());
			}
			
			if(index == 100) {
				System.out.println("The store you provided does not exist");
			}
			
		}else {
			System.out.println("The ID you provided was not valid");
		}
		
	}
	//updates store features
	public void updateStore(String ID){
		Scanner input = new Scanner(System.in);
		String status = validateID(ID);
		if(status != null) {
			if(status.equals("Admin")) {
				System.out.println("What would you like to update?");
				System.out.println("1: store name");
				System.out.println("2: location");
				System.out.println("3: contact information");
				System.out.println("4: store type opening date");
				int choice = input.nextInt();
				input.nextLine(); 
				switch(choice) {
				case 1:
					System.out.println("What is the name of the store you want to change?");
					String name = input.nextLine();
					for(int i =0;i<stores.size();i++) {
						if(name.equals(stores.get(i).storeName)) {
							System.out.println("What is the name you want to change it to?");
							String newName = input.nextLine();
							stores.get(i).storeName = newName;
							//for my purposes
							System.out.println("chnaged feature: "+stores.get(i).storeName);
							break;
						}
					}
					break;
				case 2:
					System.out.println("What is the location of the store you want to change?");
					String location = input.nextLine();
					for(int i =0;i<stores.size();i++) {
						if(location.equals(stores.get(i).location)) {
							System.out.println("What is the location you want to change it to?");
							String newLocation = input.nextLine();
							stores.get(i).location = newLocation;
							//for my own purposes
							System.out.println("chnaged feature: "+stores.get(i).location);
							break;
						}
					}
					break;
				case 3:
					System.out.println("What is the contact information of the store you want to change?");
					String info = input.nextLine();
					for(int i =0;i<stores.size();i++) {
						if(info.equals(stores.get(i).contactInfo)) {
							System.out.println("What is the contact information you want to change it to?");
							String newInfo = input.nextLine();
							stores.get(i).contactInfo = newInfo;
							//for my own purposes
							System.out.println("chnaged feature: "+stores.get(i).contactInfo);
							break;
						}
					}
					break;
				case 4:
					System.out.println("What is the opening date of the store you want to change?");
					String date = input.nextLine();
					for(int i =0;i<stores.size();i++) {
						if(date.equals(stores.get(i).opening)) {
							System.out.println("What is the date you want to change it to?");
							String newDate = input.nextLine();
							stores.get(i).opening = newDate;
							//for my own purposes
							System.out.println("chnaged feature: "+stores.get(i).opening);
							break;
						}
					}
					break;
				
				}
				
				
			}
			
			
		}else {
			System.out.println("The ID you provided was not valid");
		}
		/*
		String storeName;
		String location;
		String contactInfo;
		String storeType;
		String opening;
		 */
		
		
	}
	//assigns role and ID to the correct user role array
	public Boolean assignID(String ID) {
		Boolean created = false;
		String status = validateID(ID);
		if(status != null) {
			System.out.println("Your ID is not unique. Please try again.");
		}
		if(status == null) {
			Scanner input = new Scanner(System.in);
			System.out.println("what role will this ID have?");
			String role = input.nextLine();
			if(role.equals("Admin") || role.equals("admin")) {
				created=true;
				admins.add(ID);
			}
			else if(role.equals("Manager") || role.equals("manager")) {
				created=true;
				managers.add(ID);
			}
			else if(role.equals("Staff") || role.equals("staff")) {
				created=true;
				staff.add(ID);
			}
			else {
				System.out.println("Your input is invalid");
			}
		}
		return created;
	}
	//validates ID to make sure it's not a duplicate
	public String validateID(String ID) {
		String status=null;
		for(int i =0;i< admins.size();i++) {
			if(ID.equals(admins.get(i))) {
				status = "Admin";
			}
		}
		for(int i =0;i< managers.size();i++) {
			if(ID.equals(managers.get(i))) {
				status = "Manager";
			}
		}
		for(int i =0;i< staff.size();i++) {
			if(ID.equals(staff.get(i))) {
				status = "Staff";
			}
		}
		return status;
	}

	
	//search by location
	public int searchStoreLocation(String location) {
		int index =0;
		for(int i =0;i< stores.size();i++) {
			if(location.equals(stores.get(i).location)) {
				System.out.println("Here is the store information");
				System.out.println("Store Name: "+stores.get(i).storeName);
				System.out.println("Store Type: "+stores.get(i).storeType);
				System.out.println("Store Information: "+stores.get(i).contactInfo);
				System.out.println("Store Opening Date: "+stores.get(i).opening);
				index= i;
				//since each store has a different address
				break;
			}
		}
		return index;
	}
	//search by type
	public void searchStoreType(String type) {
		for(int i =0;i< stores.size();i++) {
			if(type.equals(stores.get(i).storeType)) {
				System.out.println("Here is the store information");
				System.out.println("Store Name: "+stores.get(i).storeName);
				System.out.println("Store Location: "+stores.get(i).location);
				System.out.println("Store Information: "+stores.get(i).contactInfo);
				System.out.println("Store Opening Date: "+stores.get(i).opening);
			}
		}
	}
	//search by opening date
	public void searchStoreDate(String date) {
		for(int i =0;i< stores.size();i++) {
			if(date.equals(stores.get(i).opening)) {
				System.out.println("Here is the store information");
				System.out.println("Store Name: "+stores.get(i).storeName);
				System.out.println("Store Location: "+stores.get(i).location);
				System.out.println("Store Type: "+stores.get(i).storeType);
				System.out.println("Store Information: "+stores.get(i).contactInfo);
				
			}
		}
	}
	//search by name
	public int searchStoreName(String name) {
		int index =100;
		for(int i =0;i< stores.size();i++) {
			if(name.equals(stores.get(i).storeName)) {
				System.out.println("Here is the store information");
				System.out.println("Store Location: "+stores.get(i).location);
				System.out.println("Store Type: "+stores.get(i).storeType);
				System.out.println("Store Information: "+stores.get(i).contactInfo);
				System.out.println("Store Opening Date: "+stores.get(i).opening);
				index = i;
				//since each store has a different name
				break;
			}
		}
		return index;
	}
	
	//for my own purposes
	public void display() {
		for(int i =0; i< stores.size();i++) {
			System.out.println(stores.get(i).storeName);
			System.out.println(stores.get(i).location);
			System.out.println(stores.get(i).contactInfo);
			System.out.println(stores.get(i).opening);
		}
	}
	//create items and adds it to the store they indicate
	public void itemCreation(String ID) {
		//String name, String description, String category, Double price, int quantity
			//checks if the person requesting to create the item is authorized
			String role = validateID(ID);
			if(role != null) {
				if(role.equals("Admin")) {
					Scanner input = new Scanner(System.in);
					System.out.println("what is the name of the item");
					String name = input.nextLine();
					Item newItem = new Item();
					newItem.itemName = name;
					System.out.println("what is the description of the item");
					String description = input.nextLine();
					newItem.description = description;
					System.out.println("what is the category of the item");
					String category = input.nextLine();
					newItem.category = category;
					System.out.println("what is the price of the item");
					Double price = input.nextDouble();
					newItem.price  = price;
					System.out.println("what is the quantity of the item");
					int quantity = input.nextInt();
					newItem.initialQuantity = quantity;
					//adds newly created item to item array
					items.add(newItem);
					System.out.println("it was added");
					
					
				}
			}
			else {
				System.out.println("The ID you provided was not valid");
			}
		
	}
	public int searchItemName(String name) {
		int index=0;
		for(int i =0; i< items.size(); i++) {
			if(name.equals(items.get(i).itemName)) {
				System.out.println("Description: "+ items.get(i).description);
				System.out.println("Category: "+ items.get(i).category);
				System.out.println("Price: "+ items.get(i).price);
				index=i;
				//since each item should be named uniquely
				break;
				}
		}
		return index;
	}
	public void searchItemCategory(String category) {
		for(int i =0; i< items.size(); i++) {
			if(category.equals(items.get(i).category)) {
				System.out.println("Name: "+ items.get(i).itemName);
				System.out.println("Description: "+ items.get(i).description);
				System.out.println("Price: "+ items.get(i).price);
				}
		}
		
	}
	public void searchItemRange(double bottom, double top) {
		for(int i =0; i< items.size(); i++) {
			if(items.get(i).price >= bottom && items.get(i).price <= top) {
				System.out.println("Name: "+ items.get(i).itemName);
				System.out.println("Description: "+ items.get(i).description);
				System.out.println("Category: "+ items.get(i).category);
				System.out.println("Price: "+ items.get(i).price);
				}
		}
		
	}
	public void addItemToStore(String ID) {
		String role = validateID(ID);
		
		if(role != null) {
			Scanner input = new Scanner(System.in);
			System.out.println("what is the item you want to add?");
			String item = input.nextLine();
			//find the item in the item array
			int itemIndex=searchItemName(item);
			//create new item
			Item newItem = new Item();
			//copy item into newly created item instance
			newItem=items.get(itemIndex);
			System.out.println("what is the quantity you want to add?");
			int quantity = input.nextInt();
			input.nextLine();
			//the limit per item is 20
			if(newItem.initialQuantity + quantity > 20) {
				System.out.println("You can not have over 20 of the same item. Please try again");
			}
			else {
				//you will subtract the amount you are adding to the store from the original quantity
				items.get(itemIndex).initialQuantity -= quantity;
				//assign the number of that item that the will store have
				newItem.quantity = quantity;
				System.out.println("what store do you want to add to");
				String store = input.nextLine();
				//get index of the store you want to add an item to
				int storeIndex=searchStoreName(store);
				//add the item to that store
				stores.get(storeIndex).addItems(newItem);
				//for my own purposes
				//stores.get(storeIndex).print();
			}
		}else {
			System.out.println("The ID you provided was not valid");
		}
	}
	
	
	public static void main(String[] args) {
		StoreManagement l2 = new StoreManagement();
		Scanner input = new Scanner(System.in);
		System.out.println("You will need to first create an ID and assign a role");
		System.out.println("Please provide your ID ");
		String newID = input.nextLine();
		Boolean status = l2.assignID(newID);
		for(int i = 0; i<3;i++) {
			if(status == true) {
				System.out.println("what would you like to do? ");
				System.out.println("1: Create a store");
				System.out.println("2: assign a role");
				System.out.println("3: Create an item");
				System.out.println("4: Add an item to a store");
				System.out.println("5: Update a store");
				int choice = input.nextInt();
				input.nextLine(); 
				System.out.println("Please provide your ID ");
				String ID = input.nextLine();
				//input.nextLine();
				System.out.println("passed through here ");
				if(choice == 1){
					//ID = input.nextLine();
					l2.storeCreation(ID);
					//l2.deleteStore(ID);
					
				}
				if(choice == 2) {
					//ID = input.nextLine();
					l2.assignID(ID);
					
				}if(choice == 3) {
					//ID = input.nextLine();
					l2.itemCreation(ID);	
				}
				if(choice == 4) {
					//ID = input.nextLine();
					l2.addItemToStore(ID);
					//for my own purposes
					System.out.println("store added? "+l2.stores.get(0).storeName);
					System.out.println("item to first store added? "+l2.stores.get(0).storeItems.get(0).itemName);
				}if(choice == 5) {
					//ID = input.nextLine();
					l2.updateStore(ID);	
				}
				
				
			}else {
				System.out.println("Please try to assign an ID again");
				newID = input.nextLine();
				status=l2.assignID(newID);
			}
		
		}
		
		
	}
	public class Store{
		String storeName;
		String location;
		String contactInfo;
		String storeType;
		String opening;
		ArrayList<Item> storeItems = new ArrayList<Item>(); //all the items in the store
		
		public void addItems(Item items) {
			//the limit of items for each store is 20 items
			if(storeItems.size() == 20) {
				System.out.println("the store has reahed capicity. More items can not be added");
			}else {
				this.storeItems.add(items);
			}
		}
		//for my purposes
		public void print() {
			for(int i=0;i<storeItems.size();i++) {
				System.out.println("this was called again ");
				System.out.println("the items inside are "+ storeItems.get(i).itemName);
				System.out.println("the quanity of items inside are "+ storeItems.get(i).initialQuantity);
			}
		}
		
	}
	public class Item{
		String itemName;
		String description;
		String category;
		Double price;
		int initialQuantity;
		//quantity this will be the amount of that item a store has
		int quantity;
		//should I add limit for amount of initial quantity?

		
	}


}

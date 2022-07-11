package Main;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import Budget.Menu;

public class App {
    public static void main(String[] args) throws Exception {
	clrscr();
	System.out.println("-----------------------------------------------");
	System.out.println("°º     | Family Budget Management App |     º°");
	
    String currentUser;
	Menu <String, String> login = new Menu<>("TadeuV","070707");
	Menu <String,String> login2 = new Menu<>("AkaneM","151515");
	Scanner inp = new Scanner(System.in);

	login.dateForm();


	while(true){
	System.out.println("Type the username:");
	String user = inp.nextLine();
	System.out.println("Type the password:");
	String pass = inp.nextLine();
	clrscr();

	if((user.equals(login.getUserName())) && (pass.equals(login.getUserPassword()))){
		currentUser = login.getUserName().substring(0, login.getUserName().length()-1);
		break;
	}else if((user.equals(login2.getUserName())) && (pass.equals(login2.getUserPassword()))){
		currentUser = login2.getUserName().substring(0, login2.getUserName().length()-1);
		break;
	}else{
		System.out.println("\nWrong password or username. Press [enter] to continue or press [Q] to exit the program.");
	}

	if (inp.nextLine().toLowerCase().equals("q")){
		System.out.println("\nBye!");
		System.exit(0);
	}
	
	}
	clrscr();
	login.dateForm();
	System.out.println("Successfully logged in "+currentUser+"!");
	login.mainMenu();

	

   






	inp.close();
    }



    public static void clrscr(){
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
				System.out.print("\033\143");
		} catch (IOException | InterruptedException ex) {}
	}
}

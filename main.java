import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {

	// API
	static private ArrayList<String[]> apiAccount = new ArrayList<String[]>();
	static private ArrayList<String[]> apiUser = new ArrayList<String[]>();
	
	private static void api() throws Exception {
		// SEED
		Mysql mysql = new Mysql();
		// - User - //
		apiUser = mysql.getClients();
//		String[] user1 = new Client("Ederson", "123456KKK", "09848026911", "Waldemar Rau 29", "1200", "Garoto de programa").toJson();
//		String[] user2 = new Client("Leo", "123456NAZ", "09848026911", "Waldemar Rau 29", "1200", "Garoto de programa").toJson();
//		String[] user3 = new Client("Cassio", "123456Bomb", "09848026911", "Waldemar Rau 29", "1200", "Garoto de programa").toJson();
//		String[] user4 = new Client("Bruno", "123456Branc", "09848026911", "Waldemar Rau 29", "1200", "Garoto de programa").toJson();
//		String[] user5 = new Client("Vitor", "02", "09848026911", "Waldemar Rau 29", "1200", "Garoto de programa").toJson();
//		String[] user6 = new Client("Bruna", "02", "09848026911", "Waldemar Rau 30", "800", "Garoto de programa").toJson();
//		apiUser.add(user1);
//		apiUser.add(user2);
//		apiUser.add(user3);
//		apiUser.add(user4);
//		apiUser.add(user5);
//		apiUser.add(user6);

		// - Client - //
		apiAccount = mysql.getAccounts();
//		String[] acc1 = new Account("Ederson", "Conta Corrente", "123.44").toJson();
//		String[] acc2 = new Account("Leo", "Conta Corrente", "123.45").toJson();
//		String[] acc3 = new Account("Cassio", "Conta Corrente", "123.46").toJson();
//		String[] acc4 = new Account("Bruno", "Conta Corrente", "123.47").toJson();
//		String[] acc5 = new Account("Vitor", "Conta Corrente", "123.48").toJson();
//		String[] acc6 = new Account("Bruna", "Conta Corrente", "12.4").toJson();
//		apiAccount.add(acc1);
//		apiAccount.add(acc2);
//		apiAccount.add(acc3);
//		apiAccount.add(acc4);
//		apiAccount.add(acc5);
//		apiAccount.add(acc6);
		
	}
	
	static private Scanner sc = new Scanner(System.in);
	static private Client logged;
	static private Account loggedAccount;
	static String[] menu_logged = {"Meu perfil", "Alterar Perfil", "Sacar Valor", "Depositar Valor", "Transferir Valor", "Deslogar"};
	static String[] menu_not_logged = {"Logar", "Registro", "Sair"}; 
	
	public static void main(String[] args) throws Exception {
		api();
		Utils.print("B ase\r\n"
				+ "A dministrativa\r\n"
				+ "N umerica de\r\n"
				+ "C apital\r\n"
				+ "O ficial	\r"
			 );		
		Utils.slash();
		Utils.print("    _                     _\r\n"
				+ "  _|_|___________________|_|_\r\n"
				+ " |__:_____________________:__|\r\n"
				+ " |___________________________|\r\n"
				+ " |__:_____________________:__|\r\n"
				+ " |___________________________|\r\n"
				+ "   |_|___________________|_|\r\n"
				+ "  /__.___________________.__\\\r\n"
				+ " /__._____________________.__\\\r\n"
				+ "/_____________________________\\\r\n"
				+ "   | |_|               |_| |\r\n"
				+ "   | |                   | |\r\n"
				+ "   | |                   | |\r\n"
				+ "   |_|                   |_|\r\n\n"
			);
		Utils.slash();
		master();
	}
	
	public static void master() throws Exception {
		if(logged == null) {
			Utils.print("Vocï¿½ nï¿½o estï¿½ logado, o que gostaria de fazer?");
			
			int menu = menu("Menu", menu_not_logged);
			switch(menu) {
			case 1:
				login(null);
				break;
			case 2:
				registro();
				break;
			case 3:
				return;
			}
		}else {
			int menu = menu("Menu", menu_logged);
			switch(menu) {
				case 1:
					me();
					break;
				case 2:
					changeUser();
					break;
				case 3:
					withdraw();
					break;
				case 4:
					deposit();
					break;
				case 5:
					transfer();
					break;
				case 6:
					logged = null;
					break;
			}
		}
		Utils.clear();
		master();
	}
	
	private static int menu(String question, String[] opt) {
		Utils.print(question+'\n');
		for(int i = 1; i<=opt.length; i++) {
			Utils.print(i+" - "+opt[i-1]);
		}
		return sc.nextInt();
	}

	private static String[] getAccount(String ident) {
		int id = getAccountId(ident);
		
		String[] account = {};
		if(id != -1) {
			account = apiAccount.get(id);	
		}
		
		return account;
	}
	private static int getAccountId(String ident) {
		
		int id = -1;
		for(int i = 0; i<apiUser.size(); i++) {
			if(apiAccount.get(i)[0].equals(ident)) {
				id = i;
			}
		}
		return id;
	}
	
	private static String[] getUser(String ident) {
		
		int id = -1;
		String[] user = {};
		for(int i = 0; i<apiUser.size(); i++) {
			if(apiUser.get(i)[0].equals(ident)) {
				id = i;
			}
		}
			
		if(id != -1) {
			user = apiUser.get(id);	
		}
		
		return user;
	}
	
	private static void login(String indentificator) {
		String inputIdentificator;
		
		if(indentificator == null) {
			Utils.print("Qual seu identificador?");
			inputIdentificator = sc.next();			
		}else {
			inputIdentificator = indentificator;
		}
		
		String[] user = getUser(inputIdentificator);
		
		if(user.length == 0) {
			Utils.print("Usuario nï¿½o encontrado");
			return;
		}
		
		Utils.print("Usuario encontrado, qual a senha?");
		String password = sc.next();

		Utils.print(user[1]);
		Utils.print(password);
		if(user[1].equals(password)) {
			Utils.print("Você logou!");

			logged = new Client(user[0], user[1], user[2], user[3], user[4], user[5]);
			
			String[] account = getAccount(user[0]);
			loggedAccount = new Account(account[0], account[1], account[2]);
			return;
		}
		Utils.print("Senha errada, tentar novamente? ");
		
		String[] opt = {"Sim","Nï¿½o"};
		int toContinue = menu("Tentar novamente?\n", opt);
		if(toContinue == 1) {
			login(inputIdentificator);
		}
		return;
	}
	
	private static void me() {
		Utils.slash();
		Utils.print("Perfil: \n\n"+logged.info());
		Utils.print("\n Conta: \n\n"+loggedAccount.info());
		Utils.slash();
	}
	
	private static void registro() throws Exception {
		Mysql mysql = new Mysql();
		
		String ident = Utils.scan("Identificador: ");
		String pass = Utils.scan("Senha: ");
		String cpf = Utils.scan("CPF: ");
		String address = Utils.scan("EndereÃ§o: ");
		String salary = Utils.scan("Salario(mensal): ");
		String job = Utils.scan("Trabalho: ");

		String[] opt = {"Conta Corrente", "Conta Poupança", "Conta Salário"};
		int optAccountKind = menu("Escolha o tipo da conta: ", opt);
		
		String accountKind = opt[optAccountKind-1];

		Account newAccount = new Account(ident, accountKind, "0");
		mysql.createAccount(ident, accountKind, "0");
		
		loggedAccount = newAccount;

		apiAccount.add(newAccount.toJson());
		mysql.createClient(ident, cpf, pass, address, salary, job);
		
		logged = new Client(ident, cpf, pass, address, salary, job);

		apiUser.add(logged.toJson());
		
		Utils.print("Novo usuï¿½rio criado!!");
	}
	
	private static void changeUser() {
		String[] optMenu = {"Nome", "CPF", "Senha"};
		int res = menu("O que gostaria de alterar? ", optMenu);

		String oldName = logged.name;
		String altered = Utils.scan("Novo dado(a): ");
		
		switch(res) {
			case 1:
				logged.name = altered;
				break;
			case 2:
				logged.cpf = altered;
				break;
			case 3:
				logged.pass = altered;
				break;
		}

		String[] oldUser = getUser(oldName);
		apiUser.set(apiUser.indexOf(oldUser), logged.toJson());

		String[] optContinue = {"Sim","Nï¿½o"};
		int toContinue = menu("Deseja alterar outra parte do usuï¿½rio?\n", optContinue);
		if(toContinue == 1) {
			changeUser();
		}
		
		return;
	}

	public static void transfer() {
		String toUser = Utils.scan("Identificador do usuÃ¡rio para enviar: ");
		
		String[] userToAccount = getAccount(toUser);
		
		if(userToAccount.length == 0 || userToAccount[0] == loggedAccount.identifier) {
			Utils.print("UsuÃ¡rio nÃ£o encontrado, todo seu dinheiro serÃ¡ transferido para o banco em cotaÃ§Ã£o monetÃ¡ria de moeda Venezuela(bolÃ­vares venezuelanos)");
			return;
		}
		
		float ammount = Utils.scanFlo("Valor para ser transferido: ");

		if(ammount > loggedAccount.getAmmount()) {
			Utils.print("VocÃª nÃ£o tem dinheiro suficiente em sua conta para fazer a transaÃ§Ã£o");
			transfer();
			return;
		}

		loggedAccount.withdraw(ammount);

		Account toAccount = new Account(userToAccount[0], userToAccount[1], userToAccount[2]);
		
		toAccount.deposit(ammount);

		apiAccount.set(apiAccount.indexOf(getAccount(loggedAccount.identifier)), loggedAccount.toJson());
		apiAccount.set(apiAccount.indexOf(getAccount(userToAccount[0])), toAccount.toJson());
	}

	public static void deposit() {
		float ammount = Utils.scanFlo("Valor para ser depositado: ");
		
		String opt[] = {"C.R.I.P.T.O <<<", "Selic", "Poupança", "Colchão"};
		int investimentType = menu("Deseja deixar o dinheiro em qual tipo de investimento?", opt);
		
		switch(investimentType) {
			case 1:{
		        int random = new Random().nextInt(11);
				Utils.print("Investimento de risco...\n");
				if(random == 7) {
					Utils.print("VocÃª mutiplicou seu patrimonio em 100.000.000% ou 100.000 vezes :)");
					ammount *= 100000;
				}else {
					Utils.print("Todo seu dinheiro foi perdido =)");
					ammount = 0;					
				}
				break;
			}
			case 2:{
				Utils.print("Considerado o investimento mais seguro do paÃ­s...\n");
				Utils.print("ApÃ³s 1 ano teve um lucro de 12% :)");
				ammount *= 1.12f;
				break;
			}
			case 3:{
				Utils.print("Um dos investimentos mais escolhidos do paÃ­s...\n");
				Utils.print("Seu dinheiro rendeu, mas abaixo da inflaÃ§Ã£o, foi de base =)");
				ammount = ammount*0.9f;
				break;
			}
			case 4:{
				Utils.print("Ainda tem pessoas que fazem isso?\n");
				Utils.print("O valor das notas que guardava em baixo do colchÃ£o perderam valor =)");
				ammount = ammount*0.8f;
				break;
			}
		}
		
		loggedAccount.deposit(ammount);
	}

	public static void withdraw() {
		float ammount = Utils.scanFlo("Valor para ser sacar: ");
		
		if(ammount > loggedAccount.getAmmount()) {
			Utils.print("VocÃª nÃ£o tem dinheiro suficiente em sua conta para fazer a transaÃ§Ã£o");
			return;
		}
		
		loggedAccount.withdraw(ammount);
		
	}
}

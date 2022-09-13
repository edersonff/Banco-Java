
public class Client {
	public String name;
	public String cpf;
	public String pass;
	public String address;
	public String salary;
	public String job;
	
	public Client(String name, String cpf, String pass, String address, String salary, String job) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.pass = pass;
		this.address = address;
		this.salary = salary;
		this.job = job;
	}

	public String[] toJson() {
		String[] json = {
				this.name, this.cpf, this.pass, this.address, this.salary, this.job
		};
		return json;
	}
	

	public String info() {
		return 	"Nome: "+this.name+", \n"+
				"CPF: "+this.cpf+", \n"+
				"Senha: "+this.pass+", \n"+
				"Endereço: "+this.pass+", \n"+
				"Trabalho: "+this.job+", \n"+
				"Salario: "+this.salary+", \n"
		;
	}
}

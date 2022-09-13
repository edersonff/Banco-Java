import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Mysql {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost";
	final private String user = "root";
	final private String passwd = "root";


	public void createAccount(String ident, String accountKind, String ammount) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/banco?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			statement.executeUpdate(String.format("insert into account (client_ident, accountKind, ammount) values ('%s', '%s', '%s')", ident, accountKind, ammount));

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	public ArrayList<String[]> getAccounts() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/banco?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from account");
			return getAccountsResult(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	private ArrayList<String[]> getAccountsResult(ResultSet resultSet) throws SQLException {

		ArrayList<String[]> accounts = new ArrayList<String[]>();
		while (resultSet.next()) {

			accounts.add(
					new Account(
						resultSet.getString("client_ident"),
						resultSet.getString("accountKind"),
						resultSet.getString("ammount")
					).toJson()
					);
		}
		return accounts;
	}
//	Account
	public void createClient(String ident, String pass, String cpf, String address, String salary, String job) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/banco?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			statement.executeUpdate(String.format("insert into client (ident, pass, cpf, address, salary, job) values ('%s', '%s', '%s', '%s', '%s', '%s')", ident, pass, cpf, address, salary, job));

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	public ArrayList<String[]> getClients() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/banco?" + "user=" + user + "&password=" + passwd);

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from client");
			return getClientsResult(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	private ArrayList<String[]> getClientsResult(ResultSet resultSet) throws SQLException {

		ArrayList<String[]> accounts = new ArrayList<String[]>();
		while (resultSet.next()) {

			accounts.add(
					new Client(
						resultSet.getString("ident"),
						resultSet.getString("cpf"),
						resultSet.getString("pass"),
						resultSet.getString("address"),
						resultSet.getString("salary"),
						resultSet.getString("job")
					).toJson());
		}
		return accounts;
	}
	
	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
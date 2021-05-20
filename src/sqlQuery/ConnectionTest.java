package sqlQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
		final String DB_NAME = "cityLodgeDB";
		
		//use try-with-resources Statement
	public Connection Connection() {
			try {
				Connection con = getConnection();
				System.out.println("Connection to database " + DB_NAME + " created successfully");

				//checking table exists
				if(new CheckTableExist().CheckTableExist(con,"roomrecord").equals("not exists")){
					new CreateTable().CreateRoomRecordTable(con,"roomrecord");
					new InsertRow(con);
				}

				return con;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			return null;
	}

	//getting connection of the database
	public Connection getConnection()
					throws SQLException, ClassNotFoundException {
		//Registering the HSQLDB JDBC driver
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
			
		/* Database files will be created in the "database" 
		 * folder in the project. If no username or password is 
		 * specified, the default SA user and an empty password are used */
		Connection con = DriverManager.getConnection
				("jdbc:hsqldb:file:database/" + DB_NAME, "SA", "");
		return con;
	}
}

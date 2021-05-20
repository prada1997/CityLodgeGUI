package sqlQuery;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTableExist {


		public String CheckTableExist(Connection con, String TABLE_NAME ){
		try {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + TABLE_NAME + " exists.");
					return "exists";
				}
				else {
					System.out.println("Table " + TABLE_NAME + " does not exist.");

				}	
				tables.close();
				return "not exists";
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			return "null";
		}
}

package sqlQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRow {
        private Statement stmt;
        //inserting data in the database in the start of the program.
		public InsertRow(Connection con) throws SQLException {

             stmt = con.createStatement();
            int result = 5;
            try {
                String insertRoom1 = "INSERT INTO roomrecord"  +
                        " VALUES ('r_100', 2, 'Standard', 'Available', null, 'AC, Fan, TV', 'file:roomImages/1.jpg')";
                String insertRoom2 = "INSERT INTO roomrecord"  +
                        " VALUES ('r_101', 4, 'Standard', 'Available', null,  'AC, Fan, TV', 'file:roomImages/2.jpg');";
                String insertRoom3 = "INSERT INTO roomrecord"  +
                        " VALUES ('s_102', 6, 'Suite', 'Available', null, 'AC, Fan, TV', 'file:roomImages/3.jpg');";
                String insertRoom4 = "INSERT INTO roomrecord"  +
                        " VALUES ('s_100', 6, 'Suite', 'Available', null, 'AC, Fan, TV', 'file:roomImages/4.jpg');";
                String insertRoom5 = "INSERT INTO roomrecord"  +
                        " VALUES ('r_103', 2, 'Standard', 'Available', null, 'AC, Fan, TV',  'file:roomImages/5.jpg');";

                stmt.executeQuery(insertRoom1);
                stmt.executeQuery(insertRoom2);
                stmt.executeQuery(insertRoom3);
                stmt.executeQuery(insertRoom4);
                stmt.executeQuery(insertRoom5);

                String insertRoom6 = "INSERT INTO hiringrecord"
                        + " VALUES ('r_100','r_100_cust_Prada_13102019', '13/10/2019', '16/10/2019', '17/10/2019',236.00, 79.00),"
                        + "('r_100', 'r_100_cust_dexter_02082019', '02/08/2019', '07/08/2019','07/08/2019',295.00,0.00),"
                        + "('r_100', 'r_100_cust_dexter_02082019', '02/08/2019', '07/08/2019','07/08/2019',295.00,0.00),"
                        + "('s_100', 's_100_cust_Vien_11102019', '11/10/2019','14/10/2019','15/10/2019',3996.00,1099.00),"
                        + "('s_100', 's_100_cust_baba_24092019', '24/09/2019','28/09/2019','28/09/2019',3996.00,0.00)";
//stmt.executeQuery(insertRoom6);
                con.commit();

                System.out.println("Insert into table test"   + " executed successfully");
			    System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

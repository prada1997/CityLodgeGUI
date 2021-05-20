package sqlQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	private PreparedStatement stmt;
		//creating table for the database to store data
	public void CreateRoomRecordTable(Connection con, String TABLE_NAME) {
		try {
			stmt = con.prepareStatement("CREATE TABLE roomrecord ("
					+ "ROOMID VARCHAR(20) NOT NULL,"
					+ "NumberOFBEDS INT ,"
					+ "ROOMTYPE VARCHAR(20) ,"
					+ "ROOMSTATUS VARCHAR(30) ,"
					+ "LASTMAINTENANCEDATE VARCHAR(40),"
					+ "FEATURES VARCHAR(500) ,"
					+ "ROOMIMAGE VARCHAR(30),"
					+ "CONSTRAINT room_PK PRIMARY KEY (ROOMID)"+");");
			stmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//creating table for the database to store data
	public void CreateHiringRecordTable(Connection con, String TABLE_NAME) {
		try {
			stmt = con.prepareStatement("CREATE TABLE hiringrecord ("
					+ "ROOMID 			VARCHAR(20), "
					+ "record_id        VARCHAR (128) NOT NULL, "
					+ "rent_date      	VARCHAR(30), "
					+ "estimated_return VARCHAR(30), "
					+ "actual_return    VARCHAR(30), "
					+ "rental_fee       NUMERIC, "
					+ "late_fee        	NUMERIC, "
					+ "CONSTRAINT 		temp_PK	PRIMARY KEY(record_id), "
					+ "CONSTRAINT 		hiringRecord_PK	FOREIGN KEY(ROOMID) REFERENCES roomrecord(ROOMID)"+");");
			stmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

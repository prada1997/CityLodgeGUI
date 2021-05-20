package sqlQuery;
import controller.RecordObservableController;
import model.*;

import java.sql.*;
import java.util.Map;

public class FetchRecords {

		private final String sql_select = "SELECT * FROM roomrecord";
		private Map<String, Room> roomRecord;
		public PreparedStatement stat;
		public FetchRecords(Connection con, RecordObservableController roomRecord) throws SQLException {
			stat = con.prepareStatement(sql_select);
			this.roomRecord = roomRecord.getMap();
			fillMap();
			roomRecord.setMap(this.roomRecord);
		}

	//fetching from the database into result and storing it to the map
	public void fillMap() {
			try{

			ResultSet resultSet = stat.executeQuery();
			while(resultSet.next()){
				if(resultSet.getString("ROOMTYPE").toLowerCase().equals("standard"))	{

					StandardRoom obj = new StandardRoom(resultSet.getString("ROOMID"),
							resultSet.getInt("NumberofBEDS"), resultSet.getString("ROOMTYPE"), resultSet.getString("roomstatus"),
							resultSet.getString("FEATURES"), resultSet.getString("ROOMIMAGE"));
					obj.setRoomStatus(resultSet.getNString(5));

					roomRecord.put(resultSet.getString(1), obj );

				}
				else {
					DateTime dateTime = new DateTime();

					SuiteRoom obj = new SuiteRoom(resultSet.getString("ROOMID"), resultSet.getInt("NumberofBEDS")
							,resultSet.getString("ROOMTYPE"), resultSet.getString("roomstatus")
							,dateTime,resultSet.getString("FEATURES"), resultSet.getString("ROOMIMAGE"));

					roomRecord.put(resultSet.getString(1), obj);
				}
			}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
}

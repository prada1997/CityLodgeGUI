package model;

public class SuiteRoom extends Room {
	
	private DateTime lastMaintenanceDate;
	
	public SuiteRoom(String roomId, int numberOfBeds,String roomType, String roomStatus,
					 DateTime lastMaintenanceDate,String features, String roomImage) {
		// TODO Auto-generated constructor stub
		super.setRoomId(roomId);
		super.setNumberOfBeds(numberOfBeds);
		super.setRoomType(roomType);
		super.setRoomStatus(roomStatus);
		this.lastMaintenanceDate = lastMaintenanceDate;
		super.setFeatures(features);
		super.setRoomImage(roomImage);
	}

	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		
		if ( super.getRoomStatus() == "Maintenance" || super.getRoomStatus() == "Rented") {
			return false;
		}	
			
		DateTime estimatedReturnDate = new DateTime(rentDate, numOfRentDay);
		HiringRecord obj = new HiringRecord(customerId, super.getRoomId(), rentDate, estimatedReturnDate);
		obj.rentalFeeCalculation(super.getNumberOfBeds(), numOfRentDay);
		super.roomRecord(obj);
		super.setRoomStatus("Rented");
		
		return true;
	}
	

	public boolean returnRoom(DateTime returnDate) {
		
		if ( super.getRoomStatus() == "Available" || super.getRoomStatus() == "Maintenance") {
			return false;
		}
				
		super.getRecord().get(super.getEntries()).setActualReturnDate(returnDate); 
		super.getRecord().get(super.getEntries()).lateFeeCalculation(super.getNumberOfBeds());
		super.setRoomStatus("Available");
		super.setEntries(getEntries() + 1);;
		
		return true;
	}
	
	
	public void performMaintenance() {

		if (!super.getRoomStatus().equals("Rented")) {
			super.setRoomStatus("Maintenance");

		}
	}

	
	public void completeMaintenance(DateTime completionDate) {
		
			if ( super.getRoomStatus() == "Maintenance") {
				super.setRoomStatus("Available");
				lastMaintenanceDate = completionDate;
			}
	}
	
	
	public String toString() {
		
		String toString;
		
		toString = super.getRoomId() + ":" + super.getNumberOfBeds() + ":" + super.getRoomType() 
				 + ":" + super.getRoomStatus() + ":" + super.getFeatures() + ":" + lastMaintenanceDate + ":" + super.getRoomImage();
		
		return toString;
	}

	
	public String getDetails() {
		
		String details = "model.Room ID: " + super.getRoomId() + "\n" + "Number of beds: " + super.getNumberOfBeds() +
						 "\n" + "Type: " + super.getRoomType() + "\n" + "Status: " + super.getRoomStatus() + "\n" +
						 "Last maintenance date: " + lastMaintenanceDate +
						 "\n" +"Feature summary: " + super.getFeatures() + "\n";
		
		
		details = details + "RENTAL RECORD: ";
		
		if (super.getRecord().isEmpty()) {
			details = details + "Empty";
			return details;
		}
		
		else {
			String	keySet = super.getRecord().keySet().toString();
			String [] keySetArray = keySet.substring(1, keySet.length() - 1).split(", ");
	
			int key = keySetArray.length;
			while (key > 0) {
				key--;
				details = details + super.getRecord().get(Integer.valueOf(keySetArray[key])).getDetails();
			}
		}	
		
		return details;
	}
	
	
	public DateTime getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
}


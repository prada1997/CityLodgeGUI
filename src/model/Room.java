package model;

import java.util.HashMap;

public abstract class Room {
	
	private String roomId;
	private int numberOfBeds;
	private String features;
	private String roomType;
	private String roomStatus;
	private int entries = 1;
	private String roomImage;
	private HashMap<Integer, HiringRecord> record = new HashMap<Integer, HiringRecord>();
	
	
	abstract public boolean rent(String customerId, DateTime rentDate, int numOfRentDay);

	abstract public boolean returnRoom(DateTime returnDate);
	
	abstract public void performMaintenance();

	abstract public void completeMaintenance(DateTime completionDate);

	public void setRoomImage(String roomImage) {
		this.roomImage = roomImage;
	}

	public String getRoomImage() {
		return roomImage;
	}

	abstract public String toString();
	
	abstract public String getDetails();
	
	
	public void roomRecord(HiringRecord object) {
		
		if (entries > 10) {
			entries = 1;
		}
	
		if (entries <= 10) {
			record.remove(entries);
			record.put(entries, object);
		}
		

	}

	public String getRoomId() {
		return roomId;
	}


	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


	public int getNumberOfBeds() {
		return numberOfBeds;
	}


	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getRoomStatus() {
		return roomStatus;
	}


	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}


	public int getEntries() {
		return entries;
	}


	public void setEntries(int entries) {
		this.entries = entries;
	}

	
	public HashMap<Integer, HiringRecord> getRecord() {
		return record;
	}


	public void setRecord(HashMap<Integer, HiringRecord> record) {
		this.record = record;
	}
	
}

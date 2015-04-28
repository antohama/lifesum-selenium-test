package com.selenium.testdata;

import java.util.Random;

public class UserData {
	
	public enum Gender {
		Male,
		Female
	}
	
	public enum Region {
		US,
		EU,
		IMP,
		AUS		
	}
	
	private Region region;
	private Gender gender;
	private int year;
	private int month;
	private int day;
	private int height;
	private int weight;
	private int activity;
	private int goalWeight;
	private int looseRate;
	
	public Region getRegion() {
		return region;
	}

	public Gender getGender() {
		return gender;
	}

	public String getYear() {
		return String.valueOf(year);
	}

	public String getMonth() {
		return String.format("%02d", month);
	}

	public String getDay() {
		return String.format("%02d", day);
	}

	public String getHeight() {
		return String.valueOf(height);
	}

	public String getWeight() {
		return String.valueOf(weight);
	}
	
	public String getActivity() {
		return String.valueOf(activity);
	}
	
	public String getGoalWeight() {
		return String.valueOf(goalWeight);
	}
	
	
	public String getLooseRate() {
		return String.valueOf(looseRate);
	}

	public UserData setRegion(Region region) {
		this.region = region;
		return this;
	}

	public UserData setGender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public UserData setYear(int year) {
		this.year = year;
		return this;
	}

	public UserData setMonth(int month) {
		this.month = month;
		return this;
	}

	public UserData setDay(int day) {
		this.day = day;
		return this;
	}

	public UserData setHeight(int height) {
		this.height = height;
		return this;
	}

	public UserData setWeight(int weight) {
		this.weight = weight;
		return this;
	}

	public UserData setActivity(int activity) {
		this.activity = activity;
		return this;
	}
	
	public UserData setGoalWeight(int goalWeight){
		this.goalWeight = goalWeight;
		return this;
	}
	
	public UserData setLooseRate(int looseRate) {
		this.looseRate = looseRate;
		return this;
	}
	
	public UserData() {
		Random rnd = new Random();
		this.region = getRandomRegion();
		this.gender = getRandomGender();
		this.year = 1916 + rnd.nextInt(86);
		this.day = 1 + rnd.nextInt(31);
		this.month = 1 + rnd.nextInt(12);
		this.height = rnd.nextInt(250);
		this.weight = rnd.nextInt(200);
		this.activity = 1 + rnd.nextInt(4);
		this.goalWeight = rnd.nextInt(this.weight);
		this.looseRate = 1 + rnd.nextInt(4);
	}

	private Region getRandomRegion() {	
		int pick = new Random().nextInt(Region.values().length);
		return Region.values()[pick];
	}
	
	private Gender getRandomGender() {	
		int pick = new Random().nextInt(Gender.values().length);
		return Gender.values()[pick];
	}

	@Override
	public String toString() {
		return "UserCharacteristics [region = " + region + ", gender = " + gender
				+ ", year = " + year + ", month = " + month + ", day = " + day
				+ ", height = " + height + ", weight = " + weight + ", activity = " + activity 
				+ ", goal = " + goalWeight + ", loose rate = " + looseRate + "]";
	}

	
}
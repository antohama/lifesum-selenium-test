package com.selenium.testdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.selenium.testdata.UserData.Gender;
import com.selenium.testdata.UserData.Region;
import com.thoughtworks.xstream.XStream;

public class UserDataGenerator {

	public static void main(String[] args) {

		if (args.length < 3) {
			System.out
					.println("Specify all necessary parameters: <amount of data sets> <input file name> <input file format>");
			return;
		}

		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];

		List<UserData> users = generateRandomUsers(amount);
		if (format.toLowerCase().equals("csv")) {
			try {
				saveUsersToCSV(users, file);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\n\n\nError message: " + e.getMessage());

			}
		} else if (format.toLowerCase().equals("xml")) {
			try {
				saveUsersToXML(users, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Wrong file format: " + format);
			return;
		}
	}

	private static void saveUsersToXML(List<UserData> users, File file)
			throws IOException {
		XStream xstream = new XStream();
		xstream.alias("userData", UserData.class);
		String xml = xstream.toXML(users);
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();

	}

	@SuppressWarnings("unchecked")
	public static List<UserData> loadUsersFromXML(String fileName)
			throws IOException {
		XStream xstream = new XStream();
		xstream.alias("userData", UserData.class);
		return (List<UserData>) xstream.fromXML(new File(fileName));
	}

	private static void saveUsersToCSV(List<UserData> users, File file)
			throws IOException {

		FileWriter writer = new FileWriter(file);

		for (UserData user : users) {
			writer.write(user.getRegion() + "," + user.getGender() + ","
					+ user.getYear() + "," + user.getMonth() + ","
					+ user.getDay() + "," + user.getHeight() + ","
					+ user.getWeight() + "\n");
		}
		writer.close();

	}

	public static List<UserData> loadUsersFromCSV(String fileName)
			throws IOException {
		File file = new File(fileName);
		List<UserData> users = new ArrayList<UserData>();
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] part = line.split(",");
			UserData user = new UserData().setRegion(Region.valueOf(part[0]))
					.setGender(Gender.valueOf(part[1]))
					.setYear(Integer.parseInt(part[2]))
					.setMonth(Integer.parseInt(part[3]))
					.setDay(Integer.parseInt(part[4]))
					.setHeight(Integer.parseInt(part[5]))
					.setWeight(Integer.parseInt(part[6]));
			users.add(user);
			line = bufferedReader.readLine();
		}

		bufferedReader.close();
		return users;
	}

	public static List<UserData> generateRandomUsers(int amount) {
		List<UserData> users = new ArrayList<UserData>();
		for (int i = 0; i < amount; i++) {
			UserData user = new UserData();
			users.add(user);
		}

		return users;
	}

}

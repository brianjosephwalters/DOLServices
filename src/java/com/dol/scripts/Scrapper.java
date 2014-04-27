/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dol.scripts;

import com.dol.db.DolDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author bjw
 */
public class Scrapper {
    private Scanner scanner;
    private HashMap<String, String> entries;
    
    public Scrapper(String filename) {
        File file = new File(filename);
        scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entries = new HashMap<>();
    }
    
    public void run() {
        if (scanner != null) {
            scanner = scanner.useDelimiter("\t");
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split("\t");
                String id = strings[0];
                String title = "";
                if (strings.length > 1)
                    title = strings[1].replace("\n", "");
                if (!id.equals(""))
                    entries.put(id, title);
            }
        }
        else {
            System.out.println("Unable to open.");
        }
    }
    
    public void insert() {
        DolDB db = new DolDB();
        Connection connection = db.dbConnection();
        String query = " INSERT INTO skill(skill_id, skill_name) VALUES (?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
        
            for (String key : this.entries.keySet()) {
                stmt.setString(1, key);
                stmt.setString(2, entries.get(key));
                stmt.executeUpdate();
                System.out.println("added: " + key + ", " + entries.get(key));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static void main (String args[]) {
        Scrapper scrapper = new Scrapper("./data/list.csv");
        scrapper.run();
        scrapper.insert();
    }
    
}

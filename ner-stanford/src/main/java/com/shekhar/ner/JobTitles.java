package com.shekhar.ner;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class JobTitles {

    public static void main(String[] args) {

        String filePath = "src/job.titles";
        String outputFilePath = "src/job.titles.compressed";
        String stopwordFile = "src/stopwords.txt";

        List<String> stopwords = new ArrayList<>();
        try {
            stopwords = Files.readAllLines(new File(stopwordFile).toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
//            List<String> remove = Arrays.asList(new String[]{"I", "II", "III", "IV", "V", "VI"});
            List<String> countryNames = Arrays.asList(new String[]{"India", "Germany", "United States", "USA", "Saudi Arabia", "Gulf", "Middle East", "United Kingdom", "UK", "U.K.", "Dublin", "UT", "Romania", "GCMM", "Spain", "Romania", "Switzerland", "Munich", "EMEA", "Americas"});
            List<String> stringWithDashes = Arrays.asList(new String[]{"Bi-Lingual","Pre-Sales","Database - DBA","Database-DBA", "Middleware - Identity Management Technical Analyst"});

            int n=0;
            while ((line = br.readLine()) != null) {
                n++;
                System.out.println (n);
                if (line.startsWith("Database")) {
                    System.out.println("hold");
                }

                String com = new String(line);
                map.put(line, com);

                // remove unicode
                StringBuilder newCom = new StringBuilder();
                for(char val : com.toCharArray()) {
                    if(val < 192) newCom.append(val);
                }
                com = newCom.toString().trim().replaceAll(" +", " ").trim();

                if (com.contains("("))
                    com = com.split("[(]")[0].trim();

                if (com.contains("|"))
                    com = com.split("[|]")[0].trim();

                if (com.contains("~"))
                    com = com.split("~")[0].trim();

                if (com.contains("/"))
                    com = com.split("/")[0].trim();

                boolean validDash = false;
                for (String s:stringWithDashes) {
                    if (com.contains(s)) {
                        validDash = true;
                    }
                }
                if (!validDash) {
                    if (com.contains("-"))
                        com = com.split("-")[0].trim();
                }

                if (com.contains("?"))
                    com = com.split("[?]")[0].trim();

                // remove country
                for (String country:countryNames) {
                    if (com.contains(country)) {
                        com = com.replaceAll(country, " ");
                    }
                }
                com = com.trim().replaceAll(" +", " ");

                com = com.split("( ){2,10}")[0];
                com = com.replaceAll("\\d","").trim();

                // remove roman letters
                // remove stopwords from the end
                String[] comParts = com.split(" ");
                for (String rem : stopwords) {
                    if (comParts[comParts.length-1].equalsIgnoreCase(rem)) {
                        // remove last part and create new comParts
                        StringBuilder b = new StringBuilder();
                        for(int i=0; i<comParts.length-1; ++i) {
                            b.append(comParts[i].trim()).append(" ");
                        }
                        com = b.toString().trim();
                        comParts = com.split(" ");
                        //com = com.replaceAll(rem, "").trim();
                    }
                }

                com = com.replaceAll("( [a-zA-Z0-9]{1})$","");


                com = com.trim().replaceAll(" +", " ");
                map.put(line, com);

            }
        } catch (Exception e) {
            System.err.println("Error: "+ e);
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
            for (String key: map.keySet()) {
                bw.write(key + ";" + map.get(key));
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}

import java.io.*;
import java.security.*;

public class MD5_project {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        String firstName = "", rollNumber = "", line;
        while ((line = br.readLine()) != null) {
        if (line.contains("\"first_name\":")) firstName = extractValue(line);
        if (line.contains("\"roll_number\":")) rollNumber = extractValue(line);
        }

        String md5Hash = generateMD5Hash((firstName + rollNumber).toLowerCase().replaceAll(" ", ""));
        writer.write(md5Hash);
         System.out.println("MD5 Hash generated and stored in output.txt");

         } catch (IOException e) {
     e.printStackTrace();
        }
    }

    private static String extractValue(String line) {
        String[] parts = line.split("\":\"", 2);
        return (parts.length > 1) ? parts[1].replace("\"", "") : "";
    }

    private static String generateMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
             byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder hashStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashStringBuilder.append(String.format("%02x", b));
             }
             return hashStringBuilder.toString();
         } catch (NoSuchAlgorithmException e) {
             throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}

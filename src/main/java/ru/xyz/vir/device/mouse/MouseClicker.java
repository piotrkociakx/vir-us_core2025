package ru.xyz.vir.device.mouse;

import ru.xyz.Vir_USCore;
import ru.xyz.function.Function;
import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.DevicePerm;
import ru.xyz.vir.device.keyboard.KeyboardAction;
import ru.xyz.vir.exception.Vir_usException;

import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Klasa MouseClicker ma metody do klikania myszą oraz przesuwania kursora.
 */
public class MouseClicker implements Function {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (Exception e) {
            throw new Vir_usException("Nie udało się utworzyć obiektu klasy Robot.", e.getMessage());
        }
    }

    @Override
    public Perm requiredPermisionLevel() {
        return new DevicePerm();
    }

    /**
     * Wykonuje lewy klik myszą.
     */
    public static void leftClick() {
        if(!Vir_USCore.contains(new MouseClicker().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Wykonuje prawy klik myszą.
     */
    public static void rightClick() {
        if(!Vir_USCore.contains(new MouseClicker().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    /**
     * Przesuwa kursor myszy do określonej pozycji.
     *
     * @param x współrzędna x docelowej pozycji kursora
     * @param y współrzędna y docelowej pozycji kursora
     */
    public static void moveMouse(int x, int y) {
        robot.mouseMove(x, y);
    }
}
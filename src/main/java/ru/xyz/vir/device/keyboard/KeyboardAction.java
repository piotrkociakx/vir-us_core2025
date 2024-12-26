package ru.xyz.vir.device.keyboard;

import ru.xyz.Vir_USCore;
import ru.xyz.function.Function;
import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.DevicePerm;
import ru.xyz.vir.camera.CameraCapture;
import ru.xyz.vir.exception.Vir_usException;

import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Klasa KeyboardAction zapewnia funkcje do naciskania klawiszy na klawiaturze.
 * <p>
 * Specjalne przyciski:
 * - ENTER
 * - BACK\_SPACE
 * - TAB
 * - CANCEL
 * - CLEAR
 * - SHIFT
 * - CONTROL
 * - ALT
 * - PAUSE
 * - CAPS\_LOCK
 * - ESCAPE
 * - SPACE
 * - PAGE\_UP
 * - PAGE\_DOWN
 * - END
 * - HOME
 * - LEFT
 * - UP
 * - RIGHT
 * - DOWN
 * - COMMA
 * - MINUS
 * - PERIOD
 * - SLASH
 * - SEMICOLON
 * - EQUALS
 * - OPEN\_BRACKET
 * - BACK\_SLASH
 * - CLOSE\_BRACKET
 * - NUMPAD0
 * - NUMPAD1
 * - NUMPAD2
 * - NUMPAD3
 * - NUMPAD4
 * - NUMPAD5
 * - NUMPAD6
 * - NUMPAD7
 * - NUMPAD8
 * - NUMPAD9
 * - MULTIPLY
 * - ADD
 * - SEPARATOR
 * - SUBTRACT
 * - DECIMAL
 * - DIVIDE
 * - DELETE
 * - NUM\_LOCK
 * - SCROLL\_LOCK
 * - F1 - F24
 * - PRINTSCREEN
 * - INSERT
 * - HELP
 * - META
 * - BACK\_QUOTE
 * - QUOTE
 * - KP\_UP
 * - KP\_DOWN
 * - KP\_LEFT
 * - KP\_RIGHT
 * - DEAD\_GRAVE
 * - DEAD\_ACUTE
 * - DEAD\_CIRCUMFLEX
 * - DEAD\_TILDE
 * - DEAD\_MACRON
 * - DEAD\_BREVE
 * - DEAD\_ABOVEDOT
 * - DEAD\_DIAERESIS
 * - DEAD\_ABOVERING
 * - DEAD\_DOUBLEACUTE
 * - DEAD\_CARON
 * - DEAD\_CEDILLA
 * - DEAD\_OGONEK
 * - DEAD\_IOTA
 * - DEAD\_VOICED\_SOUND
 * - DEAD\_SEMIVOICED\_SOUND
 * - AMPERSAND
 * - ASTERISK
 * - QUOTEDBL
 * - LESS
 * - GREATER
 * - BRACELEFT
 * - BRACERIGHT
 * - AT
 * - COLON
 * - CIRCUMFLEX
 * - DOLLAR
 * - EURO\_SIGN
 * - EXCLAMATION\_MARK
 * - INVERTED\_EXCLAMATION\_MARK
 * - LEFT\_PARENTHESIS
 * - NUMBER\_SIGN
 * - PLUS
 * - RIGHT\_PARENTHESIS
 * - UNDERSCORE
 */
public class KeyboardAction implements Function {

    @Override
    public Perm requiredPermisionLevel() {
        return new DevicePerm();
    }

    private static Robot robot;

    static {
        if(!Vir_USCore.contains(new KeyboardAction().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        try {
            robot = new Robot();
        } catch (Exception e) {
            throw new Vir_usException("Nie można utworzyć obiektu klasy Robot.", e.getMessage());
        }
    }

    /**
     * Naciska sekwencję klawiszy.
     *
     * @param keys tablica nazw klawiszy do naciśnięcia
     */
    public static void press(String... keys) {
        if(!Vir_USCore.contains(new KeyboardAction().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        for (String key : keys) {
            press(key);
        }
    }

    /**
     * Naciska pojedynczy klawisz.
     *
     * @param key nazwa klawisza do naciśnięcia
     */
    private static void press(String key) {
        int keyCode = getKeyCode(key);
        if (keyCode != -1) {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }

    /**
     * Zwraca kod klawisza dla podanej nazwy klawisza.
     *
     * @param key nazwa klawisza
     * @return kod klawisza, lub -1 jeśli klawisz nie jest rozpoznawany
     */
    private static int getKeyCode(String key) {
    switch (key.toUpperCase()) {
        case "A": return KeyEvent.VK_A;
        case "B": return KeyEvent.VK_B;
        case "C": return KeyEvent.VK_C;
        case "D": return KeyEvent.VK_D;
        case "E": return KeyEvent.VK_E;
        case "F": return KeyEvent.VK_F;
        case "G": return KeyEvent.VK_G;
        case "H": return KeyEvent.VK_H;
        case "I": return KeyEvent.VK_I;
        case "J": return KeyEvent.VK_J;
        case "K": return KeyEvent.VK_K;
        case "L": return KeyEvent.VK_L;
        case "M": return KeyEvent.VK_M;
        case "N": return KeyEvent.VK_N;
        case "O": return KeyEvent.VK_O;
        case "P": return KeyEvent.VK_P;
        case "Q": return KeyEvent.VK_Q;
        case "R": return KeyEvent.VK_R;
        case "S": return KeyEvent.VK_S;
        case "T": return KeyEvent.VK_T;
        case "U": return KeyEvent.VK_U;
        case "V": return KeyEvent.VK_V;
        case "W": return KeyEvent.VK_W;
        case "X": return KeyEvent.VK_X;
        case "Y": return KeyEvent.VK_Y;
        case "Z": return KeyEvent.VK_Z;
        case "0": return KeyEvent.VK_0;
        case "1": return KeyEvent.VK_1;
        case "2": return KeyEvent.VK_2;
        case "3": return KeyEvent.VK_3;
        case "4": return KeyEvent.VK_4;
        case "5": return KeyEvent.VK_5;
        case "6": return KeyEvent.VK_6;
        case "7": return KeyEvent.VK_7;
        case "8": return KeyEvent.VK_8;
        case "9": return KeyEvent.VK_9;
        case "ENTER": return KeyEvent.VK_ENTER;
        case "BACK_SPACE": return KeyEvent.VK_BACK_SPACE;
        case "TAB": return KeyEvent.VK_TAB;
        case "CANCEL": return KeyEvent.VK_CANCEL;
        case "CLEAR": return KeyEvent.VK_CLEAR;
        case "SHIFT": return KeyEvent.VK_SHIFT;
        case "CONTROL": return KeyEvent.VK_CONTROL;
        case "ALT": return KeyEvent.VK_ALT;
        case "PAUSE": return KeyEvent.VK_PAUSE;
        case "CAPS_LOCK": return KeyEvent.VK_CAPS_LOCK;
        case "ESCAPE": return KeyEvent.VK_ESCAPE;
        case "SPACE": return KeyEvent.VK_SPACE;
        case "PAGE_UP": return KeyEvent.VK_PAGE_UP;
        case "PAGE_DOWN": return KeyEvent.VK_PAGE_DOWN;
        case "END": return KeyEvent.VK_END;
        case "HOME": return KeyEvent.VK_HOME;
        case "LEFT": return KeyEvent.VK_LEFT;
        case "UP": return KeyEvent.VK_UP;
        case "RIGHT": return KeyEvent.VK_RIGHT;
        case "DOWN": return KeyEvent.VK_DOWN;
        case "COMMA": return KeyEvent.VK_COMMA;
        case "MINUS": return KeyEvent.VK_MINUS;
        case "PERIOD": return KeyEvent.VK_PERIOD;
        case "SLASH": return KeyEvent.VK_SLASH;
        case "SEMICOLON": return KeyEvent.VK_SEMICOLON;
        case "EQUALS": return KeyEvent.VK_EQUALS;
        case "OPEN_BRACKET": return KeyEvent.VK_OPEN_BRACKET;
        case "BACK_SLASH": return KeyEvent.VK_BACK_SLASH;
        case "CLOSE_BRACKET": return KeyEvent.VK_CLOSE_BRACKET;
        case "NUMPAD0": return KeyEvent.VK_NUMPAD0;
        case "NUMPAD1": return KeyEvent.VK_NUMPAD1;
        case "NUMPAD2": return KeyEvent.VK_NUMPAD2;
        case "NUMPAD3": return KeyEvent.VK_NUMPAD3;
        case "NUMPAD4": return KeyEvent.VK_NUMPAD4;
        case "NUMPAD5": return KeyEvent.VK_NUMPAD5;
        case "NUMPAD6": return KeyEvent.VK_NUMPAD6;
        case "NUMPAD7": return KeyEvent.VK_NUMPAD7;
        case "NUMPAD8": return KeyEvent.VK_NUMPAD8;
        case "NUMPAD9": return KeyEvent.VK_NUMPAD9;
        case "MULTIPLY": return KeyEvent.VK_MULTIPLY;
        case "ADD": return KeyEvent.VK_ADD;
        case "SEPARATOR": return KeyEvent.VK_SEPARATOR;
        case "SUBTRACT": return KeyEvent.VK_SUBTRACT;
        case "DECIMAL": return KeyEvent.VK_DECIMAL;
        case "DIVIDE": return KeyEvent.VK_DIVIDE;
        case "DELETE": return KeyEvent.VK_DELETE;
        case "NUM_LOCK": return KeyEvent.VK_NUM_LOCK;
        case "SCROLL_LOCK": return KeyEvent.VK_SCROLL_LOCK;
        case "F1": return KeyEvent.VK_F1;
        case "F2": return KeyEvent.VK_F2;
        case "F3": return KeyEvent.VK_F3;
        case "F4": return KeyEvent.VK_F4;
        case "F5": return KeyEvent.VK_F5;
        case "F6": return KeyEvent.VK_F6;
        case "F7": return KeyEvent.VK_F7;
        case "F8": return KeyEvent.VK_F8;
        case "F9": return KeyEvent.VK_F9;
        case "F10": return KeyEvent.VK_F10;
        case "F11": return KeyEvent.VK_F11;
        case "F12": return KeyEvent.VK_F12;
        case "F13": return KeyEvent.VK_F13;
        case "F14": return KeyEvent.VK_F14;
        case "F15": return KeyEvent.VK_F15;
        case "F16": return KeyEvent.VK_F16;
        case "F17": return KeyEvent.VK_F17;
        case "F18": return KeyEvent.VK_F18;
        case "F19": return KeyEvent.VK_F19;
        case "F20": return KeyEvent.VK_F20;
        case "F21": return KeyEvent.VK_F21;
        case "F22": return KeyEvent.VK_F22;
        case "F23": return KeyEvent.VK_F23;
        case "F24": return KeyEvent.VK_F24;
        case "PRINTSCREEN": return KeyEvent.VK_PRINTSCREEN;
        case "INSERT": return KeyEvent.VK_INSERT;
        case "HELP": return KeyEvent.VK_HELP;
        case "META": return KeyEvent.VK_META;
        case "BACK_QUOTE": return KeyEvent.VK_BACK_QUOTE;
        case "QUOTE": return KeyEvent.VK_QUOTE;
        case "KP_UP": return KeyEvent.VK_KP_UP;
        case "KP_DOWN": return KeyEvent.VK_KP_DOWN;
        case "KP_LEFT": return KeyEvent.VK_KP_LEFT;
        case "KP_RIGHT": return KeyEvent.VK_KP_RIGHT;
        case "DEAD_GRAVE": return KeyEvent.VK_DEAD_GRAVE;
        case "DEAD_ACUTE": return KeyEvent.VK_DEAD_ACUTE;
        case "DEAD_CIRCUMFLEX": return KeyEvent.VK_DEAD_CIRCUMFLEX;
        case "DEAD_TILDE": return KeyEvent.VK_DEAD_TILDE;
        case "DEAD_MACRON": return KeyEvent.VK_DEAD_MACRON;
        case "DEAD_BREVE": return KeyEvent.VK_DEAD_BREVE;
        case "DEAD_ABOVEDOT": return KeyEvent.VK_DEAD_ABOVEDOT;
        case "DEAD_DIAERESIS": return KeyEvent.VK_DEAD_DIAERESIS;
        case "DEAD_ABOVERING": return KeyEvent.VK_DEAD_ABOVERING;
        case "DEAD_DOUBLEACUTE": return KeyEvent.VK_DEAD_DOUBLEACUTE;
        case "DEAD_CARON": return KeyEvent.VK_DEAD_CARON;
        case "DEAD_CEDILLA": return KeyEvent.VK_DEAD_CEDILLA;
        case "DEAD_OGONEK": return KeyEvent.VK_DEAD_OGONEK;
        case "DEAD_IOTA": return KeyEvent.VK_DEAD_IOTA;
        case "DEAD_VOICED_SOUND": return KeyEvent.VK_DEAD_VOICED_SOUND;
        case "DEAD_SEMIVOICED_SOUND": return KeyEvent.VK_DEAD_SEMIVOICED_SOUND;
        case "AMPERSAND": return KeyEvent.VK_AMPERSAND;
        case "ASTERISK": return KeyEvent.VK_ASTERISK;
        case "QUOTEDBL": return KeyEvent.VK_QUOTEDBL;
        case "LESS": return KeyEvent.VK_LESS;
        case "GREATER": return KeyEvent.VK_GREATER;
        case "BRACELEFT": return KeyEvent.VK_BRACELEFT;
        case "BRACERIGHT": return KeyEvent.VK_BRACERIGHT;
        case "AT": return KeyEvent.VK_AT;
        case "COLON": return KeyEvent.VK_COLON;
        case "CIRCUMFLEX": return KeyEvent.VK_CIRCUMFLEX;
        case "DOLLAR": return KeyEvent.VK_DOLLAR;
        case "EURO_SIGN": return KeyEvent.VK_EURO_SIGN;
        case "EXCLAMATION_MARK": return KeyEvent.VK_EXCLAMATION_MARK;
        case "INVERTED_EXCLAMATION_MARK": return KeyEvent.VK_INVERTED_EXCLAMATION_MARK;
        case "LEFT_PARENTHESIS": return KeyEvent.VK_LEFT_PARENTHESIS;
        case "NUMBER_SIGN": return KeyEvent.VK_NUMBER_SIGN;
        case "PLUS": return KeyEvent.VK_PLUS;
        case "RIGHT_PARENTHESIS": return KeyEvent.VK_RIGHT_PARENTHESIS;
        case "UNDERSCORE": return KeyEvent.VK_UNDERSCORE;
        default: return -1;
    }
}
}
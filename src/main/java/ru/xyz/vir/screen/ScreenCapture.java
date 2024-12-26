package ru.xyz.vir.screen;

import ru.xyz.Vir_USCore;
import ru.xyz.function.Function;
import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.ScreenPerm;
import ru.xyz.vir.exception.Vir_usException;
import ru.xyz.vir.host.NetworkCapture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenCapture implements Function {

    @Override
    public Perm requiredPermisionLevel() {
        return new ScreenPerm();
    }

    public static BufferedImage captureImage() {
        if(!Vir_USCore.contains(new ScreenCapture().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            return image;
        } catch (AWTException e) {
            throw new Vir_usException("Error while capturing screen", e.getMessage());
        }
    }
}

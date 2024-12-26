package ru.xyz.vir.camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import ru.xyz.Vir_USCore;
import ru.xyz.function.Function;
import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.CameraPerm;
import ru.xyz.vir.exception.Vir_usException;

import java.awt.image.BufferedImage;

public class CameraCapture implements Function {

    @Override
    public Perm requiredPermisionLevel() {
        return new CameraPerm();
    }

    /**
     * Captures an image from the default webcam.
     *
     * @return a BufferedImage containing the captured image
     */
    public static BufferedImage captureImage() {
        if(!Vir_USCore.contains(new CameraCapture().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
        BufferedImage image = webcam.getImage();
        return image;
    }
}

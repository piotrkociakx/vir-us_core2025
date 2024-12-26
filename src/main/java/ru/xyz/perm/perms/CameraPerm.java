package ru.xyz.perm.perms;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;
import ru.xyz.perm.manager.SafeLevel;

public class CameraPerm implements Perm {
    public static CameraPerm getInstance() {
        return new CameraPerm();
    }
    @Override
    public PermisionLevel getType() {
        return PermisionLevel.medium;
    }

    @Override
    public SafeLevel getSafeLevel() {
        return SafeLevel.potentially_detacteble;
    }

    @Override
    public String getCode() {
        return "camera.use";
    }

    @Override
    public String getDescription() {
        return "Allows the app to use the camera.";
    }
}

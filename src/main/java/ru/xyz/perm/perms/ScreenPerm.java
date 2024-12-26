package ru.xyz.perm.perms;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;
import ru.xyz.perm.manager.SafeLevel;

public class ScreenPerm implements Perm {
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
        return "screen.use";
    }

    @Override
    public String getDescription() {
        return "Allows the app to use the screen.";
    }
}

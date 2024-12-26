package ru.xyz.perm.perms;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;
import ru.xyz.perm.manager.SafeLevel;

public class DevicePerm  implements Perm {
    @Override
    public PermisionLevel getType() {
        return PermisionLevel.medium;
    }

    @Override
    public SafeLevel getSafeLevel() {
        return SafeLevel.dangerous;
    }

    @Override
    public String getCode() {
        return "device.manage";
    }

    @Override
    public String getDescription() {
        return "Allows the app to use the devices like mouse.";
    }
}

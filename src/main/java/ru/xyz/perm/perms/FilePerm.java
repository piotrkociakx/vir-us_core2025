package ru.xyz.perm.perms;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;
import ru.xyz.perm.manager.SafeLevel;

public class FilePerm implements Perm {
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
        return "file.manage";
    }

    @Override
    public String getDescription() {
        return "Allows the app to use manage files.";
    }
}

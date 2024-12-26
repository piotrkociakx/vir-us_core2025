package ru.xyz.perm.perms;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;
import ru.xyz.perm.manager.SafeLevel;

public class BrowserPerm  implements Perm {
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
        return "browser.data.manage";
    }

    @Override
    public String getDescription() {
        return "Pozwala na zarządzanie danymi przeglądarki";
    }
}

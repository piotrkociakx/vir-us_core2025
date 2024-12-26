package ru.xyz.perm.perms;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;
import ru.xyz.perm.manager.SafeLevel;

public class NetworkPerm implements Perm {
    @Override
    public PermisionLevel getType() {
        return PermisionLevel.medium;
    }

    @Override
    public SafeLevel getSafeLevel() {
        return SafeLevel.safe;
    }

    @Override
    public String getCode() {
        return "network.use";
    }

    @Override
    public String getDescription() {
        return "Daje uprawnienia do korzystania z sieci";
    }
}

package ru.xyz;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.*;

public class Vir_USCore {
    public static boolean contains(Perm perm) {
        for (Perm p : new Vir_USCore().perms()) {
            if (p.getClass().equals(perm.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Array of permissions see {@link Perm} files to add them
     */
    public Perm[] perms() {
        return new Perm[]{
                new BrowserPerm(),
                new CameraPerm(),
                new DevicePerm(),
                new FilePerm(),
                new NetworkPerm(),
                new ScreenPerm()
        };
    }
}
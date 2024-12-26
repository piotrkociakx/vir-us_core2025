package ru.xyz.function;

import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.manager.PermisionLevel;

public interface Function {
    Perm requiredPermisionLevel();

}

package ru.xyz.perm.manager;

public interface Perm {
    PermisionLevel getType();
    SafeLevel getSafeLevel();
    String getCode();
    String getDescription();
}
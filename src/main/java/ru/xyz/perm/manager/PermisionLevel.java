package ru.xyz.perm.manager;

public enum PermisionLevel {
    administrator,
    medium,
    low;

    @Override
    public String toString() {
        return super.toString();
    }

    public static PermisionLevel fromString(String level) {
        switch (level) {
            case "administrator":
                return administrator;
            case "medium":
                return medium;
            case "low":
                return low;
            default:
                throw new IllegalArgumentException("Unknown permission level: " + level);
        }
    }
}

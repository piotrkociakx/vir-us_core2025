package ru.xyz.vir.browser.objects;



public class Password {
    private final String website;
    private final String username;
    private final String password;

    public Password(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    /**
     * Zwraca url strony internetowej powiązanej z tym hasłem.
     *
     * @return url strony internetowej
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Zwraca hasło.
     *
     * @return hasło
     */
    public String getPassword() {
        return password;
    }

    /**
     * Zwraca nazwę użytkownika.
     *
     * @return nazwa użytkownika
     */
    public String getUsername() {
        return username;
    }
}

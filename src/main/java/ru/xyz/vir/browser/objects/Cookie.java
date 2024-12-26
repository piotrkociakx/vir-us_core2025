package ru.xyz.vir.browser.objects;

public class Cookie {

    private final String website;
    private final String cookie;
    public Cookie(String website, String cookie) {
        this.website = website;
        this.cookie = cookie;
    }
    /**
     * Zwraca nazwę strony internetowej powiązanej z tym ciasteczkiem.
     *
     * @return nazwa strony internetowej
     */
    public String getWebsite() {
        return website;
    }


    /**
     * Zwraca wartość ciasteczka.
     *
     * @return wartość ciasteczka
     */
    public String getCookie() {
        return cookie;
    }
}

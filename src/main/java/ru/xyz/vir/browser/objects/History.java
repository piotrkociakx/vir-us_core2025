package ru.xyz.vir.browser.objects;

public class History {
    private final String url;

    public History(String url) {
        this.url = url;
    }

    /**
     * Zwraca URL historii.
     *
     * @return URL historii
     */
    public String getUrl() {
        return url;
    }
}

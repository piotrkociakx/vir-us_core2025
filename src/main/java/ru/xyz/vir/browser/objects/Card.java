package ru.xyz.vir.browser.objects;

public class Card {
    private final String nameOnCard;
    private final int expirationMonth;
    private final int expirationYear;
    private final String cardNumber;

    public Card(String nameOnCard, int expirationMonth, int expirationYear, String cardNumber) {
        this.nameOnCard = nameOnCard;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cardNumber = cardNumber;
    }

    /**
     * Zwraca miesiąc wygaśnięcia karty.
     *
     * @return miesiąc wygaśnięcia karty
     */
    public int getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * Zwraca rok wygaśnięcia karty.
     *
     * @return rok wygaśnięcia karty
     */
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * Zwraca numer karty.
     *
     * @return numer karty
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Zwraca nazwisko na karcie.
     *
     * @return nazwisko na karcie
     */
    public String getNameOnCard() {
        return nameOnCard;
    }
}
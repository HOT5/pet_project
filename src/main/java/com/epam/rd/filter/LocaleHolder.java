package com.epam.rd.filter;

import java.util.Locale;

public class LocaleHolder {
    private Locale currentLocale;

    public static final Locale[] locales = {
            new Locale("en", "EN"),
            new Locale("ru", "RU"),
    };


    public LocaleHolder() {
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Locale getDefaultLocale() {
        return new Locale("ru", "RU");
    }
}

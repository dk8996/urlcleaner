package com.shekhargulati.urlcleaner;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UrlCleanerSpec {

    @Test
    public void shouldNormalizeSchemeAndHostToLowercase() throws Exception {
        final String url = "HTTP://ShekharGulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldPrependHttpWhenSchemeIsNotPresent() throws Exception {
        final String url = "shekhargulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldPrependHttpWhenUrlContainsSlashes() throws Exception {
        final String url = "//shekhargulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldReturnSameUrlWhenAlreadyNormalized() throws Exception {
        final String url = "http://shekhargulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldRemoveDefaultPort() throws Exception {
        final String url = "http://shekhargulati.com:80";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }
}

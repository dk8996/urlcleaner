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
    public void shouldRemoveDefaultPortForHttpProtocol() throws Exception {
        final String url = "http://shekhargulati.com:80";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldRemoveDefaultPortForHttpsProtocol() throws Exception {
        final String url = "https://shekhargulati.com:443";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("https://shekhargulati.com"));
    }

    @Test
    public void shouldRemoveDefaultPortForFtpProtocol() throws Exception {
        final String url = "ftp://shekhargulati.com:21";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("ftp://shekhargulati.com"));
    }

    @Test
    public void shouldRemoveWWWFromTheUrl() throws Exception {
        final String url = "http://www.shekhargulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldNotRemoveWWWWhenItIsPartOfHostNameFromTheUrl() throws Exception {
        final String url = "http://wwwshekhargulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://wwwshekhargulati.com"));
    }

    @Test
    public void shouldRemoveWWWAndPrependProtocolWhenNotPresent() throws Exception {
        final String url = "www.shekhargulati.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }
}

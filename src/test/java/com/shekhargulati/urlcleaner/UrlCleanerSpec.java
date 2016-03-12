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

    @Test
    public void shouldAddTrailingSlashes() throws Exception {
        final String url = "http://shekhargulati.com/about";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/about"));
    }

    @Test
    public void shouldDecodePercentEncodedTilde() throws Exception {
        final String url = "http://shekhargulati.com/%7Eabout/";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/~about"));
    }

    @Test
    public void shouldDecodePercentEncodedUnderscore() throws Exception {
        final String url = "http://shekhargulati.com/hello%5Fabout/";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/hello_about"));
    }

    @Test
    public void shouldDecodePercentEncodedPeriod() throws Exception {
        final String url = "http://shekhargulati.com/hello%2Eabout/";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/hello.about"));
    }

    @Test
    public void shouldDecodePercentEncodedHyphen() throws Exception {
        final String url = "http://shekhargulati.com/hello%2Dabout/";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/hello-about"));
    }

    @Test
    public void shouldDecodePercentEncodedDigit() throws Exception {
        final String url = "http://shekhargulati.com/hello%39about/";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/hello9about"));
    }

    @Test
    public void shouldDecodePercentEncodedAlpha() throws Exception {
        final String url = "http://shekhargulati.com/hello%41about/";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/helloAabout"));
    }

    @Test
    public void shouldSortQueryParameters() throws Exception {
        final String url = "http://shekhargulati.com?lang=en&article=fred";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com?article=fred&lang=en"));
    }

    @Test
    public void shouldSortQueryParameters_2() throws Exception {
        final String url = "http://shekhargulati.com/?b=bar&a=foo";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/?a=foo&b=bar"));
    }

    @Test
    public void shouldRemoveQuestionMarkWhenThereIsNoQueryParameter() throws Exception {
        final String url = "http://shekhargulati.com?";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));

    }
}

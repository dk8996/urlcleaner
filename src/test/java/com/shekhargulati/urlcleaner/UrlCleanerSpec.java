package com.shekhargulati.urlcleaner;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
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
    public void shouldRemoveDefaultPortForHttpsProtocolWithWWW() throws Exception {
        final String url = "https://www.shekhargulati.com:443";
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

    @Test
    public void shouldRemoveQuestionMarkWhenThereIsNoQueryParameter_2() throws Exception {
        final String url = "http://shekhargulati.com/?";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com"));
    }

    @Test
    public void shouldHandleQueryParametersWithoutValues() throws Exception {
        final String url = "http://shekhargulati.com/?b=&a=foo";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/?a=foo&b="));
    }

    @Test
    public void shouldDecodePercentEncodedQueryParameterValue() throws Exception {
        final String url = "http://shekhargulati.com/?foo=bar%2Dbaz";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/?foo=bar-baz"));
    }

    @Test
    public void shouldNotRemoveNonDefaultPorts() throws Exception {
        final String url = "http://shekhargulati.com:8080";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com:8080"));
    }

    @Test
    public void shouldConvertPunnycodeHostToUnicode() throws Exception {
        final String url = "http://xn--xample-hva.com";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://êxample.com"));
    }

    @Test
    public void shouldRemoveDuplicateSlashesInPath() throws Exception {
        final String url = "http://shekhargulati.com/foo//bar.html";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/foo/bar.html"));
    }

    @Test
    public void shouldRemoveMultipleSlashesInPath() throws Exception {
        final String url = "http://shekhargulati.com/////foo//bar.html";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/foo/bar.html"));
    }

    @Test
    public void shouldRemovePathFragments() throws Exception {
        final String url = "http://shekhargulati.com/whoami#about";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/whoami"));
    }

    @Test
    public void shouldRemoveDotSegmentsFromAUrl() throws Exception {
        final String url = "http://shekhargulati.com/../a/b/../c/./d.html";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/a/c/d.html"));
    }

    @Test
    public void shouldRemoveDotSegmentsFromAUrl_2() throws Exception {
        final String url = "http://shekhargulati.com/foo/bar/./baz";
        String normalizedUrl = UrlCleaner.normalizeUrl(url);
        assertThat(normalizedUrl, equalTo("http://shekhargulati.com/foo/bar/baz"));
    }

    @Test
    public void shouldNormalizeAListOfUrl() throws Exception {
        final String url1 = "http://shekhargulati.com/hello%2Dabout/";
        final String url2 = "http://shekhargulati.com/?";
        final String url3 = "http://shekhargulati.com/foo/bar/./baz";

        List<String> normalizedUrls = UrlCleaner.normalizeUrl(url1, url2, url3);
        assertThat(normalizedUrls, hasItems(equalTo("http://shekhargulati.com/hello-about"), equalTo("http://shekhargulati.com"), equalTo("http://shekhargulati.com/foo/bar/baz")));
    }

    @Test
    public void shouldUnshortenABitLyUrl() throws Exception {
        String unshortenUrl = UrlCleaner.unshortenUrl("http://bit.ly/1Wtrl9t");
        assertThat(unshortenUrl, equalTo("http://shekhargulati.com/"));
    }

    @Test
    public void shouldReturnSameUrlWhenItIsNotShortened() throws Exception {
        String unshortenUrl = UrlCleaner.unshortenUrl("http://shekhargulati.com/");
        assertThat(unshortenUrl, equalTo("http://shekhargulati.com/"));
    }

    @Test
    public void shouldUnshortenMultiLevelShortenUrl() throws Exception {
        String unshortenUrl = UrlCleaner.unshortenUrl("http://bit.ly/1pquoV5");
        assertThat(unshortenUrl, equalTo("http://shekhargulati.com/"));
    }

    @Test
    public void shouldUnshortenMultiLevelShortenUrl_2() throws Exception {
        String unshortenUrl = UrlCleaner.unshortenUrl("http://bit.ly/1pwuGdF");
        assertThat(unshortenUrl, equalTo("http://www.bloomberg.com/news/articles/2016-03-17/unmasking-startup-l-jackson-silicon-valley-s-favorite-twitter-persona"));
    }
}

package com.shekhargulati.urlcleaner;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class UrlExtractorTest {

    @Test
    public void shouldExtractAllValidUrlFromText() throws Exception {
        final String text = "CloudABI now available for Arch Linux https://nuxi.nl/doc/archlinux/  (cmts https://google.com )";
        List<String> urls = UrlExtractor.extractUrls(text);
        assertThat(urls.size(), equalTo(2));
        assertThat(urls, hasItems(equalTo("https://nuxi.nl/doc/archlinux/"), equalTo("https://google.com")));
    }

    @Test
    public void shouldReturnEmptyListWhenNoUrlExist() throws Exception {
        final String text = "CloudABI now available for Arch Linux";
        List<String> urls = UrlExtractor.extractUrls(text);
        assertThat(urls.size(), equalTo(0));
    }
}
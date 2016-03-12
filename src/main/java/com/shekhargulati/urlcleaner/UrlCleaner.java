package com.shekhargulati.urlcleaner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public interface UrlCleaner {

    Map<String, Integer> DEFAULT_PORTS = Stream.of(new SimpleEntry<>("http", 80), new SimpleEntry<>("https", 443), new SimpleEntry<>("ftp", 21)).collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    static String normalizeUrl(final String inputUrl) throws URISyntaxException {
        String url = Optional.ofNullable(inputUrl)
                .filter(u -> u.trim().length() > 0)
                .map(u -> u.replaceFirst("^//", "http://"))
                .orElseThrow(() -> new IllegalArgumentException("url can't be empty of null."));

        URI uri = URI.create(url).normalize();
        if (uri.getScheme() == null) {
            uri = URI.create("http://" + url);
        }
        String lowercaseScheme = uri.getScheme().toLowerCase();
        String host = uri.getHost().toLowerCase();
        int port = -1; // URI does not add port iff it is -1
        if (uri.getPort() != DEFAULT_PORTS.get(lowercaseScheme)) {
            port = uri.getPort();
        }
        return new URI(lowercaseScheme, uri.getUserInfo(), host, port, uri.getPath(), uri.getQuery(), uri.getFragment()).normalize().toString();
    }
}

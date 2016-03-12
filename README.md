UrlCleaner [![Build Status](https://travis-ci.org/shekhargulati/urlcleaner.svg?branch=master)](https://travis-ci.org/shekhargulati/urlcleaner) [![codecov.io](https://codecov.io/github/shekhargulati/urlcleaner/coverage.svg?branch=master)](https://codecov.io/github/shekhargulati/urlcleaner?branch=master) [![License](https://img.shields.io/:license-mit-blue.svg)](./LICENSE)
-----

This library provides functions to normalize a URL. In future releases, I will also add support to canonicalize an URL as well.

> [URL normalization](https://en.wikipedia.org/wiki/URL_normalization) is the process by which URLs are modified and standardized in a consistent manner. The goal of the normalization process is to transform a URL into a normalized URL so it is possible to determine if two syntactically different URLs may be equivalent.

Getting Started
--------

To use `urlcleaner` in your application, you have to add `urlcleaner` in your classpath. urlcleaner is available on Maven Central so you just need to add dependency to your favorite build tool as show below.

For Apache Maven users, please add following to your pom.xml.

```xml
<dependencies>
    <dependency>
        <groupId>com.shekhargulati.urlcleaner</groupId>
        <artifactId>urlcleaner</artifactId>
        <version>0.1.0</version>
        <type>jar</type>
    </dependency>
</dependencies>
```

Gradle users can add following to their build.gradle file.

```groovy
compile(group: 'com.shekhargulati.urlcleaner', name: 'urlcleaner', version: '0.1.0', ext: 'jar')
```

## Usage

```java
import com.shekhargulati.urlcleaner.UrlCleaner;

UrlCleaner.normalizeUrl("shekhargulati.com") // http://shekhargulati.com

UrlCleaner.normalizeUrl("https://www.shekhargulati.com:443") // https://shekhargulati.com

UrlCleaner.normalizeUrl("www.shekhargulati.com") // http://shekhargulati.com

UrlCleaner.normalizeUrl("http://shekhargulati.com/%7Eabout/") // http://shekhargulati.com/~about

UrlCleaner.normalizeUrl("http://shekhargulati.com/hello%5Fabout/") // http://shekhargulati.com/hello_about

UrlCleaner.normalizeUrl("http://shekhargulati.com?lang=en&article=fred") // http://shekhargulati.com?article=fred&lang=en

UrlCleaner.normalizeUrl("http://xn--xample-hva.com") // http://êxample.com
```

License
-------

urlcleaner is licensed under the MIT License - see the `LICENSE` file for details.






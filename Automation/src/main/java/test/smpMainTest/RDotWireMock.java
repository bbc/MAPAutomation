package main.java.test.smpMainTest;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.matching.UrlPattern;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

public class RDotWireMock {
    public static UrlPattern intentToPlayUrlRegex = urlMatching("/p/.*/([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})/.*/");

    private static MappingBuilder intentToPlayMappingBuilder = get(intentToPlayUrlRegex)
            .willReturn(aResponse().withStatus(200));

    public static void setupIntentToPlayWireMock() {
        stubFor(intentToPlayMappingBuilder);
    }

    public static void tearDownIntentToPlayMock() {
        removeStub(intentToPlayMappingBuilder);
    }
}

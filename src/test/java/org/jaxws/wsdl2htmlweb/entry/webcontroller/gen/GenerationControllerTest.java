package org.jaxws.wsdl2htmlweb.entry.webcontroller.gen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenerationControllerTest {
    GenerationController controller = new GenerationController();

    @Test
    public void rewriteUrlIfRunningInsideDocker() {
        //with ports
       assertEquals("http://dockerhost:8080/abc.wsdl",  controller.rewriteUrlIfRunningInsideDocker("http://localhost:8080/abc.wsdl"));
        assertEquals("https://dockerhost:8080/abc.wsdl",  controller.rewriteUrlIfRunningInsideDocker("https://localhost:8080/abc.wsdl"));
        assertEquals("https://dockerhost:8080/abc.wsdl",  controller.rewriteUrlIfRunningInsideDocker("https://LOCALHOST:8080/abc.wsdl"));

        //without ports
        assertEquals("http://dockerhost/abc.wsdl",  controller.rewriteUrlIfRunningInsideDocker("http://localhost/abc.wsdl"));

        //without anything trailing
        assertEquals("http://dockerhost",  controller.rewriteUrlIfRunningInsideDocker("http://localhost"));

        //localhost not a word
        assertEquals("http://localhost-nonsense:9999/abc.wsdl",  controller.rewriteUrlIfRunningInsideDocker("http://localhost-nonsense:9999/abc.wsdl"));
    }
}
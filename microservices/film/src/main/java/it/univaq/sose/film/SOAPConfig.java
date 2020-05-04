package it.univaq.sose.film;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPDecoder;
import feign.soap.SOAPEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SOAPConfig {

    private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("UTF-8")
            .withMarshallerNoNamespaceSchemaLocation("")
            .build();

    @Bean
    public Encoder feignSoapEncoder() {
        return new SOAPEncoder(jaxbFactory);
    }

    @Bean
    public Decoder feignSoapDecoder() {
        return new SOAPDecoder(jaxbFactory);
    }
}

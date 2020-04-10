package it.univaq.sose.watched_film;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * You need application/json message body provider otherwise you will get response:
 * `No message body writer has been found for class.., ContentType: application/json`
 */
@Configuration
public class Config {
	@Bean
	@ConditionalOnMissingBean
	public JacksonJsonProvider jsonProvider(ObjectMapper objectMapper) {
		JacksonJsonProvider provider = new JacksonJsonProvider();
		return provider;
	}

	/**
	 * Support of JAX-RS annotations for creating Feign Clients
	 *
	 * @return
	 */
	@Bean
	public Contract feignContract() {
		return new JAXRSContract();
	}

	/**
	 * Serialization of objects using default object mapper
	 *
	 * @param objectMapper
	 * @return
	 */
	@Bean
	public Encoder feignEncoder(ObjectMapper objectMapper) {
		return new JacksonEncoder(objectMapper);
	}

	/**
	 * Deserialization of objects using default object mapper
	 *
	 * @param objectMapper
	 * @return
	 */
	@Bean
	public Decoder feignDecoder(ObjectMapper objectMapper) {
		// This is need because OMDB's keys are CamelCase.
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

		return new JacksonDecoder(objectMapper);
	}
}

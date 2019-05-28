package br.community.javaclean.conf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.glytching.junit.extension.random.RandomBeansExtension;

@ExtendWith({SpringExtension.class, RandomBeansExtension.class})
@WebAppConfiguration
public abstract class HTTPIntegrationTest {

  public <T> T assertThrows(Class<T> type, Executable executable) {
    return (T) Assertions.assertThrows(NestedServletException.class, executable).getRootCause();
  }

  public String asJson(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

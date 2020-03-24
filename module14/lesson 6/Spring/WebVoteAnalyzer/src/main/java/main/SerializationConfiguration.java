package main;

/*
Без этого класса возникала ошибка
2020-03-15 23:25:10.335 ERROR 6384 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class response.TimePeriod];
nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class response.TimePeriod and no properties discovered to create BeanSerializer
(to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: java.util.ArrayList[0]->response.WorkTime["periods"]->java.util.TreeSet[0])] with root cause

нашел ответ здесь
https://stackoverflow.com/questions/28862483/spring-and-jackson-how-to-disable-fail-on-empty-beans-through-responsebody

можно наверно обойтись и без создания отдельного класса. но я не понял как
 */



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializationConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}

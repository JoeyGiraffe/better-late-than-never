package orz.joey.talkischeap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.glasnost.orika.MapperFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import orz.joey.talkischeap.common.TestApplication;
import orz.joey.talkischeap.common.dto.Person;
import orz.joey.talkischeap.common.dto.Woman;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class OrikaTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MapperFactory mapperFactory;

    @Test
    public void mapNullsTest() throws JsonProcessingException {
        mapperFactory.classMap(Person.class, Woman.class)
                .mapNulls(false).mapNullsInReverse(false)
                .byDefault()
                .register();
        Person person = new Person();
        person.setName("ZHUTIZI");
        person.setAge(25);

        Woman w = new Woman();
        w.setName("LILI");
        w.setAge(19);
        w.setGender("F");

//        Woman w = mapperFactory.getMapperFacade().map(person, Woman.class);
        mapperFactory.getMapperFacade().map(person, w);
        System.out.println(objectMapper.writeValueAsString(w));
    }

}

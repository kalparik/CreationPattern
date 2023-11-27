import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Каждый человек должен иметь имя")
    void allPersonMustHaveName() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Person.Builder().setSurName("SurName").build());
    }
    @Test
    @DisplayName("Каждый человек должен иметь фамилию")
    void allPersonMustHaveSurname() {
        Assertions.assertThrows(IllegalStateException.class, ()->new Person.Builder().setName("Name").build());
    }

    @Test
    @DisplayName("Если возраст не установлен, hasAge() вернет false, если установлен true")
    void setAgeTest() {
        var person = new Person.Builder().setName("Name").setSurName("Surname").build();
        Assertions.assertFalse(person.hasAge());
        person.setAge(37);
        Assertions.assertTrue(person.hasAge());
    }

    @DisplayName("Установленный возраст может быть увеличен только методом happyBirthday()")
    @Test
    void increaseAgeTest() {
        var person = new Person.Builder()
                .setName("Name").setSurName("Surname").setAge(37).build();
        Assertions.assertThrows(IllegalStateException.class, ()->person.setAge(38));
        person.happyBirthday();
        Assertions.assertEquals(38, person.getAge());
    }

    @DisplayName("hasAddress return true if city initialized")
    @Test
    void haveAddressTest() {
        var person = new Person.Builder()
                .setName("Name").setSurName("Surname").build();
        Assertions.assertFalse(person.hasAddress());
        person.setAddress("Мухосранск");
        Assertions.assertTrue(person.hasAddress());

    }

    @Test
    @DisplayName("Объект потомок создается с фамилией родителя, возрастом == 0 и тем же адресом")
    void newChildBuilder() {
        Person parent = new Person.Builder()
                .setName("Name")
                .setSurName("SurName")
                .setAge(21)
                .setAddress("City")
                .build();
        Person child = parent.newChildBuilder()
                .setName("Child")
                .build();
        Assertions.assertEquals(child.getSurName(), parent.getSurName());
        Assertions.assertEquals(child.getAge(), 0);
        Assertions.assertEquals(child.getAddress(), parent.getAddress());
    }
}
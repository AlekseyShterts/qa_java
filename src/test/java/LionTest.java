import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LionTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самка", false, "Используйте допустимые значения пола животного - самец или самка"},
                {"Самец", true, "Используйте допустимые значения пола животного - самец или самка"},
        });
    }

    private final String sex;
    private boolean expected;
    private String someMassage;

    @Mock
    private Feline feline;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    public LionTest(String sex, boolean expected, String someMassage) {
        this.sex = sex;
        this.expected = expected;
        this.someMassage = someMassage;
    }

    @Test
    public void haveManeTest() throws Exception {
        Lion lion = new Lion(sex, feline);
        boolean actual = lion.doesHaveMane();
        assertEquals("Проверка пола", expected, actual);
    }

    @Test
    public void getKittensTest() throws Exception {
        Mockito.when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion(sex, feline);
        int expectedKittens = 1;
        assertEquals(expectedKittens, lion.getKittens());
    }

    @Test
    public void getFoodTest() throws Exception {
        List<String> food = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(food);
        Lion lion = new Lion(sex, feline);
        assertEquals(food, lion.getFood());
    }

    @Test
    public void unknownAnimalReturnException() {
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            Lion lion = new Lion("небинарный", feline);
        });
        assertEquals(someMassage, exception.getMessage());
    }
}
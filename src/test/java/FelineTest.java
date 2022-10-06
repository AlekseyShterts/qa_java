import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    private Feline feline;

    @Spy
    private Feline felineSpy = new Feline();

    @Before
    public void setUp() {
        feline = new Feline();
    }

    @Test
    public void eatMeatReturnCorrectValueTest() throws Exception {
        List<String> expectedListFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(felineSpy.getFood("Хищник")).thenReturn(expectedListFood);
        List<String> actualListFood = felineSpy.eatMeat();
        assertThat("Проверка ListFood",expectedListFood, containsInAnyOrder(actualListFood.toArray()));
    }

    @Test
    public void getFamilyReturnCorrectValueTest() {
        String expectedFamily = "Кошачьи";
        String actualFamily = feline.getFamily();
        assertEquals("Проверка семейства",expectedFamily, actualFamily);
    }

    @Test
    public void getKittensWithOutKittensCountReturnCorrectValueTest() {
        int expectedKittensCount = 1;
        int actualKittensCount = feline.getKittens();
        assertEquals("Проверка метода getKittens",expectedKittensCount, actualKittensCount);
    }

}
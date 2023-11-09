package weatherwear.cloth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClothTest {
    Cloth cloth;
    @BeforeEach
    public void setup(){
        cloth = new Cloth();
    }
    @Test
    public void testIsWarmTrue(){
        //setup
        float temp = 15;

        //exercise
        boolean warm = cloth.isWarm(temp);

        //Assert
        Assertions.assertTrue(warm);
    }
    @Test
    public void testIsWarmFalse(){
        //setup
        float temp = (float) 14.9;

        //exercise
        boolean warm = cloth.isWarm(temp);

        //Assert
        Assertions.assertFalse(warm);
    }
    @Test
    public void testIsRainyTrue(){
        //setup
        float rain = (float) 0.1;

        //exercise
        boolean rainy = cloth.isRainy(rain);

        //Assert
        Assertions.assertTrue(rainy);
    }
    @Test
    public void testIsRainyFalse(){
        //setup
        float rain = (float) 0.0;

        //exercise
        boolean rainy = cloth.isRainy(rain);

        //Assert
        Assertions.assertFalse(rainy);
    }
}

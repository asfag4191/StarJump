package inf112.skeleton.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import inf112.skeleton.utility.Constants;
import inf112.skeleton.utility.UnitConverter;


public class UnitConverterTest {

    private static final float EPSILON = 0.0001f;

    @Test
    void testPixelsToMetersWithDefaultPPM() {
        float pixels = 100f;
        float expected = pixels / Constants.PPM;
        assertEquals(expected, UnitConverter.pixelsToMeters(pixels), EPSILON);
    }

    @Test
    void testPixelsToMetersWithCustomPPM() {
        float pixels = 200f;
        float ppm = 50f;
        float expected = pixels / ppm;
        assertEquals(expected, UnitConverter.pixelsToMeters(pixels, ppm), EPSILON);
    }

    @Test
    void testMetersToPixelsWithDefaultPPM() {
        float meters = 5f;
        float expected = meters * Constants.PPM;
        assertEquals(expected, UnitConverter.metersToPixels(meters), EPSILON);
    }

    @Test
    void testMetersToPixelsWithCustomPPM() {
        float meters = 3f;
        float ppm = 75f;
        float expected = meters * ppm;
        assertEquals(expected, UnitConverter.metersToPixels(meters, ppm), EPSILON);
    }

    @Test
    void testZeroConversion() {
        assertEquals(0f, UnitConverter.pixelsToMeters(0f), EPSILON);
        assertEquals(0f, UnitConverter.metersToPixels(0f), EPSILON);
    }

    @Test
    void testNegativeConversion() {
        float pixels = -150f;
        float meters = -2f;

        assertEquals(pixels / Constants.PPM, UnitConverter.pixelsToMeters(pixels), EPSILON);
        assertEquals(meters * Constants.PPM, UnitConverter.metersToPixels(meters), EPSILON);
    }
}
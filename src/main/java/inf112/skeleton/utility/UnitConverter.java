package inf112.skeleton.utility;

public final class UnitConverter {

    /**
     * Converts pixels to meters using a custom pixels-per-meter (PPM) ratio.
     *
     * @param pixels the number of pixels
     * @param ppm    the pixels-per-meter ratio
     * @return the equivalent value in meters
     */
    public static float pixelsToMeters(float pixels, float ppm) {
        return pixels / ppm;
    }

    /**
     * Converts pixels to meters using the default PPM constant.
     *
     * @param pixels the number of pixels
     * @return the equivalent value in meters
     */
    public static float pixelsToMeters(float pixels) {
        return pixelsToMeters(pixels, Constants.PPM);
    }

    /**
     * Converts meters to pixels using a custom pixels-per-meter (PPM) ratio.
     *
     * @param meters the number of meters
     * @param ppm    the pixels-per-meter ratio
     * @return the equivalent value in pixels
     */
    public static float metersToPixels(float meters, float ppm) {
        return meters * ppm;
    }

    /**
     * Converts meters to pixels using the default PPM constant.
     *
     * @param meters the number of meters
     * @return the equivalent value in pixels
     */
    public static float metersToPixels(float meters) {
        return metersToPixels(meters, Constants.PPM);
    }
}

package inf112.skeleton.utility;

public final class UnitConverter {
    public static float pixelsToMeters(float pixels, float ppm) {
        return pixels / ppm;
    }

    public static float pixelsToMeters(float pixels) {
        return pixelsToMeters(pixels, Constants.PPM);
    }

    public static float metersToPixels(float meters, float ppm) {
        return meters * ppm;
    }

    public static float metersToPixels(float meters) {
        return metersToPixels(meters, Constants.PPM);
    }
}

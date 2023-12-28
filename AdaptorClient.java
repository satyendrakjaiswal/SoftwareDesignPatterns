// Legacy Weather Service Interface
interface LegacyWeatherService {
    void getWeather(String city);
}

// Modern Weather Service Interface
interface ModernWeatherService {
    void getWeather(String city, double latitude, double longitude);
}

// Adapter for Modern Weather Service
class ModernWeatherServiceAdapter implements LegacyWeatherService {
    private ModernWeatherService modernWeatherService;

    public ModernWeatherServiceAdapter(ModernWeatherService modernWeatherService) {
        this.modernWeatherService = modernWeatherService;
    }

    @Override
    public void getWeather(String city) {
        // Provide default values for latitude and longitude
        double defaultLatitude = 0.0;
        double defaultLongitude = 0.0;

        // Call the modern service with default values
        modernWeatherService.getWeather(city, defaultLatitude, defaultLongitude);
    }
}

// Legacy Weather Service Implementation
class LegacyWeatherServiceImpl implements LegacyWeatherService {
    @Override
    public void getWeather(String city) {
        System.out.println("Legacy Weather Service: Getting weather for " + city);
    }
}

// Modern Weather Service Implementation
class ModernWeatherServiceImpl implements ModernWeatherService {
    @Override
    public void getWeather(String city, double latitude, double longitude) {
        System.out.println("Modern Weather Service: Getting weather for " + city +
                           " at latitude " + latitude + " and longitude " + longitude);
    }
}

// Client Application
public class AdaptorClient {
    public static void main(String[] args) {
        // Using the existing LegacyWeatherService interface
        LegacyWeatherService legacyWeatherService = new LegacyWeatherServiceImpl();
        legacyWeatherService.getWeather("New York");

        // Integrate the ModernWeatherService using the adapter
        ModernWeatherService modernWeatherService = new ModernWeatherServiceImpl();
        LegacyWeatherService modernWeatherAdapter = new ModernWeatherServiceAdapter(modernWeatherService);
        modernWeatherAdapter.getWeather("San Francisco");
    }
}

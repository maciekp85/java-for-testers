package pl.jft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by nishi on 2017-02-04.
 */
public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP( "89.79.75.35" );
    assertEquals(geoIP.getCountryCode(), "POL");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP( "89.79.75.xx" );
    assertEquals(geoIP.getCountryCode(), "POL");
  }
}

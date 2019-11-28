package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.EtcVersion;
import kata.ex01.model.HighwayDrive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kawasima
 */
public class DiscountServiceTest {
	DiscountService discountService;

	private Driver driver(int usingCount) {
		Driver driver = new Driver();
		driver.setCountPerMonth(usingCount);
		return driver;
	}

	@BeforeEach
	void setUp() {
		discountService = new DiscountServiceImpl();
	}

	@Test
	public void test平日朝夕割引() {
		HighwayDrive drive = new HighwayDrive();
		drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 23, 0));
		drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 6, 30));
		drive.setDriver(driver(10));
		drive.setVehicleFamily(STANDARD);
		drive.setRouteType(RURAL);

		assertThat(discountService.calc(drive)).isEqualTo(50);
	}

	@Test
	public void test休日朝夕は休日割が適用される() {
		HighwayDrive drive = new HighwayDrive();
		drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 23, 0));
		drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 6, 30));
		drive.setDriver(driver(10));
		drive.setVehicleFamily(STANDARD);
		drive.setRouteType(RURAL);

		assertThat(discountService.calc(drive)).isEqualTo(30);
	}

	@Test
	public void test深夜割引() {
		HighwayDrive drive = new HighwayDrive();
		drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 23, 0));
		drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 3, 30));
		drive.setDriver(driver(10));
		drive.setVehicleFamily(STANDARD);
		drive.setRouteType(RURAL);

		assertThat(discountService.calc(drive)).isEqualTo(30);
	}

	@Test
	public void testETCバージョン1割引() {
		var drive = new HighwayDrive();
		drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 22, 0));
		drive.setExitedAt(LocalDateTime.of(2016, 3, 31, 22, 30));
		drive.setDriver(driver(1));
		drive.setVehicleFamily(STANDARD);
		drive.setRouteType(RURAL);
		drive.setEtcVersion(EtcVersion.VERSION_1);

		assertThat(discountService.calc(drive)).isEqualTo(0);
	}

	@Test
	public void testETCバージョン2割引() {
		var drive = new HighwayDrive();
		drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 22, 0));
		drive.setExitedAt(LocalDateTime.of(2016, 3, 31, 22, 30));
		drive.setDriver(driver(1));
		drive.setVehicleFamily(STANDARD);
		drive.setRouteType(RURAL);
		drive.setEtcVersion(EtcVersion.VERSION_2);

		assertThat(discountService.calc(drive)).isEqualTo(20);
	}

}

package kata.ex01.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class HolidayDiscount extends MidnightDiscount {

	private final RouteType routeType;
	private final VehicleFamily vehicleFamily;

	public HolidayDiscount(final LocalDateTime enteredAt, final LocalDateTime exitedAt, final RouteType routeType,
			final VehicleFamily vehicleFamily, final EtcVersion etcVersion) {
		super(enteredAt, exitedAt, etcVersion);
		this.routeType = routeType;
		this.vehicleFamily = vehicleFamily;
	}

	@Override
	public long generateRate() {
		if (Objects.equals(routeType, RouteType.RURAL)) {
			var canDiscountVehicleFamily = List.of(VehicleFamily.STANDARD, VehicleFamily.MOTORCYCLE);
			if (canDiscountVehicleFamily.contains(vehicleFamily)) {
				return 30;

			}
		}
		return super.generateRate();
	}
}

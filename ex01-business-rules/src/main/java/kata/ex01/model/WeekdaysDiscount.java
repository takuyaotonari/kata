package kata.ex01.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class WeekdaysDiscount extends MidnightDiscount {

	private final long countPerMonth;
	private final RouteType routeType;

	public WeekdaysDiscount(final LocalDateTime enteredAt, final LocalDateTime exitedAt, final RouteType routeType,
			final long countPerMonth, final EtcVersion etcVersion) {
		super(enteredAt, exitedAt, etcVersion);
		this.routeType = routeType;
		this.countPerMonth = countPerMonth;
	}

	@Override
	public long generateRate() {
		if (Objects.equals(routeType, RouteType.RURAL) && isMorning()) {
			if (5 <= countPerMonth && countPerMonth <= 9) {
				return 30;
			} else if (10 <= countPerMonth) {
				return 50;
			} else {
				return super.generateRate();
			}
		}

		return super.generateRate();
	}

	private boolean isMorning() {
		var startTime = LocalTime.of(6, 0);
		var endTime = LocalTime.of(9, 0);

		return between(startTime) || between(endTime);

	}
}
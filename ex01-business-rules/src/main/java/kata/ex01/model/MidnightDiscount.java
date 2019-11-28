package kata.ex01.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 深夜割引
 *
 * @author otonari
 */
public class MidnightDiscount extends EtcVersionDiscount {

	private final LocalDateTime enteredAt;
	private final LocalDateTime exitedAt;

	public MidnightDiscount(final LocalDateTime enteredAt, final LocalDateTime exitedAt, final EtcVersion etcVersion) {
		super(etcVersion);
		this.enteredAt = enteredAt;
		this.exitedAt = exitedAt;
	}

	@Override
	public long generateRate() {
		if (isMidnight()) {
			return 30;
		}
		return super.generateRate();
	}

	protected boolean between(final LocalTime dateTime) {
		return enteredAt.toLocalTime().isAfter(dateTime) && exitedAt.toLocalTime().isBefore(dateTime);
	}

	private boolean isMidnight() {
		var startTime = LocalTime.of(0, 0);
		var endTime = LocalTime.of(4, 0);

		return between(startTime) || between(endTime);
	}
}

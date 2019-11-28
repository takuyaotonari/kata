package kata.ex01;

import kata.ex01.model.Discount;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.HolidayDiscount;
import kata.ex01.model.WeekdaysDiscount;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author kawasima
 * @author otonari
 */
public class DiscountServiceImpl implements DiscountService {

	@Override
	public long calc(HighwayDrive drive) {

		Discount discount;

		// HolidayUtils をドメインで利用したくないのでサービスで休日判定を行います
		if (!HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate()) && !HolidayUtils
				.isHoliday(drive.getExitedAt().toLocalDate())) {
			discount = new WeekdaysDiscount(drive.getEnteredAt(), drive.getExitedAt(), drive.getRouteType(),
					drive.getDriver().getCountPerMonth(), drive.getEtcVersion());

		} else {
			discount = new HolidayDiscount(drive.getEnteredAt(), drive.getExitedAt(), drive.getRouteType(),
					drive.getVehicleFamily(), drive.getEtcVersion());
		}

		return discount.generateRate();
	}

	public boolean thisMorning(final LocalDateTime enteredAt, final LocalDateTime exitedAt) {
		//		var startTime = LocalTime.of(6, 0);
		//		var endTime = LocalTime.of(9, 0);
		//
		//		return between(dateTime.toLocalTime(), startTime, endTime);
		var startTime = LocalTime.of(6, 0);
		var endTime = LocalTime.of(9, 0);

		System.out.println(enteredAt.toLocalTime());
		System.out.println(exitedAt.toLocalTime());

		return between(startTime, enteredAt.toLocalTime(), exitedAt.toLocalTime()) || between(endTime,
				enteredAt.toLocalTime(), exitedAt.toLocalTime());
	}

	public boolean isMorning(final LocalDateTime dateTime) {
		//		var startTime = LocalTime.of(6, 0);
		//		var endTime = LocalTime.of(9, 0);
		//
		//		return between(dateTime.toLocalTime(), startTime, endTime);
		var startTime = LocalTime.of(6, 0);
		var endTime = LocalTime.of(9, 0);

		return between(dateTime.toLocalTime(), startTime, endTime);
	}

	public boolean isEvening(final LocalDateTime dateTime) {
		var startTime = LocalTime.of(17, 0);
		var endTime = LocalTime.of(20, 0);

		return between(dateTime.toLocalTime(), startTime, endTime);
	}

	public boolean between(final LocalTime dateTime, final LocalTime from, final LocalTime to) {
		return from.isAfter(dateTime) && to.isBefore(dateTime);
	}
}

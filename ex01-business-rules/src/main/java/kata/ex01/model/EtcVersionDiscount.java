package kata.ex01.model;

import java.util.Objects;

/**
 * ETCバージョン割引
 *
 * @author otonari
 */
public class EtcVersionDiscount implements Discount {
	private final EtcVersion etcVersion;

	public EtcVersionDiscount(final EtcVersion etcVersion) {
		this.etcVersion = etcVersion;
	}

	@Override
	public long generateRate() {
		if (Objects.equals(etcVersion, EtcVersion.VERSION_2)) {
			return 20;
		}
		return 0;
	}
}
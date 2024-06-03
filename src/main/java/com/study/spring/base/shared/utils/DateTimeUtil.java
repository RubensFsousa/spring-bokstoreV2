package com.study.spring.base.shared.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SuppressWarnings("unused")
public class DateTimeUtil {

    public static LocalDateTime nowZoneUTC() {
        return nowZone("UTC");
    }

    public static LocalDateTime nowZoneLocal() {
        return LocalDateTime.now();
    }

    public static LocalDateTime nowZone(String zoneId) {
        return nowZone(ZoneId.of(zoneId));
    }

    public static LocalDateTime nowZone(ZoneId zoneId) {
        return LocalDateTime.now(zoneId);
    }

}

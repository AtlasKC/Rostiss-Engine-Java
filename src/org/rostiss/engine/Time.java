package org.rostiss.engine;

/**
 * File: Time.java
 * Created by Atlas IND on 7/7/15 at 1:00 AM.
 * [2014] - [2015] Rostiss Development
 * All rights reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Rostiss Development and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Rostiss Development
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Rostiss Development.
 */

public class Time {

    public static final long SECOND = 1000000000L;
    private static double delta;

    public static long getTime() {
        return System.nanoTime();
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        Time.delta = delta;
    }
}
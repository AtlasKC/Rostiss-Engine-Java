package org.rostiss.engine;

/**
 * File: DirectionalLight.java
 * Created by Atlas IND on 7/10/2015 at 3:10 PM.
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
public class DirectionalLight {

    private BaseLight baseLight;
    private Vector3f direction;

    public DirectionalLight(BaseLight baseLight, Vector3f direction) {
        this.baseLight = baseLight;
        this.direction = direction.normalized();
    }

    public BaseLight getBaseLight() {
        return baseLight;
    }

    public void setBaseLight(BaseLight baseLight) {
        this.baseLight = baseLight;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
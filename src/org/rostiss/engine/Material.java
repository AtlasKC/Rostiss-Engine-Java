package org.rostiss.engine;

/**
 * File: Material.java
 * Created by Atlas IND on 7/9/2015 at 6:07 PM.
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
public class Material {

    private Texture texture;
    private Vector3f color;
    private float specularIntensity, specularExponent;

    public Material(Texture texture, Vector3f color) {
        this(texture, color, 2, 32);
    }

    public Material(Texture texture, Vector3f color, float specularIntensity, float specularExponent) {
        this.texture = texture;
        this.color = color;
        this.specularIntensity = specularIntensity;
        this.specularExponent = specularExponent;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getSpecularIntensity() {
        return specularIntensity;
    }

    public void setSpecularIntensity(float specularIntensity) {
        this.specularIntensity = specularIntensity;
    }

    public float getSpecularExponent() {
        return specularExponent;
    }

    public void setSpecularExponent(float specularExponent) {
        this.specularExponent = specularExponent;
    }
}
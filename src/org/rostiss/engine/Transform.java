package org.rostiss.engine;

/**
 * File: Transform.java
 * Created by Atlas IND on 7/8/2015 at 8:50 PM.
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
public class Transform {

    private static Camera camera;
    private static float zNear, zFar, width, height, fov;

    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform() {
        this.translation = new Vector3f(0, 0, 0);
        this.rotation = new Vector3f(0, 0, 0);
        this.scale = new Vector3f(1, 1, 1);
    }

    public Matrix4f getTransformation() {
        Matrix4f translation = new Matrix4f().initTranslation(this.translation.getX(), this.translation.getY(), this.translation.getZ());
        Matrix4f rotation = new Matrix4f().initRotation(this.rotation.getX(), this.rotation.getY(), this.rotation.getZ());
        Matrix4f scale = new Matrix4f().initScale(this.scale.getX(), this.scale.getY(), this.scale.getZ());
        return translation.mul(rotation.mul(scale));
    }

    public Matrix4f getProjectedTransformation() {
        Matrix4f transformation = getTransformation();
        Matrix4f projection = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
        Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPosition().getX(), -camera.getPosition().getY(), -camera.getPosition().getZ());
        return projection.mul(cameraRotation.mul(cameraTranslation.mul(transformation)));
    }

    public void setProjection(float fov, float width, float height, float zNear, float zFar) {
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
    }

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public void setTranslation(float x, float y, float z) {
        this.translation = new Vector3f(x, y, z);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation = new Vector3f(x, y, z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setScale(float x, float y, float z) {
        this.scale = new Vector3f(x, y, z);
    }
}
package com.company.util;

public class GameObject {
    private Vector3f centre= new Vector3f(0,0,0);			// Centre of object, using 3D as objects may be scaled
    private int width=10;
    private int height=10;
    private boolean hasTexture=false;
    private String textureLocation;
    private String blanktexture="res/blankSprite.png";


    public GameObject(String textureLocation, int width, int height, Vector3f centre) {
        hasTexture = true;
        this.textureLocation = textureLocation;
        this.width = width;
        this.height = height;
        this.centre = centre;
    }

    public GameObject(String textureLocation, Vector3f centre) {
        hasTexture = true;
        this.textureLocation = textureLocation;
        this.centre = centre;
    }


    public void setCentre(Vector3f centre) {
        this.centre = centre;
        //make sure to put boundaries on the gameObject
    }
    public void printCentre(){
        System.out.println("x: "+centre.getX() + "  y: " + centre.getY());
    }
    public Vector3f getCentre() {
        return centre;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getTexture() {
        if(hasTexture)
        {
            return textureLocation;
        }
        return blanktexture;
    }

}

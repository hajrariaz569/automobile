package com.example.allinoneapplication.nearbyareas.mapmodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("icon")
    private String icon;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("formatted_address")
    private String formatted_address;

    @SerializedName("opening_hours")
    private OpeningHours openingHours;

    @SerializedName("photos")
    private List<Photo> photos = new ArrayList<Photo>();

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("rating")
    private Double rating;

    @SerializedName("reference")
    private String reference;

    @SerializedName("scope")
    private String scope;

    @SerializedName("types")
    private List<String> types = new ArrayList<String>();

    @SerializedName("vicinity")
    private String vicinity;

    @SerializedName("price_level")
    private Integer priceLevel;

    @SerializedName("user_ratings_total")
    private double user_ratings_total;

    private String goodComment;


    public Result(String placeId, String icon , String name, String vicinity, Geometry geometry, String goodComment) {
        this.placeId = placeId;
        this.icon=icon;
        this.name = name;
        this.vicinity = vicinity;
        this.geometry = geometry;
        this.goodComment=goodComment;
    }


    /**
     * @return The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @param geometry The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The openingHours
     */
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    /**
     * @param openingHours The opening_hours
     */
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * @return The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * @param photos The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * @param placeId The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * @return The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * @return The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * @return The vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     * @param vicinity The vicinity
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    /**
     * @return The priceLevel
     */
    public Integer getPriceLevel() {
        return priceLevel;
    }


    /**
     * @param priceLevel The price_level
     */
    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }


    public double getUser_ratings_total() {
        return user_ratings_total;
    }

    public void setUser_ratings_total(double user_ratings_total) {
        this.user_ratings_total = user_ratings_total;
    }

    public String getGoodComment() {
        return goodComment;
    }

    public void setGoodComment(String goodComment) {
        this.goodComment = goodComment;
    }
}

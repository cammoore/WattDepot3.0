/**
 * Sensor.java created on Oct 3, 2013 by Cam Moore.
 */
package org.wattdepot3.datamodel;

import java.util.HashSet;
import java.util.Set;

/**
 * Sensor - Represents the device making measurements.
 * 
 * @author Cam Moore
 * 
 */
public class Sensor {
  /** A unique id for the User. */
  private String id;
  /** The URI to the sensor. */
  private String uri;
  /** The location of the sensor. */
  private Location location;
  /** The model of the sensor. */
  private SensorModel model;
  /** Additional properties of the sensor. */
  private Set<Property> properties;
  /** The owner of this sensor. */
  private UserGroup owner;

  /**
   * Default constructor.
   */
  public Sensor() {

  }

  /**
   * @param uniqueId
   *          The unique id.
   * @param uri
   *          The URI to the meter.
   * @param location
   *          The meter's location.
   * @param model
   *          The meter's model.
   * @param owner
   *          the owner of the location.
   */
  public Sensor(String uniqueId, String uri, Location location, SensorModel model, UserGroup owner) {
    this.id = uniqueId;
    this.uri = uri;
    this.location = location;
    this.model = model;
    this.properties = new HashSet<Property>();
    this.owner = owner;
  }

  /**
   * @param e
   *          The Property to add.
   * @return true if added.
   * @see java.util.List#add(java.lang.Object)
   */
  public boolean addProperty(Property e) {
    return properties.add(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Sensor other = (Sensor) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    }
    else if (!id.equals(other.id)) {
      return false;
    }
    if (location == null) {
      if (other.location != null) {
        return false;
      }
    }
    else if (!location.equals(other.location)) {
      return false;
    }
    if (model == null) {
      if (other.model != null) {
        return false;
      }
    }
    else if (!model.equals(other.model)) {
      return false;
    }
    if (owner == null) {
      if (other.owner != null) {
        return false;
      }
    }
    else if (!owner.equals(other.owner)) {
      return false;
    }
    if (properties == null) {
      if (other.properties != null) {
        return false;
      }
    }
    else if (!properties.equals(other.properties)) {
      return false;
    }
    if (uri == null) {
      if (other.uri != null) {
        return false;
      }
    }
    else if (!uri.equals(other.uri)) {
      return false;
    }
    return true;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return the location
   */
  public Location getLocation() {
    return location;
  }

  /**
   * @return the model
   */
  public SensorModel getModel() {
    return model;
  }

  /**
   * @return the owner
   */
  public UserGroup getOwner() {
    return owner;
  }

  /**
   * @return the properties
   */
  public Set<Property> getProperties() {
    return properties;
  }

  /**
   * @param key
   *          The key.
   * @return The value of associated with the key.
   */
  public Property getProperty(String key) {
    for (Property p : properties) {
      if (p.getKey().equals(key)) {
        return p;
      }
    }
    return null;
  }

  /**
   * @return the uri
   */
  public String getUri() {
    return uri;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((location == null) ? 0 : location.hashCode());
    result = prime * result + ((model == null) ? 0 : model.hashCode());
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
    result = prime * result + ((properties == null) ? 0 : properties.hashCode());
    result = prime * result + ((uri == null) ? 0 : uri.hashCode());
    return result;
  }

  /**
   * @param o
   *          The Property to remove.
   * @return true if removed.
   * @see java.util.List#remove(java.lang.Object)
   */
  public boolean removeProperty(Object o) {
    return properties.remove(o);
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @param location
   *          the location to set
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * @param model
   *          the model to set
   */
  public void setModel(SensorModel model) {
    this.model = model;
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(UserGroup owner) {
    this.owner = owner;
  }

  /**
   * @param properties the properties to set
   */
  public void setProperties(Set<Property> properties) {
    this.properties = properties;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Sensor [id=" + getId() + ", uri=" + uri + ", location=" + location + ", model=" + model
        + ", properties=" + properties + "]";
  }

}

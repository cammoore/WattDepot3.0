/**
 * DepositoryMeasurementResource.java This file is part of WattDepot 3.
 *
 * Copyright (C) 2013  Cam Moore
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.wattdepot3.restlet;

import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.wattdepot3.datamodel.Measurement;

/**
 * DepositoryMeasurementResource - HTTP Interface for storing, deleting, getting
 * a Measurement.
 * 
 * @author Cam Moore
 * 
 */
public interface DepositoryMeasurementResource {

  /**
   * Defines PUT /wattdepot/{group_id}/depository/{depository_id}/measurement/
   * API call.
   * 
   * @param meas
   *          The Measurement to store.
   */
  @Put
  public void store(Measurement meas);

  /**
   * Defined the DEL
   * /wattdepot/{group_id}/depository/{depository_id}/measurement/ API call.
   * 
   * @param meas
   *          The Measurement to delete.
   */
  @Delete
  public void remove(Measurement meas);

}

/**
 * UserGroupsServerResource.java This file is part of WattDepot 3.
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
package org.wattdepot3.server.restlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;

import org.wattdepot3.datamodel.Property;
import org.wattdepot3.datamodel.UserGroup;
import org.wattdepot3.datamodel.UserInfo;
import org.wattdepot3.restlet.UserGroupsResource;

/**
 * UserGroupsServerResource - Handles the UserGroup HTTP API
 * ("/wattdepot/{group_id}/groups").
 * 
 * @author Cam Moore
 * 
 */
public class UserGroupsServerResource extends WattDepotServerResource implements UserGroupsResource {

  /*
   * (non-Javadoc)
   * 
   * @see org.wattdepot3.restlet.UserGroupsResource#retrieve()
   */
  @Override
  public ArrayList<UserGroup> retrieve() {
    getLogger().log(Level.INFO, "GET /wattdepot/{" + groupId + "}/usergroups/");
    ArrayList<UserGroup> ret = new ArrayList<UserGroup>();
    UserGroup g1 = new UserGroup("UH");
    UserInfo i1 = new UserInfo("cmoore", "Cam", "Moore", "cmoore@hawaii.edu", false,
        new HashSet<Property>());
    g1.add(i1);
    ret.add(g1);
    return ret;
  }

}

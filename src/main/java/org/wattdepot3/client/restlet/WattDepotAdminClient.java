/**
 * WattDepotAdminClient.java This file is part of WattDepot 3.
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
package org.wattdepot3.client.restlet;

import org.restlet.resource.ClientResource;
import org.wattdepot3.client.WattDepotAdminInterface;
import org.wattdepot3.datamodel.UserGroup;
import org.wattdepot3.datamodel.UserInfo;
import org.wattdepot3.datamodel.UserPassword;
import org.wattdepot3.exception.BadCredentialException;
import org.wattdepot3.exception.IdNotFoundException;
import org.wattdepot3.restlet.API;
import org.wattdepot3.restlet.UserGroupResource;
import org.wattdepot3.restlet.UserInfoResource;
import org.wattdepot3.restlet.UserPasswordResource;

/**
 * WattDepotAdminClient - Admin level client.
 * 
 * @author Cam Moore
 * 
 */
public class WattDepotAdminClient extends WattDepotClient implements WattDepotAdminInterface {

  /**
   * Creates a new WattDepotAdminClient.
   * 
   * @param serverUri
   *          The URI of the WattDepot server (e.g.
   *          "http://server.wattdepot.org/")
   * @param username
   *          The name of the user. The user must be defined in the WattDepot
   *          server.
   * @param password
   *          The password for the user.
   * @throws BadCredentialException
   *           If the user or password don't match the credentials on the
   *           WattDepot server.
   */
  public WattDepotAdminClient(String serverUri, String username, String password)
      throws BadCredentialException {
    super(serverUri, username, password);
    if (!getGroupId().equals("admin")) {
      throw new BadCredentialException("Wrong group.");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot3.client.WattDepotAdminInterface#deleteUser(java.lang.String)
   */
  @Override
  public void deleteUser(String id) throws IdNotFoundException {
    ClientResource client = makeClient(getGroupId() + "/" + API.USER_URI + id);
    UserInfoResource resource = client.wrap(UserInfoResource.class);
    resource.remove();
    client.release();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot3.client.WattDepotAdminInterface#deleteUserGroup(java.lang
   * .String)
   */
  @Override
  public void deleteUserGroup(String id) throws IdNotFoundException {
    ClientResource client = makeClient(getGroupId() + "/" + API.USER_GROUP_URI + id);
    UserGroupResource resource = client.wrap(UserGroupResource.class);
    resource.remove();
    client.release();
  }

  /* (non-Javadoc)
   * @see org.wattdepot3.client.WattDepotAdminInterface#deleteUserPassword(java.lang.String)
   */
  @Override
  public void deleteUserPassword(String id) throws IdNotFoundException {
    ClientResource client = makeClient(getGroupId() + "/" + API.USER_PASSWORD_URI + id);
    UserPasswordResource resource = client.wrap(UserPasswordResource.class);
    resource.remove();
    client.release();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot3.client.WattDepotAdminInterface#putUser(org.wattdepot3.datamodel
   * .UserInfo)
   */
  @Override
  public void putUser(UserInfo user) {
    ClientResource client = makeClient(getGroupId() + "/" + API.USER_URI + user.getId());
    UserInfoResource resource = client.wrap(UserInfoResource.class);
    resource.store(user);
    client.release();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot3.client.WattDepotAdminInterface#putUserGroup(org.wattdepot3
   * .datamodel.UserGroup)
   */
  @Override
  public void putUserGroup(UserGroup group) {
    ClientResource client = makeClient(getGroupId() + "/" + API.USER_GROUP_URI + group.getId());
    UserGroupResource resource = client.wrap(UserGroupResource.class);
    resource.store(group);
    client.release();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.wattdepot3.client.WattDepotAdminInterface#putUserPassword(org.wattdepot3
   * .datamodel.UserPassword)
   */
  @Override
  public void putUserPassword(UserPassword password) {
    ClientResource client = makeClient(getGroupId() + "/" + API.USER_PASSWORD_URI + password.getId());
    UserPasswordResource resource = client.wrap(UserPasswordResource.class);
    resource.store(password);
    client.release();
  }

}

/**
 * WattDepotComponent.java This file is part of WattDepot 3.
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
package org.wattdepot3.server;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;
import org.wattdepot3.datamodel.UserGroup;
import org.wattdepot3.datamodel.UserInfo;
import org.wattdepot3.datamodel.UserPassword;
import org.wattdepot3.server.depository.impl.hibernate.MeasurementImpl;

/**
 * WattDepotComponent - Main class to start the WattDepot3 Server.
 * 
 * @author Cam Moore
 * 
 */
public class WattDepotComponent extends Component {

  /**
   * Sets up the WattDepotComponent with the given WattDepot.
   * 
   * @param depot
   *          The persitent store.
   * @param port
   *          the port number on which the restlet server listens.
   */
  public WattDepotComponent(WattDepot depot, int port) {
    setName("WattDepot HTTP API Server");
    setDescription("WattDepot3 RESTful server.");
    setAuthor("Cam Moore");
    // Add a CLAP client connector
    getClients().add(Protocol.CLAP);
    getClients().add(Protocol.FILE);
    getClients().add(Protocol.HTTP);

    // Adds a HTTP server connector
    Server server = getServers().add(Protocol.HTTP, port);
    server.getContext().getParameters().set("tracing", "true");

    WattDepotApplication app = new WattDepotApplication();
    app.setDepot(depot);
    // configure the JacksonRepresentation so that it uses ISO-8601 compliant
    // notation
    MeasurementImpl source = new MeasurementImpl();
    Representation rep = app.getConverterService().toRepresentation(source);
    @SuppressWarnings("rawtypes")
    ObjectMapper mapper = ((JacksonRepresentation) rep).getObjectMapper();
    mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    getDefaultHost().attachDefault(app);
    app.setComponent(this);

    // Set up the security realm
    MemoryRealm realm = new MemoryRealm();
    realm.setName("WattDepot Security");
    getRealms().add(realm);
    for (UserGroup group : app.getDepot().getUserGroups()) {
      app.getRoles().add(new Role(group.getId()));
      for (UserInfo info : group.getUsers()) {
        UserPassword up = app.getDepot().getUserPassword(info.getId());
        User user = new User(info.getId(), up.getPlainText(), info.getFirstName(),
            info.getLastName(), info.getEmail());
        realm.getUsers().add(user);
        realm.map(user, app.getRole(group.getId()));
      }
    }

    // Set the realm's default enroler and verifier
    app.getContext().setDefaultEnroler(realm.getEnroler());
    app.getContext().setDefaultVerifier(realm.getVerifier());

//    // Configure the log service
//    getLogService().setLoggerName("WattDepot3.AccessLog");
//    getLogService().setLogPropertiesRef("clap://system/org/wattdepot3/server/log.properties");
  }

}

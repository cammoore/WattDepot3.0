/**
 * Manager.java This file is part of WattDepot 3.
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
package org.wattdepot3.server.depository.impl.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.wattdepot3.server.ServerProperties;

/**
 * Manager - provides access to the singleton SessionFactory needed to persist
 * the objects.
 * 
 * @author Cam Moore
 * 
 */
public class Manager {
  private static SessionFactory sessionFactory;
  private static ServiceRegistry serviceRegistry;

  /**
   * @param properties
   *          The ServerProperties that contain the database configuration.
   * @return The singleton SessionFactory.
   */
  public static SessionFactory getFactory(ServerProperties properties) {
    if (sessionFactory == null) {
      Configuration cfg = new Configuration()
          .addClass(org.wattdepot3.datamodel.CollectorMetaData.class)
          .addClass(org.wattdepot3.datamodel.Depository.class)
          .addClass(org.wattdepot3.server.depository.impl.hibernate.DepositoryImpl.class)
          .addClass(org.wattdepot3.datamodel.Measurement.class)
          .addClass(org.wattdepot3.server.depository.impl.hibernate.MeasurementImpl.class)
          .addClass(org.wattdepot3.datamodel.MeasurementType.class)
          .addClass(org.wattdepot3.datamodel.Property.class)
          .addClass(org.wattdepot3.datamodel.Sensor.class)
          .addClass(org.wattdepot3.datamodel.SensorGroup.class)
          .addClass(org.wattdepot3.datamodel.SensorLocation.class)
          .addClass(org.wattdepot3.datamodel.SensorModel.class)
          .addClass(org.wattdepot3.datamodel.UserGroup.class)
          .addClass(org.wattdepot3.datamodel.UserInfo.class)
          .addClass(org.wattdepot3.datamodel.UserPassword.class)
          .setProperty("hibernate.connection.driver_class",
              properties.get(ServerProperties.DB_CONNECTION_DRIVER))
          .setProperty("hibernate.connection.url",
              properties.get(ServerProperties.DB_CONNECTION_URL))
          .setProperty("hibernate.connection.username",
              properties.get(ServerProperties.DB_USER_NAME))
          .setProperty("hibernate.connection.password",
              properties.get(ServerProperties.DB_PASSWORD))
          .setProperty("hibernate.c3p0.min_size", "5").setProperty("hibernate.c3p0.max_size", "20")
          .setProperty("hibernate.c3p0.timeout", "1800")
          .setProperty("hibernate.c3p0.max_statements", "50")
          .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
          .setProperty("hibernate.show_sql", properties.get(ServerProperties.DB_SHOW_SQL))
          .setProperty("hibernate.hbm2ddl.auto", properties.get(ServerProperties.DB_TABLE_UPDATE));
      serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties())
          .buildServiceRegistry();
      // A SessionFactory is set up once for an application
      sessionFactory = cfg.buildSessionFactory(serviceRegistry);
      
//      // A SessionFactory is set up once for an application
//      sessionFactory = new Configuration().configure() // configures settings
//                                                       // from
//                                                       // hibernate.cfg.xml
//          .buildSessionFactory();

    }
    return sessionFactory;
  }
  // TODO need a way to close the session factory when the program is pau.
}

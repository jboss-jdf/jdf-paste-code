/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.jdf.examples.pastecode.session;

import java.io.InputStream;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.jdf.examples.pastecode.model.CodeFragment;
import org.jboss.jdf.examples.pastecode.model.User;
import org.yaml.snakeyaml.Yaml;

/**
 * Populate the database with data.sql. Needed because import.sql doesn't
 * support multi-line inserts
 *
 * @author Pete Muir
 * @author Martin Gencur
 */
@Startup
@Singleton
public class PopulateDatabase
{

    private static final String DATA_CODEFRAGMENTS_FILE_NAME = "data_codefragments.yml";
    private static final String DATA_USERS_FILE_NAME = "data_users.yml";

    @Inject
    private Logger log;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void startup()
    {
        /*
        Seeding the database using a yml file to make it easy to add multi-line code snippets. Also
         */
        log.info("Beginning to seed database");

        final InputStream userIs = PopulateDatabase.class.getClassLoader().getResourceAsStream(DATA_USERS_FILE_NAME);
        final InputStream fragsIs = PopulateDatabase.class.getClassLoader().getResourceAsStream(DATA_CODEFRAGMENTS_FILE_NAME);
        final Yaml yaml = new Yaml();

        for (Object u : yaml.loadAll(userIs)) {
            log.fine("Persisting user " + u);
            ((User)u).setId(null);
            entityManager.persist(u);
        }

        for (Object f : yaml.loadAll(fragsIs)) {
            log.fine("Persisting code fragments");
            ((CodeFragment)f).setId(null);
            entityManager.persist(f);
        }

        log.info("Finished seeding database");
    }
}

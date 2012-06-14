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

package org.jboss.weld.examples.pastecode.model;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.inject.Typed;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;

import static javax.persistence.GenerationType.AUTO;

/**
 * A registered user.
 *
 * @author <a href="https://community.jboss.org/lightguard">Jason Porter</a>
 */
@Entity
@Typed()
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(unique = true)
    @NaturalId
    private String username;

    @Email
    private String email;

    @NotNull
    private String password;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CodeFragment> codeFragments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CodeFragment> getCodeFragments() {
        return codeFragments;
    }

    public void setCodeFragments(Set<CodeFragment> codeFragments) {
        this.codeFragments = codeFragments;
    }
}

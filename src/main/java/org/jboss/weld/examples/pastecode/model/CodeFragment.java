/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.weld.examples.pastecode.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.inject.Typed;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import static javax.persistence.GenerationType.AUTO;

/**
 * The entity class for the pasted code "fragment". This is the main entity
 * class in the application.
 *
 * @author Martin Gencur
 * @author Pete Muir
 */
@Entity
@Audited
@Typed()
public class CodeFragment implements Serializable {

    private static final long MS_PER_SECOND = 1000;
    private static final long MS_PER_MINUTE = 60 * MS_PER_SECOND;
    private static final long MS_PER_HOUR = 60 * MS_PER_MINUTE;
    private static final long MS_PER_DAY = 24 * MS_PER_HOUR;

    private static final SimpleDateFormat df = new SimpleDateFormat("d MMM");
    private static final long serialVersionUID = -1369003119799094069L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Lob
    @Size(min = 1, message = "Must enter some text!")
    private String text;

    private String user;

    private String hash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getFriendlyDate() {
        if (getDatetime() == null)
            return "unknown";

        Date now = new Date();

        long age = now.getTime() - getDatetime().getTime();

        long days = (long) Math.floor(age / MS_PER_DAY);
        age -= (days * MS_PER_DAY);
        long hours = (long) Math.floor(age / MS_PER_HOUR);
        age -= (hours * MS_PER_HOUR);
        long minutes = (long) Math.floor(age / MS_PER_MINUTE);

        if (days < 7) {
            StringBuilder sb = new StringBuilder();

            if (days > 0) {
                sb.append(days);
                sb.append(days > 1 ? " days " : " day ");
            }

            if (hours > 0) {
                sb.append(hours);
                sb.append(hours > 1 ? " hrs " : " hr ");
            }

            if (minutes > 0) {
                sb.append(minutes);
                sb.append(minutes > 1 ? " minutes " : " minute ");
            }

            if (hours == 0 && minutes == 0) {
                sb.append("just now");
            } else {
                sb.append("ago");
            }

            return sb.toString();
        } else {
            return df.format(getDatetime());
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "At " + getFriendlyDate() + " by " + getUser();
    }
}

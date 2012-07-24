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

package org.jboss.jdf.examples.pastecode.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CodeFragment.class)
public abstract class CodeFragment_ {

	public static volatile SingularAttribute<CodeFragment, Long> id;
	public static volatile SingularAttribute<CodeFragment, String> text;
	public static volatile SingularAttribute<CodeFragment, String> hash;
	public static volatile SingularAttribute<CodeFragment, Language> language;
	public static volatile SingularAttribute<CodeFragment, Date> datetime;
	public static volatile SingularAttribute<CodeFragment, User> user;
	public static volatile SingularAttribute<CodeFragment, String> note;

}


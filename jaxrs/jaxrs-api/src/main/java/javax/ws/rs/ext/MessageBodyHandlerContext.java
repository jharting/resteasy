/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Context shared by handlers. Handlers can be used to intercept calls
 * to <tt>javax.ws.rs.ext.MessageBodyReader.readFrom</tt> and
 * <tt>javax.ws.rs.ext.MessageBodyWriter.writeTo</tt>. The getters and
 * setters in this context class correspond to the parameters in
 * the aforementioned methods.
 *
 * @param <T> Java type supported by corresponding message body provider
 *
 * @author Santiago Pericas-Geertsen
 * @author Bill Burke
 * @since 2.0
 * @see ReadFromHandler
 * @see WriteToHandler
 */
public interface MessageBodyHandlerContext<T> {

    /**
     * Get a mutable map of properties that can be used for
     * communication between handlers and between filters. In
     * the Client API, this property map is initialized by calling
     * {@link javax.ws.rs.client.Configuration#getProperties()} on
     * the configuration object associated with the corresponding
     * {@link javax.ws.rs.client.Invocation} or
     * {@link javax.ws.rs.client.Invocation.Builder} instance on
     * which a filter or handler is registered.
     * Otherwise, it is initialized to the empty map.
     *
     * @return a mutable property map
     * @see javax.ws.rs.client.Configuration
     */
    Map<String, Object> getProperties();

    /**
     * Get annotations on the formal declaration of the resource
     * method parameter that is the target of the message body
     * conversion. As part of the client API, this method will
     * return null.
     *
     * @return annotations on the resource method parameter
     */
    Annotation[] getAnnotations();

    /**
     * Update annotations on the formal declaration of the resource
     * method parameter that is the target of the message body conversion.
     * Calling this method has no effect in the client API.
     *
     * @param annotations annotations for the resource method parameter
     */
    void setAnnotations(Annotation[] annotations);

    /**
     * Get Java type supported by corresponding message body provider.
     *
     * @return java type supported by provider
     */
    Class<T> getType();

    /**
     * Update Java type before calling message body provider.
     *
     * @param type java type for provider
     */
    void setType(Class<T> type);

    /**
     * Get the type of the object to be produced or written.
     *
     * @return type of object produced or written
     */
    Type getGenericType();

    /**
     * Update type of the object to be produced or written.
     *
     * @param genericType new type for object
     */
    void setGenericType(Type genericType);

    /**
     * Get media type of HTTP entity.
     *
     * @return media type of HTTP entity
     */
    MediaType getMediaType();

    /**
     * Update media type of HTTP entity.
     *
     * @param mediaType new type for HTTP entity
     */
    void setMediaType(MediaType mediaType);

    /**
     * Get mutable map of HTTP headers.
     *
     * @return map of HTTP headers
     */
    MultivaluedMap<String, String> getHeaders();
}

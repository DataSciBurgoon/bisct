/**
 * Copyright (c) 2011 Michael Kutschke.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michael Kutschke - initial API and implementation.
 */
package org.eclipse.recommenders.jayes.util;

public class NumericalInstabilityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NumericalInstabilityException() {
    }

    public NumericalInstabilityException(String message) {
        super(message);
    }

    public NumericalInstabilityException(Throwable cause) {
        super(cause);
    }

    public NumericalInstabilityException(String message, Throwable cause) {
        super(message, cause);
    }
}

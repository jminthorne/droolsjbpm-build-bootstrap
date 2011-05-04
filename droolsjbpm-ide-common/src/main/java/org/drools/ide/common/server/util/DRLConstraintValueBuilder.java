/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.ide.common.server.util;

import org.drools.ide.common.client.modeldriven.SuggestionCompletionEngine;

/**
 * A Helper class for building parts of DRL from higher-order representations
 * (i.e. Guided Rule Editor, Guided Template Rule Editor and Guided Decision
 * Table).
 */
public class DRLConstraintValueBuilder {

    /**
     * Concatenate a String to the provided buffer suitable for the fieldValue
     * and fieldType. Strings and Dates are escaped with double-quotes, whilst
     * Numerics, Booleans, (Java 1.5+) enums and all other fieldTypes are not
     * escaped at all. Guvnor-type enums are really a pick list of Strings and
     * in these cases the underlying fieldType is a String.
     * 
     * @param buf
     * @param fieldType
     * @param fieldValue
     */
    public void buildFieldValue(StringBuilder buf,
                                        String fieldType,
                                        String fieldValue) {
        if ( fieldType == null ) {
            //This should ideally be an error however we show leniency to legacy code
            buf.append( fieldValue );
            return;
        }

        if ( fieldType.equals( SuggestionCompletionEngine.TYPE_BOOLEAN ) ) {
            buf.append( fieldValue );
        } else if ( fieldType.equals( SuggestionCompletionEngine.TYPE_DATE ) ) {
            buf.append( "\"" );
            buf.append( fieldValue );
            buf.append( "\"" );
        } else if ( fieldType.equals( SuggestionCompletionEngine.TYPE_NUMERIC ) ) {
            buf.append( fieldValue );
        } else if ( fieldType.equals( SuggestionCompletionEngine.TYPE_STRING ) ) {
            buf.append( "\"" );
            buf.append( fieldValue );
            buf.append( "\"" );
        } else {
            buf.append( fieldValue );
        }

    }

}

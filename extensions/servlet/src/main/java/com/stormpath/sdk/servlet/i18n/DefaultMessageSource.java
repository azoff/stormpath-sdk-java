/*
 * Copyright 2015 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.sdk.servlet.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @since 1.0.RC3
 */
public class DefaultMessageSource implements MessageSource {
    
    public static final String BUNDLE_BASE_NAME = "com.stormpath.sdk.servlet.i18n";

    @Override
    public String getMessage(String key, Locale locale) {
        ResourceBundle bundle = getBundle(locale);
        return bundle.getString(key);
    }

    @Override
    public String getMessage(String key, Locale locale, Object... args) {
        ResourceBundle bundle = getBundle(locale);
        String msg = bundle.getString(key);

        try {
            return MessageFormat.format(msg, args);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    protected ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }
}

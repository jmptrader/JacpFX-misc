/*
 * **********************************************************************
 *
 *  Copyright (C) 2010 - 2014
 *
 *  [Component.java]
 *  JACPFX Project (https://github.com/JacpFX/JacpFX/)
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language
 *  governing permissions and limitations under the License.
 *
 *
 * *********************************************************************
 */

package quickstart.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andy Moncsek on 24.04.14.
 */
@Configuration
@ComponentScan
public class BaseConfiguration {
    public static final String PERSPECTIVE_ONE = "idPone";
    public static final String PERSPECTIVE_TWO = "idPtwo";


    public static final String TARGET_CONTAINER_TOP = "PLeft";
    public static final String TARGET_CONTAINER_MAIN = "PMain";

    public static final String COMPONENT_ONE = "id006";
    public static final String COMPONENT_TWO = "id007";
    public static final String FRAGMENT_ONE = "id0001";
    public static final String FRAGMENT_TWO = "id0002";
}

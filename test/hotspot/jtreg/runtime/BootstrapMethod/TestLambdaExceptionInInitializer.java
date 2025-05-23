/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8208172
 * @library /test/lib
 * @compile TestPkg/LambdaMetafactory.java
 * @compile TestPkg/Lambda.jasm
 * @run driver TestLambdaExceptionInInitializer
 */

import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;

public class TestLambdaExceptionInInitializer {
    public static void main(String args[]) throws Throwable {

        // Run Lamba class
        ProcessBuilder pb = ProcessTools.createLimitedTestJavaProcessBuilder("TestPkg.Lambda");
        OutputAnalyzer output = new OutputAnalyzer(pb.start());

        output.shouldContain("Exception in thread \"main\" java.lang.ExceptionInInitializerError");

        output.shouldContain("Caused by: java.lang.NullPointerException");
        output.shouldContain("at TestPkg.LambdaMetafactory.throwNpe(LambdaMetafactory.java:34)");
        output.shouldContain("at TestPkg.LambdaMetafactory.<clinit>(LambdaMetafactory.java:30)");
        output.shouldHaveExitValue(1);
    }
}

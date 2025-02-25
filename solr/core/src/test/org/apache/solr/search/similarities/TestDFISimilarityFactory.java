/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.solr.search.similarities;

import org.apache.lucene.search.similarities.DFISimilarity;
import org.apache.lucene.search.similarities.IndependenceChiSquared;
import org.apache.lucene.search.similarities.Similarity;
import org.junit.BeforeClass;

/** Tests {@link DFISimilarityFactory} */
public class TestDFISimilarityFactory extends BaseSimilarityTestCase {

  @BeforeClass
  public static void beforeClass() throws Exception {
    initCore("solrconfig-basic.xml", "schema-dfi.xml");
  }

  /** dfi with no parameters */
  public void test() throws Exception {
    Similarity sim = getSimilarity("text");
    assertEquals(DFISimilarity.class, sim.getClass());
    DFISimilarity dfi = (DFISimilarity) sim;
    assertTrue(dfi.getDiscountOverlaps());
    assertTrue(dfi.getIndependence() instanceof IndependenceChiSquared);
  }

  /** dfi with discountOverlaps parameter set to false */
  public void testParameters() throws Exception {
    Similarity sim = getSimilarity("text_params");
    assertEquals(DFISimilarity.class, sim.getClass());
    DFISimilarity dfr = (DFISimilarity) sim;
    assertFalse(dfr.getDiscountOverlaps());
  }
}

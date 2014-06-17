//  AbYSS_SettingsTest.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//
//  Copyright (c) 2014 Antonio J. Nebro
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>

package org.uma.test.experiments.settings;

import org.uma.jmetal.core.Algorithm;
import org.uma.jmetal.core.Problem;
import org.uma.jmetal.experiment.Settings;
import org.uma.jmetal.experiment.settings.AbYSS_Settings;
import org.uma.jmetal.operator.crossover.SBXCrossover;
import org.uma.jmetal.operator.localSearch.MutationLocalSearch;
import org.uma.jmetal.operator.mutation.PolynomialMutation;
import org.uma.jmetal.problem.Fonseca;
import org.uma.jmetal.util.JMetalException;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Antonio J. Nebro
 * Date: 12/06/13
 * Time: 07:48
 * To change this template use File | Settings | File Templates.
 */
public class AbYSS_SettingsTest {
  Properties configuration_ ;

  @Before
  public void init() throws FileNotFoundException, IOException {
    configuration_ = new Properties();
    InputStreamReader isr = new InputStreamReader(new FileInputStream(ClassLoader.getSystemResource("AbYSS.conf").getPath()));
    configuration_.load(isr);
  }

  @Test
  public void testSettings() throws JMetalException {
    double epsilon = 0.000000000000001 ;
    Settings abyssSettings = new AbYSS_Settings("Fonseca");
    Algorithm algorithm = abyssSettings.configure() ;
    Problem problem = new Fonseca("Real") ;
    SBXCrossover crossover = (SBXCrossover)algorithm.getOperator("crossover") ;
    double pc = (Double)crossover.getParameter("probability") ;
    double dic = (Double)crossover.getParameter("distributionIndex") ;
    MutationLocalSearch improvement = (MutationLocalSearch)algorithm.getOperator("improvement") ;
    int improvementRounds = (Integer)improvement.getParameter("improvementRounds") ;
    PolynomialMutation mutation = (PolynomialMutation)improvement.getParameter("mutation") ;
    double pm = (Double)mutation.getParameter("probability") ;
    double dim = (Double)mutation.getParameter("distributionIndex") ;

    assertEquals("AbYSS_SettingsTest", 20, ((Integer)algorithm.getInputParameter("populationSize")).intValue());
    assertEquals("AbYSS_SettingsTest", 25000, ((Integer)algorithm.getInputParameter("maxEvaluations")).intValue());
    assertEquals("AbYSS_SettingsTest", 10, ((Integer)algorithm.getInputParameter("refSet1Size")).intValue());
    assertEquals("AbYSS_SettingsTest", 10, ((Integer)algorithm.getInputParameter("refSet2Size")).intValue());
    assertEquals("AbYSS_SettingsTest", 100, ((Integer)algorithm.getInputParameter("archiveSize")).intValue());

    assertEquals("AbYSS_SettingsTest", 1.0, pc, epsilon);
    assertEquals("AbYSS_SettingsTest", 20.0, dic, epsilon);

    assertEquals("AbYSS_SettingsTest", 1, improvementRounds);
    assertEquals("AbYSS_SettingsTest", 20.0, dim, epsilon);
    assertEquals("AbYSS_SettingsTest", 1.0/problem.getNumberOfVariables(), pm, epsilon);
  }

  @Test
  public void testSettings2() throws JMetalException {
    double epsilon = 0.000000000000001 ;
    Settings abyssSettings = new AbYSS_Settings("Fonseca");
    Algorithm algorithm = abyssSettings.configure(configuration_) ;
    Problem problem = new Fonseca("Real") ;
    SBXCrossover crossover = (SBXCrossover)algorithm.getOperator("crossover") ;
    double pc = (Double)crossover.getParameter("probability") ;
    double dic = (Double)crossover.getParameter("distributionIndex") ;
    MutationLocalSearch improvement = (MutationLocalSearch)algorithm.getOperator("improvement") ;
    int improvementRounds = (Integer)improvement.getParameter("improvementRounds") ;
    PolynomialMutation mutation = (PolynomialMutation)improvement.getParameter("mutation") ;
    double pm = (Double)mutation.getParameter("probability") ;
    double dim = (Double)mutation.getParameter("distributionIndex") ;

    assertEquals("AbYSS_SettingsTest", 20, ((Integer)algorithm.getInputParameter("populationSize")).intValue());
    assertEquals("AbYSS_SettingsTest", 25000, ((Integer)algorithm.getInputParameter("maxEvaluations")).intValue());
    assertEquals("AbYSS_SettingsTest", 10, ((Integer)algorithm.getInputParameter("refSet1Size")).intValue());
    assertEquals("AbYSS_SettingsTest", 10, ((Integer)algorithm.getInputParameter("refSet2Size")).intValue());
    assertEquals("AbYSS_SettingsTest", 100, ((Integer)algorithm.getInputParameter("archiveSize")).intValue());

    assertEquals("AbYSS_SettingsTest", 1.0, pc, epsilon);
    assertEquals("AbYSS_SettingsTest", 20.0, dic, epsilon);

    assertEquals("AbYSS_SettingsTest", 1, improvementRounds);
    assertEquals("AbYSS_SettingsTest", 20.0, dim, epsilon);
    assertEquals("AbYSS_SettingsTest", 1.0/problem.getNumberOfVariables(), pm, epsilon);
  }
}

#
# Renjin : JVM-based interpreter for the R language for the statistical analysis
# Copyright © 2010-2019 BeDataDriven Groep B.V. and contributors
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, a copy is available at
# https://www.gnu.org/licenses/gpl-2.0.txt
#

# Generated by gen-dist-tests.R using GNU R version 3.3.1 (2016-06-21)
library(hamcrest)
library(stats)
set.seed(1)
test.rhyper.1 <- function() assertThat({set.seed(1);rhyper(n = 0x1p+0, m = c(0x1.8p+1, 0x1.4p+1, 0x1.8p+1), n = c(0x1.4p+2, 0x1.8p+1, 0x1.3p+3), k = c(0x1p+1, 0x1.cp+1, 0x0p+0))}, throwsError())
test.rhyper.2 <- function() assertThat({set.seed(1);rhyper(n = 1:5, m = c(0x1.8p+1, 0x1.4p+1, 0x1.8p+1), n = c(0x1.4p+2, 0x1.8p+1, 0x1.3p+3), k = c(0x1p+1, 0x1.cp+1, 0x0p+0))}, throwsError())
test.rhyper.3 <- function() assertThat({set.seed(1);rhyper(n = 0x1.ep+3, m = c(0x1.8p+1, 0x1.4p+1, 0x1.8p+1), n = c(0x1.4p+2, 0x1.8p+1, 0x1.3p+3), k = c(0x1p+1, 0x1.cp+1, 0x0p+0))}, throwsError())
test.rhyper.4 <- function() assertThat({set.seed(1);rhyper(n = numeric(0), m = c(0x1.8p+1, 0x1.4p+1, 0x1.8p+1), n = c(0x1.4p+2, 0x1.8p+1, 0x1.3p+3), k = c(0x1p+1, 0x1.cp+1, 0x0p+0))}, throwsError())
test.rhyper.5 <- function() assertThat({set.seed(1);rhyper(n = 0x1.8p+1, m = c(NA, 0x1.4p+1, 0x1.8p+1), n = c(0x1.4p+2, 0x1.8p+1, 0x1.3p+3), k = c(0x1p+1, 0x1.cp+1, 0x0p+0))}, throwsError())

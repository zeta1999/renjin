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

# Generated by gen-summary-tests.R using GNU R version 3.4.2 (2017-09-28)
library(hamcrest)
cumsum.foo <- function(...) 41
Math.bar <- function(...) 44
Summary.bar <- function(...) 45
test.cumsum.1 <- function() assertThat(cumsum(NULL), identicalTo(numeric(0)))
test.cumsum.2 <- function() assertThat(cumsum(logical(0)), identicalTo(integer(0)))
test.cumsum.3 <- function() assertThat(cumsum(c(TRUE, TRUE, FALSE, FALSE, TRUE)), identicalTo(c(1L, 2L, 2L, 2L, 3L)))
test.cumsum.4 <- function() assertThat(cumsum(c(TRUE, TRUE, TRUE)), identicalTo(1:3))
test.cumsum.5 <- function() assertThat(cumsum(c(TRUE, TRUE, NA)), identicalTo(c(1L, 2L, NA)))
test.cumsum.6 <- function() assertThat(cumsum(c(FALSE, FALSE, FALSE, FALSE)), identicalTo(c(0L, 0L, 0L, 0L)))
test.cumsum.7 <- function() assertThat(cumsum(c(FALSE, FALSE, FALSE, FALSE, NA)), identicalTo(c(0L, 0L, 0L, 0L, NA)))
test.cumsum.8 <- function() assertThat(cumsum(c(NA, NA, NA)), identicalTo(c(NA_integer_, NA_integer_, NA_integer_)))
test.cumsum.9 <- function() assertThat(cumsum(structure(c(TRUE, FALSE), .Names = c("a", "b"))), identicalTo(structure(c(1L, 1L), .Names = c("a", "b"))))
test.cumsum.10 <- function() assertThat(cumsum(integer(0)), identicalTo(integer(0)))
test.cumsum.11 <- function() assertThat(cumsum(structure(integer(0), .Names = character(0))), identicalTo(structure(integer(0), .Names = character(0))))
test.cumsum.12 <- function() assertThat(cumsum(c(NA_integer_, NA_integer_, NA_integer_)), identicalTo(c(NA_integer_, NA_integer_, NA_integer_)))
test.cumsum.13 <- function() assertThat(cumsum(1:3), identicalTo(c(1L, 3L, 6L)))
test.cumsum.14 <- function() assertThat(cumsum(c(1L, NA, 4L, NA, 999L)), identicalTo(c(1L, NA, NA, NA, NA)))
test.cumsum.15 <- function() assertThat(cumsum(c(1L, 2L, 1073741824L, 1073741824L)), identicalTo(c(1L, 3L, 1073741827L, NA)))
test.cumsum.16 <- function() assertThat(cumsum(structure(1:2, .Names = c("a", "b"))), identicalTo(structure(c(1L, 3L), .Names = c("a", "b"))))
test.cumsum.17 <- function() assertThat(cumsum(numeric(0)), identicalTo(numeric(0)))
test.cumsum.18 <- function() assertThat(cumsum(structure(numeric(0), .Names = character(0))), identicalTo(structure(numeric(0), .Names = character(0))))
test.cumsum.19 <- function() assertThat(cumsum(c(NA_real_, NA_real_)), identicalTo(c(NA_real_, NA_real_)))
test.cumsum.20 <- function() assertThat(cumsum(c(3.14159, 6.28319, 9.42478, 12.5664, 15.708)), identicalTo(c(3.14159, 9.42478, 18.84956, 31.41596, 47.12396), tol = 0.000100))
test.cumsum.21 <- function() assertThat(cumsum(c(-3.14159, -6.28319, -9.42478, -12.5664, -15.708)), identicalTo(c(-3.14159, -9.42478, -18.84956, -31.41596, -47.12396), tol = 0.000100))
test.cumsum.22 <- function() assertThat(cumsum(c(1.5, 2.5)), identicalTo(c(1.5, 4), tol = 0.000100))
test.cumsum.23 <- function() assertThat(cumsum(c(1.5, NA)), identicalTo(c(1.5, NA), tol = 0.000100))
test.cumsum.24 <- function() assertThat(cumsum(c(1.5, NaN)), identicalTo(c(1.5, NaN), tol = 0.000100))
test.cumsum.25 <- function() assertThat(cumsum(c(Inf, -1.5)), identicalTo(c(Inf, Inf)))
test.cumsum.26 <- function() assertThat(cumsum(c(-Inf, -1.5)), identicalTo(c(-Inf, -Inf)))
test.cumsum.27 <- function() assertThat(cumsum(c(Inf, 1.5)), identicalTo(c(Inf, Inf)))
test.cumsum.28 <- function() assertThat(cumsum(c(Inf, -1.5)), identicalTo(c(Inf, Inf)))
test.cumsum.29 <- function() assertThat(cumsum(structure(c(1.5, 2.5), .Names = c("a", "b"))), identicalTo(structure(c(1.5, 4), .Names = c("a", "b")), tol = 0.000100))
test.cumsum.30 <- function() assertThat(cumsum(character(0)), identicalTo(numeric(0)))
test.cumsum.31 <- function() assertThat(cumsum(c(NA_character_, NA_character_)), identicalTo(c(NA_real_, NA_real_)))
test.cumsum.32 <- function() assertThat(cumsum(structure(character(0), .Names = character(0))), identicalTo(structure(numeric(0), .Names = character(0))))
test.cumsum.33 <- function() assertThat(cumsum(c("a", "b")), identicalTo(c(NA_real_, NA_real_)))
test.cumsum.34 <- function() assertThat(cumsum(c("4.1", "blahh", "99.9", "-413", NA)), identicalTo(c(4.1, NA, NA, NA, NA), tol = 0.000100))
test.cumsum.35 <- function() assertThat(cumsum(structure(c("4.1", "99.9"), .Names = c("a", "b"))), identicalTo(structure(c(4.1, 104), .Names = c("a", "b")), tol = 0.000100))
test.cumsum.36 <- function() assertThat(cumsum(c("TRUE", "TRUE", "FALSE")), identicalTo(c(NA_real_, NA_real_, NA_real_)))
test.cumsum.37 <- function() assertThat(cumsum(c("true", "false", "true")), identicalTo(c(NA_real_, NA_real_, NA_real_)))
test.cumsum.38 <- function() assertThat(cumsum(c("true", "false", "true")), identicalTo(c(NA_real_, NA_real_, NA_real_)))
test.cumsum.39 <- function() assertThat(cumsum(complex(0)), identicalTo(complex(0)))
test.cumsum.40 <- function() assertThat(cumsum(c(NA_complex_, NA_complex_, NA_complex_)), identicalTo(c(NA_complex_, NA_complex_, NA_complex_)))
test.cumsum.41 <- function() assertThat(cumsum(structure(complex(0), .Named = character(0))), identicalTo(complex(0)))
test.cumsum.42 <- function() assertThat(cumsum(c(1+3i, 4+6i, 1-3i)), identicalTo(c(1+3i, 5+9i, 6+6i)))
test.cumsum.43 <- function() assertThat(cumsum(structure(1:12, .Dim = 3:4)), identicalTo(c(1L, 3L, 6L, 10L, 15L, 21L, 28L, 36L, 45L, 55L, 66L, 78L)))
test.cumsum.44 <- function() assertThat(cumsum(structure(1:3, rando.attrib = 941L)), identicalTo(c(1L, 3L, 6L)))
test.cumsum.45 <- function() assertThat(cumsum(structure(1:3, .Dim = 3L, .Dimnames = list(c("a", "b", "c")))), identicalTo(structure(c(1L, 3L, 6L), .Names = c("a", "b", "c"))))
test.cumsum.46 <- function() assertThat(cumsum(structure("foo", class = "foo")), identicalTo(41))
test.cumsum.47 <- function() assertThat(cumsum(structure(list(1L, "bar"), class = "bar")), identicalTo(44))

package org.whsv26.reporter
package util

extension (str: String)
  def toScreamingSnakeCase = str
    .toSnakeCase
    .toUpperCase

  def toSnakeCase = "[A-Z\\d]".r
    .replaceAllIn(str, "_" + _.group(0).toLowerCase())
    .dropWhile(_ == '_')




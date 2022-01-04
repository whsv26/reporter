package org.whsv26.reporter

extension (s: String) {
  def toSnakeCase = "[A-Z\\d]".r.replaceAllIn(s, { m =>
    "_" + m.group(0).toLowerCase()
  }).dropWhile(_ == '_')

  def toScreamingSnakeCase = s.toSnakeCase.toUpperCase
}



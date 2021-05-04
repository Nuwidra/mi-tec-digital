Mi-TEC-Digital
==============

[![Java CI with Maven](https://github.com/martinicr/mi-tec-digital/actions/workflows/maven.yml/badge.svg)](https://github.com/martinicr/mi-tec-digital/actions/workflows/maven.yml)

Aplicación Demo para gestionar Estudiantes, Profesores y Cursos.

Para las sentencias:

mvn exec:java -Dexec.args="-cc 1 Genetica Biologia 4", para agregar un nuevo curso

mvn exec:java -Dexec.args="-cu 1 Generica Biologia 3", para actualizar un curso existente

Por favor usar:

mvn exec:java -Dexec.args="-cc 1 Genetica 4 Biologia", para agregar un nuevo curso

mvn exec:java -Dexec.args="-cu 1 Generica 3 Biologia", para actualizar un curso existente
